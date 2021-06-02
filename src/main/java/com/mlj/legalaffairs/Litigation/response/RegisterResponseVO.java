/**
 * 
 */
package com.mlj.legalaffairs.Litigation.response;

import java.util.List;

/**
 * @author VmL
 *
 */
public class RegisterResponseVO {
	
	private long highCourtID;
	private long catID;
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
	private String ministryName;
	private String departmentName;
	private String counselName;
	private String registeredDate;
	private String modifiedDate;
	private String renominatedCounselName;
	private String renominatedDate;
	private String remarks;
	
	private List<RegisterResponseVO> data;

	public long getHighCourtID() {
		return highCourtID;
	}

	public void setHighCourtID(long highCourtID) {
		this.highCourtID = highCourtID;
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

	public String getMinistryName() {
		return ministryName;
	}

	public void setMinistryName(String ministryName) {
		this.ministryName = ministryName;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getCounselName() {
		return counselName;
	}

	public void setCounselName(String counselName) {
		this.counselName = counselName;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public List<RegisterResponseVO> getData() {
		return data;
	}

	public void setData(List<RegisterResponseVO> data) {
		this.data = data;
	}

	public long getCatID() {
		return catID;
	}

	public void setCatID(long catID) {
		this.catID = catID;
	}

	public String getCaseType() {
		return caseType;
	}

	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}

	public int getNumberOfCases() {
		return numberOfCases;
	}

	public void setNumberOfCases(int numberOfCases) {
		this.numberOfCases = numberOfCases;
	}

	public String getRegisteredDate() {
		return registeredDate;
	}

	public void setRegisteredDate(String registeredDate) {
		this.registeredDate = registeredDate;
	}

	public String getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getRenominatedCounselName() {
		return renominatedCounselName;
	}

	public void setRenominatedCounselName(String renominatedCounselName) {
		this.renominatedCounselName = renominatedCounselName;
	}

	public String getRenominatedDate() {
		return renominatedDate;
	}

	public void setRenominatedDate(String renominatedDate) {
		this.renominatedDate = renominatedDate;
	}
}
