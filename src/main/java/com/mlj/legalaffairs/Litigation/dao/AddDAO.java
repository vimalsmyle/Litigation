/**
 * 
 */
package com.mlj.legalaffairs.Litigation.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.util.Units;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.LineSpacingRule;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import com.mlj.legalaffairs.Litigation.constants.DataBaseConstants;
import com.mlj.legalaffairs.Litigation.constants.ExtraConstants;
import com.mlj.legalaffairs.Litigation.request.CounselRequestVO;
import com.mlj.legalaffairs.Litigation.request.DepartmentRequestVO;
import com.mlj.legalaffairs.Litigation.request.MinistryRequestVO;
import com.mlj.legalaffairs.Litigation.request.RegisterRequestVO;
import com.mlj.legalaffairs.Litigation.response.ResponseVO;
import com.mlj.legalaffairs.Litigation.response.CaseVO;
import com.mlj.legalaffairs.Litigation.response.CounselResponseVO;
import com.mlj.legalaffairs.Litigation.response.DepartmentResponseVO;
import com.mlj.legalaffairs.Litigation.response.MinistryResponseVO;
import com.mlj.legalaffairs.Litigation.response.RegisterResponseVO;

/**
 * @author K Vimal Kumar
 *
 */
public class AddDAO {
	
	public static Connection getConnection() throws ClassNotFoundException,
	SQLException {
		Connection connection = null;
		Class.forName(DataBaseConstants.DRIVER_CLASS);
		connection = DriverManager.getConnection(DataBaseConstants.DRIVER_URL, DataBaseConstants.USER_NAME,
				DataBaseConstants.PASSWORD);
		return connection;
}
	
	public List<MinistryResponseVO> getMinistrydetails() throws SQLException {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MinistryResponseVO> ministryList = null;
		MinistryResponseVO ministryResponseVO = null;
		try {
			con = getConnection();
			
			ministryList = new LinkedList<MinistryResponseVO>();
			
			pstmt = con.prepareStatement("SELECT * FROM ministrydetails ORDER BY MinistryName ASC");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ministryResponseVO = new MinistryResponseVO();
				
				ministryResponseVO.setMinistryID(rs.getInt("MinistryID"));
				ministryResponseVO.setMinistryName(rs.getString("MinistryName"));
				ministryResponseVO.setAddress(rs.getString("Address"));
				ministryResponseVO.setTypeOfAddress(rs.getString("TypeOfAddress"));
				ministryResponseVO.setDate(rs.getString("RegisteredDate"));
				
				ministryList.add(ministryResponseVO);
			}
			
		}
		catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pstmt.close();
			rs.close();
			con.close();
		}
		
		return ministryList;
	}
	
	public ResponseVO addMinistry(MinistryRequestVO ministryRequestVO) throws SQLException {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResponseVO responsevo = new ResponseVO();

		try {
			con = getConnection();
			pstmt = con.prepareStatement("INSERT INTO ministrydetails (MinistryName, Address, TypeOfAddress, RegisteredDate) VALUES (?, ?, ?, NOW())");
			pstmt.setString(1, ministryRequestVO.getMinistryName().trim());
			pstmt.setString(2, ministryRequestVO.getAddress().trim());
			pstmt.setString(3, ministryRequestVO.getTypeOfAddress().trim());
			
			if (pstmt.executeUpdate() > 0) {
				responsevo.setMessage("Ministry Added Successfully");
				responsevo.setResult("Success");
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			responsevo.setMessage("INTERNAL SERVER ERROR");
			responsevo.setResult("Failure");
			e.printStackTrace();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			responsevo.setMessage("SERVER ERROR");
			responsevo.setResult("Failure");
		} finally {
			pstmt.close();
			con.close();
		}
		return responsevo;
	}
	
	public ResponseVO editMinistry(MinistryRequestVO ministryRequestVO) throws SQLException {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResponseVO responsevo = new ResponseVO();

		try {
			con = getConnection();
			pstmt = con.prepareStatement("UPDATE ministrydetails SET MinistryName = ?, Address = ?, TypeOfAddress = ?, ModifiedDate = NOW() WHERE MinistryID = ?");
			pstmt.setString(1, ministryRequestVO.getMinistryName().trim());
			pstmt.setString(2, ministryRequestVO.getAddress().trim());
			pstmt.setString(3, ministryRequestVO.getTypeOfAddress().trim());
			pstmt.setInt(4, ministryRequestVO.getMinistryID());
			
			if (pstmt.executeUpdate() > 0) {
				responsevo.setMessage("Ministry Details Updated Successfully");
				responsevo.setResult("Success");
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			responsevo.setMessage("INTERNAL SERVER ERROR");
			responsevo.setResult("Failure");
			e.printStackTrace();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			responsevo.setMessage("SERVER ERROR");
			responsevo.setResult("Failure");
		} finally {
			pstmt.close();
			con.close();
		}
		return responsevo;
		
	}
	
	public List<DepartmentResponseVO> getDepartmentdetails() throws SQLException {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<DepartmentResponseVO> departmentList = null;
		DepartmentResponseVO departmentResponseVO = null;
		try {
			con = getConnection();
			
			departmentList = new LinkedList<DepartmentResponseVO>();
			
			pstmt = con.prepareStatement("SELECT dd.DepartmentID, dd.DepartmentName, md.MinistryName, dd.Address, dd.TypeOfAddress, dd.RegisteredDate FROM departmentdetails AS dd LEFT JOIN ministrydetails AS md ON dd.MinistryID = md.MinistryID ORDER BY dd.DepartmentName ASC");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				departmentResponseVO = new DepartmentResponseVO();
				
				departmentResponseVO.setDepartmentID(rs.getInt("DepartmentID"));
				departmentResponseVO.setDepartmentName(rs.getString("DepartmentName"));
				departmentResponseVO.setMinistryName(rs.getString("MinistryName"));
				departmentResponseVO.setAddress(rs.getString("Address"));
				departmentResponseVO.setTypeOfAddress(rs.getString("TypeOfAddress"));
				departmentResponseVO.setDate(rs.getString("RegisteredDate"));
				
				departmentList.add(departmentResponseVO);
			}
			
		}
		catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pstmt.close();
			rs.close();
			con.close();
		}
		
		return departmentList;
	}
	
	public ResponseVO addDepartment(DepartmentRequestVO departmentRequestVO) throws SQLException {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResponseVO responsevo = new ResponseVO();

		try {
			con = getConnection();
			pstmt = con.prepareStatement("INSERT INTO departmentdetails (DepartmentName, MinistryID, Address, TypeOfAddress, RegisteredDate) VALUES (?, ?, ?, ?, NOW())");
			pstmt.setString(1, departmentRequestVO.getDepartmentName().trim());
			pstmt.setInt(2, departmentRequestVO.getMinistryID());
			pstmt.setString(3, departmentRequestVO.getAddress().trim());
			pstmt.setString(4, departmentRequestVO.getTypeOfAddress().trim());
			
			if (pstmt.executeUpdate() > 0) {
				responsevo.setMessage("Department Added Successfully");
				responsevo.setResult("Success");
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			responsevo.setMessage("INTERNAL SERVER ERROR");
			responsevo.setResult("Failure");
			e.printStackTrace();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			responsevo.setMessage("SERVER ERROR");
			responsevo.setResult("Failure");
		} finally {
			pstmt.close();
			con.close();
		}
		return responsevo;
	}
	
	public ResponseVO editDepartment(DepartmentRequestVO departmentRequestVO) throws SQLException {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResponseVO responsevo = new ResponseVO();

		try {
			con = getConnection();
			pstmt = con.prepareStatement("UPDATE departmentdetails SET DepartmentName = ?, MinistryID = ?, Address = ?, TypeOfAddress = ?, ModifiedDate = NOW() WHERE DepartmentID = ?");
			pstmt.setString(1, departmentRequestVO.getDepartmentName().trim());
			pstmt.setInt(2, departmentRequestVO.getMinistryID());
			pstmt.setString(3, departmentRequestVO.getAddress().trim());
			pstmt.setString(4, departmentRequestVO.getTypeOfAddress().trim());
			pstmt.setInt(5, departmentRequestVO.getDepartmentID());
			
			if (pstmt.executeUpdate() > 0) {
				responsevo.setMessage("Department Details Updated Successfully");
				responsevo.setResult("Success");
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			responsevo.setMessage("INTERNAL SERVER ERROR");
			responsevo.setResult("Failure");
			e.printStackTrace();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			responsevo.setMessage("SERVER ERROR");
			responsevo.setResult("Failure");
		} finally {
			pstmt.close();
			con.close();
		}
		return responsevo;
	}

	public List<CounselResponseVO> getCounseldetails() throws SQLException {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<CounselResponseVO> counselList = null;
		CounselResponseVO counselResponseVO = null;
		try {
			con = getConnection();
			
			counselList = new LinkedList<CounselResponseVO>();
			
			pstmt = con.prepareStatement("SELECT cd.CounselID, cd.Title, cd.Name, ct.CounselType, cd.Address, cd.MobileNumber, cd.EmailID, cd.TelephoneNumber, ctd.CourtName FROM counseldetails AS cd LEFT JOIN counselType AS ct ON cd.CounselTypeID = ct.CounselTypeID LEFT JOIN courtdetails AS ctd ON ctd.CourtID = cd.CourtID WHERE cd.Status = 1 ORDER BY Name ASC");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				counselResponseVO = new CounselResponseVO();
				
				counselResponseVO.setCounselID(rs.getInt("CounselID"));
				counselResponseVO.setTitle(rs.getString("Title"));
				counselResponseVO.setName(rs.getString("Name"));
				counselResponseVO.setCounselType(rs.getString("CounselType"));
				counselResponseVO.setAddress(rs.getString("Address"));
				counselResponseVO.setMobileNumber(rs.getString("MobileNumber"));
				counselResponseVO.setEmailID(rs.getString("EmailID"));
				counselResponseVO.setTelephoneNumber(rs.getString("TelephoneNumber"));
				counselResponseVO.setCourtName(rs.getString("CourtName"));
				
				counselList.add(counselResponseVO);
			}
			
		}
		catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pstmt.close();
			rs.close();
			con.close();
		}
		
		return counselList;
	}

	public ResponseVO addCounsel(CounselRequestVO counselvo) throws SQLException {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResponseVO responsevo = new ResponseVO();

		try {
			con = getConnection();
			pstmt = con.prepareStatement("INSERT INTO counseldetails (Title, Name, CounselTypeID, TermFrom, TermUpto, Address, MobileNumber, EmailID, TelephoneNumber, CourtID, Remarks, RegisteredDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW())");
			pstmt.setString(1, counselvo.getTitle().trim());
			pstmt.setString(2, counselvo.getName().trim());
			pstmt.setInt(3, counselvo.getCounselTypeID());
			pstmt.setString(4, counselvo.getTermFrom().trim());
			pstmt.setString(5, counselvo.getTermUpto().trim());
			pstmt.setString(6, counselvo.getAddress().trim());
			pstmt.setString(7, counselvo.getMobileNumber().trim());
			pstmt.setString(8, counselvo.getEmailID().trim());
			pstmt.setString(9, counselvo.getTelephoneNumber().trim());
			pstmt.setInt(10, counselvo.getCourtID());
			pstmt.setString(11, counselvo.getRemarks().trim());
			
			if (pstmt.executeUpdate() > 0) {
				responsevo.setMessage("Counsel Added Successfully");
				responsevo.setResult("Success");
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			responsevo.setMessage("INTERNAL SERVER ERROR");
			responsevo.setResult("Failure");
			e.printStackTrace();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			responsevo.setMessage("SERVER ERROR");
			responsevo.setResult("Failure");
		} finally {
			pstmt.close();
			con.close();
		}
		return responsevo;
	}

	public ResponseVO editCounsel(CounselRequestVO counselvo) throws SQLException {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResponseVO responsevo = new ResponseVO();

		try {
			con = getConnection();
			pstmt = con.prepareStatement("UPDATE counseldetails SET Title = ?, Name = ?, CounselTypeID = ?, TermFrom = ?, TermUpto = ?, Address = ?, MobileNumber = ?, EmailID = ?, TelephoneNumber = ?, CourtID = ?, Remarks = ?, ModifiedDate = NOW() WHERE CounselID = ?");
			pstmt.setString(1, counselvo.getTitle().trim());
			pstmt.setString(2, counselvo.getName().trim());
			pstmt.setInt(3, counselvo.getCounselTypeID());
			pstmt.setString(4, counselvo.getTermFrom().trim());
			pstmt.setString(5, counselvo.getTermUpto().trim());
			pstmt.setString(6, counselvo.getAddress().trim());
			pstmt.setString(7, counselvo.getMobileNumber().trim());
			pstmt.setString(8, counselvo.getEmailID().trim());
			pstmt.setString(9, counselvo.getTelephoneNumber().trim());
			pstmt.setInt(10, counselvo.getCourtID());
			pstmt.setString(11, counselvo.getRemarks().trim());
			pstmt.setInt(12, counselvo.getCounselID());
			
			if (pstmt.executeUpdate() > 0) {
				responsevo.setMessage("Counsel Details Updated Successfully");
				responsevo.setResult("Success");
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			responsevo.setMessage("INTERNAL SERVER ERROR");
			responsevo.setResult("Failure");
			e.printStackTrace();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			responsevo.setMessage("SERVER ERROR");
			responsevo.setResult("Failure");
		} finally {
			pstmt.close();
			con.close();
		}
		return responsevo;
	}
	
	public List<RegisterResponseVO> getNominationdetails(int courtType, int year) throws SQLException {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<RegisterResponseVO> registerList = null;
		RegisterResponseVO registerResponseVO = null;
		try {
			
			con = getConnection();
			
			registerList = new LinkedList<RegisterResponseVO>();
			String query = "SELECT * FROM <change> <fileYear> = ";
			query = query.replaceAll("<change>", (courtType == 1 ? "highcourtregister" : "catregister"));
			pstmt = con.prepareStatement(query.replaceAll("<fileYear>", (year != 0 ? "WHERE FileYear = "+ year : "")));
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				registerResponseVO = new RegisterResponseVO();
				
				registerResponseVO.setHighCourtID(rs.getLong("HighCourtID"));
				registerResponseVO.setFileNumber(rs.getString("FileNumber"));
				registerResponseVO.setFileYear(rs.getInt("FileYear"));
				registerResponseVO.setFiledBy(rs.getString("FiledBy"));
				registerResponseVO.setCaseTypeID(rs.getInt("CaseTypeID"));
				
				PreparedStatement casename = con.prepareStatement("SELECT * FROM CaseType WHERE CaseTypeID = "+rs.getInt("CaseTypeID"));
				ResultSet rs1 = casename.executeQuery();
				if(rs1.next()) {
				registerResponseVO.setCaseType(rs1.getString("Case"));
				}
				
				registerResponseVO.setCaseNumber("CaseNumber");
				registerResponseVO.setCaseYear(rs.getInt("CaseYear"));
				registerResponseVO.setFrNumber(rs.getString("FRNumber"));
				registerResponseVO.setFrYear(rs.getInt("FRYear"));
				registerResponseVO.setNumberOfCases(rs.getInt("NumberOfCases"));
				
				PreparedStatement minDept = con.prepareStatement("SELECT m.MinistryName, d.DepartmentName FROM ministrydetails AS m LEFT JOIN departmentdetails AS d on m.MinistryID = d.MinistryID WHERE m.MinistryID = "+rs.getInt("MinistryID") + " AND d.DepartmentID = "+ rs.getInt("DepartmentID"));
				ResultSet rs2 = minDept.executeQuery();
				if(rs1.next()) {
					registerResponseVO.setMinistryName(rs2.getString("MinistryName"));
					registerResponseVO.setDepartmentName(rs2.getString("DepartmentName"));
				}
				
				PreparedStatement counsel = con.prepareStatement("SELECT Name FROM counseldetails WHERE CounselID = ");
				counsel.setInt(1, rs.getInt("CounselID"));
				ResultSet rs3 = counsel.executeQuery();
				
				if(rs3.next()) {
					registerResponseVO.setCounselName(rs3.getString("Name"));
					if(rs.getInt("RenominatedCounselID") != 0) {
						counsel.setInt(1, rs.getInt("RenominatedCounselID"));
						ResultSet rs4 = counsel.executeQuery();
						registerResponseVO.setRenominatedCounselName(rs4.getString("Name"));
					}
				}
				registerResponseVO.setRenominatedDate(dateformatter(rs.getString("RenominatedDate")));
				registerResponseVO.setRegisteredDate(dateformatter(rs.getString("RegisteredDate")));
				registerResponseVO.setRemarks(rs.getString("Remarks"));
				
				registerList.add(registerResponseVO);
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pstmt.close();
			rs.close();
			con.close();
		}
		
		return registerList;
	}
	
	public ResponseVO addNomination(RegisterRequestVO registerRequestVO) throws Exception{
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		ResponseVO responsevo = new ResponseVO();
		LocalDate currentdate = LocalDate.now();
//		String drivename = "D:/<folder>/Drafts/"+currentdate.getYear()+"/"+currentdate.getMonth()+"/";
		String drivename = "D:/<folder>/Drafts/"+currentdate.getYear()+"/"+"<folder1>";
		String fontColour = "000000";
		String fileNumber = "File No: "+registerRequestVO.getFileNumber()+"/"+currentdate.getYear()+"/LIT/"+(registerRequestVO.getCourtType() == 1 ? "HC" : registerRequestVO.getCourtType() == 2 ? "CAT" : registerRequestVO.getCourtType() == 3 ? "NCLT" : registerRequestVO.getCourtType() == 4 ? "KSCDRC" : "");

		try {
			con = getConnection();
			String query = "INSERT INTO <register> (FileNumber, FileYear, FiledBy, CaseTypeID, CaseNumber, CaseYear, <change1> NumberOfCases, MinistryID, DepartmentID, CounselID, CounselOnRecordID, RegisteredDate, Remarks) VALUES (?, ?, ?, ?, ?, ?, <change2> ?, ?, ?, ?, ?, NOW(), ?)";
			query = query.replaceAll("<change1>", registerRequestVO.getCourtType() == 1 ? "FRNumber, FRYear," : "");
			query = query.replaceAll("<change2>", registerRequestVO.getCourtType() == 1 ? "?, ?," : "");
			query = query.replaceAll("<register>", registerRequestVO.getCourtType() == 1 ? "highcourtregister" : "cattregister"); 
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, registerRequestVO.getFileNumber().trim());
			pstmt.setInt(2, registerRequestVO.getFileYear());
			pstmt.setString(3, registerRequestVO.getFiledBy().trim());
			pstmt.setInt(4, registerRequestVO.getCaseTypeID());
			pstmt.setString(5, registerRequestVO.getCaseNumber().trim());
			pstmt.setInt(6, registerRequestVO.getCaseYear());
			
			if(registerRequestVO.getCourtType() == 1) {
			
				pstmt.setString(7, registerRequestVO.getFrNumber().trim());
				pstmt.setInt(8, registerRequestVO.getFrYear());
				pstmt.setInt(9, registerRequestVO.getNumberOfCases());
				pstmt.setInt(10, registerRequestVO.getMinistryID());
				pstmt.setInt(11, registerRequestVO.getDepartmentID());
				pstmt.setInt(12, registerRequestVO.getCounselID());
				pstmt.setInt(13, registerRequestVO.getCounselOnRecordID());
				pstmt.setString(14, registerRequestVO.getRemarks());
			
			} else {
				
				pstmt.setInt(7, registerRequestVO.getNumberOfCases());
				pstmt.setInt(8, registerRequestVO.getMinistryID());
				pstmt.setInt(9, registerRequestVO.getDepartmentID());
				pstmt.setInt(10, registerRequestVO.getCounselID());
				pstmt.setInt(11, registerRequestVO.getCounselOnRecordID());
				pstmt.setString(12, registerRequestVO.getRemarks());
				
			}
			
//			if (pstmt.executeUpdate() > 0) {
				
				if(!registerRequestVO.isOld()) {
				// create Nomination letter in doc format
				//change accordingly to nclt and kscdrc
				
				CaseVO caseVO = new CaseVO();
				if(registerRequestVO.getCaseTypeID() != 78) {caseVO = fetchCaseDetails(registerRequestVO.getCaseTypeID());}
				
				String caseNumber = (registerRequestVO.getCourtType() == 1 ? (registerRequestVO.getCaseTypeID() != 78 ? (!registerRequestVO.getCaseNumber().equalsIgnoreCase("0") ? (caseVO.getAbbrevation()+" No: "+registerRequestVO.getCaseNumber()+"/"+registerRequestVO.getCaseYear()) : (registerRequestVO.getCaseType()+" No: /"+registerRequestVO.getCaseYear()+" (F.R No: "+registerRequestVO.getFrNumber()+"/"+registerRequestVO.getFrYear()+")")) : registerRequestVO.getCaseNumber() ): (caseVO.getAbbrevation() + " No: 170/" + registerRequestVO.getCaseNumber() + "/" + registerRequestVO.getCaseYear()));
				drivename = drivename.replaceAll("<folder>", registerRequestVO.getCourtType() == 1 ? "High Court Nominations" : "CAT Nominations");
				drivename = drivename.replaceAll("<folder1>", registerRequestVO.getCourtType() == 1 ? currentdate.getMonth()+"/" : "");
				
				File directory = new File(drivename);
				if (!directory.exists()) { directory.mkdirs(); }
				
				XWPFDocument document = new XWPFDocument();
				XWPFParagraph noteSheetTitle = document.createParagraph();
				noteSheetTitle.setAlignment(ParagraphAlignment.CENTER);
				
				XWPFRun noteSheetTitleRun = noteSheetTitle.createRun();
				noteSheetTitleRun.setText("Department of Legal Affairs");
				noteSheetTitleRun.setColor(fontColour);
				noteSheetTitleRun.setBold(true);
				noteSheetTitleRun.setFontFamily("Book Antiqua");
				noteSheetTitleRun.setFontSize(18);
				
				//https://www.baeldung.com/java-microsoft-word-with-apache-poi
				
//				https://www.javatpoint.com/apache-poi-word-paragraph
				
				XWPFParagraph noteSheetFileNumber = document.createParagraph();
				noteSheetFileNumber.setAlignment(ParagraphAlignment.RIGHT);
				noteSheetFileNumber.setSpacingAfter(0);
				
				XWPFRun noteSheetFileNumberRun = noteSheetFileNumber.createRun();
				noteSheetFileNumberRun.setText(fileNumber);
				noteSheetFileNumberRun.setColor(fontColour);
				noteSheetFileNumberRun.setFontFamily("Verdana");
				noteSheetFileNumberRun.setFontSize(11);
				
				XWPFParagraph noteSheetDate = document.createParagraph();
				noteSheetDate.setAlignment(ParagraphAlignment.RIGHT);
				
				XWPFRun noteSheetDateRun = noteSheetDate.createRun();
				noteSheetDateRun.setText("Dated: "+dateformatter(currentdate.toString()));
				noteSheetDateRun.setColor(fontColour);
				noteSheetDateRun.setFontFamily("Verdana");
				noteSheetDateRun.setFontSize(11);
				
				XWPFParagraph noteSheetCaseNumber = document.createParagraph();
				noteSheetCaseNumber.setAlignment(ParagraphAlignment.CENTER);
				
				XWPFRun noteSheetCaseNumberRun = noteSheetCaseNumber.createRun();
//				noteSheetCaseNumberRun.setText(!registerRequestVO.getCaseNumber().equalsIgnoreCase("0") ? (registerRequestVO.getCaseType()+"No: "+registerRequestVO.getCaseNumber()+"/"+registerRequestVO.getCaseYear()) : (registerRequestVO.getCaseType()+"No: /"+registerRequestVO.getCaseYear()+"(F.R No: "+registerRequestVO.getFrNumber()+"/"+registerRequestVO.getFrYear()+")"));
				noteSheetCaseNumberRun.setText(caseNumber);
				noteSheetCaseNumberRun.setColor(fontColour);
				noteSheetCaseNumberRun.setBold(true);
				noteSheetCaseNumberRun.setFontFamily("Verdana");
				noteSheetCaseNumberRun.setFontSize(12);
				
				XWPFParagraph noteSheetSL = document.createParagraph();
				noteSheetSL.setAlignment(ParagraphAlignment.LEFT);
				
				XWPFRun noteSheetSLRun = noteSheetSL.createRun();
				noteSheetSLRun.setText("SL.1 (R)"); 
				noteSheetSLRun.setColor(fontColour);
				noteSheetSLRun.setFontFamily("Verdana");
				noteSheetSLRun.setFontSize(11);
				
				XWPFParagraph noteSheetPara1 = document.createParagraph();
				noteSheetPara1.setAlignment(ParagraphAlignment.BOTH);
				
				XWPFRun noteSheetPara1Run = noteSheetPara1.createRun();
				// modify for nomination through dept letters and other sources
				if(registerRequestVO.isReference()) {
					noteSheetPara1Run.setText("                We have received a letter dated " + registerRequestVO.getReferenceDate() +" from the "+ registerRequestVO.getReferenceFrom() + "requesting us for nomination of Central Government Counsel in the above matter.");
				} else {
					noteSheetPara1Run.setText("                We have received a copy of " + (registerRequestVO.getCaseTypeID() != 78 ? caseVO.getCaseName() : registerRequestVO.getCaseType()) +" from the " + (registerRequestVO.getCourtType() == 1 ? "Office of the Additional Solicitor General of India, High Court of Karnataka for nomination of Central Government Counsel." : "Hon`ble Central Administrative Tribunal, Bangalore for nomination of Central Government Counsel.") );	
				}
				 
				noteSheetPara1Run.setColor(fontColour);
				noteSheetPara1Run.addCarriageReturn();
				noteSheetPara1Run.setFontFamily("Verdana");
				noteSheetPara1Run.setFontSize(10);
				
				XWPFParagraph noteSheetPara2 = document.createParagraph();
				noteSheetPara2.setAlignment(ParagraphAlignment.BOTH);
				
				XWPFRun noteSheetPara2Run = noteSheetPara2.createRun(); 
				
				CounselResponseVO counselResponseVO = null;
				CounselResponseVO counselOnRecordResponseVO = null;

				if(registerRequestVO.getCounselOnRecordID() != 0) {
					counselResponseVO =  fetchCounselDetails(registerRequestVO.getCounselID());
					counselOnRecordResponseVO = fetchCounselDetails(registerRequestVO.getCounselOnRecordID());	
					noteSheetPara2Run.setText("                As directed in the FR, a fair nomination in favour of "+ counselResponseVO.getTitle().trim()+ ". " +counselResponseVO.getName().trim()+", " + counselResponseVO.getCounselType() +" and " + counselOnRecordResponseVO.getTitle().trim()+". "+ counselOnRecordResponseVO.getName().trim()+ ", "+ counselOnRecordResponseVO.getCounselType().trim()+" as an advocate on record to assist the Ld. ASG is put up for signature please.");
				} else {
					counselResponseVO =  fetchCounselDetails(registerRequestVO.getCounselID());
					noteSheetPara2Run.setText("                As directed in the FR, a fair nomination in favour of "+ counselResponseVO.getTitle().trim()+ ". " +counselResponseVO.getName().trim()+", " + counselResponseVO.getCounselType() +" is put up for signature please.");
				}
				
				noteSheetPara2Run.setColor(fontColour);
				noteSheetPara2Run.setFontFamily("Verdana");
				noteSheetPara2Run.setFontSize(10);
				noteSheetPara2Run.addCarriageReturn();
				noteSheetPara2Run.addCarriageReturn();
				
				XWPFParagraph courtClerkSign = document.createParagraph();
				courtClerkSign.setAlignment(ParagraphAlignment.LEFT);
				
				XWPFRun courtClerkSignRun = courtClerkSign.createRun(); 
				courtClerkSignRun.setText("Court Clerk"); 
				courtClerkSignRun.setColor(fontColour);
				courtClerkSignRun.setFontFamily("Verdana");
				courtClerkSignRun.setUnderline(UnderlinePatterns.SINGLE);
				courtClerkSignRun.setFontSize(10);
				courtClerkSignRun.addCarriageReturn();
				courtClerkSignRun.addCarriageReturn();
				courtClerkSignRun.addCarriageReturn();
				
				XWPFParagraph ALASign = document.createParagraph();
				ALASign.setAlignment(ParagraphAlignment.LEFT);
				
				XWPFRun ALASignRun = ALASign.createRun();  
				ALASignRun.setText("Assistant L.A"); 
				ALASignRun.setColor(fontColour);
				ALASignRun.setFontFamily("Verdana");
				ALASignRun.setUnderline(UnderlinePatterns.SINGLE);
				ALASignRun.setFontSize(10);
				ALASignRun.addCarriageReturn();
				ALASignRun.addCarriageReturn();
				
				if(registerRequestVO.getCourtType() == 1) {
				
				XWPFParagraph textMessage = document.createParagraph();
				textMessage.setAlignment(ParagraphAlignment.LEFT);
				
				XWPFRun textMessageRun = textMessage.createRun();  
				textMessageRun.setText("Text message has been sent to the counsel."); 
				textMessageRun.setColor(fontColour);
				textMessageRun.setFontFamily("Verdana");
				textMessageRun.setFontSize(10);
				textMessageRun.addCarriageReturn();
				textMessageRun.addCarriageReturn();
				
				XWPFParagraph courtClerkSign1 = document.createParagraph();
				courtClerkSign1.setAlignment(ParagraphAlignment.LEFT);
				
				XWPFRun courtClerkSignRun1 = courtClerkSign1.createRun(); 
				courtClerkSignRun1.setText("Court Clerk"); 
				courtClerkSignRun1.setColor(fontColour);
				courtClerkSignRun1.setFontFamily("Verdana");
				courtClerkSignRun1.setUnderline(UnderlinePatterns.SINGLE);
				courtClerkSignRun1.setFontSize(10);
				courtClerkSignRun1.addCarriageReturn();
				courtClerkSignRun1.addCarriageReturn();
				courtClerkSignRun1.addCarriageReturn();
				courtClerkSignRun1.addCarriageReturn();
				courtClerkSignRun1.addCarriageReturn();
				courtClerkSignRun1.addCarriageReturn();
				courtClerkSignRun1.addCarriageReturn();
				courtClerkSignRun1.addCarriageReturn();
				courtClerkSignRun1.addCarriageReturn();
				courtClerkSignRun1.addCarriageReturn();
				courtClerkSignRun1.addCarriageReturn();
				courtClerkSignRun1.addCarriageReturn();
				courtClerkSignRun1.addCarriageReturn();
				courtClerkSignRun1.addCarriageReturn();
				courtClerkSignRun1.addCarriageReturn();
				courtClerkSignRun1.addCarriageReturn();
				courtClerkSignRun1.addCarriageReturn();
				courtClerkSignRun1.addCarriageReturn();
				courtClerkSignRun1.addCarriageReturn();
				courtClerkSignRun1.addCarriageReturn();
				courtClerkSignRun1.addCarriageReturn();
				courtClerkSignRun1.addCarriageReturn();
				courtClerkSignRun1.addCarriageReturn();
				courtClerkSignRun1.addCarriageReturn();
				courtClerkSignRun1.addCarriageReturn();
				courtClerkSignRun1.addCarriageReturn();
				courtClerkSignRun1.addCarriageReturn();
				courtClerkSignRun1.addCarriageReturn();
				courtClerkSignRun1.addCarriageReturn();
				courtClerkSignRun1.addCarriageReturn();
				courtClerkSignRun1.addCarriageReturn();
				courtClerkSignRun1.addCarriageReturn();
				courtClerkSignRun1.addCarriageReturn();
				courtClerkSignRun1.addCarriageReturn();
				
				} else {
					ALASignRun.addCarriageReturn();
					ALASignRun.addCarriageReturn();
					ALASignRun.addCarriageReturn();
					ALASignRun.addCarriageReturn();
					ALASignRun.addCarriageReturn();
					ALASignRun.addCarriageReturn();
					ALASignRun.addCarriageReturn();
					ALASignRun.addCarriageReturn();
					ALASignRun.addCarriageReturn();
					ALASignRun.addCarriageReturn();
					ALASignRun.addCarriageReturn();
					ALASignRun.addCarriageReturn();
					ALASignRun.addCarriageReturn();
					ALASignRun.addCarriageReturn();
					ALASignRun.addCarriageReturn();
					ALASignRun.addCarriageReturn();
					ALASignRun.addCarriageReturn();
					ALASignRun.addCarriageReturn();
					ALASignRun.addCarriageReturn();
					ALASignRun.addCarriageReturn();
					ALASignRun.addCarriageReturn();
					ALASignRun.addCarriageReturn();
					ALASignRun.addCarriageReturn();
					ALASignRun.addCarriageReturn();
					ALASignRun.addCarriageReturn();
					ALASignRun.addCarriageReturn();
					ALASignRun.addCarriageReturn();
					ALASignRun.addCarriageReturn();
					ALASignRun.addCarriageReturn();
					ALASignRun.addCarriageReturn();
					ALASignRun.addCarriageReturn();
					ALASignRun.addCarriageReturn();
					ALASignRun.addCarriageReturn();
					ALASignRun.addCarriageReturn();
					ALASignRun.addCarriageReturn();
					ALASignRun.addCarriageReturn();
					ALASignRun.addCarriageReturn();
					ALASignRun.addCarriageReturn();
					ALASignRun.addCarriageReturn();
					ALASignRun.addCarriageReturn();
				}
				
				XWPFParagraph logo = document.createParagraph();
				logo.setAlignment(ParagraphAlignment.CENTER);
				logo.setSpacingAfter(0);
				
				XWPFRun logoRun = logo.createRun(); ///Litigation/src/main/webapp/images/Emblem.png
				logoRun.addPicture(new FileInputStream(new File(ExtraConstants.EMBLEM)), XWPFDocument.PICTURE_TYPE_PNG, "Emblem", Units.toEMU(40), Units.toEMU(50));
				
				XWPFParagraph letterHead = document.createParagraph();
				letterHead.setAlignment(ParagraphAlignment.CENTER);
				letterHead.setSpacingAfter(0);
				
				XWPFRun GOIRun = letterHead.createRun();
				GOIRun.setText("Government of India");
				GOIRun.setFontFamily("Calibri");
				GOIRun.setColor(fontColour);
				GOIRun.setFontSize(10);
				GOIRun.setBold(true);
				GOIRun.addCarriageReturn();
				
				XWPFRun MLJRun = letterHead.createRun();
				MLJRun.setText("Ministry of Law & Justice");
				MLJRun.setFontFamily("Calibri"); MLJRun.setColor(fontColour); MLJRun.setFontSize(10); MLJRun.setBold(true);
				MLJRun.addCarriageReturn();
				
				XWPFRun DLARun = letterHead.createRun();
				DLARun.setText("Department of Legal Affairs");
				DLARun.setFontFamily("Calibri"); DLARun.setColor(fontColour); DLARun.setFontSize(10); DLARun.setBold(true);
				DLARun.addCarriageReturn();
				
				XWPFRun BSRun = letterHead.createRun();
				BSRun.setText("Branch Secretariat");
				BSRun.setFontFamily("Calibri"); BSRun.setColor(fontColour); BSRun.setFontSize(10); BSRun.setBold(true);
				BSRun.addCarriageReturn();
				
				XWPFRun Add1Run = letterHead.createRun();
				Add1Run.setText("4th Floor, D & F Wing, Kendriya Sadan");
				Add1Run.setFontFamily("Calibri"); Add1Run.setColor(fontColour); Add1Run.setFontSize(10); Add1Run.setBold(true);
				Add1Run.addCarriageReturn();
				
				XWPFRun Add2Run = letterHead.createRun();
				Add2Run.setText("Koramangala, Bengaluru – 560 034");
				Add2Run.setFontFamily("Calibri"); Add2Run.setColor(fontColour); Add2Run.setFontSize(10); Add2Run.setBold(true);
				Add2Run.addCarriageReturn();
				
				XWPFRun emailRun = letterHead.createRun();
				emailRun.setText("E-mail: mljblore-ka@nic.in");
				emailRun.setFontFamily("Calibri"); emailRun.setColor(fontColour); emailRun.setFontSize(10); emailRun.setBold(true);
				emailRun.addCarriageReturn();
				
				XWPFRun phoneRun = letterHead.createRun();
				phoneRun.setText("Ph: 080-25532002/2003   Fax: 080-25502003");
				phoneRun.setFontFamily("Calibri"); phoneRun.setColor(fontColour); phoneRun.setFontSize(10); phoneRun.setBold(true);
				
				XWPFParagraph fileNumberDate = document.createParagraph();
				fileNumberDate.setAlignment(ParagraphAlignment.BOTH);
				fileNumberDate.setSpacingAfter(0);
				
				XWPFRun fileNumberDateRun = fileNumberDate.createRun();
				fileNumberDateRun.setText(fileNumber + "                                                                                                                                                                 Dated: " + dateformatter(currentdate.toString()));
				fileNumberDateRun.setFontFamily("Calibri"); fileNumberDateRun.setColor(fontColour); fileNumberDateRun.setFontSize(10);
				
				XWPFParagraph to = document.createParagraph();
				to.setAlignment(ParagraphAlignment.LEFT);
				to.setSpacingAfter(0);
				
				XWPFRun toRun = to.createRun();
				toRun.setText("To");
				toRun.setFontFamily("Calibri");toRun.setColor(fontColour); toRun.setFontSize(10);
				toRun.addCarriageReturn();
				
				// add department address details after updating the database
				
				XWPFParagraph subject = document.createParagraph();
				subject.setAlignment(ParagraphAlignment.BOTH);
				subject.setSpacingAfter(0);
				
				XWPFRun subjectRun = subject.createRun();
				subjectRun.setText("Sub: Nomination of " + counselResponseVO.getAbbrevation() + " in case ");
				subjectRun.setFontFamily("Calibri"); subjectRun.setColor(fontColour); subjectRun.setFontSize(10);
//				subjectRun.addCarriageReturn();
				
				XWPFRun subject1Run = subject.createRun();
				subject1Run.setText(caseNumber);
				subject1Run.setFontFamily("Calibri"); subject1Run.setColor(fontColour); subject1Run.setFontSize(10);
				subject1Run.setBold(true);
				
				XWPFRun subject2Run = subject.createRun();
				subject2Run.setText(" filed by " +registerRequestVO.getFiledByTitle() + " "+registerRequestVO.getFiledBy() + " Vs " + registerRequestVO.getFiledAgainst() + " before the Hon’ble " + (registerRequestVO.getCourtType() == 1 ? "High Court of Karnataka" : "Central Administrative Tribunal") +", Bangalore.");
				subject2Run.setFontFamily("Calibri"); subject2Run.setColor(fontColour); subject2Run.setFontSize(10);
				subject2Run.addCarriageReturn();
				subject2Run.addCarriageReturn();
				
				if(registerRequestVO.isReference()) {
				XWPFRun referenceRun = subject.createRun();
				referenceRun.setText("Ref: "+registerRequestVO.getReferenceFrom()+"'s "+registerRequestVO.getReferenceType()+" " + (registerRequestVO.getReferenceType().equalsIgnoreCase("email") ? "" : "No.") + registerRequestVO.getReferenceNumber() +" dated " + (dateformatter(registerRequestVO.getReferenceDate()) +"."));
				referenceRun.setFontFamily("Calibri"); referenceRun.setColor(fontColour); referenceRun.setFontSize(10);
				referenceRun.addCarriageReturn();
				}
				
				XWPFParagraph sir = document.createParagraph();
				sir.setAlignment(ParagraphAlignment.LEFT);
				sir.setSpacingAfter(0);
				
				XWPFRun sirRun = sir.createRun();
				sirRun.setText("Sir,");
				sirRun.setFontFamily("Calibri");sirRun.setColor(fontColour); sirRun.setFontSize(10);
				
				XWPFParagraph para1 = document.createParagraph();
				para1.setAlignment(ParagraphAlignment.BOTH);
				para1.setSpacingAfter(0);
				para1.setSpacingAfterLines(0);
				
				XWPFRun para1Run = para1.createRun();
				
				if(!registerRequestVO.isEnclosure()) {
					if(registerRequestVO.getReferenceFrom().equalsIgnoreCase("Department") && registerRequestVO.getReferenceType().equalsIgnoreCase("letter")) {
						para1Run.setText("                I am to refer to your letter cited above and to intimate that "+ counselResponseVO.getTitle()+ ". " +counselResponseVO.getName().trim()+", " + counselResponseVO.getCounselType() + " has been engaged in the above matter to represent ");
					} else {
						para1Run.setText("                Please note that "+ counselResponseVO.getTitle()+ ". " +counselResponseVO.getName().trim()+", " + counselResponseVO.getCounselType() + " has been engaged in the above matter to represent ");
					}
				} else {
					para1Run.setText("                A copy of the " + (registerRequestVO.getCaseTypeID() != 78 ? caseVO.getCaseName() : registerRequestVO.getCaseType()) + " received from the " + (registerRequestVO.getCourtType() == 1 ? "Office of the Additional Solicitor General of India, High Court of Karnataka, Bangalore is enclosed. Please note that "+ counselResponseVO.getTitle()+ ". " +counselResponseVO.getName().trim()+", " + counselResponseVO.getCounselType() + " has been engaged in the above matter to represent " : "Hon`ble Central Administrative Tribunal, Bangalore is enclosed. Please note that " + counselResponseVO.getTitle()+ ". " +counselResponseVO.getName()+", " + counselResponseVO.getCounselType() + " has been engaged in the above matter to represent "));	
				}
				
				para1Run.setFontFamily("Calibri");para1Run.setColor(fontColour); para1Run.setFontSize(10);
				
				XWPFRun para11Run = para1.createRun();
				para11Run.setText((!registerRequestVO.getRespondents().equalsIgnoreCase("0") ? "Respondent No(s): " + registerRequestVO.getRespondents() : "Union of India"));
				para11Run.setFontFamily("Calibri");para11Run.setColor(fontColour); para11Run.setFontSize(10);para11Run.setBold(true);
				
				XWPFRun para12Run = para1.createRun();
				if(registerRequestVO.getCounselOnRecordID() != 0) {
					para12Run.setText(". "+counselOnRecordResponseVO.getTitle().trim()+". "+counselOnRecordResponseVO.getName().trim()+", "+ counselOnRecordResponseVO.getCounselType().trim() +" has been nominated to assist the Learned Additional Solicitor General as an Advocate on Record in the above matter.");
				} else {
					para12Run.setText((registerRequestVO.getCourtType() == 1 ? " before the Hon`ble High Court of Karnataka, Bangalore." : " before the Hon`ble Central Administrative Tribunal, Bangalore."));	
				}
				para12Run.setFontFamily("Calibri");para12Run.setColor(fontColour); para12Run.setFontSize(10);
				para12Run.addCarriageReturn();
				
				XWPFParagraph para2 = document.createParagraph();
				para2.setAlignment(ParagraphAlignment.BOTH);
				para2.setSpacingAfter(0);
				
				XWPFRun para2Run = para2.createRun();
				if(registerRequestVO.getCaseNumber().equalsIgnoreCase("0")) {

					if(registerRequestVO.getCounselOnRecordID()!= 0) {
						para2Run.setText("                The said Counsel "+ (registerRequestVO.getCounselOnRecordID()!=0 ? "("+counselOnRecordResponseVO.getTitle().trim()+". "+ counselOnRecordResponseVO.getName().trim()+")" : "") + " is being requested to intimate the " + caseVO.getAbbrevation() +" No. /"+ registerRequestVO.getCaseYear() +" allotted to the FR No."+ registerRequestVO.getFrNumber() +"/" + registerRequestVO.getFrYear() + " to the department/this Ministry as soon as possible.");
					} else {
						para2Run.setText("                The said Counsel is being requested to intimate the " + caseVO.getAbbrevation() +" No: /"+ registerRequestVO.getCaseYear() +" allotted to the FR No: "+ registerRequestVO.getFrNumber() +"/" + registerRequestVO.getFrYear() + " to the department/this Ministry as soon as possible.");
					}
					para2Run.setUnderline(UnderlinePatterns.SINGLE);
				} else {
					para2Run.setText("                Therefore, the department is advised to contact the "+(registerRequestVO.getCounselOnRecordID() !=0 ? "Learned Addl.SG ("+counselResponseVO.getTitle().trim() + ", " + counselResponseVO.getName().trim() + ") and "+ counselOnRecordResponseVO.getCounselType().trim() + " ("+counselOnRecordResponseVO.getTitle().trim()+". " + counselOnRecordResponseVO.getName().trim()+") immediately along with all papers for doing the needful in the matter" : "said counsel (whose contact details are given below) with all relevant papers and files for preparation of the case on behalf of the department till the disposal of the case or expiry of his/her term of engagement, whichever is earlier. "));
				}
				para2Run.setFontFamily("Calibri");para2Run.setColor(fontColour); para2Run.setFontSize(10);
				para2Run.addCarriageReturn();
				
				XWPFParagraph para3 = document.createParagraph();
				para3.setAlignment(ParagraphAlignment.BOTH);
				para3.setSpacingAfter(0);
				
				XWPFRun para3Run = para3.createRun();
				if(!registerRequestVO.getCaseNumber().equalsIgnoreCase("0")) {
					para3Run.setText("                The department is further requested to make correspondence with the Counsel directly and to take follow up action in the matter.");
				} else {
					para3Run.setText("                Therefore, the department is advised to contact the "+(registerRequestVO.getCounselOnRecordID() !=0 ? "Learned Addl.SG ("+counselResponseVO.getTitle().trim() + ", " + counselResponseVO.getName().trim() + ") and "+ counselOnRecordResponseVO.getCounselType().trim() + "("+counselOnRecordResponseVO.getTitle().trim()+". " + counselOnRecordResponseVO.getName()+") immediately along with all papers for doing the needful in the matter" : "said counsel (whose contact details are given below) with all relevant papers and files for preparation of the case on behalf of the department till the disposal of the case or expiry of his/her term of engagement, whichever is earlier. "));
				}
				para3Run.setFontFamily("Calibri");para3Run.setColor(fontColour); para3Run.setFontSize(10);
				para3Run.addCarriageReturn();
				
				XWPFParagraph note = document.createParagraph();
				note.setAlignment(ParagraphAlignment.BOTH);
				note.setSpacingAfter(0);
				
				XWPFRun note1Run = note.createRun();
				if(registerRequestVO.getCourtType() == 1) {
					if(counselResponseVO.getAbbrevation().equalsIgnoreCase("CGC") || counselResponseVO.getAbbrevation().equalsIgnoreCase("Asst.SG")) {
						note1Run.setText("NOTE: 1. Appearance fees & drafting fees including conference fee are paid by this Ministry. However, Misc. expenses i.e. typing, photocopy, postage, Court Fee, Notarization etc., are to be borne by the concerned Department at their own satisfaction after taking into account the details of miscellaneous expenses actually incurred. 2. ");
					} else if (counselResponseVO.getAbbrevation().equalsIgnoreCase("SPC")) {
						note1Run.setText("NOTE: 1. The fee payable to Senior Panel Counsel in the case will be borne by the concerned department as per the Senior Panel Counsel, terms and conditions. 2. ");
					} else if (counselResponseVO.getAbbrevation().equalsIgnoreCase("Ld.ASG")) {
						note1Run.setText("NOTE: 1. The fee payable to the Ld. Addl. SG in the case will be borne by the concerned department as per Law Officer (Conditions of Service) Rules, 1987 as amended from time to time. " + (counselOnRecordResponseVO.getCounselType().equalsIgnoreCase("CGC") ? "2. Appearance fees & drafting fees including conference fee are paid by this Ministry. However, Misc. expenses i.e., typing, photocopy, postage, Court Fee, Notarization etc., are to be borne by the concerned Department at their own satisfaction after taking into account the details of miscellaneous expenses actually incurred. 3. " : "NOTE: 2. The fee payable to Senior Panel Counsel in the case will be borne by the concerned department as per the Senior Panel Counsel, terms and conditions. 3. "));
					}
					note1Run.setUnderline(UnderlinePatterns.SINGLE);
					
				} else {
					if(counselResponseVO.getAbbrevation().equalsIgnoreCase("ACGSC")) {
						note1Run.setText("NOTE: 1. The fee payable to the Additional Central Government Standing Counsel in the case will be borne by the concerned department as per O.M No. 26(2)/1999-Judl. Dated 24.09.1999 r/w O.M. No. 26(1)/2014/Judl. dated 01.01.2015. 2. ");
					} else if(counselResponseVO.getAbbrevation().equalsIgnoreCase("SPC")) {
						note1Run.setText("NOTE: 1. The fee payable to the Senior Panel Counsel in the case will be borne by the concerned department as per O.M No. 26(1)/1999-Judl. Dated 24.09.1999 r/w O.M. No. 26(1)/2014/Judl. dated 01.01.2015. 2. ");
					} else if (counselResponseVO.getAbbrevation().equalsIgnoreCase("Ld.ASG")) {
						note1Run.setText("NOTE: 1. The fee payable to the Ld. Addl. SG in the case will be borne by the concerned department as per Law Officer (Conditions of Service) Rules, 1987 as amended from time to time. " + (counselOnRecordResponseVO.getCounselType().equalsIgnoreCase("ACGSC") ? "2. The fee payable to the Additional Central Government Standing Counsel in the case will be borne by the concerned department as per O.M No. 26(2)/1999-Judl. Dated 24.09.1999 r/w O.M. No. 26(1)/2014/Judl. dated 01.01.2015. 3. " : "NOTE: 2. The fee payable to the Senior Panel Counsel in the case will be borne by the concerned department as per O.M No. 26(1)/1999-Judl. Dated 24.09.1999 r/w O.M. No. 26(1)/2014/Judl. dated 01.01.2015. 3. "));
					}
					note1Run.setUnderline(UnderlinePatterns.SINGLE);
				}
				note1Run.setFontFamily("Calibri");note1Run.setColor(fontColour); note1Run.setFontSize(10);
				
				XWPFRun note2Run = note.createRun();
				note2Run.setText(fileNumber +" may be quoted in all your future correspondences in this regard.");
				note2Run.setFontFamily("Calibri");note2Run.setColor(fontColour); note2Run.setFontSize(10);
				note2Run.setUnderline(UnderlinePatterns.SINGLE);
				
				XWPFParagraph faithfully = document.createParagraph();
				faithfully.setAlignment(ParagraphAlignment.RIGHT);
				faithfully.setSpacingAfter(0);
				
				XWPFRun faithfullyRun = faithfully.createRun();
				faithfullyRun.setText("Yours faithfully");
				faithfullyRun.setFontFamily("Calibri");faithfullyRun.setColor(fontColour); faithfullyRun.setFontSize(10);
				faithfullyRun.addCarriageReturn();
				
				if(registerRequestVO.isEnclosure()) {
					XWPFParagraph enclosure = document.createParagraph();
					enclosure.setAlignment(ParagraphAlignment.LEFT);
					
					XWPFRun enclosureRun = enclosure.createRun();
					enclosureRun.setText("Encl:");
					enclosureRun.setFontFamily("Calibri");enclosureRun.setColor(fontColour); enclosureRun.setFontSize(10);
					enclosureRun.setUnderline(UnderlinePatterns.SINGLE);
					
					XWPFRun enclosure1Run = enclosure.createRun();
					enclosure1Run.setText(" "+caseVO.getAbbrevation() + " Copy");
					enclosure1Run.setFontFamily("Calibri");enclosure1Run.setColor(fontColour); enclosure1Run.setFontSize(10);
					enclosure1Run.addCarriageReturn();
				} else {
					faithfullyRun.addCarriageReturn();
					faithfullyRun.addCarriageReturn();
				}
				
				XWPFParagraph signature = document.createParagraph();
				signature.setAlignment(ParagraphAlignment.RIGHT);
				signature.setSpacingAfter(0); signature.setSpacingAfterLines(0);
				
				XWPFRun nameRun = signature.createRun();
				nameRun.setText("B Nanda Kumar");
				nameRun.setFontFamily("Calibri");nameRun.setColor(fontColour); nameRun.setFontSize(10);
				
				XWPFRun signatureRun = signature.createRun();
				signatureRun.setText("Assistant Legal Adviser & In-Charge");
				signatureRun.setFontFamily("Calibri");signatureRun.setColor(fontColour); signatureRun.setFontSize(10);
				
				XWPFParagraph copyto = document.createParagraph();
				copyto.setAlignment(ParagraphAlignment.LEFT);
				copyto.setSpacingAfter(0);
				
				XWPFRun copytoRun = copyto.createRun();
				copytoRun.setText("Copy to:-");
				copytoRun.setFontFamily("Calibri");copytoRun.setColor(fontColour); copytoRun.setFontSize(10);
				copytoRun.setUnderline(UnderlinePatterns.SINGLE);
				
				XWPFParagraph counselDetails = document.createParagraph();
				counselDetails.setAlignment(ParagraphAlignment.BOTH);
//				counselDetails.setSpacingLineRule(LineSpacingRule.AT_LEAST);
				counselDetails.setSpacingAfterLines(0);
				counselDetails.setSpacingAfter(0);
				
				XWPFRun counselNameRun = counselDetails.createRun();
				counselNameRun.setText((registerRequestVO.getCourtType() == 1 ? "1. " : "") +counselResponseVO.getName());
				counselNameRun.setFontFamily("Calibri");counselNameRun.setColor(fontColour); counselNameRun.setFontSize(10);
				counselNameRun.addCarriageReturn();
				
				XWPFRun counselDesignationRun = counselDetails.createRun();
				counselDesignationRun.setText(counselResponseVO.getCounselType());
				counselDesignationRun.setFontFamily("Calibri");counselDesignationRun.setColor(fontColour); counselDesignationRun.setFontSize(10);
				counselDesignationRun.addCarriageReturn();
				
				XWPFRun counselAddressRun = counselDetails.createRun();
				counselAddressRun.setText(counselResponseVO.getAddress().trim()+"; Mobile: "+counselResponseVO.getMobileNumber().trim()+"; Email: "+counselResponseVO.getEmailID().trim()+(counselResponseVO.getTelephoneNumber()!= null ? "; Telephone:"+counselResponseVO.getTelephoneNumber()+" - ": " - "));
				counselAddressRun.setFontFamily("Calibri");counselAddressRun.setColor(fontColour); counselAddressRun.setFontSize(10);
				
				XWPFRun counselpara1Run = counselDetails.createRun();
				counselpara1Run.setFontFamily("Calibri");counselpara1Run.setColor(fontColour); counselpara1Run.setFontSize(10);
				
				if(registerRequestVO.getCourtType() == 1) {
					if(registerRequestVO.getCounselOnRecordID() != 0) {
						counselpara1Run.setText("He is requested to do the needful in the above-mentioned case to protect the interest of Union of India.");
						counselpara1Run.addCarriageReturn();
						counselpara1Run.addCarriageReturn();
					} else {
						counselpara1Run.setText("(a) On receipt of this Nomination letter you are requested to: ");
					}
				} else {
					
					if(registerRequestVO.getCounselOnRecordID() != 0) {
						counselpara1Run.setText("He is requested to do the needful in the above-mentioned case to protect the interest of Union of India.");
						counselpara1Run.addCarriageReturn();
						counselpara1Run.addCarriageReturn();
					} else {
						counselpara1Run.setText("(i). The engagement is governed by the "+(counselResponseVO.getAbbrevation().equalsIgnoreCase("ACGSC") ? "O.M No.26(2)" : "O.M No.26(1)") + ")/1999 -Judl. Dated 24.09.1999 r/w O.M. No.26(1)/2014/Judl. dated 01.01.2015 issued by the Ministry of Law & Justice, Department of Legal Affairs, Shastri Bhavan, New Delhi- 110 001.");
						counselpara1Run.addCarriageReturn();
						counselpara1Run.addCarriageReturn();
					}
				}
				
				if(registerRequestVO.getCounselOnRecordID() == 0) {
				
				XWPFRun counselpara2Run = counselDetails.createRun();
				counselpara2Run.setFontFamily("Calibri");counselpara2Run.setColor(fontColour); counselpara2Run.setFontSize(10);
				
				if(registerRequestVO.getCourtType() == 1) {
					counselpara2Run.setText("(i). File your memo of appearance immediately and also to ensure that your name is shown in the cause list whenever the case is listed ");
					counselpara2Run.setUnderline(UnderlinePatterns.SINGLE);
				} else {
					counselpara2Run.setText("(ii). You are requested to file your memo of appearance immediately on receipt of this nomination letter under intimation to this Branch Secretariat with a copy to the concerned department /Ministry, failing which the case will be reallocated to any other Counsel without any further notice.");
					counselpara2Run.addCarriageReturn();
					counselpara2Run.addCarriageReturn();
				}
				
				XWPFParagraph counselDetails1 = document.createParagraph();
				counselDetails1.setAlignment(ParagraphAlignment.BOTH);
				counselDetails1.setSpacingLineRule(LineSpacingRule.AT_LEAST);
				counselDetails1.setSpacingAfterLines(0);
				counselDetails1.setSpacingAfter(0);
				
				XWPFRun counselpara3Run = counselDetails.createRun();
				counselpara3Run.setFontFamily("Calibri");counselpara3Run.setColor(fontColour); counselpara3Run.setFontSize(10);
				
				if(registerRequestVO.getCourtType() == 1) {
					counselpara3Run.setText("under intimation to this Branch Secretariat quoting ");
				} else {
					counselpara3Run.setText("(iii). You are also requested to contact and make correspondence with the department directly and take follow up action in the matter. Further, you are requested to intimate the Department, the progress of the case regularly including obtaining and forwarding certified copy of the order/judgment to the concerned department wherever necessary.");
					counselpara3Run.addCarriageReturn();
					counselpara3Run.addCarriageReturn();
					counselpara3Run.addCarriageReturn();
				}
				
				XWPFRun counselpara4Run = counselDetails.createRun();
				counselpara4Run.setFontFamily("Calibri");counselpara4Run.setColor(fontColour); counselpara4Run.setFontSize(10);
				
				if(registerRequestVO.getCourtType() == 1) {
					counselpara4Run.setText(fileNumber);
					counselpara4Run.setUnderline(UnderlinePatterns.SINGLE);
				}
				
				if(registerRequestVO.getCourtType() == 1) {
					XWPFRun counselpara5Run = counselDetails.createRun();
					counselpara5Run.setFontFamily("Calibri");counselpara5Run.setColor(fontColour); counselpara5Run.setFontSize(10);
					counselpara5Run.setText(" with a copy of the concerned department/ Ministry, failing which the case will be reallocated to any other Counsel without any further notice.");
					counselpara5Run.addCarriageReturn();
					
					XWPFRun counselpara6Run = counselDetails1.createRun();
					counselpara6Run.setFontFamily("Calibri");counselpara6Run.setColor(fontColour); counselpara6Run.setFontSize(10);
					counselpara6Run.setUnderline(UnderlinePatterns.SINGLE);
					
					if(registerRequestVO.getCaseNumber().equalsIgnoreCase("0")) {
					counselpara6Run.setText("(ii). Intimate the "+caseVO.getAbbrevation()+" No. /"+ registerRequestVO.getCaseYear()+" allotted to the FR No: "+"/"+ registerRequestVO.getFrYear()+" to this Branch Secretariat and to the concerned department/ Ministry to update our records and also for timely process of your Fee Bill.");
					counselpara6Run.addCarriageReturn();
					counselpara6Run.addCarriageReturn();
					} else {
						counselpara6Run.setText("(ii). Contact and make correspondence with the department directly and take follow up action in the matter. Further, you are requested to intimate the department, the progress of the case regularly including obtaining and forwarding certified copy of the order/Judgment to the concerned department whenever necessary.");
						counselpara6Run.addCarriageReturn();
						counselpara6Run.addCarriageReturn();
					}
					
					XWPFRun counselpara7Run = counselDetails1.createRun();
					counselpara7Run.setFontFamily("Calibri");counselpara7Run.setColor(fontColour); counselpara7Run.setFontSize(10);
					
					if(registerRequestVO.getCaseNumber().equalsIgnoreCase("0")) {
						counselpara7Run.setText("(iii). Contact and make correspondence with the department directly and take follow up action in the matter. Further, you are requested to intimate the department, the progress of the case regularly including obtaining and forwarding certified copy of the order/Judgment to the concerned department whenever necessary.");
						counselpara7Run.addCarriageReturn();
						counselpara7Run.addCarriageReturn();
						counselpara7Run.setUnderline(UnderlinePatterns.SINGLE);
						}
					
					XWPFRun counselpara8Run = counselDetails1.createRun();
					counselpara8Run.setFontFamily("Calibri");counselpara8Run.setColor(fontColour); counselpara8Run.setFontSize(10);
					
					counselpara8Run.setText("(b). Your engagement is governed by the O.M No. 26(2)/1999-Judl. Dated:24.09.1999 r/w O.M No. 26(1)/2014/Judl. Dated 01.10.2015 issued by the Ministry of Law & Justice, Department of Legal Affairs, Shastri Bhavan, New Delhi-110 001.");
					counselpara8Run.addCarriageReturn();
					counselpara8Run.addCarriageReturn();
					
					XWPFRun counselpara9Run = counselDetails1.createRun();
					counselpara9Run.setFontFamily("Calibri");counselpara9Run.setColor(fontColour); counselpara9Run.setFontSize(10);
					counselpara9Run.setText("2. Copy to the Additional SG, Hon`ble High Court of Karnataka, Bangalore- For kind information.");
					counselpara9Run.addCarriageReturn();
					counselpara9Run.addCarriageReturn();
				}
				
				} else {
					
					XWPFParagraph counselOnRecordDetails = document.createParagraph();
					counselOnRecordDetails.setAlignment(ParagraphAlignment.BOTH);
					counselOnRecordDetails.setSpacingAfterLines(0);
					counselOnRecordDetails.setSpacingAfter(0);
					
					XWPFRun counselOnRecordNameRun = counselDetails.createRun();
					counselOnRecordNameRun.setText("2. " +counselOnRecordResponseVO.getName());
					counselOnRecordNameRun.setFontFamily("Calibri");counselOnRecordNameRun.setColor(fontColour); counselOnRecordNameRun.setFontSize(10);
					counselOnRecordNameRun.addCarriageReturn();
					
					XWPFRun counselOnRecordDesignationRun = counselDetails.createRun();
					counselOnRecordDesignationRun.setText(counselOnRecordResponseVO.getCounselType());
					counselOnRecordDesignationRun.setFontFamily("Calibri");counselOnRecordDesignationRun.setColor(fontColour); counselOnRecordDesignationRun.setFontSize(10);
					counselOnRecordDesignationRun.addCarriageReturn();
					
					XWPFRun counselOnRecordAddressRun = counselDetails.createRun();
					counselOnRecordAddressRun.setText(counselOnRecordResponseVO.getAddress().trim()+"; Mobile: "+counselOnRecordResponseVO.getMobileNumber().trim()+"; Email: "+counselOnRecordResponseVO.getEmailID().trim()+(counselOnRecordResponseVO.getTelephoneNumber() != null ? "; Telephone:"+counselOnRecordResponseVO.getTelephoneNumber()+" - ": " - ") + (registerRequestVO.getCaseNumber().equalsIgnoreCase("0") ? "(i)" : "") + " He is requested to assist the Ld. Addl.SG as an advocate on record in the above-mentioned subject.");
					counselOnRecordAddressRun.setFontFamily("Calibri");counselOnRecordAddressRun.setColor(fontColour); counselOnRecordAddressRun.setFontSize(10);
					
					
					if(registerRequestVO.getCaseNumber().equalsIgnoreCase("0")) {
						XWPFRun counselpara3Run = counselDetails.createRun();
						counselpara3Run.setText("(ii). He is also requested to intimate the "+ caseVO.getAbbrevation().trim() +" No. /"+ registerRequestVO.getCaseYear()+" allotted to the FR No. "+ registerRequestVO.getFrNumber()+"/" + registerRequestVO.getFrYear()+" to this Branch Secretariat and to the concerned department/ Ministry to update our records and also for timely process of your Fee Bill. ");
						counselpara3Run.setFontFamily("Calibri");counselpara3Run.setColor(fontColour); counselpara3Run.setFontSize(10);
						counselpara3Run.addCarriageReturn();	
						counselpara3Run.addCarriageReturn();
						counselpara3Run.addCarriageReturn();
					} else {
						counselOnRecordAddressRun.addCarriageReturn();
						counselOnRecordAddressRun.addCarriageReturn();
					}
				}
				
				XWPFParagraph signature1 = document.createParagraph();
				signature1.setAlignment(ParagraphAlignment.RIGHT);
				signature1.setSpacingAfter(0);signature1.setSpacingAfterLines(0);
				
				XWPFRun name1Run = signature1.createRun();
				name1Run.setText("B Nanda Kumar");
				name1Run.setFontFamily("Calibri");name1Run.setColor(fontColour); name1Run.setFontSize(10);
				
				XWPFRun signature1Run = signature1.createRun();
				signature1Run.setText("Assistant Legal Adviser & In-Charge");
				signature1Run.setFontFamily("Calibri");signature1Run.setColor(fontColour); signature1Run.setFontSize(10);
				
				FileOutputStream out = new FileOutputStream(drivename + "F-"+registerRequestVO.getFileNumber()+ ".docx");
				document.write(out);
				out.close();
				document.close();
				
				FileInputStream file = new FileInputStream(new File("D:\\Register\\High Court & CAT Nomination Register.xlsx"));

	            XSSFWorkbook workbook = new XSSFWorkbook(file);
	            // change every year
	            XSSFSheet sheet = (registerRequestVO.getCourtType() == 1 ? workbook.getSheetAt(0) : workbook.getSheetAt(9));
	            
	            Row row = sheet.createRow(sheet.getLastRowNum()+1);
	            
	            int columnCount = 0;
	            
	            Cell cell = row.createCell(columnCount);
	            cell.setCellValue((String) registerRequestVO.getFileNumber());
	            
	            Cell cell1 = row.createCell(++columnCount);
	            cell1.setCellValue((String) registerRequestVO.getFiledBy());
	            
	            Cell cell2 = row.createCell(++columnCount);
	            cell2.setCellValue((String) ((registerRequestVO.getCaseTypeID() == 68 || registerRequestVO.getCaseTypeID() == 74) ? registerRequestVO.getCaseNumber() : caseVO.getAbbrevation()+" No: "+registerRequestVO.getCaseNumber()));
	            
	            if(registerRequestVO.getCourtType() == 1) {
	            	
	            	Cell cell7 = row.createCell(++columnCount);
		            cell7.setCellValue((String) registerRequestVO.getFrNumber());
		            
	            }
	            
	            Cell cell3 = row.createCell(++columnCount);
	            cell3.setCellValue(registerRequestVO.getCaseYear());
	            
	            Cell cell4 = row.createCell(++columnCount);
	            cell4.setCellValue(registerRequestVO.getMinistryID());
	            
	            Cell cell5 = row.createCell(++columnCount);
	            cell5.setCellValue((String) dateformatter(currentdate.toString()));
	            
	            Cell cell6 = row.createCell(++columnCount);
	            if(registerRequestVO.getCounselID() != 0) {
		            cell6.setCellValue((String) counselResponseVO.getName());
		            } else {
		            	cell6.setCellValue((String) "Return");
		            }
	            
	            if(registerRequestVO.getCounselOnRecordID() != 0) {
	            	Cell cell9 = row.createCell(++columnCount);
		            cell9.setCellValue((String) counselOnRecordResponseVO.getName());	
	            }
	            
	            file.close();
	            
	            FileOutputStream outputStream = new FileOutputStream("D:\\Register\\High Court & CAT Nomination Register.xlsx");
	            workbook.write(outputStream);
	            workbook.close();
	            outputStream.close();
				
				responsevo.setResult("Success");
				responsevo.setMessage("Nomination Letter created Successfully"); 
				} else {
					
					FileInputStream file = new FileInputStream(new File("D:\\Register\\High Court & CAT Nomination Register.xlsx"));

		            XSSFWorkbook workbook = new XSSFWorkbook(file);
		            // change every year
		            XSSFSheet sheet = (registerRequestVO.getCourtType() == 1 ? workbook.getSheetAt(0) : workbook.getSheetAt(9));
		            
		            int rowCount = sheet.getLastRowNum();
		            Row row = sheet.createRow(++rowCount);
		            
		            int columnCount = 0;
		            
		            Cell cell = row.createCell(columnCount);
		            cell.setCellValue((String) registerRequestVO.getFileNumber());
		            
		            Cell cell1 = row.createCell(++columnCount);
		            cell1.setCellValue((String) registerRequestVO.getFiledBy());
		            
		            Cell cell2 = row.createCell(++columnCount);
		            
		            CaseVO caseVO = fetchCaseDetails(registerRequestVO.getCaseTypeID());
		            cell2.setCellValue((String) ((registerRequestVO.getCaseTypeID() == 68 || registerRequestVO.getCaseTypeID() == 74) ? registerRequestVO.getCaseNumber() : caseVO.getAbbrevation()+" No: "+registerRequestVO.getCaseNumber()));
		            
		            if(registerRequestVO.getCourtType() == 1) {
		            	
		            	Cell cell7 = row.createCell(++columnCount);
			            cell7.setCellValue((String) registerRequestVO.getFrNumber());
			            
		            }
		            
		            Cell cell3 = row.createCell(++columnCount);
		            cell3.setCellValue(registerRequestVO.getCaseYear());
		            
		            Cell cell4 = row.createCell(++columnCount);
		            cell4.setCellValue(registerRequestVO.getMinistryID());
		            
		            Cell cell5 = row.createCell(++columnCount);
		            cell5.setCellValue((String) dateformatter(currentdate.toString()));
		            
		            Cell cell6 = row.createCell(++columnCount);
		            
		            if(registerRequestVO.getCounselID() != 0) {
		            CounselResponseVO counselResponseVO =  fetchCounselDetails(registerRequestVO.getCounselID());
		            cell6.setCellValue((String) counselResponseVO.getName());
		            } else {
		            	cell6.setCellValue((String) "Return");
		            }
		            
		            if(registerRequestVO.getCounselOnRecordID() != 0) {
		            	Cell cell9 = row.createCell(++columnCount);
			            
			            CounselResponseVO counselOnRecordResponseVO =  fetchCounselDetails(registerRequestVO.getCounselOnRecordID());
			            cell9.setCellValue((String) counselOnRecordResponseVO.getName());	
		            }
		            
		            file.close();
		            
		            FileOutputStream outputStream = new FileOutputStream("D:\\Register\\High Court & CAT Nomination Register.xlsx");
		            workbook.write(outputStream);
		            workbook.close();
		            outputStream.close();
					
					responsevo.setResult("Success");
					responsevo.setMessage("Register Updated Successfully");
				}
//			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			responsevo.setMessage("INTERNAL SERVER ERROR");
			responsevo.setResult("Failure");
			e.printStackTrace();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			responsevo.setMessage("SERVER ERROR");
			responsevo.setResult("Failure");
		} finally {
			pstmt.close();
			con.close();
		}
		return responsevo;
	}
	
	public ResponseVO editNominationDetails(RegisterRequestVO registerRequestVO, long id) throws SQLException {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResponseVO responsevo = new ResponseVO();

		try {
			con = getConnection();
			String query = "INSERT INTO <register> (CaseNumber, CaseYear, <change1> CounselID, ModifiedDate, RenominatedCounselID, RenominatedDate, Remarks) VALUES (?, ?, <change2> ?, NOW(), ?, NOW(), ?)";
			
			query = query.replaceAll("<register>", registerRequestVO.getCourtType() == 1 ? "highcourtregister" : "cattregister"); 
			query = query.replaceAll("<change1>", registerRequestVO.getCourtType() == 1 ? "FRNumber, FRYear," : "");
			query = query.replaceAll("<change2>", registerRequestVO.getCourtType() == 1 ? "?, ?," : "");
			
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, registerRequestVO.getCaseNumber());
			pstmt.setInt(2, registerRequestVO.getCaseYear());
			
			if(registerRequestVO.getCourtType() == 1) {
			
				pstmt.setString(3, registerRequestVO.getFrNumber());
				pstmt.setInt(4, registerRequestVO.getFrYear());
				pstmt.setInt(5, registerRequestVO.getCounselID());
				pstmt.setInt(6, registerRequestVO.getRenominatedCounselID());
				pstmt.setString(7, registerRequestVO.getRemarks());
			
			} else {
				
				pstmt.setInt(3, registerRequestVO.getCounselID());
				pstmt.setInt(4, registerRequestVO.getRenominatedCounselID());
				pstmt.setString(5, registerRequestVO.getRemarks());
				
			}
			
			if (pstmt.executeUpdate() > 0) {
				responsevo.setMessage("Register Updated Successfully");
				responsevo.setResult("Success");
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			responsevo.setMessage("INTERNAL SERVER ERROR");
			responsevo.setResult("Failure");
			e.printStackTrace();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			responsevo.setMessage("SERVER ERROR");
			responsevo.setResult("Failure");
		} finally {
			pstmt.close();
			con.close();
		}
		return responsevo;
	}
	
	public static String dateformatter(String date) throws ParseException {
		
		SimpleDateFormat DBformat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat clientFormat = new SimpleDateFormat("dd.MM.yyyy");
		return clientFormat.format(DBformat.parse(date));
	}
	
	public static CounselResponseVO fetchCounselDetails(int id) throws SQLException, Exception {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CounselResponseVO counselResponseVO = new CounselResponseVO();
		
		try {
			con = getConnection();
			
			pstmt = con.prepareStatement("SELECT * FROM counseldetails AS cd LEFT JOIN counseltype AS ct ON ct.CounselTypeID = cd.CounselTypeID WHERE CounselID = " +id);
			rs = pstmt.executeQuery();
			if(rs.next()) {

				counselResponseVO.setTitle(rs.getString("Title"));
				counselResponseVO.setName(rs.getString("Name"));
				counselResponseVO.setCounselType(rs.getString("CounselType"));
				counselResponseVO.setAbbrevation(rs.getString("Abbrevation"));
				counselResponseVO.setAddress(rs.getString("Address"));
				counselResponseVO.setMobileNumber(rs.getString("MobileNumber"));
				counselResponseVO.setEmailID(rs.getString("EmailID"));
				counselResponseVO.setTelephoneNumber(rs.getString("TelephoneNumber"));
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			pstmt.close();
			con.close();
		}
				
		return counselResponseVO;
		
	}
	
	public static CaseVO fetchCaseDetails(int id) throws SQLException, Exception {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CaseVO caseVO = new CaseVO();
		
		try {
			con = getConnection();
			
			pstmt = con.prepareStatement("SELECT * FROM casetype WHERE CaseID = " +id);
			rs = pstmt.executeQuery();
			if(rs.next()) {

				caseVO.setCaseName(rs.getString("CaseName"));
				caseVO.setAbbrevation(rs.getString("Abbrevation"));
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			pstmt.close();
			con.close();
		}
				
		return caseVO;
		
	}


}
