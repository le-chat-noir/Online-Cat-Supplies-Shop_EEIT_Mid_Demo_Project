package _09_.payment.controller;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.query.Query;

import _08_.order.model.OrderBean;
import _08_.order.model.OrderDetailBean;
import ecpay.payment.integration.AllInOne;
import utility.HibernateUtility;


@WebServlet("/LinkPay")
public class LinkPayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static AllInOne all;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request, response);
	}


	private void processAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println(request.getParameter("orderID"));
		int orderId = Integer.parseInt(request.getParameter("orderID"));

		System.out.println(request.getParameter("orderID"));
		//		int orderId = 1;
		String Url = request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
		
		String merchantTradeNo = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 20);
		int totalAmount = 0;
		
		Session session = HibernateUtility.getSessionFactory().getCurrentSession();		
		session.beginTransaction();
		OrderBean oBean = session.get(OrderBean.class, orderId);
		totalAmount = oBean.getTotalAmount();
		oBean.setPayID(merchantTradeNo);
		session.save(oBean);
		session.getTransaction().commit();
		
		session = HibernateUtility.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		Query<OrderDetailBean> query = session.createQuery("FROM OrderDetailBean where OrderID = :orderId", OrderDetailBean.class);
		query.setParameter("orderId", orderId);
		String itemName = "";
		for(OrderDetailBean od:query.list()) {
//			System.out.println(od.getProductName());
			itemName += od.getProductName();
//			System.out.println(od.getUnitPrice());
			itemName += " " + od.getUnitPrice() + "元";
//			System.out.println(od.getQuantity());
			itemName += " x" + od.getQuantity() + "#";
		}
		itemName = itemName.substring(0, itemName.length() - 1);
		session.getTransaction().commit();
		
		System.out.println(itemName);
		
		
		
		String redir = createPayLink.genAioCheckOutOneTime(merchantTradeNo, String.valueOf(totalAmount), itemName, Url);

		System.out.println(redir);
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(redir);
	}


}
