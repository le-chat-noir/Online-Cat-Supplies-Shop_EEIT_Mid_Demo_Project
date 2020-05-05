package _08_.order.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Orders")
public class OrderBean {
	private int orderID;
	private int userID;
	private int totalAmount;
	private String shippingAddress;
	private String shippingPhone;
	private String shippingName;
	private String orderNote;
	private String payID;
	private String payStatus;
	
	private Set<OrderDetailBean> orderDetailBeans = new HashSet<OrderDetailBean>(0);

	
	@Id
	@Column(name = "orderID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getOrderID() {
		return orderID;
	}
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	
	@Column(name = "userID")
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	@Column(name = "totalAmount")
	public int getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	@Column(name = "shippingAddress")
	public String getShippingAddress() {
		return shippingAddress;
	}
	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	
	@Column(name = "shippingPhone")
	public String getShippingPhone() {
		return shippingPhone;
	}
	public void setShippingPhone(String shippingPhone) {
		this.shippingPhone = shippingPhone;
	}
	
	@Column(name = "shippingName")
	public String getShippingName() {
		return shippingName;
	}
	public void setShippingName(String shippingName) {
		this.shippingName = shippingName;
	}
	
	@Column(name = "orderNote")
	public String getOrderNote() {
		return orderNote;
	}
	public void setOrderNote(String orderNote) {
		this.orderNote = orderNote;
	}
	
	@Column(name = "payID")
	public String getPayID() {
		return payID;
	}
	public void setPayID(String payID) {
		this.payID = payID;
	}
	@Column(name = "payStatus")
	public String getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "orderBean", cascade = CascadeType.ALL)
	public Set<OrderDetailBean> getOrderDetailBeans() {
		return orderDetailBeans;
	}
	public void setOrderDetailBeans(Set<OrderDetailBean> orderDetailBeans) {
		this.orderDetailBeans = orderDetailBeans;
	}
	
}
