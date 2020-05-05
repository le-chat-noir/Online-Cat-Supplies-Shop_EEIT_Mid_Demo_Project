package _08_.order.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import utility.HibernateUtility;



@WebServlet("/quickLogin")
public class QuickLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SessionFactory factory;
	private Session session;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request, response);
	}

	@SuppressWarnings("rawtypes")
	private void processAction(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String account = request.getParameter("account");
		String password = request.getParameter("password");
				
				
		factory = HibernateUtility.getSessionFactory();
		session = factory.getCurrentSession();
		session.beginTransaction();
		
		String hql = "select uid, accountVerified, name, email from AccountBean WHERE account = :account AND password = :password";
		Query query = session.createQuery(hql);
		query.setParameter("account", account);
		query.setParameter("password", password);
		
		List listResult = query.list();
		Iterator iterator =  listResult.iterator();
			
		if(!iterator.hasNext()) {
			session.getTransaction().commit();
			response.getWriter().print("false");
			response.sendRedirect("quickCheckOut.jsp?LoginError=1");
		}else {
			Object[] resultList = (Object[]) iterator.next();
			int uid = (int) resultList[0];
			String verified = (String) resultList[1];
			String name = (String) resultList[2];
			String email = (String) resultList[3];
			session.getTransaction().commit();
			if (verified==null || !verified.equalsIgnoreCase("OK")) {
				request.setAttribute("uid", uid);
				request.setAttribute("account", account);
				request.setAttribute("email", email);
				String textUrl = request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/activate?UID=" + uid + "&account=" + account;
				request.setAttribute("textUrl", textUrl);
				request.getRequestDispatcher("ConfirmActivation.jsp?").forward(request, response);
			}else {
				HttpSession thisSession = request.getSession();
				thisSession.setAttribute("name", name);
				thisSession.setAttribute("uid", uid);
				thisSession.setAttribute("email", email);
				
				response.sendRedirect("ConfirmOrder.jsp?cartType=global");	
			}
		}
	}
}
