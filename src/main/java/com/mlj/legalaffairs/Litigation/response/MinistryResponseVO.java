/**
 * 
 */
package com.mlj.legalaffairs.Litigation.response;

import java.util.List;

/**
 * @author VmL
 *
 */
public class MinistryResponseVO {
	
	private int ministryID;
	private String ministryName;
	private String address;
	private String typeOfAddress;
	private String date;
	
	private List<MinistryResponseVO> data;

	public int getMinistryID() {
		return ministryID;
	}

	public void setMinistryID(int ministryID) {
		this.ministryID = ministryID;
	}

	public String getMinistryName() {
		return ministryName;
	}

	public void setMinistryName(String ministryName) {
		this.ministryName = ministryName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTypeOfAddress() {
		return typeOfAddress;
	}

	public void setTypeOfAddress(String typeOfAddress) {
		this.typeOfAddress = typeOfAddress;
	}

	public List<MinistryResponseVO> getData() {
		return data;
	}

	public void setData(List<MinistryResponseVO> data) {
		this.data = data;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
}
