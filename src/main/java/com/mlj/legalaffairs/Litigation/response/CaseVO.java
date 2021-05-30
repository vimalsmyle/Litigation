/**
 * 
 */
package com.mlj.legalaffairs.Litigation.response;

/**
 * @author VmL
 *
 */
public class CaseVO {
	
	private int caseID;
	private String caseName;
	private String abbrevation;
	
	public int getCaseID() {
		return caseID;
	}
	public void setCaseID(int caseID) {
		this.caseID = caseID;
	}
	public String getCaseName() {
		return caseName;
	}
	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}
	public String getAbbrevation() {
		return abbrevation;
	}
	public void setAbbrevation(String abbrevation) {
		this.abbrevation = abbrevation;
	}
	
}
