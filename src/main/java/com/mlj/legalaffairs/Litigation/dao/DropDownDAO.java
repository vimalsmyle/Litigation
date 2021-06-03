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
			PreparedStatement pstmt = con.prepareStatement("SELECT CaseID, CaseName FROM caseType WHERE CourtTypeID= ?");
			pstmt.setInt(1, courtID);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				cases.put(rs.getInt("CaseID"), rs.getString("CaseName"));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		return cases;
	}

	public HashMap<String, String> getallhouses(int blockID, int roleid, String id) {
		// TODO Auto-generated method stub
		HashMap<String, String> houses = new HashMap<String, String>();
		
		Connection con = null;
		try {
			con = getConnection();
			String query = "SELECT CustomerUniqueID, HouseNumber from customerdetails WHERE BlockID = ? <change>";
			PreparedStatement pstmt = con.prepareStatement(query.replaceAll("<change>", (roleid == 1 || roleid == 2 || roleid == 4 || roleid == 5) ? "ORDER BY CustomerID ASC" : (roleid == 3) ? " AND CustomerUniqueID = '"+id+"'" :""));
			pstmt.setInt(1, blockID);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				houses.put(rs.getString("CustomerUniqueID"), rs.getString("HouseNumber"));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		return houses;
	}
	
}
