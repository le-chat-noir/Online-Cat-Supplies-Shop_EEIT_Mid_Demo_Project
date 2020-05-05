package _02_.account.login.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import _01_.account.model.AccountService;
import utility.HibernateUtility;


@WebServlet("/SendResetMail")
public class SendResetPwdMailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request, response);
	}
	
	@SuppressWarnings("rawtypes")
	private void processAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String account = request.getParameter("account");
		
		SessionFactory factory = HibernateUtility.getSessionFactory();
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		String hqlStr = "Select uid from AccountBean where account = :account and email= :email";
		
		Query query = session.createQuery(hqlStr);
		query.setParameter("account", account);
		query.setParameter("email", email);
		Object resultUid = query.uniqueResult();

		session.getTransaction().commit();
		
		if(resultUid!=null) {
			int uid = (int)resultUid;
			session = factory.getCurrentSession();
			AccountService resetService = new AccountService(session);
			resetService.updateAccountVerified(uid, "RESET");
			
			String textUrl = request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/ResetPwd.jsp?uid=" + uid + "&account=" + account +"&email=" + email;
			
			SendPwdResetMail sendMail = new SendPwdResetMail();
			sendMail.sendPwdReset(email, textUrl);
			
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print("true");
		}else {
			System.out.println("Hi");
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print("false");
		}
			
	}

}
