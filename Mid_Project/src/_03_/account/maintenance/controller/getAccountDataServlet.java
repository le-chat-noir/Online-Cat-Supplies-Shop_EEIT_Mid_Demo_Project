package _03_.account.maintenance.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.JSONArray;
import org.json.JSONObject;

import _01_.account.model.AccountBean;
import _01_.account.model.AccountService;
import utility.HibernateUtility;

@WebServlet("/getUserInfo")
public class getAccountDataServlet extends HttpServlet {
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
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		System.out.println(session.getAttribute("uid"));
		if (session.getAttribute("uid") != null) {

			int uid = (int) session.getAttribute("uid");
			SessionFactory factory = HibernateUtility.getSessionFactory();
			Session thisSession = factory.getCurrentSession();
			AccountService listService = new AccountService(thisSession);
			AccountBean userAccount = listService.selectData(uid);
			
			Blob blob = userAccount.getProfileImage();
			System.out.println(blob);
			InputStream imgStream = null;
			String imgEncoded;
			if(blob!=null) {			
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
			}else {
				imgEncoded=null;
			}
			
			
			JSONArray jsonArray = new JSONArray();
			JSONObject jboj = new JSONObject();

			jboj.put("name", userAccount.getName());
			jboj.put("mail", userAccount.getEmail());
			jboj.put("phone", userAccount.getPhone());
			jboj.put("address", userAccount.getAddress());
			jboj.put("image", imgEncoded);

			jsonArray.put(jboj);
			String jsonString = jsonArray.toString();
			response.getWriter().write(jsonString);
		}

	}

}
