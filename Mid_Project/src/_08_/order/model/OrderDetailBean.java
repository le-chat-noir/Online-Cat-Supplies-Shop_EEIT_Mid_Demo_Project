package _08_.order.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "OrdersDetails")
public class OrderDetailBean {
	private int orderListId;
	private int productID;
	private String productName; 
	private int quantity;
	private int unitPrice;
	private int subTotal;
	
	private OrderBean orderBean;
	
	@Id
	@Column(name = "orderListId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getOrderListId() {
		return orderListId;
	}

	public void setOrderListId(int orderListId) {
		this.orderListId = orderListId;
	}

	@Column(name = "productID")
	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	@Column(name = "productName")
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Column(name = "quantity")
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Column(name = "unitPrice")
	public int getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	@Column(name = "subTotal")
	public int getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(int subTotal) {
		this.subTotal = subTotal;
	}

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "orderID")
	public OrderBean getOrderBean() {
		return orderBean;
	}

	public void setOrderBean(OrderBean orderBean) {
		this.orderBean = orderBean;
	}
	
}
