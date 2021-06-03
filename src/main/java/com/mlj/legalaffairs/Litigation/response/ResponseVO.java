/**
 * 
 */
package com.mlj.legalaffairs.Litigation.response;

import java.util.HashMap;
import java.util.List;

/**
 * @author VmL
 *
 */
public class ResponseVO {
	
	private String result;
	private String Message;
	private List<CounselResponseVO> counseldetails;
	private HashMap<Integer, String> dropDownCourts;
	private HashMap<Integer, String> dropDownCases;
	private UserDetails userDetails;
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getMessage() {
		return Message;
	}
	public void setMessage(String message) {
		Message = message;
	}
	public List<CounselResponseVO> getCounseldetails() {
		return counseldetails;
	}
	public void setCounseldetails(List<CounselResponseVO> counseldetails) {
		this.counseldetails = counseldetails;
	}
	public UserDetails getUserDetails() {
		return userDetails;
	}
	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}
	public HashMap<Integer, String> getDropDownCourts() {
		return dropDownCourts;
	}
	public void setDropDownCourts(HashMap<Integer, String> dropDownCourts) {
		this.dropDownCourts = dropDownCourts;
	}
	public HashMap<Integer, String> getDropDownCases() {
		return dropDownCases;
	}
	public void setDropDownCases(HashMap<Integer, String> dropDownCases) {
		this.dropDownCases = dropDownCases;
	}
	
}
