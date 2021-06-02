/**
 * 
 */
package com.mlj.legalaffairs.Litigation.bo;

import java.sql.SQLException;

import javax.mail.MessagingException;

import com.mlj.legalaffairs.Litigation.dao.LoginDAO;
import com.mlj.legalaffairs.Litigation.exceptions.BusinessException;
import com.mlj.legalaffairs.Litigation.request.LoginVO;
import com.mlj.legalaffairs.Litigation.request.UserManagementRequestVO;
import com.mlj.legalaffairs.Litigation.response.ResponseVO;

/**
 * @author K VimaL Kumar
 * 
 */
public class LoginBO {

	public ResponseVO validateUser(LoginVO loginvo)
			throws ClassNotFoundException, BusinessException, SQLException {
		// TODO Auto-generated method stub

		ResponseVO responsevo = new ResponseVO();

		LoginDAO logindao = new LoginDAO();
		
		if (loginvo.getUserID().isEmpty()) {
			
			throw new BusinessException("ENTER USER ID");
		}	
		
		if (loginvo.getPassword().isEmpty()) {
		
			throw new BusinessException("ENTER PASSWORD");
		}
		
		responsevo = logindao.validateUser(loginvo);

		return responsevo;
	}

	public ResponseVO forgotpassword(String userid)
			throws ClassNotFoundException, BusinessException, SQLException, MessagingException {
		// TODO Auto-generated method stub

		ResponseVO responsevo = new ResponseVO();

		LoginDAO logindao = new LoginDAO();

		if (userid.isEmpty()) {

			throw new BusinessException("ENTER USER ID");
		}
		
		boolean checkuserid = logindao.checkuserid(userid);
		
		if(!checkuserid){
			throw new BusinessException("User has not yet Registered");
		}

		responsevo = logindao.forgotpassword(userid);

		return responsevo;
	}
	
	public ResponseVO changepassword(UserManagementRequestVO usermanagementvo) throws SQLException, BusinessException {
		// TODO Auto-generated method stub

		LoginDAO logindao = new LoginDAO();
		ResponseVO responsevo = new ResponseVO();
		
			if(usermanagementvo.getUserID().isEmpty() || usermanagementvo.getOldPassword().isEmpty() || usermanagementvo.getNewPassword().isEmpty()){
				throw new BusinessException("ALL FIELDS ARE MANDATORY");
			}			
		
		if(usermanagementvo.getOldPassword().contentEquals(usermanagementvo.getNewPassword())){
			throw new BusinessException("NEW PASSWORD CANNOT BE SAME AS OLD PASSWORD");
		}

		if(logindao.checkoldpassword(usermanagementvo)) {
			throw new BusinessException("INCORRECT OLD PASSWORD");
		}
		
		responsevo = logindao.changepassword(usermanagementvo); 

		return responsevo;
		
	}

}
