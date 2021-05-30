/**
 * 
 */
package com.mlj.legalaffairs.Litigation.dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Formatter;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import com.mlj.legalaffairs.Litigation.constants.DataBaseConstants;
import com.mlj.legalaffairs.Litigation.request.RegisterRequestVO;
import com.mlj.legalaffairs.Litigation.response.ResponseVO;
import com.mlj.legalaffairs.Litigation.response.CounselResponseVO;
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
connection = DriverManager.getConnection(DataBaseConstants.DRIVER_URL,
		DataBaseConstants.USER_NAME, DataBaseConstants.PASSWORD);
return connection;
}

	public List<CounselResponseVO> getCounseldetails() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<RegisterResponseVO> getHighCourtNominationdetails(int courtType, int year) throws SQLException {
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
			pstmt = con.prepareStatement(query.replaceAll("<fileYear>", (year != 0 ? "FileYear = "+ year : "")));
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
				
				PreparedStatement counsel = con.prepareStatement("SELECT Name FROM counseldetails WHERE CounselID = "+rs.getInt("CounselID"));
				ResultSet rs3 = counsel.executeQuery();
				
				if(rs3.next()) {
					registerResponseVO.setCounselName(rs3.getString("Name"));
				}
				
				registerResponseVO.setDate(dateformatter(rs.getString("Date")));
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
	
	public ResponseVO addNomination(RegisterRequestVO registerRequestVO) throws SQLException, IOException{
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		ResponseVO responsevo = new ResponseVO();
		LocalDate currentdate = LocalDate.now();
		String drivename = "D:/<folder>/Drafts/"+currentdate.getYear()+"/"+currentdate.getMonth()+"/";
		String fontColour = "000000";
		String fileNumber = "File No: "+registerRequestVO.getFileNumber()+"/"+currentdate.getYear()+"/LIT/"+(registerRequestVO.getCourtType() == 1 ? "HC" : "CAT");

		try {
			con = getConnection();
			String query = "INSERT INTO <register> (FileNumber, FileYear, FiledBy, CaseTypeID, CaseNumber, CaseYear, <change1> NumberOfCases, MinistryID, DepartmentID, CounselID, Date, Remarks) VALUES (?, ?, ?, ?, ?, ?, <change2> ?, ?, ?, ?, NOW(), ?)";
			query = query.replaceAll("<change1>", registerRequestVO.getCourtType() == 1 ? "FRNumber, FRYear," : "");
			query = query.replaceAll("<change2>", registerRequestVO.getCourtType() == 1 ? "?, ?," : "");
			query = query.replaceAll("<register>", registerRequestVO.getCourtType() == 1 ? "highcourtregister" : "cattregister"); 
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, registerRequestVO.getFileNumber());
			pstmt.setInt(2, registerRequestVO.getFileYear());
			pstmt.setString(3, registerRequestVO.getFiledBy());
			pstmt.setInt(4, registerRequestVO.getCaseTypeID());
			pstmt.setString(5, registerRequestVO.getCaseNumber());
			pstmt.setInt(6, registerRequestVO.getCaseYear());
			
			if(registerRequestVO.getCourtType() == 1) {
			
				pstmt.setString(7, registerRequestVO.getFrNumber());
				pstmt.setInt(8, registerRequestVO.getFrYear());
				pstmt.setInt(9, registerRequestVO.getNumberOfCases());
				pstmt.setInt(10, registerRequestVO.getMinistryID());
				pstmt.setInt(11, registerRequestVO.getDepartmentID());
				pstmt.setInt(12, registerRequestVO.getCounselID());
				pstmt.setString(13, registerRequestVO.getRemarks());
			
			} else {
				
				pstmt.setInt(7, registerRequestVO.getNumberOfCases());
				pstmt.setInt(8, registerRequestVO.getMinistryID());
				pstmt.setInt(9, registerRequestVO.getDepartmentID());
				pstmt.setInt(10, registerRequestVO.getCounselID());
				pstmt.setString(11, registerRequestVO.getRemarks());
				
			}
			
			if (pstmt.executeUpdate() > 0) {
				
				// create Nomination letter in doc format
				
				drivename = drivename.replaceAll("<folder>", registerRequestVO.getCourtType() == 1 ? "High Court Nominations" : "CAT Nominations");
				
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
				noteSheetTitleRun.addCarriageReturn();
				
				//https://www.baeldung.com/java-microsoft-word-with-apache-poi
				
//				https://www.javatpoint.com/apache-poi-word-paragraph
				
				XWPFParagraph noteSheetFileNumber = document.createParagraph();
				noteSheetFileNumber.setAlignment(ParagraphAlignment.RIGHT);
				
				XWPFRun noteSheetFileNumberRun = noteSheetFileNumber.createRun();
				noteSheetFileNumberRun.setText(fileNumber);
				noteSheetFileNumberRun.setColor(fontColour);
				noteSheetFileNumberRun.setFontFamily("Verdana");
				noteSheetFileNumberRun.setFontSize(11);
				noteSheetFileNumberRun.addCarriageReturn();
				
				XWPFParagraph noteSheetDate = document.createParagraph();
				noteSheetDate.setAlignment(ParagraphAlignment.RIGHT);
				
				XWPFRun noteSheetDateRun = noteSheetFileNumber.createRun();
				noteSheetDateRun.setText(currentdate.toString());
				noteSheetDateRun.setColor(fontColour);
				noteSheetDateRun.setFontFamily("Verdana");
				noteSheetDateRun.setFontSize(11);
				noteSheetDateRun.addCarriageReturn();
				
				XWPFParagraph noteSheetCaseNumber = document.createParagraph();
				noteSheetCaseNumber.setAlignment(ParagraphAlignment.CENTER);
				
				XWPFRun noteSheetCaseNumberRun = noteSheetFileNumber.createRun();
				noteSheetCaseNumberRun.setText(!registerRequestVO.getCaseNumber().equalsIgnoreCase("0") ? (registerRequestVO.getCaseType()+"No: "+registerRequestVO.getCaseNumber()+"/"+registerRequestVO.getCaseYear()) : (registerRequestVO.getCaseType()+"No: /"+registerRequestVO.getCaseYear()+"(F.R No: "+registerRequestVO.getFrNumber()+"/"+registerRequestVO.getFrYear()+")")); // set case number based on case type
				noteSheetCaseNumberRun.setColor(fontColour);
				noteSheetCaseNumberRun.setBold(true);
				noteSheetCaseNumberRun.setFontFamily("Verdana");
				noteSheetCaseNumberRun.setFontSize(12);
				noteSheetCaseNumberRun.addCarriageReturn();
				
				XWPFParagraph noteSheetSL = document.createParagraph();
				noteSheetSL.setAlignment(ParagraphAlignment.LEFT);
				
				XWPFRun noteSheetSLRun = noteSheetFileNumber.createRun();
				noteSheetSLRun.setText("SL.1 (R)"); 
				noteSheetSLRun.setColor(fontColour);
				noteSheetSLRun.setFontFamily("Verdana");
				noteSheetSLRun.setFontSize(11);
				noteSheetSLRun.addCarriageReturn();
				
				XWPFParagraph noteSheetPara1 = document.createParagraph();
				noteSheetPara1.setAlignment(ParagraphAlignment.DISTRIBUTE);
				
				XWPFRun noteSheetPara1Run = noteSheetFileNumber.createRun();
				noteSheetPara1Run.setText("We have received a copy of " +registerRequestVO.getCaseType() +"from the Office of the Additional Solicitor General of India, High Court of Karnataka for nomination of Central Government Counsel."); 
				noteSheetPara1Run.setColor(fontColour);
				noteSheetPara1Run.addCarriageReturn();
				noteSheetPara1Run.setFontFamily("Verdana");
				noteSheetPara1Run.setFontSize(10);
				noteSheetPara1Run.addCarriageReturn();
				
				XWPFParagraph noteSheetPara2 = document.createParagraph();
				noteSheetPara2.setAlignment(ParagraphAlignment.DISTRIBUTE);
				
				XWPFRun noteSheetPara2Run = noteSheetFileNumber.createRun();  // fetch counsel name, designation, court etc from db
				noteSheetPara2Run.setText("As directed in the FR, a fair nomination in favour of " +registerRequestVO.getCounselID() +"is put up for signature please."); 
				noteSheetPara2Run.setColor(fontColour);
				noteSheetPara2Run.setFontFamily("Verdana");
				noteSheetPara2Run.setFontSize(10);
				noteSheetPara2Run.addCarriageReturn();
				noteSheetPara2Run.addCarriageReturn();
				noteSheetPara2Run.addCarriageReturn();
				
				//continue from here
				
				FileOutputStream out = new FileOutputStream("F-"+registerRequestVO.getFileNumber()+".docx");
				document.write(out);
				out.close();
				document.close();
				
				responsevo.setResult("Success");
				responsevo.setMessage("Nomination created Successfully");
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
		SimpleDateFormat clientFormat = new SimpleDateFormat("dd-MM-yyyy");
		return clientFormat.format(DBformat.parse(date));
	}
}
