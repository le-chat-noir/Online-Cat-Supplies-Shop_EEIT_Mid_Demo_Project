package _08_.order.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

public class OrderDAO {
	private Session session;

	public OrderDAO(Session session) {
		this.session = session;
	}

	public Session getSession() {
		return session;
	}
	
	public List<OrderBean> selectAllData(int uid) {
		Query<OrderBean> query;
		query = getSession().createQuery("From OrderBean Where userId = ?1", OrderBean.class);
		query.setInteger(1, uid);
		return query.list();
	}
		
	public void deleteData(int orderID) {
		Query query;
		query = getSession().createQuery("Delete From OrderBean Where orderID = ?1");
		query.setInteger(1, orderID);
		query.executeUpdate();
	}
}
