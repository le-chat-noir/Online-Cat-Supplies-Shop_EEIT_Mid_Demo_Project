package _08_.order.model;

import java.util.List;

import org.hibernate.Session;

public class OrderService {
	private Session session;
	private OrderDAO oDAO;
	
	public OrderService(Session session) {
		this.session = session;
		oDAO = new OrderDAO(session);
	}
	
	public List<OrderBean> selectAllData(int uid){
		session.beginTransaction();
		List<OrderBean> beanList = oDAO.selectAllData(uid);
		session.getTransaction().commit();
		return beanList;
	}
	
	
	public void deleteData(int orderID) {
		session.beginTransaction();
		oDAO.deleteData(orderID);
		session.getTransaction().commit();
	}
	
	
}
