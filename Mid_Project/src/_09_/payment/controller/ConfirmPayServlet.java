package _09_.payment.controller;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.query.Query;

import _08_.order.model.OrderBean;
import utility.HibernateUtility;

@WebServlet("/confirmPay")
public class ConfirmPayServlet extends HttpServlet {
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
		Enumeration<String> params = request.getParameterNames();
		while (params.hasMoreElements()) {
			String paramName = params.nextElement();
			System.out.println("Parameter Name - " + paramName + ", Value - " + request.getParameter(paramName));
		}
		String MerchantTradeNo = request.getParameter("MerchantTradeNo");
		int orderID = 0;
		String shipName = null;
		String shipAddress = null;
		String shipPhone = null;
		
		if (Integer.parseInt(request.getParameter("RtnCode")) == 1) {
			Session session = HibernateUtility.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Query<OrderBean> query = session.createQuery("FROM OrderBean where payID = :payId", OrderBean.class);
			query.setParameter("payId", MerchantTradeNo);
			OrderBean obean = query.uniqueResult();
			orderID = obean.getOrderID();
			shipName = obean.getShippingName();
			shipAddress = obean.getShippingAddress();
			shipPhone = obean.getShippingPhone();
			obean.setPayStatus("OK");
			
			session.save(obean);
			session.getTransaction().commit();
		}
		
		
		SendPaidMail sendMail = new SendPaidMail(); 
		sendMail.PrepareMechMail(orderID, shipName, shipAddress, shipPhone);
		
		
		response.getWriter().print(1);
	}

}
