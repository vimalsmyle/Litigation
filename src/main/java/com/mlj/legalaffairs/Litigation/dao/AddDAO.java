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
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import com.mlj.legalaffairs.Litigation.constants.DataBaseConstants;
import com.mlj.legalaffairs.Litigation.constants.ExtraConstants;
import com.mlj.legalaffairs.Litigation.request.RegisterRequestVO;
import com.mlj.legalaffairs.Litigation.response.ResponseVO;
import com.mlj.legalaffairs.Litigation.response.CaseVO;
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
		connection = DriverManager.getConnection(DataBaseConstants.DRIVER_URL, DataBaseConstants.USER_NAME,
				DataBaseConstants.PASSWORD);
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
	
	public ResponseVO addNomination(RegisterRequestVO registerRequestVO) throws Exception{
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
			
//			if (pstmt.executeUpdate() > 0) {
				
				if(!registerRequestVO.isOld()) {
				// create Nomination letter in doc format
				
				CaseVO caseVO = new CaseVO();
				if(registerRequestVO.getCaseTypeID() != 78) {caseVO = fetchCaseDetails(registerRequestVO.getCaseTypeID());}
				
				String caseNumber = (registerRequestVO.getCourtType() == 1 ? (registerRequestVO.getCaseTypeID() != 78 ? (!registerRequestVO.getCaseNumber().equalsIgnoreCase("0") ? (caseVO.getAbbrevation()+" No: "+registerRequestVO.getCaseNumber()+"/"+registerRequestVO.getCaseYear()) : (registerRequestVO.getCaseType()+" No: /"+registerRequestVO.getCaseYear()+"(F.R No: "+registerRequestVO.getFrNumber()+"/"+registerRequestVO.getFrYear()+")")) : registerRequestVO.getCaseNumber() ): (caseVO.getAbbrevation() + " No: 170/" + registerRequestVO.getCaseNumber() + "/" + registerRequestVO.getCaseYear()));
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
				noteSheetPara1Run.setText("	We have received a copy of " + (registerRequestVO.getCaseTypeID() != 78 ? caseVO.getCaseName() : registerRequestVO.getCaseType()) +" from the " + (registerRequestVO.getCourtType() == 1 ? "Office of the Additional Solicitor General of India, High Court of Karnataka for nomination of Central Government Counsel." : "Hon`ble Central Administrative Tribunal, Bangalore") ); 
				noteSheetPara1Run.setColor(fontColour);
				noteSheetPara1Run.addCarriageReturn();
				noteSheetPara1Run.setFontFamily("Verdana");
				noteSheetPara1Run.setFontSize(10);
				
				XWPFParagraph noteSheetPara2 = document.createParagraph();
				noteSheetPara2.setAlignment(ParagraphAlignment.BOTH);
				
				XWPFRun noteSheetPara2Run = noteSheetPara2.createRun(); 
				
				CounselResponseVO counselResponseVO =  fetchCounselDetails(registerRequestVO.getCounselID());
				
				noteSheetPara2Run.setText("	As directed in the FR, a fair nomination in favour of "+ counselResponseVO.getTitle()+ ". " +counselResponseVO.getName()+", " + counselResponseVO.getCounselType() +" is put up for signature please."); 
				noteSheetPara2Run.setColor(fontColour);
				noteSheetPara2Run.setFontFamily("Verdana");
				noteSheetPara2Run.setFontSize(10);
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
				}
				
				XWPFParagraph logo = document.createParagraph();
				logo.setAlignment(ParagraphAlignment.CENTER);
				logo.setSpacingAfter(0);
				
				XWPFRun logoRun = logo.createRun(); ///Litigation/src/main/webapp/images/Emblem.png
				logoRun.addPicture(new FileInputStream(new File(ExtraConstants.EMBLEM)), XWPFDocument.PICTURE_TYPE_PNG, "Emblem", Units.toEMU(40), Units.toEMU(50));
				
				XWPFParagraph letterHead = document.createParagraph();
				letterHead.setAlignment(ParagraphAlignment.CENTER);
				letterHead.setSpacingAfterLines(1);
				
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
				phoneRun.addCarriageReturn();
				
				XWPFParagraph fileNumberDate = document.createParagraph();
				fileNumberDate.setAlignment(ParagraphAlignment.BOTH);
				fileNumberDate.setSpacingAfter(1);
				
				XWPFRun fileNumberDateRun = fileNumberDate.createRun();
				fileNumberDateRun.setText(fileNumber + "                                                                                                                                                                 Dated: " + dateformatter(currentdate.toString()));
				fileNumberDateRun.setFontFamily("Calibri"); fileNumberDateRun.setColor(fontColour); fileNumberDateRun.setFontSize(10);
				fileNumberDateRun.addCarriageReturn();
				
				XWPFParagraph to = document.createParagraph();
				to.setAlignment(ParagraphAlignment.LEFT);
				to.setSpacingAfter(1);
				
				XWPFRun toRun = to.createRun();
				toRun.setText("To");
				toRun.setFontFamily("Calibri");toRun.setColor(fontColour); toRun.setFontSize(10);
				toRun.addCarriageReturn();
				
				// add department address details after updating the database
				
				XWPFParagraph subject = document.createParagraph();
				subject.setAlignment(ParagraphAlignment.BOTH);
				subject.setSpacingAfter(1);
				
				XWPFRun subjectRun = subject.createRun();
				subjectRun.setText("Sub: Nomination of " + counselResponseVO.getAbbrevation() + " in case " + caseNumber + " filed by " +registerRequestVO.getFiledByTitle() + " "+registerRequestVO.getFiledBy() + " Vs " + registerRequestVO.getFiledAgainst() + " before the Hon’ble " + (registerRequestVO.getCourtType() == 1 ? "High Court of Karnataka" : "Central Administrative Tribunal") +", Bangalore.");
				subjectRun.setFontFamily("Calibri"); subjectRun.setColor(fontColour); subjectRun.setFontSize(10);
				subjectRun.addCarriageReturn();
				
				XWPFParagraph sir = document.createParagraph();
				sir.setAlignment(ParagraphAlignment.LEFT);
				sir.setSpacingAfter(1);
				
				XWPFRun sirRun = sir.createRun();
				sirRun.setText("Sir,");
				sirRun.setFontFamily("Calibri");sirRun.setColor(fontColour); sirRun.setFontSize(10);
				
				XWPFParagraph para1 = document.createParagraph();
				para1.setAlignment(ParagraphAlignment.BOTH);
				
				XWPFRun para1Run = para1.createRun();
				para1Run.setText("                A copy of the " + (registerRequestVO.getCaseTypeID() != 78 ? caseVO.getCaseName() : registerRequestVO.getCaseType()) + " received from the " + (registerRequestVO.getCourtType() == 1 ? "Office of the Additional Solicitor General of India, High Court of Karnataka, Bangalore is enclosed. Please note that "+ counselResponseVO.getTitle()+ ". " +counselResponseVO.getName()+", " + counselResponseVO.getCounselType() + " has been engaged in the above matter to represent "+ (!registerRequestVO.getRespondents().equalsIgnoreCase("0") ? "Respondent No(s): " + registerRequestVO.getRespondents() : "Union of India ") : "Hon`ble Central Administrative Tribunal, Bangalore is enclosed. Please note that " + counselResponseVO.getTitle()+ ". " +counselResponseVO.getName()+", " + counselResponseVO.getCounselType() + " has been engaged in the above matter to represent " + (!registerRequestVO.getRespondents().equalsIgnoreCase("0") ? "Respondent No(s): " + registerRequestVO.getRespondents() : "Union of India ") ) + (registerRequestVO.getCourtType() == 1 ? " before the Hon`ble High Court of Karnataka, Bangalore." : " before the Hon`ble Central Administrative Tribunal, Bangalore."));
				para1Run.setFontFamily("Calibri");para1Run.setColor(fontColour); para1Run.setFontSize(10);
				
				XWPFParagraph para2 = document.createParagraph();
				para2.setAlignment(ParagraphAlignment.BOTH);
				
				XWPFRun para2Run = para2.createRun();
				if(registerRequestVO.getCaseNumber().equalsIgnoreCase("0")) {
					para2Run.setText("                The said Counsel is being requested to intimate the " + caseVO.getAbbrevation() +" No. /"+ registerRequestVO.getCaseYear() +" allotted to the FR No."+ registerRequestVO.getFrNumber() +"/" + registerRequestVO.getFrYear() + " to the department/this Ministry as soon as possible.");
					para2Run.setUnderline(UnderlinePatterns.SINGLE);
				} else {
					para2Run.setText("                Therefore, the department is advised to contact the said Counsel (whose contact details are given below) with all relevant papers and files for preparation of the case on behalf of the department till the disposal of the case or expiry of his/her term of engagement, whichever is earlier. ");
				}
				para2Run.setFontFamily("Calibri");para2Run.setColor(fontColour); para2Run.setFontSize(10);
				
				XWPFParagraph para3 = document.createParagraph();
				para3.setAlignment(ParagraphAlignment.BOTH);
				
				XWPFRun para3Run = para3.createRun();
				if(!registerRequestVO.getCaseNumber().equalsIgnoreCase("0")) {
					para3Run.setText("                The department is further requested to make correspondence with the Counsel directly and to take follow up action in the matter.");
				} else {
					para3Run.setText("                Therefore, the department is advised to contact the said Counsel (whose contact details are given below) with all relevant papers and files for preparation of the case on behalf of the department till the disposal of the case or expiry of his/her term of engagement, whichever is earlier. ");
				}
				para3Run.setFontFamily("Calibri");para3Run.setColor(fontColour); para3Run.setFontSize(10);
				
				XWPFParagraph note = document.createParagraph();
				note.setAlignment(ParagraphAlignment.BOTH);
				
				XWPFRun note1Run = note.createRun();
				if(registerRequestVO.getCourtType() == 1) {
					if(counselResponseVO.getAbbrevation().equalsIgnoreCase("CGC")) {
						note1Run.setText("NOTE: 1. Appearance fees & drafting fees including conference fee are paid by this Ministry. However, Misc. expenses i.e. typing, photocopy, postage, Court Fee, Notarization etc., are to be borne by the concerned Department at their own satisfaction after taking into account the details of miscellaneous expenses actually incurred. ");
						note1Run.setUnderline(UnderlinePatterns.SINGLE);
					} else {
						note1Run.setText("NOTE: 1. The fee payable to Senior Panel Counsel in the case will be borne by the concerned department as per the Senior Panel Counsel, terms and conditions. ");
						note1Run.setUnderline(UnderlinePatterns.SINGLE);
					}
					
				} else {
					if(counselResponseVO.getAbbrevation().equalsIgnoreCase("ACGSC")) {
						note1Run.setText("NOTE: 1. The fee payable to the Additional Central Government Standing Counsel in the case will be borne by the concerned department as per O.M No. 26(2)/1999-Judl. Dated 24.09.1999 r/w O.M. No. 26(1)/2014/Judl. dated 01.01.2015. ");
						note1Run.setUnderline(UnderlinePatterns.SINGLE);
					} else {
						note1Run.setText("NOTE: 1. The fee payable to the Senior Panel Counsel in the case will be borne by the concerned department as per O.M No. 26(1)/1999-Judl. Dated 24.09.1999 r/w O.M. No. 26(1)/2014/Judl. dated 01.01.2015. ");
						note1Run.setUnderline(UnderlinePatterns.SINGLE);
					}
				}
				note1Run.setFontFamily("Calibri");note1Run.setColor(fontColour); note1Run.setFontSize(10);
				
				XWPFRun note2Run = note.createRun();
				note2Run.setText("2. "+ fileNumber +" may be quoted in all your future correspondences in this regard.");
				note2Run.setFontFamily("Calibri");note2Run.setColor(fontColour); note2Run.setFontSize(10);
				note2Run.setUnderline(UnderlinePatterns.SINGLE);
				
				XWPFParagraph faithfully = document.createParagraph();
				faithfully.setAlignment(ParagraphAlignment.RIGHT);
				
				XWPFRun faithfullyRun = faithfully.createRun();
				faithfullyRun.setText("Yours faithfully");
				faithfullyRun.setFontFamily("Calibri");faithfullyRun.setColor(fontColour); faithfullyRun.setFontSize(10);
				
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
				
				XWPFParagraph signature = document.createParagraph();
				signature.setAlignment(ParagraphAlignment.RIGHT);
				signature.setSpacingAfter(0);
				
				XWPFRun signatureRun = signature.createRun();
				signatureRun.setText("Assistant Legal Adviser & In-Charge");
				signatureRun.setFontFamily("Calibri");signatureRun.setColor(fontColour); signatureRun.setFontSize(10);
				
				XWPFParagraph copyto = document.createParagraph();
				copyto.setAlignment(ParagraphAlignment.LEFT);
				
				XWPFRun copytoRun = copyto.createRun();
				copytoRun.setText("Copy to:-");
				copytoRun.setFontFamily("Calibri");copytoRun.setColor(fontColour); copytoRun.setFontSize(10);
				copytoRun.setUnderline(UnderlinePatterns.SINGLE);
				
				//continue from here
				
				FileOutputStream out = new FileOutputStream(drivename + "F-"+registerRequestVO.getFileNumber()+".docx");
				document.write(out);
				out.close();
				document.close();
				
				responsevo.setResult("Success");
				responsevo.setMessage("Nomination Letter created Successfully"); 
				} else {
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
