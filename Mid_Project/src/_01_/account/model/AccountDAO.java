package _01_.account.model;


import java.sql.Blob;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;





public class AccountDAO implements IAccountDAO{
	private Session session;
	
	public AccountDAO(Session session) {
		this.session = session;
	}
	
	@Override
	public Session getSession() {
		return session;
	}
	
	@Override
	public AccountBean selectData(int ID) {
		return getSession().get(AccountBean.class, ID);
	}
	
	@Override
	public List<AccountBean> selectAllData() {
		Query<AccountBean> query = getSession().createQuery("From AccountBean", AccountBean.class);
		return query.list();
	}
	
	@Override
	public AccountBean updateName(int ID, String name) {
		AccountBean myBean = getSession().get(AccountBean.class, ID);
		if(myBean!=null) {
			myBean.setName(name);
		}
		return myBean;
	}
	
	@Override
	public AccountBean updateAccountVerified(int ID, String accountVerified) {
		AccountBean myBean = getSession().get(AccountBean.class, ID);
		if(myBean!=null) {
			myBean.setAccountVerified(accountVerified);
		}
		return myBean;
	}
	
	@Override
	public AccountBean updateAddress(int ID, String address) {
		AccountBean myBean = getSession().get(AccountBean.class, ID);
		if(myBean!=null) {
			myBean.setAddress(address);
		}
		return myBean;
	}
	
	@Override
	public AccountBean updateEmail(int ID, String email) {
		AccountBean myBean = getSession().get(AccountBean.class, ID);
		if(myBean!=null) {
			myBean.setEmail(email);
		}
		return myBean;
	}
	
	@Override
	public AccountBean updatePhone(int ID, String phone) {
		AccountBean myBean = getSession().get(AccountBean.class, ID);
		if(myBean!=null) {
			myBean.setPhone(phone);
		}
		return myBean;
	}
	
	@Override
	public AccountBean updatePwd(int ID, String pwd) {
		AccountBean myBean = getSession().get(AccountBean.class, ID);
		if(myBean!=null) {
			myBean.setPassword(pwd);
		}
		return myBean;
	}
	@Override
	public AccountBean updateImg(int ID, Blob img) {
		AccountBean myBean = getSession().get(AccountBean.class, ID);
		if(myBean!=null) {
			myBean.setProfileImage(img);
		}
		return myBean;
	}
	
	@Override
	public boolean deleteData(int ID) {
		AccountBean myBean = getSession().get(AccountBean.class, ID);
		if (myBean!=null) {
			getSession().delete(myBean);
			return true;
		}
		return false;
	}

	@Override
	public int insertData(AccountBean aBeam) {
		
		try {
			int createdID = (int)this.getSession().save(aBeam);
			return createdID;
		}
		catch (Exception e) {
			
		}return -1;
		
	}
	
	@Override
	public AccountBean updateData(int ID, AccountBean aBean) {
		AccountBean myBean = getSession().get(AccountBean.class, ID);
		
		if (myBean!=null) {
			myBean.setName(aBean.getName());
			myBean.setPhone(aBean.getPhone());
			myBean.setAddress(aBean.getAddress());
			myBean.setEmail(aBean.getEmail());
			myBean.setProfileImage(aBean.getProfileImage());
			return myBean;
		}
		return myBean;
	}
	
	public Blob getProfileImage(int ID, AccountBean abean) {
		AccountBean myBean = getSession().get(AccountBean.class, ID);
		if (myBean!=null) {
			return myBean.getProfileImage();
		}
		return null;
	}
	
	
}
