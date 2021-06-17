/**
 * 
 */
package com.mlj.legalaffairs.Litigation.request;

/**
 * @author VmL
 *
 */
public class DepartmentRequestVO {
	
	private int departmentID; 
	private String DepartmentName; // text box
	private int ministryID; // dropdown from backend
	private String address; // text box
	private String typeOfAddress; // dropdown...values will mention later...String should be sent to backend
	
	public int getDepartmentID() {
		return departmentID;
	}
	public void setDepartmentID(int departmentID) {
		this.departmentID = departmentID;
	}
	public String getDepartmentName() {
		return DepartmentName;
	}
	public void setDepartmentName(String departmentName) {
		DepartmentName = departmentName;
	}
	public int getMinistryID() {
		return ministryID;
	}
	public void setMinistryID(int ministryID) {
		this.ministryID = ministryID;
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
