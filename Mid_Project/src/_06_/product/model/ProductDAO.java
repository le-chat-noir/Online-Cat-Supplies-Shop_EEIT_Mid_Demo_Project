package _06_.product.model;

import java.sql.Blob;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

public class ProductDAO {
	private Session session;

	public ProductDAO(Session session) {
		this.session = session;
	}

	public Session getSession() {
		return session;
	}

	public ProductBean selectData(int pid) {
		return getSession().get(ProductBean.class, pid);
	}

	public List<ProductBean> selectAllData(int catID) {
		Query<ProductBean> query;
		if (catID != 0) {
			query = getSession().createQuery("From ProductBean Where catID = :catID", ProductBean.class);
			query.setParameter("catID", catID);
		} else {
			query = getSession().createQuery("From ProductBean", ProductBean.class);

		}
		return query.list();
	}

	public ProductBean updatepName(int pid, String pName) {
		ProductBean myBean = getSession().get(ProductBean.class, pid);
		if (myBean != null) {
			myBean.setpName(pName);
		}
		return myBean;
	}

	public ProductBean updatepDescription(int pid, String pDescription) {
		ProductBean myBean = getSession().get(ProductBean.class, pid);
		if (myBean != null) {
			myBean.setpDescription(pDescription);
		}
		return myBean;
	}

	public ProductBean updatepPrice(int pid, int pPrice) {
		ProductBean myBean = getSession().get(ProductBean.class, pid);
		if (myBean != null) {
			myBean.setpPrice(pPrice);
		}
		return myBean;
	}

	public ProductBean updatecatID(int pid, int catID) {
		ProductBean myBean = getSession().get(ProductBean.class, pid);
		if (myBean != null) {
			myBean.setCatID(catID);
		}
		return myBean;
	}

	public ProductBean updatepImage(int pid, Blob pImage) {
		ProductBean myBean = getSession().get(ProductBean.class, pid);
		if (myBean != null) {
			myBean.setpImage(pImage);
		}
		return myBean;
	}

	public ProductBean updateProduct(int pid, ProductBean pBean) {
		ProductBean myBean = getSession().get(ProductBean.class, pid);
		if (myBean != null) {
			myBean.setpName(pBean.getpName());
			myBean.setpDescription(pBean.getpDescription());
			myBean.setpPrice(pBean.getpPrice());
			myBean.setpImage(pBean.getpImage());
			myBean.setCatID(pBean.getCatID());
		}
		return myBean;
	}

	public int insertProduct(ProductBean aBean) {
		try {
			int createdID = (int) this.getSession().save(aBean);
			return createdID;
		} catch (Exception e) {
		}
		return -1;
	}

	public boolean deleteData(int ID) {
		ProductBean myBean = getSession().get(ProductBean.class, ID);
		if (myBean != null) {
			getSession().delete(myBean);
			return true;
		}
		return false;
	}
}
