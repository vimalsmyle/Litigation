/**
 * 
 */
package com.mlj.legalaffairs.Litigation.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import com.mlj.legalaffairs.Litigation.constants.DataBaseConstants;
import com.mlj.legalaffairs.Litigation.request.LoginVO;

/**
 * @author K VimaL Kumar
 *
 */
public class DropDownDAO {

	static LoginVO loginvo = new LoginVO();

	public static Connection getConnection() throws ClassNotFoundException,
			SQLException {
		Connection connection = null;
		Class.forName(DataBaseConstants.DRIVER_CLASS);
		connection = DriverManager.getConnection(DataBaseConstants.DRIVER_URL,
				DataBaseConstants.USER_NAME, DataBaseConstants.PASSWORD);
		return connection;
	}
	
	public HashMap<Integer, String> getallcourts() {
		// TODO Auto-generated method stub
		
		HashMap<Integer, String> courts = new HashMap<Integer, String>(); 
		Connection con = null;
		try {
			con = getConnection();
			
			PreparedStatement pstmt = con.prepareStatement("SELECT CourtID, Abbrevation FROM courtdetails");
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				courts.put(rs.getInt("CourtID"), rs.getString("Abbrevation"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return courts;
	}

	public HashMap<Integer, String> getallcases(int courtID) {
		// TODO Auto-generated method stub

		HashMap<Integer, String> cases = new HashMap<Integer, String>(); 
		Connection con = null;

		try {
			con = getConnection();
			PreparedStatement pstmt = con.prepareStatement("SELECT CaseID, CaseName FROM caseType WHERE CourtTypeID= "+courtID);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				cases.put(rs.getInt("CaseID"), rs.getString("CaseName"));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		return cases;
	}

	public HashMap<Integer, String> getAllMinistries() {
		// TODO Auto-generated method stub

		HashMap<Integer, String> ministries = new HashMap<Integer, String>(); 
		Connection con = null;
		try {
			con = getConnection();
			
			PreparedStatement pstmt = con.prepareStatement("SELECT MinistryID, MinistryName FROM ministrydetails");
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				ministries.put(rs.getInt("MinistryID"), rs.getString("MinistryName"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ministries;
		
	}

	public HashMap<Integer, String> getAllDepartments(int ministryID) {
		// TODO Auto-generated method stub

		HashMap<Integer, String> departments = new HashMap<Integer, String>(); 
		Connection con = null;

		try {
			con = getConnection();
			PreparedStatement pstmt = con.prepareStatement("SELECT DepartmentID, DepartmentName FROM departmentdetails WHERE MinistryID= "+ (ministryID!= 0 ? ministryID : 0));
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				departments.put(rs.getInt("DepartmentID"), rs.getString("DepartmentName"));
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		return departments;
		
	}
	
	public HashMap<Integer, String> getAllCounsels(int courtID) {
		// TODO Auto-generated method stub

		HashMap<Integer, String> counsels = new HashMap<Integer, String>(); 
		Connection con = null;

		try {
			con = getConnection();
			PreparedStatement pstmt = con.prepareStatement("SELECT CounselID, Name FROM counseldetails WHERE CourtID= "+courtID+" ORDER BY Name ASC");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				counsels.put(rs.getInt("CounselID"), rs.getString("Name"));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		return counsels;
	}
	
}
