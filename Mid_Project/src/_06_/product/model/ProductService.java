package _06_.product.model;

import java.util.List;

import org.hibernate.Session;



public class ProductService {
	private Session session;
	private ProductDAO aDAO;
	
	public ProductService(Session session) {
		this.session = session;
		aDAO = new ProductDAO(session);
	}
	
	public int insertData(ProductBean aBean) {
		session.beginTransaction();
		int createdID =  aDAO.insertProduct(aBean);
		session.getTransaction().commit();
		return createdID;
	}
	
	public ProductBean updateProduct(int ID, ProductBean aBean) {
		session.beginTransaction();
		ProductBean sBean =  aDAO.updateProduct(ID, aBean);
		session.getTransaction().commit();
		return sBean;
	}
	
	public boolean deleteproduct(int ID) {
		session.beginTransaction();
		boolean sBean =  aDAO.deleteData(ID);
		session.getTransaction().commit();
		return sBean;
	}
	
	public List<ProductBean> selectAllData(int catID) {
		session.beginTransaction();
		List<ProductBean> beanList = aDAO.selectAllData(catID);
		session.getTransaction().commit();
		return beanList;
	}
	public ProductBean selectData(int pid) {
		session.beginTransaction();
		ProductBean pBean = aDAO.selectData(pid);
		session.getTransaction().commit();
		return pBean;
	}
	
}
