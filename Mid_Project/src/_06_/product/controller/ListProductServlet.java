package _06_.product.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONArray;
import org.json.JSONObject;


import _06_.product.model.ProductBean;
import _06_.product.model.ProductService;
import utility.HibernateUtility;

@WebServlet("/ListProduct")
public class ListProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processAction(request, response);
	}

	private void processAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		int catID = Integer.parseInt(request.getParameter("catID"));
		ProductService pService = new ProductService(HibernateUtility.getSessionFactory().getCurrentSession());
		
		List<ProductBean> pList = pService.selectAllData(catID);

		JSONArray jsonArray = new JSONArray();

		for (ProductBean pBean : pList) {
			JSONObject jobj = new JSONObject();
			jobj.put("pid", pBean.getPid());
			jobj.put("catID", pBean.getCatID());
			jobj.put("pName", pBean.getpName());
			jobj.put("pDescription", pBean.getpDescription());
			jobj.put("pPrice", pBean.getpPrice());

			Blob blob = pBean.getpImage();

			InputStream imgStream = null;
			String imgEncoded;

			try {
				imgStream = blob.getBinaryStream();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			ByteArrayOutputStream imgStramOut = new ByteArrayOutputStream();
			byte[] buffer = new byte[512];
			int n = 0;
			while (-1 != (n = imgStream.read(buffer))) {
				imgStramOut.write(buffer, 0, n);
			}
			byte[] imgByte = imgStramOut.toByteArray();
			imgEncoded = Base64.encodeBase64String(imgByte);

			jobj.put("pImg", imgEncoded);
			jsonArray.put(jobj);

		}

		String jsonString = jsonArray.toString();
//		System.out.println(jsonString);
		response.getWriter().write(jsonString);
		response.getWriter().flush();
	}

}
