package _08_.order.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

public class OrderDetailDAO {
	private Session session;
	
	public OrderDetailDAO(Session session) {
		this.session = session;
	}

	public Session getSession() {
		return session;
	}
	
	public List<OrderDetailBean> selectAllData(int orderId) {
		Query<OrderDetailBean> query;
		query = getSession().createQuery("From OrderDetailBean Where orderId = :orderId", OrderDetailBean.class);
		query.setParameter("orderId", orderId);
		return query.list();
	}
		
	public void deleteData(int orderId) {
		Query query;
		query = getSession().createQuery("Delete From OrderDetailBean Where orderId = :orderId");
		query.setParameter("orderId", orderId);
		query.executeUpdate();
	}
}
