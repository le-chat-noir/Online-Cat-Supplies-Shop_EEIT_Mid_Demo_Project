package _02_.account.login.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import _01_.account.model.AccountBean;
import _01_.account.model.AccountService;
import utility.HibernateUtility;

@WebServlet("/DoResetPwd")
public class ResetPwdServlet extends HttpServlet {
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
		String account = request.getParameter("account");
		String email = request.getParameter("email");
		String pwd = request.getParameter("password");

		int uid = -1;
		try {
			uid = Integer.parseInt(request.getParameter("uid"));
		} catch (Exception e) {
			response.sendRedirect("ERROR404.html");
		}

		SessionFactory factory = HibernateUtility.getSessionFactory();
		Session session = factory.getCurrentSession();

		AccountService resetPwdService = new AccountService(session);
		AccountBean resetBean = resetPwdService.selectData(uid);
		
		if (resetBean.getAccountVerified().equals("RESET") && resetBean.getEmail().equals(email)
				&& resetBean.getAccount().equalsIgnoreCase(account)) {
			session = factory.getCurrentSession();
			resetPwdService = new AccountService(session);
			resetPwdService.updatePwd(uid, pwd);
			session = factory.getCurrentSession();
			resetPwdService = new AccountService(session);
			resetPwdService.updateAccountVerified(uid, "OK");
			response.getWriter().write("<p>密碼已更新</p><br>");
			response.getWriter().write("<a href='http://" + request.getServerName() + ":" + request.getServerPort()
					+ request.getContextPath() + "/Login.jsp'>點這裡返回登入頁面</a>");
		} else {
			response.sendRedirect("ERROR404.html");
		}

	}

}
