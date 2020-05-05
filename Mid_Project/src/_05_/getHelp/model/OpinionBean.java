package _05_.getHelp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "opinion")
public class OpinionBean {

	private int oid;
	private Integer uid;
	private String userName;
	private String userMail;
	private String opinionTitle;
	private String opinionText;
	private String Solution;
	private String caseStatus;
	
	@Id
	@Column(name = "oid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getOid() {
		return oid;
	}
	public void setOid(int oid) {
		this.oid = oid;
	}
	
	
	@Column(name = "uid")
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	
	@Column(name = "userName")
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Column(name = "userMail")
	public String getUserMail() {
		return userMail;
	}
	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}
	
	@Column(name = "opinionTitle")
	public String getOpinionTitle() {
		return opinionTitle;
	}
	public void setOpinionTitle(String opinionTitle) {
		this.opinionTitle = opinionTitle;
	}
	
	@Column(name = "opinionText")
	public String getOpinionText() {
		return opinionText;
	}
	public void setOpinionText(String opinionText) {
		this.opinionText = opinionText;
	}
	
	@Column(name = "solution")
	public String getSolution() {
		return Solution;
	}
	public void setSolution(String solution) {
		Solution = solution;
	}
	
	@Column(name = "caseStatus")
	public String getCaseStatus() {
		return caseStatus;
	}
	public void setCaseStatus(String caseStatus) {
		this.caseStatus = caseStatus;
	}
	
	
}
