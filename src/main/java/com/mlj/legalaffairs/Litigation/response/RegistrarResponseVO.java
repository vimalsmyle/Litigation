/**
 * 
 */
package com.mlj.legalaffairs.Litigation.response;

import java.util.List;

/**
 * @author VmL
 *
 */
public class RegistrarResponseVO {
	
	private long highCourtID;
	private long catID;
	private int fileNumber;
	private int fileYear;
	private String filedBy;
	private int caseTypeID;
	private String caseNumber;
	private int caseYear;
	private int frNumber;
	private int frYear;
	private String ministryName;
	private String departmentName;
	private String counselName;
	private String date;
	private String remarks;
	
	private List<RegistrarResponseVO> data;

	public long getHighCourtID() {
		return highCourtID;
	}

	public void setHighCourtID(long highCourtID) {
		this.highCourtID = highCourtID;
	}

	public int getFileNumber() {
		return fileNumber;
	}

	public void setFileNumber(int fileNumber) {
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

	public int getFrNumber() {
		return frNumber;
	}

	public void setFrNumber(int frNumber) {
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public List<RegistrarResponseVO> getData() {
		return data;
	}

	public void setData(List<RegistrarResponseVO> data) {
		this.data = data;
	}

	public long getCatID() {
		return catID;
	}

	public void setCatID(long catID) {
		this.catID = catID;
	}
	
}
