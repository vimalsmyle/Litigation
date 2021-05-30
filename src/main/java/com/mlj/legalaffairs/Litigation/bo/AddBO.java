/**
 * 
 */
package com.mlj.legalaffairs.Litigation.bo;

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

	public ResponseVO addcounsel(CounselRequestVO counselvo) throws BusinessException{
		// TODO Auto-generated method stub
		return null;
	}

	public ResponseVO editcounsel(CounselRequestVO counselvo) throws BusinessException{
		// TODO Auto-generated method stub
		return null;
	}

	public ResponseVO addNomination(RegisterRequestVO registerRequestVO)  throws Exception{
		// TODO Auto-generated method stub
		
		ResponseVO responsevo = adddao.addNomination(registerRequestVO);
		
		return responsevo;
	}

}
