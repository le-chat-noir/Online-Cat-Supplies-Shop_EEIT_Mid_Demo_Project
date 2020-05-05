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

import _08_.order.model.OrderBean;
import _08_.order.model.OrderService;
import utility.HibernateUtility;

@WebServlet("/ShowOrder")
public class ShowOrderServlet extends HttpServlet {
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
		System.out.println(request.getSession().getAttribute("uid"));
		HttpSession session = request.getSession();
		int uid = (int) session.getAttribute("uid");
		OrderService oService = new OrderService(HibernateUtility.getSessionFactory().getCurrentSession());
		
		List<OrderBean> orderList = oService.selectAllData(uid);
		
		JSONArray jsonArray = new JSONArray();
		for(OrderBean oBean:orderList) {
			JSONObject jobj = new JSONObject();
			jobj.put("orderID", oBean.getOrderID());
			jobj.put("totalAmount", oBean.getTotalAmount());
			jobj.put("shipName", oBean.getShippingName());
			jobj.put("shipAddress", oBean.getShippingAddress());
			jobj.put("shipPhone", oBean.getShippingPhone());
			jobj.put("msg", oBean.getOrderNote());
			if(oBean.getPayStatus()==null) {
				jobj.put("payStatus", "NO");
			}else {
				jobj.put("payStatus", oBean.getPayStatus());
			}
			jsonArray.put(jobj);
		}
		
		String jsonString = jsonArray.toString();
		response.getWriter().write(jsonString);
		response.getWriter().flush();
	}

}
