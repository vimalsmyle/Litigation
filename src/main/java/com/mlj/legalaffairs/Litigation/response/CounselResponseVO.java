/**
 * 
 */
package com.mlj.legalaffairs.Litigation.response;

import java.util.List;

/**
 * @author VmL
 *
 */
public class CounselResponseVO {
	
	private int CounselID;
	private String title;
	private String name;
	private String counselType;
	private String termFrom;
	private String termUpto;
	private String address;
	private String mobileNumber;
	private String emailID;
	private String telephoneNumber;
	private String remarks;
	
	private List<CounselResponseVO> data;

	public int getCounselID() {
		return CounselID;
	}

	public void setCounselID(int counselID) {
		CounselID = counselID;
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

	public String getCounselType() {
		return counselType;
	}

	public void setCounselType(String counselType) {
		this.counselType = counselType;
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

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public List<CounselResponseVO> getData() {
		return data;
	}

	public void setData(List<CounselResponseVO> data) {
		this.data = data;
	}

}
