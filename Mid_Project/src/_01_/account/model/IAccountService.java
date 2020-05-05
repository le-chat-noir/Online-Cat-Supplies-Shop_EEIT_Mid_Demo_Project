package _01_.account.model;

import java.sql.Blob;
import java.util.List;



public interface IAccountService {

	int insertData(AccountBean aBeam);

	AccountBean updateName(int ID, String name);

	AccountBean updatePwd(int ID, String pwd);

	AccountBean updateAddress(int ID, String address);

	AccountBean updateEmail(int ID, String email);

	AccountBean updatePhone(int ID, String phone);


	AccountBean selectData(int ID);

	List<AccountBean> selectAllData();

	boolean deleteData(int ID);

	AccountBean updateAccountVerified(int ID, String accountVerified);

	AccountBean updateData(int ID, AccountBean aBean);

	AccountBean updateImg(int ID, Blob img);

}
