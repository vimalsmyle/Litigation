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
}
