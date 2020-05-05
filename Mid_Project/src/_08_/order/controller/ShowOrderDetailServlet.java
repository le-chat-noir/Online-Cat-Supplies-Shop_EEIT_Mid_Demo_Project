package _08_.order.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import _08_.order.model.OrderDetailBean;
import _08_.order.model.OrderDetailService;
import utility.HibernateUtility;

@WebServlet("/ShowOrderDetail")
public class ShowOrderDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request, response);
	}

	private void processAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		try {
		int uid = (int)session.getAttribute("uid");
		int orderID = Integer.parseInt(request.getParameter("orderID"));
		OrderDetailService odService = new OrderDetailService(HibernateUtility.getSessionFactory().getCurrentSession());
		
		List<OrderDetailBean> odList = odService.selectAllData(orderID);
		
		JSONArray jsonArray = new JSONArray();
		for(OrderDetailBean odBean:odList) {
			JSONObject jobj = new JSONObject();
			jobj.put("pName", odBean.getProductName());
			jobj.put("unitPrice", odBean.getUnitPrice());
			jobj.put("quantity", odBean.getQuantity());
			jobj.put("subTotal", odBean.getSubTotal());
			jsonArray.put(jobj);
		}
		
		
		String jsonString = jsonArray.toString();
//		System.out.println(jsonString);
		response.getWriter().write(jsonString);
		response.getWriter().flush();
		}catch (Exception e) {
			response.getWriter().write("[]");
			response.getWriter().flush();
		}
	}

}
