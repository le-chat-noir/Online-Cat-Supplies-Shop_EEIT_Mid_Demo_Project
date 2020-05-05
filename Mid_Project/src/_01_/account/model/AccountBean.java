package _01_.account.model;


import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "userProfiles")
public class AccountBean {
	private int uid;
	private String account;
	private String password;
	private String name;
	private String email;
	private String phone;
	private String address;
	
	private String accountVerified;
	private Blob profileImage;
	
	@Id
	@Column(name = "UID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	
	@Column(name = "account")
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	
	@Column(name = "password")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name = "phoneNumber")
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Column(name = "address")
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Column(name = "accountVerified")
	public String getAccountVerified() {
		return accountVerified;
	}
	public void setAccountVerified(String accountVerified) {
		this.accountVerified = accountVerified;
	}
	
    @Column(name = "profileImage")
	public Blob getProfileImage() {
		return profileImage;
	}
	public void setProfileImage(Blob profileImage) {
		this.profileImage = profileImage;
	}
	
	
	
}
