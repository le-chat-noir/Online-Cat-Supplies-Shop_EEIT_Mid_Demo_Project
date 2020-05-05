package _09_.payment.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import _08_.order.model.OrderDetailService;
import _08_.order.model.OrderService;
import utility.HibernateUtility;


@WebServlet("/deleteOrder")
public class DeleteOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request, response);
	}


	private void processAction(HttpServletRequest request, HttpServletResponse response) {
		int orderID = Integer.parseInt(request.getParameter("orderID"));
		OrderDetailService odService = new OrderDetailService(HibernateUtility.getSessionFactory().getCurrentSession());
		odService.deleteData(orderID);
		OrderService oService = new OrderService(HibernateUtility.getSessionFactory().getCurrentSession());
		oService.deleteData(orderID);
	}

}
