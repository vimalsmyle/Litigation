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
	private String caseType; 
	private int courtType; // dropdown from backend API no: 6.a
	private String fileNumber; // textbox only numbers
	private int fileYear; // dropdown  
	private String filedByTitle; // dropdown should send string to backend (Dr or Ms or M/s or Shri or Smt or Kum.)
	private String filedBy; // textbox
	private String filedAgainst; // textbox
	private int caseTypeID; // dropdown from backend should send key to backend API Number 6. b 
	private String caseNumber; // textbox 
	private int caseYear; // dropdown should send selected value to backend (current year and below upto 30 years i.e 2021 to 2000)
	
	// should be disabled if courttype is other than 1
	private String frNumber; // textbox
	private int frYear; // dropdown should send selected value to backend (current year and below upto 30 years i.e 2021 to 2019)
	
	
	private int numberOfCases; // textbox should accept int only
	private int ministryID; // dropdown from backend should send key to backend
	private int departmentID;// dropdown from backend should send key to backend
	private int counselID;// dropdown from backend should send key to backend 
	private int counselOnRecordID;// dropdown from backend should send key to backend; enable only when Ld.ASG is selected
	private String remarks; // textbox empty also accept
	private boolean old; // checkbox
	private String respondents; // textbox
	private boolean enclosure; //checkbox 
	private boolean reference; // checkbox
	
	// should be enabled only when reference checkbox is enabled
	private String referenceDate; //only date picker (format yyyy-MM-dd)
	private String referenceNumber; // textbox 
	private String referenceFrom; // dropdown should send value selected (Department or other dropdown)
	private String referenceType; // dropdown (letter or email)
	
	private int renominatedCounselID;// dropdown from backend should send key to backend (only for edit/update)
	
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
	public int getCounselOnRecordID() {
		return counselOnRecordID;
	}
	public void setCounselOnRecordID(int counselOnRecordID) {
		this.counselOnRecordID = counselOnRecordID;
	}
	public String getFiledAgainst() {
		return filedAgainst;
	}
	public void setFiledAgainst(String filedAgainst) {
		this.filedAgainst = filedAgainst;
	}
	public boolean isOld() {
		return old;
	}
	public void setOld(boolean old) {
		this.old = old;
	}
	public String getRespondents() {
		return respondents;
	}
	public void setRespondents(String respondents) {
		this.respondents = respondents;
	}
	public String getFiledByTitle() {
		return filedByTitle;
	}
	public void setFiledByTitle(String filedByTitle) {
		this.filedByTitle = filedByTitle;
	}
	public boolean isEnclosure() {
		return enclosure;
	}
	public void setEnclosure(boolean enclosure) {
		this.enclosure = enclosure;
	}
	public int getRenominatedCounselID() {
		return renominatedCounselID;
	}
	public void setRenominatedCounselID(int renominatedCounselID) {
		this.renominatedCounselID = renominatedCounselID;
	}
	public boolean isReference() {
		return reference;
	}
	public void setReference(boolean reference) {
		this.reference = reference;
	}
	public String getReferenceNumber() {
		return referenceNumber;
	}
	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}
	public String getReferenceFrom() {
		return referenceFrom;
	}
	public void setReferenceFrom(String referenceFrom) {
		this.referenceFrom = referenceFrom;
	}
	public String getReferenceDate() {
		return referenceDate;
	}
	public void setReferenceDate(String referenceDate) {
		this.referenceDate = referenceDate;
	}
	public String getReferenceType() {
		return referenceType;
	}
	public void setReferenceType(String referenceType) {
		this.referenceType = referenceType;
	}
	
}
