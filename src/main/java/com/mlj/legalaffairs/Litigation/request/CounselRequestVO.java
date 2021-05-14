/**
 * 
 */
package com.mlj.legalaffairs.Litigation.request;

/**
 * @author VmL
 *
 */
public class CounselRequestVO {
	
	private int counselID;
	private String title;
	private String name;
	private int counselTypeID;
	private String termFrom;
	private String termUpto;
	private String address;
	private String mobileNumber;
	private String emailID;
	private String telephoneNumber;
	private int courtID;
	private String remarks;
	
	
	public int getCounselID() {
		return counselID;
	}
	public void setCounselID(int counselID) {
		this.counselID = counselID;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCounselTypeID() {
		return counselTypeID;
	}
	public void setCounselTypeID(int counselTypeID) {
		this.counselTypeID = counselTypeID;
	}
	public String getTermFrom() {
		return termFrom;
	}
	public void setTermFrom(String termFrom) {
		this.termFrom = termFrom;
	}
	public String getTermUpto() {
		return termUpto;
	}
	public void setTermUpto(String termUpto) {
		this.termUpto = termUpto;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getEmailID() {
		return emailID;
	}
	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}
	public String getTelephoneNumber() {
		return telephoneNumber;
	}
	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}
	public int getCourtID() {
		return courtID;
	}
	public void setCourtID(int courtID) {
		this.courtID = courtID;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
}
