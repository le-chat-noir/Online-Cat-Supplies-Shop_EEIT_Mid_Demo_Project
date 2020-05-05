package _01_.account.register.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sql.rowset.serial.SerialBlob;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import _01_.account.model.AccountBean;
import _01_.account.model.AccountService;
import utility.HibernateUtility;

@WebServlet("/CreateAccount")
@MultipartConfig
public class CreateAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private SessionFactory SessionFactory;
	private Session session;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request, response);
	}

	private void processAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
		SessionFactory = HibernateUtility.getSessionFactory();
		session = SessionFactory.getCurrentSession();

		try {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");

			String account = request.getParameter("account");
			String password = request.getParameter("password");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");
			String address = request.getParameter("address");
			Part profileImagePart = request.getPart("profileImage");

			System.out.println(profileImagePart.getName());
	        System.out.println(profileImagePart.getSize());
	        System.out.println(profileImagePart.getContentType());
			
			byte[] imageByteArray = new byte[10240];
			if (profileImagePart != null && profileImagePart.getSubmittedFileName()!=null && profileImagePart.getSubmittedFileName().length()!=0) {
				InputStream imageStream = profileImagePart.getInputStream();
				ByteArrayOutputStream imageOutput = new ByteArrayOutputStream();
				for (int length = 0; (length = imageStream.read(imageByteArray)) > 0;) {
					imageOutput.write(imageByteArray, 0, length);
				}
				imageOutput.flush();
				imageOutput.close();
				imageStream.close();
			}else imageByteArray=null;
			
			long size = profileImagePart.getSize();
			byte[] b = new byte[(int) size];
			SerialBlob sb = null;
			profileImagePart.getInputStream().read(b);
			sb = new SerialBlob(b);
			SerialBlob blob = sb;
			
			
			AccountService accBeanService = new AccountService(session);
			
			AccountBean regBean = new AccountBean();
			
			regBean.setAccount(account);
			regBean.setPassword(password);
			regBean.setName(name);
			regBean.setEmail(email);
			regBean.setPhone(phone);
			System.out.println(address);
			if (address.trim().length() > 0 && address!=null) {
				regBean.setAddress(address);
			}
			if(profileImagePart != null && profileImagePart.getSubmittedFileName()!=null && profileImagePart.getSubmittedFileName().length()!=0) {
				regBean.setProfileImage(blob);
			}
			
			int uid = accBeanService.insertData(regBean);
			String textUrl = request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/activate?UID=" + uid + "&account=" + account;
			SendVerifyEmail verifyMail = new SendVerifyEmail();
			verifyMail.sendAccountVerify(email, textUrl);
			response.sendRedirect("RegOK.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("Error.html");
		}
	}

}
