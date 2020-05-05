package _06_.product.model;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class ProductBean {
	private int pid;
	private int catID;
	private String pName;
	private String pDescription;
	private int pPrice;
	private Blob pImage;
	
	@Id
	@Column(name = "PID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	
	@Column(name = "catID")
	public int getCatID() {
		return catID;
	}
	public void setCatID(int catID) {
		this.catID = catID;
	}
	
	@Column(name = "pName")
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	
	@Column(name = "pDescription")
	public String getpDescription() {
		return pDescription;
	}
	public void setpDescription(String pDescription) {
		this.pDescription = pDescription;
	}
	
	@Column(name = "pPrice")
	public int getpPrice() {
		return pPrice;
	}
	public void setpPrice(int pPrice) {
		this.pPrice = pPrice;
	}
	
	@Column(name = "pImage")
	public Blob getpImage() {
		return pImage;
	}
	public void setpImage(Blob pImage) {
		this.pImage = pImage;
	}
}
