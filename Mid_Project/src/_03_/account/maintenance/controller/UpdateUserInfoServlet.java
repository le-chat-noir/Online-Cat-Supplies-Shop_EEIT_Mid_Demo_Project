package _03_.account.maintenance.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sql.rowset.serial.SerialBlob;

import _01_.account.model.AccountBean;
import _01_.account.model.AccountService;
import utility.HibernateUtility;

@MultipartConfig
@WebServlet("/UpdateUserInfo")
public class UpdateUserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processAction(request, response);
	}

	private void processAction(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		if (request.getSession().getAttribute("uid") != null) {
			String name = request.getParameter("name");
			String mail = request.getParameter("mail");
			String phone = request.getParameter("phone");
			String address = request.getParameter("addrsss");
			Part profileImagePart = request.getPart("profileImage");
			System.out.println(profileImagePart.getName());
	        System.out.println(profileImagePart.getSize());
	        System.out.println(profileImagePart.getContentType());
			
	        SerialBlob blob = null;
	        if(profileImagePart.getContentType()!=null) {
				long size = profileImagePart.getSize();
				byte[] b = new byte[(int) size];
				SerialBlob sb = null;
				profileImagePart.getInputStream().read(b);
				try {
					sb = new SerialBlob(b);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				blob = sb;
	        }
	        
			AccountService updateService = new AccountService(HibernateUtility.getSessionFactory().getCurrentSession());
			AccountBean aBean = new AccountBean();
			aBean.setName(name);
			aBean.setEmail(mail);
			aBean.setPhone(phone);
			aBean.setAddress(address);
			if(profileImagePart.getContentType()!=null) {
				aBean.setProfileImage(blob);
			}
			updateService.updateData((int)request.getSession().getAttribute("uid"), aBean);
		}
	}

}
