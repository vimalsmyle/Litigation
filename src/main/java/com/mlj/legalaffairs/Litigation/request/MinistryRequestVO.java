/**
 * 
 */
package com.mlj.legalaffairs.Litigation.request;

/**
 * @author DELL
 *
 */
public class MinistryRequestVO {

	private int ministryID;
	private String ministryName; // text box
	private String address; // text box
	private String typeOfAddress; // dropdown...values will mention later...String should be sent to backend
	
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
	
}
