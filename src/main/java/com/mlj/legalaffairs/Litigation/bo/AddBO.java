/**
 * 
 */
package com.mlj.legalaffairs.Litigation.bo;

import java.sql.SQLException;

import com.mlj.legalaffairs.Litigation.dao.AddDAO;
import com.mlj.legalaffairs.Litigation.exceptions.BusinessException;
import com.mlj.legalaffairs.Litigation.request.CounselRequestVO;
import com.mlj.legalaffairs.Litigation.request.RegisterRequestVO;
import com.mlj.legalaffairs.Litigation.response.ResponseVO;

/**
 * @author VmL
 *
 */
public class AddBO {
	
	AddDAO adddao = new AddDAO();

	public ResponseVO addcounsel(CounselRequestVO counselvo) throws BusinessException, SQLException{
		// TODO Auto-generated method stub
		return adddao.addCounsel(counselvo);
	}

	public ResponseVO editcounsel(CounselRequestVO counselvo) throws BusinessException, SQLException{
		// TODO Auto-generated method stub
		return adddao.editCounsel(counselvo);
	}

	public ResponseVO addNomination(RegisterRequestVO registerRequestVO)  throws Exception{
		// TODO Auto-generated method stub
		
		ResponseVO responsevo = adddao.addNomination(registerRequestVO);
		
		return responsevo;
	}

	public ResponseVO editNominationDetails(RegisterRequestVO registerRequestVO, long id) throws SQLException {
		// TODO Auto-generated method stub
		return adddao.editNominationDetails(registerRequestVO, id);
	}

}
