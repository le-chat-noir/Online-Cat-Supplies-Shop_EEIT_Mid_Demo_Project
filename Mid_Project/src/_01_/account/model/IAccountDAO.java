package _01_.account.model;

import java.sql.Blob;
import java.util.List;

import org.hibernate.Session;



public interface IAccountDAO {

	boolean deleteData(int ID);

	Session getSession();

	AccountBean selectData(int ID);

	List<AccountBean> selectAllData();

	AccountBean updateName(int ID, String name);

	AccountBean updateAddress(int ID, String address);

	AccountBean updateEmail(int ID, String email);

	AccountBean updatePhone(int ID, String phone);

	AccountBean updatePwd(int ID, String pwd);


	int insertData(AccountBean aBeam);

	AccountBean updateAccountVerified(int ID, String accountVerified);

	AccountBean updateData(int ID, AccountBean aBean);

	AccountBean updateImg(int ID, Blob img);
	
	

}
