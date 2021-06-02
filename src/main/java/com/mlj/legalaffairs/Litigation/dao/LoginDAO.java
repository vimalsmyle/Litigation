/**
 * 
 */
package com.mlj.legalaffairs.Litigation.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.mail.MessagingException;

import com.mlj.legalaffairs.Litigation.constants.DataBaseConstants;
import com.mlj.legalaffairs.Litigation.constants.ExtraConstants;
import com.mlj.legalaffairs.Litigation.exceptions.BusinessException;
import com.mlj.legalaffairs.Litigation.request.LoginVO;
import com.mlj.legalaffairs.Litigation.request.MailRequestVO;
import com.mlj.legalaffairs.Litigation.request.UserManagementRequestVO;
import com.mlj.legalaffairs.Litigation.response.ResponseVO;
import com.mlj.legalaffairs.Litigation.response.UserDetails;
import com.mlj.legalaffairs.Litigation.utils.Encryptor;

/**
 * @author K VimaL Kumar 
 * 
 */
public class LoginDAO {

	public Connection con = null;

	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Connection connection = null;
		Class.forName(DataBaseConstants.DRIVER_CLASS);
		connection = DriverManager.getConnection(DataBaseConstants.DRIVER_URL, DataBaseConstants.USER_NAME,
				DataBaseConstants.PASSWORD);
		return connection;
	}

	public ResponseVO validateUser(LoginVO loginvo) throws ClassNotFoundException, BusinessException, SQLException {
		// TODO Auto-generated method stub

		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		ResponseVO responsevo = new ResponseVO();
		UserDetails userDetails = new UserDetails();

		try {
			con = getConnection();
			pstmt = con.prepareStatement("SELECT u.ID, u.UserID, u.UserName, u.UserPassword, u.RoleID, u.EmployeeID, ed.EmailID, ed.MobileNumber FROM USER AS u LEFT JOIN employeedetails AS ed ON u.EmployeeID = ed.EmployeeID WHERE u.UserID = ? AND u.UserPassword = ?");
			pstmt.setString(1, loginvo.getUserID());
			pstmt.setString(2, loginvo.getPassword());
			resultSet = pstmt.executeQuery();
			if (resultSet.next()) {

				if (loginvo.getUserID().equals(resultSet.getString("UserID"))) {

					if (loginvo.getPassword().equals(resultSet.getString("UserPassword"))) {

							userDetails.setRoleID(resultSet.getInt("RoleID"));
							userDetails.setEmail(resultSet.getString("EmailID"));
							userDetails.setMobileNumber(resultSet.getString("MobileNumber"));
							userDetails.setEmployeeID(resultSet.getInt("EmployeeID"));
							userDetails.setUserName(resultSet.getString("UserName"));
							userDetails.setID(resultSet.getInt("ID"));

							responsevo.setUserDetails(userDetails);
							responsevo.setResult("Success");
							responsevo.setMessage("Successfully Logged In");

					} else {
						responsevo.setResult("Failure");
						responsevo.setMessage("Incorrect Password");
					}

				} else {
					responsevo.setResult("Failure");
					responsevo.setMessage("Invalid UserID");
				}
			} else {
				responsevo.setResult("Failure");
				responsevo.setMessage("Invalid Credentials");
			}

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

			pstmt.close();
			resultSet.close();
			con.close();

		}

		return responsevo;
	}

	public ResponseVO forgotpassword(String userid) throws SQLException, MessagingException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;

		ResponseVO responsevo = new ResponseVO();

		try {
			con = getConnection();
			ExtraMethodsDAO maildao = new ExtraMethodsDAO();
			MailRequestVO mailrequestvo = new MailRequestVO();
			mailrequestvo.setFileLocation("NoAttachment");
			pstmt = con.prepareStatement("SELECT EmployeeID, UserPassword FROM user WHERE UserID = ?");
			pstmt.setString(1, userid);

			rs = pstmt.executeQuery();

			if (rs.next()) {

					if (rs.getInt("EmployeeID") != 0) {

						pstmt1 = con.prepareStatement("SELECT Email FROM employeedetails WHERE EmployeeID=?");
						pstmt1.setString(1, rs.getString("EmployeeID"));
						
						rs1 = pstmt1.executeQuery();

						if (rs1.next()) {
							mailrequestvo.setToEmail(rs1.getString("Email"));
						}
					}
					
				mailrequestvo.setUserID(userid);
				mailrequestvo.setUserPassword(Encryptor.decrypt(ExtraConstants.key1, ExtraConstants.key2, rs.getString("UserPassword")));
				responsevo.setResult(maildao.sendmail(mailrequestvo));

			} else {
				responsevo.setMessage("UserID is not Registered");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// pstmt.close();
			// ps.close();
			// rs.close();
			con.close();
		}

		return responsevo;

	}

	public ResponseVO changepassword(UserManagementRequestVO usermanagementvo) throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		ResponseVO responsevo = new ResponseVO();
		
		try {
			con = getConnection();

			pstmt = con.prepareStatement("UPDATE user SET UserPassword = ?, ModifiedDate = NOW() where UserID = ?");
			pstmt.setString(1, usermanagementvo.getNewPassword());
			pstmt.setString(2, usermanagementvo.getUserID().trim());

			if (pstmt.executeUpdate() > 0) {
				responsevo.setResult("Success");
				responsevo.setMessage("Password Updated Successfully");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			responsevo.setResult("Failure");
			responsevo.setMessage("Password Updation Failed");
		} finally {
			pstmt.close();
			con.close();
		}

		return responsevo;
	}

	public boolean checkuserid(String userid) throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean result = false;

		try {
			con = getConnection();

			pstmt = con.prepareStatement("SELECT UserID FROM user where UserID = ?");
			pstmt.setString(1, userid.trim());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = true;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pstmt.close();
			rs.close();
			con.close();
		}

		return result;
	}

	public boolean checkoldpassword(UserManagementRequestVO usermanagementvo) throws SQLException {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean result = false;

		try {
			con = getConnection();

			pstmt = con.prepareStatement("SELECT UserPassword FROM user where UserID = ?");
			pstmt.setString(1, usermanagementvo.getUserID());
			rs = pstmt.executeQuery();
			if (rs.next()) {

				result = usermanagementvo.getOldPassword().toLowerCase().equalsIgnoreCase(rs.getString("UserPassword").toLowerCase());
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pstmt.close();
			rs.close();
			con.close();
		}

		return result;

	}

}
