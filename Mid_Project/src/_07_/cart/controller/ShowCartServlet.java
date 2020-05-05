package _07_.cart.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONArray;
import org.json.JSONObject;

import _06_.product.model.ProductBean;
import _06_.product.model.ProductService;
import utility.HibernateUtility;

@WebServlet("/ShowCart")
public class ShowCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			processAction(request, response);
		} catch (Exception e) {
			   TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			processAction(request, response);
		} catch (Exception e) {
			   TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void processAction(HttpServletRequest request, HttpServletResponse response) throws IOException, Exception {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		System.out.println("Hi");
		HttpSession session = request.getSession();
		Cookie[] cookiesFromClient = request.getCookies();
		String cartType = "null";
		if (request.getParameter("cartType") != null) {
			cartType = request.getParameter("cartType");
		}
		int loc = -1;
		if (session.getAttribute("name") != null && !cartType.equalsIgnoreCase("Global")) {
			String user = (String) session.getAttribute("name");
			for (int i = 0; i < cookiesFromClient.length; i++) {
				if (cookiesFromClient[i].getName().equals(user + "Cart")) {
					loc = i;
				}
			}
		} else {
			for (int i = 0; i < cookiesFromClient.length; i++) {
				if (cookiesFromClient[i].getName().equals("Cart")) {
					loc = i;
				}
			}
		}

		if (loc != -1) {
			String cartStr = cookiesFromClient[loc].getValue();
			JSONArray cartArray = new JSONArray(cartStr);

  			ProductService pService = new ProductService(HibernateUtility.getSessionFactory().getCurrentSession());
  			List<ProductBean> pList = pService.selectAllData(0);
  			JSONArray jsonArray = new JSONArray();
  
  			for (ProductBean pBean : pList) {
  				for (int i = 0; i < cartArray.length(); i++) {
  
  					if (pBean.getPid() == cartArray.getJSONObject(i).getInt("pid")) {
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
  						jobj.put("quantity", cartArray.getJSONObject(i).getInt("quantity"));
  
  						jsonArray.put(jobj);
  					}
  				}
  			}

			String jsonString = jsonArray.toString();
			response.getWriter().write(jsonString);
			response.getWriter().flush();

		} else {
			response.getWriter().write("[]");
			response.getWriter().flush();
		}

	}

}
