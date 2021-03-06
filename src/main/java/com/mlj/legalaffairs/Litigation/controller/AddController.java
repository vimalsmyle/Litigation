/**
 * 
 */
package com.mlj.legalaffairs.Litigation.controller;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mlj.legalaffairs.Litigation.bo.AddBO;
import com.mlj.legalaffairs.Litigation.dao.AddDAO;
import com.mlj.legalaffairs.Litigation.exceptions.BusinessException;
import com.mlj.legalaffairs.Litigation.request.CounselRequestVO;
import com.mlj.legalaffairs.Litigation.request.DepartmentRequestVO;
import com.mlj.legalaffairs.Litigation.request.MinistryRequestVO;
import com.mlj.legalaffairs.Litigation.request.RegisterRequestVO;
import com.mlj.legalaffairs.Litigation.response.CounselResponseVO;
import com.mlj.legalaffairs.Litigation.response.DepartmentResponseVO;
import com.mlj.legalaffairs.Litigation.response.MinistryResponseVO;
import com.mlj.legalaffairs.Litigation.response.RegisterResponseVO;
import com.mlj.legalaffairs.Litigation.response.ResponseVO;

/**
 * @author VmL
 *
 */
@Controller
public class AddController {
	
	AddDAO adddao = new AddDAO();
	AddBO addbo = new AddBO();
	
	/* Counsel */
	
	@RequestMapping(value = "/ministry", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	MinistryResponseVO ministrydetails() throws SQLException {

		MinistryResponseVO ministryResponsevo = new MinistryResponseVO();

		ministryResponsevo.setData(adddao.getMinistrydetails());

		return ministryResponsevo;
	}

	@RequestMapping(value = "/ministry/add", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody
	ResponseVO addministry(@RequestBody MinistryRequestVO ministryRequestVO) throws ClassNotFoundException,
			SQLException, BusinessException {
		ResponseVO responsevo = new ResponseVO();
		try {
			 responsevo = addbo.addMinistry(ministryRequestVO);
			
		} catch (BusinessException e) {
			responsevo.setResult("Failure");
			responsevo.setMessage(e.getMessage());
		}

		return responsevo;
	}
	
	@RequestMapping(value = "/ministry/edit/{ministryID}", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody
	ResponseVO editministry(@RequestBody MinistryRequestVO ministryRequestVO, @PathVariable("ministryID") int ministryID) throws ClassNotFoundException,
			SQLException, BusinessException {

		ministryRequestVO.setMinistryID(ministryID);
		ResponseVO responsevo = new ResponseVO();
		try {
			 responsevo = addbo.editMinistry(ministryRequestVO);
			
		} catch (BusinessException e) {
			responsevo.setResult("Failure");
			responsevo.setMessage(e.getMessage());
		}

		return responsevo;
	}
	
	/* Counsel */
	
	@RequestMapping(value = "/department", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody DepartmentResponseVO departmentdetails() throws SQLException {

		DepartmentResponseVO departmentresponsevo = new DepartmentResponseVO();

		departmentresponsevo.setData(adddao.getDepartmentdetails());

		return departmentresponsevo;
	}

	@RequestMapping(value = "/department/add", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody
	ResponseVO adddepartment(@RequestBody DepartmentRequestVO departmentRequestVO) throws ClassNotFoundException,
			SQLException, BusinessException {
		ResponseVO responsevo = new ResponseVO();
		try {
			 responsevo = addbo.addDepartment(departmentRequestVO);
			
		} catch (BusinessException e) {
			responsevo.setResult("Failure");
			responsevo.setMessage(e.getMessage());
		}

		return responsevo;
	}
	
	@RequestMapping(value = "/department/edit/{departmentID}", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody
	ResponseVO editdepartment(@RequestBody DepartmentRequestVO departmentRequestVO, @PathVariable("departmentID") int departmentID) throws ClassNotFoundException,
			SQLException, BusinessException {

		departmentRequestVO.setDepartmentID(departmentID);
		ResponseVO responsevo = new ResponseVO();
		try {
			 responsevo = addbo.editDepartment(departmentRequestVO);
			
		} catch (BusinessException e) {
			responsevo.setResult("Failure");
			responsevo.setMessage(e.getMessage());
		}

		return responsevo;
	}
	
	/* Counsel */
	
	@RequestMapping(value = "/counsel", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	CounselResponseVO counseldetails() throws SQLException {

		CounselResponseVO counselresponsevo = new CounselResponseVO();

		counselresponsevo.setData(adddao.getCounseldetails());

		return counselresponsevo;
	}

	@RequestMapping(value = "/counsel/add", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody
	ResponseVO addcounsel(@RequestBody CounselRequestVO counselvo) throws ClassNotFoundException,
			SQLException, BusinessException {
		ResponseVO responsevo = new ResponseVO();
		try {
			 responsevo = addbo.addcounsel(counselvo);
			
		} catch (BusinessException e) {
			responsevo.setResult("Failure");
			responsevo.setMessage(e.getMessage());
		}

		return responsevo;
	}
	
	@RequestMapping(value = "/counsel/edit/{counselID}", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody
	ResponseVO editcounsel(@RequestBody CounselRequestVO counselvo, @PathVariable("counselID") int counselID) throws ClassNotFoundException,
			SQLException, BusinessException {

		counselvo.setCounselID(counselID);
		ResponseVO responsevo = new ResponseVO();
		try {
			 responsevo = addbo.editcounsel(counselvo);
			
		} catch (BusinessException e) {
			responsevo.setResult("Failure");
			responsevo.setMessage(e.getMessage());
		}

		return responsevo;
	}
	
	@RequestMapping(value = "/nomination/{courttype}/{year}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody RegisterResponseVO HighCourtRegisterdetails(@PathVariable("courttype") int courtType, @PathVariable("year") int year) throws SQLException {

		RegisterResponseVO hcresponsevo = new RegisterResponseVO();
		
		hcresponsevo.setData(adddao.getNominationdetails(courtType, year));

		return hcresponsevo;
	}

	@RequestMapping(value = "/nomination/add", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody ResponseVO addNomination(@RequestBody RegisterRequestVO registerRequestVO) throws ClassNotFoundException,
			SQLException, BusinessException, IOException {
		ResponseVO responsevo = new ResponseVO();
		try {
			 responsevo = addbo.addNomination(registerRequestVO);
			
		} catch (Exception e) {
			responsevo.setResult("Failure");
			responsevo.setMessage(e.getMessage());
		}

		return responsevo;
	}
	
	@RequestMapping(value = "/nomination/edit/{id}", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody ResponseVO editNomination(@RequestBody RegisterRequestVO registerRequestVO, @PathVariable("id") long id) throws ClassNotFoundException,
			SQLException, BusinessException, IOException {
		ResponseVO responsevo = new ResponseVO();
		
		try {
			 responsevo = addbo.editNominationDetails(registerRequestVO, id);
			
		} catch (Exception e) {
			responsevo.setResult("Failure");
			responsevo.setMessage(e.getMessage());
		}

		return responsevo;
	}
	
}
