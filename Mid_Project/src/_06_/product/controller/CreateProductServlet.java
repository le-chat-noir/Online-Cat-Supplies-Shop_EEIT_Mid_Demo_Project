package _06_.product.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sql.rowset.serial.SerialBlob;

import _06_.product.model.ProductBean;
import _06_.product.model.ProductService;
import utility.HibernateUtility;

@WebServlet("/CreateProduct")
@MultipartConfig
public class CreateProductServlet extends HttpServlet {
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
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		try {
			
			String pName = request.getParameter("pName");
			String pDescription = request.getParameter("pDescription");
			int catID = Integer.parseInt(request.getParameter("catID"));
			int pPrice = Integer.parseInt(request.getParameter("pPrice"));
			Part pImagePart = request.getPart("pImage");
			System.out.println("aa " + request.getParameter("pid")); 
			System.out.println(request.getParameter("pid").trim()==null);
			System.out.println(request.getParameter("pid").trim()=="");
			System.out.println(request.getParameter("pid").equalsIgnoreCase(""));
			System.out.println(request.getParameter("pid").getClass());
			
			if (request.getParameter("pid") != null && !request.getParameter("pid").equalsIgnoreCase("")) {
				ProductService pService = new ProductService(HibernateUtility.getSessionFactory().getCurrentSession());
				int pid = Integer.parseInt(request.getParameter("pid"));
				if (pService.selectData(pid) != null)
					;
				updateBean(pid, catID, pName, pDescription, pPrice, pImagePart);
			} else {
				createBean(catID, pName, pDescription, pPrice, pImagePart);
			}

			System.out.println(pImagePart.getName());
			System.out.println(pImagePart.getSize());
			System.out.println(pImagePart.getContentType());

			response.sendRedirect("BackEnd/insert_product.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("Error.html");
		}
	}

	private void createBean(int catID, String pName, String pDescription, int pPrice, Part pImagePart) throws IOException, SQLException {
		ProductService updateBeanService = new ProductService(HibernateUtility.getSessionFactory().getCurrentSession());
		ProductBean upBean = new ProductBean();
		upBean.setpName(pName);
		upBean.setpDescription(pDescription);
		upBean.setCatID(catID);
		upBean.setpPrice(pPrice);
		upBean.setpImage(img2Blob(pImagePart));
		updateBeanService.insertData(upBean);
		
	}

	private void updateBean(int pid, int catID, String pName, String pDescription, int pPrice, Part pImagePart)
			throws IOException, SQLException {
		ProductService updateBeanService = new ProductService(HibernateUtility.getSessionFactory().getCurrentSession());
		ProductBean upBean = updateBeanService.selectData(pid);
		updateBeanService = new ProductService(HibernateUtility.getSessionFactory().getCurrentSession());
		upBean.setpName(pName);
		upBean.setpDescription(pDescription);
		upBean.setCatID(catID);
		upBean.setpPrice(pPrice);
		if (pImagePart.getContentType() != null && pImagePart != null && pImagePart.getSize()!=0) {
			upBean.setpImage(img2Blob(pImagePart));
		}
		updateBeanService.updateProduct(pid, upBean);
	}

	private Blob img2Blob(Part pImagePart) throws IOException, SQLException {
		byte[] imageByteArray = new byte[10240];

		InputStream imageStream = pImagePart.getInputStream();
		ByteArrayOutputStream imageOutput = new ByteArrayOutputStream();
		for (int length = 0; (length = imageStream.read(imageByteArray)) > 0;) {
			imageOutput.write(imageByteArray, 0, length);
		}
		imageOutput.flush();
		imageOutput.close();
		imageStream.close();

		long size = pImagePart.getSize();
		byte[] b = new byte[(int) size];
		SerialBlob sb = null;
		pImagePart.getInputStream().read(b);
		sb = new SerialBlob(b);
		SerialBlob blob = sb;
		return blob;
	}

}
