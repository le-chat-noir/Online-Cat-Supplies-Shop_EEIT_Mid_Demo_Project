package _05_.getHelp.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;


public class OpinionDAO {
	private Session session;

	public OpinionDAO(Session session) {
		this.session = session;
	}

	public Session getSession() {
		return session;
	}
	
	public OpinionBean getData(int oid) {
		return getSession().get(OpinionBean.class, oid);
	}
	

	public List<OpinionBean> getAllData() {
		Query<OpinionBean> query;
		query = getSession().createQuery("From OpinionBean", OpinionBean.class);
		return query.list();
	}
	
	public OpinionBean updateSolution(int oid, String solution) {
		OpinionBean myBean = getSession().get(OpinionBean.class, oid);
		if (myBean != null) {
			myBean.setSolution(solution);
		}
		return myBean;
	}
	
	public OpinionBean updateCaseStatus(int oid, String caseStatus) {
		OpinionBean myBean = getSession().get(OpinionBean.class, oid);
		if (myBean != null) {
			myBean.setCaseStatus(caseStatus);
		}
		return myBean;
	}
	
	public OpinionBean updateCaseStatus(int oid, Integer uid) {
		OpinionBean myBean = getSession().get(OpinionBean.class, oid);
		if (myBean != null) {
			myBean.setUid(uid);
		}
		return myBean;
	}
	
	public int insertData(OpinionBean oBean) {
		try {
			int createdID = (int) this.getSession().save(oBean);
			return createdID;
		} catch (Exception e) {
		}
		return -1;
	}
	
}
