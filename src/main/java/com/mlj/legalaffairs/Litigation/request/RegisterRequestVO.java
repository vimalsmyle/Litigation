/**
 * 
 */
package com.mlj.legalaffairs.Litigation.request;

/**
 * @author VmL
 *
 */
public class RegisterRequestVO {
	
	private long highCourtID;
	private long catID;
	private int courtType;
	private String fileNumber;
	private int fileYear;
	private String filedBy;
	private int caseTypeID;
	private String caseType;
	private String caseNumber;
	private int caseYear;
	private String frNumber;
	private int frYear;
	private int numberOfCases;
	private int ministryID;
	private int departmentID;
	private int counselID;
	private String remarks;
	
	public long getHighCourtID() {
		return highCourtID;
	}
	public void setHighCourtID(long highCourtID) {
		this.highCourtID = highCourtID;
	}
	public long getCatID() {
		return catID;
	}
	public void setCatID(long catID) {
		this.catID = catID;
	}
	public String getFileNumber() {
		return fileNumber;
	}
	public void setFileNumber(String fileNumber) {
		this.fileNumber = fileNumber;
	}
	public int getFileYear() {
		return fileYear;
	}
	public void setFileYear(int fileYear) {
		this.fileYear = fileYear;
	}
	public String getFiledBy() {
		return filedBy;
	}
	public void setFiledBy(String filedBy) {
		this.filedBy = filedBy;
	}
	public int getCaseTypeID() {
		return caseTypeID;
	}
	public void setCaseTypeID(int caseTypeID) {
		this.caseTypeID = caseTypeID;
	}
	public String getCaseType() {
		return caseType;
	}
	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}
	public String getCaseNumber() {
		return caseNumber;
	}
	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}
	public int getCaseYear() {
		return caseYear;
	}
	public void setCaseYear(int caseYear) {
		this.caseYear = caseYear;
	}
	public String getFrNumber() {
		return frNumber;
	}
	public void setFrNumber(String frNumber) {
		this.frNumber = frNumber;
	}
	public int getFrYear() {
		return frYear;
	}
	public void setFrYear(int frYear) {
		this.frYear = frYear;
	}
	public int getNumberOfCases() {
		return numberOfCases;
	}
	public void setNumberOfCases(int numberOfCases) {
		this.numberOfCases = numberOfCases;
	}
	public int getMinistryID() {
		return ministryID;
	}
	public void setMinistryID(int ministryID) {
		this.ministryID = ministryID;
	}
	public int getDepartmentID() {
		return departmentID;
	}
	public void setDepartmentID(int departmentID) {
		this.departmentID = departmentID;
	}
	public int getCounselID() {
		return counselID;
	}
	public void setCounselID(int counselID) {
		this.counselID = counselID;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public int getCourtType() {
		return courtType;
	}
	public void setCourtType(int courtType) {
		this.courtType = courtType;
	}
	
}
