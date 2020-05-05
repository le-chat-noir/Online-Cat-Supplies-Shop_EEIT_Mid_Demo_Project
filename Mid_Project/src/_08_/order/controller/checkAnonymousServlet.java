package _08_.order.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/checkQuickCart")
public class checkAnonymousServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request, response);
	}

	private void processAction(HttpServletRequest request, HttpServletResponse response) {
		System.out.println(request.getSession().getAttribute("uid"));
		if(request.getSession().getAttribute("uid")!=null) {
			doUserCartCheckOut(request, response);
		}else {
			doQuickCheckOut(request, response);
		}
	}

	private void doQuickCheckOut(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("quick");
		try {
			request.getRequestDispatcher("quickLogin.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	private void doUserCartCheckOut(HttpServletRequest request, HttpServletResponse response) {
		try {
			System.out.println("hi");
			request.setAttribute("cart", "user");
//			request.setAttribute("shipPhone", "0912345678");
//			request.setAttribute("shipName", "Test");
//			request.setAttribute("shipAddress", "TestAddr");
			request.getRequestDispatcher("ConfirmOrder.jsp?cartType=user").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	

}
