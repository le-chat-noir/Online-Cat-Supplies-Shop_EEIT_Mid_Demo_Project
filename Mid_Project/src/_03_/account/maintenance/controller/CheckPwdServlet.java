package _03_.account.maintenance.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import _01_.account.model.AccountBean;
import _01_.account.model.AccountService;
import utility.HibernateUtility;


@WebServlet("/checkPwd")
public class CheckPwdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request, response);
	}


	private void processAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("uid")!=null) {
			System.out.println("Hi");
			int uid = (int) session.getAttribute("uid");
			System.out.println(uid);
			SessionFactory factory = HibernateUtility.getSessionFactory();
			Session serviceSession = factory.getCurrentSession();
			AccountService aService = new AccountService(serviceSession);
			AccountBean aBean = aService.selectData(uid);
			if(aBean.getPassword().equalsIgnoreCase(request.getParameter("pwd"))) {
				response.setContentType("text/html;charset=UTF-8");
				response.getWriter().print(true);
				System.out.println("Hi");
			}			
		}
	}

}
