package _08_.order.model;

import java.util.List;

import org.hibernate.Session;

public class OrderDetailService {
	private Session session;
	private OrderDetailDAO odDAO;
	
	public OrderDetailService(Session session) {
		this.session = session;
		odDAO = new OrderDetailDAO(session);
	}
	
	public List<OrderDetailBean> selectAllData(int orderId) {
		session.beginTransaction();
		List<OrderDetailBean> odBeanList = odDAO.selectAllData(orderId);
		session.getTransaction().commit();
		return odBeanList;
	}
	
	public void deleteData(int orderId) {
		session.beginTransaction();
		odDAO.deleteData(orderId);
		session.getTransaction().commit();
	}
	
}
