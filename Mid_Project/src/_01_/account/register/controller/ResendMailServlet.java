package _01_.account.register.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import _01_.account.model.AccountService;
import utility.HibernateUtility;

/**
 * Servlet implementation class ResendMail
 */
@WebServlet("/ResendMail")
public class ResendMailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SessionFactory factory;
	private Session session;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request, response);
	}

	private void processAction(HttpServletRequest request, HttpServletResponse response) {
		String textUrl = (String) request.getParameter("textUrl");
		String email = (String) request.getParameter("email");
		String update = (String) request.getParameter("update");
		int uid = Integer.parseInt(request.getParameter("uid"));
		SendVerifyEmail sendMail = new SendVerifyEmail();
		
		if(update.equals("true")) {
			
			
			factory = HibernateUtility.getSessionFactory();
			session = factory.getCurrentSession();
			AccountService updateService = new AccountService(session);
			updateService.updateEmail(uid, email);
			
			sendMail.sendAccountVerify(email, textUrl);
		}
		else {
			
			sendMail.sendAccountVerify(email, textUrl);
		}	
		
	}
}
