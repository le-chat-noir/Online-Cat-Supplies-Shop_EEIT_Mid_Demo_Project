package _05_.getHelp.model;

import java.util.List;

import org.hibernate.Session;

public class OpinionService {
	private Session session;
	private OpinionDAO oDAO;
	
	public OpinionService(Session session) {
		this.session = session;
		oDAO = new OpinionDAO(session);
	}
	
	public List<OpinionBean> getAllData() {
		session.beginTransaction();
		List<OpinionBean> beanList = oDAO.getAllData();
		session.getTransaction().commit();
		return beanList;
	}
	
	public OpinionBean updateSolution(int oid, String solution) {
		session.beginTransaction();
		OpinionBean myBean = oDAO.updateSolution(oid, solution);
		session.getTransaction().commit();
		return myBean;
	}
	
	public OpinionBean updateCaseStatus(int oid, String caseStatus) {
		session.beginTransaction();
		OpinionBean myBean = oDAO.updateCaseStatus(oid, caseStatus);
		session.getTransaction().commit();
		return myBean;
	}
	
	public OpinionBean updateUid(int oid, Integer uid) {
		session.beginTransaction();
		OpinionBean myBean = oDAO.updateCaseStatus(oid, uid);
		session.getTransaction().commit();
		return myBean;
	}
	
	public int insertData(OpinionBean oBean) {
		session.beginTransaction();
		int createdID =  oDAO.insertData(oBean);
		session.getTransaction().commit();
		return createdID;
	}
}
