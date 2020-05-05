package _01_.account.model;

import java.sql.Blob;
import java.util.List;

import org.hibernate.Session;


public class AccountService implements IAccountService{
	private Session session;
	private AccountDAO aDAO;

	public AccountService(Session session) {
		this.session = session;
		aDAO = new AccountDAO(session);
	}
	
	@Override
	public int insertData(AccountBean aBeam) {
		session.beginTransaction();
		int createdID =  aDAO.insertData(aBeam);
		session.getTransaction().commit();
		return createdID;
	}

	@Override
	public AccountBean updateName(int ID, String name) {
		session.beginTransaction();
		AccountBean sBean =  aDAO.updateName(ID, name);
		session.getTransaction().commit();
		return sBean;
	}
	
	@Override
	public AccountBean updatePwd(int ID, String pwd) {
		session.beginTransaction();
		AccountBean sBean =  aDAO.updatePwd(ID, pwd);
		session.getTransaction().commit();
		return sBean;
	}
	
	@Override
	public AccountBean updateAddress(int ID, String address) {
		session.beginTransaction();
		AccountBean sBean =  aDAO.updateAddress(ID, address);
		session.getTransaction().commit();
		return sBean;
	}
	
	@Override
	public AccountBean updateEmail(int ID, String email) {
		session.beginTransaction();
		AccountBean sBean =  aDAO.updateEmail(ID, email);
		session.getTransaction().commit();
		return sBean;
	}
	
	@Override
	public AccountBean updatePhone(int ID, String phone) {
		session.beginTransaction();
		AccountBean sBean =  aDAO.updatePhone(ID, phone);
		session.getTransaction().commit();
		return sBean;
	}
	
	@Override
	public AccountBean updateImg(int ID, Blob img) {
		session.beginTransaction();
		AccountBean sBean =  aDAO.updateImg(ID, img);
		session.getTransaction().commit();
		return sBean;
	}
	
	@Override
	public AccountBean updateAccountVerified(int ID, String accountVerified) {
		session.beginTransaction();
		AccountBean sBean =  aDAO.updateAccountVerified(ID, accountVerified);
		session.getTransaction().commit();
		return sBean;
	}

	@Override
	public AccountBean selectData(int ID) {
		session.beginTransaction();
		AccountBean sBean =  aDAO.selectData(ID);
		session.getTransaction().commit();
		return sBean;
	}

	@Override
	public List<AccountBean> selectAllData() {
		session.beginTransaction();
		List<AccountBean> sBeanList =  aDAO.selectAllData();
		session.getTransaction().commit();
		return sBeanList;
	}

	@Override
	public boolean deleteData(int ID) {
		session.beginTransaction();
		boolean sBeanResult =  aDAO.deleteData(ID);
		session.getTransaction().commit();
		return sBeanResult;
	}
	
	@Override
	public AccountBean updateData(int ID, AccountBean aBean) {
		session.beginTransaction();
		AccountBean sBean =  aDAO.updateData(ID, aBean);
		session.getTransaction().commit();
		return sBean;
	}
	
	
}
