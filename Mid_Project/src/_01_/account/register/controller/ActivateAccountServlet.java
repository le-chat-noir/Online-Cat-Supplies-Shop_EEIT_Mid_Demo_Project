package _01_.account.register.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import _01_.account.model.AccountBean;
import utility.HibernateUtility;


@WebServlet("/activate")
public class ActivateAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SessionFactory SessionFactory;
	private Session session;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request, response);
	}

	private void processAction(HttpServletRequest request, HttpServletResponse response) {
		SessionFactory = HibernateUtility.getSessionFactory();
		session = SessionFactory.getCurrentSession();
		response.setContentType("text/html;charset=UTF-8");
		
		int uid = Integer.parseInt(request.getParameter("UID"));
		String account = request.getParameter("account");
		
		try {
			session.beginTransaction();
			PrintWriter out = response.getWriter();
			
			AccountBean activeBean = session.get(AccountBean.class, uid);
			
			if(activeBean.getAccount().equalsIgnoreCase(account)) {
				activeBean.setAccountVerified("OK");
				session.save(activeBean);
				session.getTransaction().commit();
				out.write("帳號啟用成功<br>");
				out.write("Welcome, " + activeBean.getName() + "<br>");
			}else {
				out.write("無效的啟用資訊");
				session.getTransaction().commit();
			}
		} catch (Exception e) {
			session.getTransaction().rollback();
		}
	}
}
