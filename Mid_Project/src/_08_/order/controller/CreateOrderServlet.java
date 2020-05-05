package _08_.order.controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.JSONArray;

import _06_.product.model.ProductBean;
import _06_.product.model.ProductService;
import _08_.order.model.OrderBean;
import _08_.order.model.OrderDetailBean;
import utility.HibernateUtility;

@WebServlet("/CreateOrder")
public class CreateOrderServlet extends HttpServlet {
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
		String cartType = request.getParameter("cartType");
		String name = (String) request.getSession().getAttribute("name");
		int loc = -1;
		Cookie[] cookiesFromClient = request.getCookies();
		if (cartType.equalsIgnoreCase("user")) {
			for (int i = 0; i < cookiesFromClient.length; i++) {
				if (cookiesFromClient[i].getName().equals(name + "Cart")) {
					loc = i;
				}
			}
		}else {
			System.out.println("Hej");
			for (int i = 0; i < cookiesFromClient.length; i++) {
				if (cookiesFromClient[i].getName().equals("Cart")) {
					loc = i;
				}
			}
		}
		
		String cartStr = cookiesFromClient[loc].getValue();
		JSONArray cartArray = new JSONArray(cartStr);

		ProductService pService = new ProductService(HibernateUtility.getSessionFactory().getCurrentSession());
		List<ProductBean> pList = pService.selectAllData(0);

		SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();

		OrderBean oBean = new OrderBean();
		Set<OrderDetailBean> dBeanTrans = new HashSet<OrderDetailBean>();

		int total = 0;
		oBean.setUserID((int) request.getSession().getAttribute("uid"));
		oBean.setShippingAddress(String.valueOf(request.getParameter("shipAddress")));
		oBean.setShippingName(String.valueOf(request.getParameter("shipName")));
		oBean.setShippingPhone(String.valueOf(request.getParameter("shipPhone")));

		for (ProductBean pBean : pList) {
			for (int i = 0; i < cartArray.length(); i++) {
				if (pBean.getPid() == cartArray.getJSONObject(i).getInt("pid")) {
					int subTotal = pBean.getpPrice() * cartArray.getJSONObject(i).getInt("quantity");
					total += subTotal; 
					OrderDetailBean dBean = new OrderDetailBean();
					dBean.setQuantity(cartArray.getJSONObject(i).getInt("quantity"));
					dBean.setProductID(pBean.getPid());
					dBean.setUnitPrice(pBean.getpPrice());
					dBean.setProductName(pBean.getpName());
					dBean.setOrderBean(oBean);
					dBean.setSubTotal(subTotal);
					dBeanTrans.add(dBean);
				}
			}
		}

		if(request.getParameter("orderNote")!="") {
			oBean.setOrderNote(String.valueOf(request.getParameter("orderNote")));
		}

		
		oBean.setTotalAmount(total);
		oBean.setOrderDetailBeans(dBeanTrans);
		session.save(oBean);
		session.getTransaction().commit();
		
		if(cartType.equalsIgnoreCase("user")) {
			Cookie cookiesToDelete = new Cookie(name+"Cart", "[]");
			cookiesToDelete.setMaxAge(0);
			response.addCookie(cookiesToDelete);
		}
		else {
			Cookie cookiesToDelete = new Cookie("Cart", "[]");
			cookiesToDelete.setMaxAge(0);
			response.addCookie(cookiesToDelete);
		}
		
		response.sendRedirect("Orders.jsp");
	}

}
