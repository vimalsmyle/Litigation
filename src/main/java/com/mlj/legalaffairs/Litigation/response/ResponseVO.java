/**
 * 
 */
package com.mlj.legalaffairs.Litigation.response;

import java.util.List;

/**
 * @author VmL
 *
 */
public class ResponseVO {
	
	private String result;
	private String Message;
	private List<CounselResponseVO> counseldetails;
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
	
}
