/**
 * 
 */
package com.mlj.legalaffairs.Litigation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mlj.legalaffairs.Litigation.dao.DropDownDAO;
import com.mlj.legalaffairs.Litigation.response.ResponseVO;

/**
 * @author K VimaL Kumar
 *
 */
@Controller
public class DropDownController {
	
	DropDownDAO dropdowndao = new DropDownDAO();
	
	@RequestMapping(value = "/courts",method = RequestMethod.GET, 
			produces="application/json")
	public @ResponseBody ResponseVO getAllCourts() {
		
		ResponseVO responsevo = new ResponseVO();
		responsevo.setDropDownCourts(dropdowndao.getallcourts());

		return responsevo;
	}
	
	@RequestMapping(value = "/cases/{courtID}",method = RequestMethod.GET, 
			produces="application/json")
	public @ResponseBody ResponseVO getAllCases(@PathVariable("courtID") int courtid) {
		
		ResponseVO responsevo = new ResponseVO();
		responsevo.setDropDownCases(dropdowndao.getallcases(courtid));
		
		return responsevo;
	}
	
	@RequestMapping(value = "/ministries",method = RequestMethod.GET, 
			produces="application/json")
	public @ResponseBody ResponseVO getAllMinistries() {
		
		ResponseVO responsevo = new ResponseVO();
		responsevo.setDropDownMinistries(dropdowndao.getAllMinistries());
		
		return responsevo;
	}
	
	@RequestMapping(value = "/departments/{ministryID}",method = RequestMethod.GET, 
			produces="application/json")
	public @ResponseBody ResponseVO getAllDepartments(@PathVariable("ministryID") int ministryID) {
		
		ResponseVO responsevo = new ResponseVO();
		responsevo.setDropDownDepartments(dropdowndao.getAllDepartments(ministryID));
		
		return responsevo;
	}
	
	@RequestMapping(value = "/counsels/{courtID}",method = RequestMethod.GET, 
			produces="application/json")
	public @ResponseBody ResponseVO getAllCounsels(@PathVariable("courtID") int courtid) {
		
		ResponseVO responsevo = new ResponseVO();
		responsevo.setDropDownCounsels(dropdowndao.getAllCounsels(courtid));
		
		return responsevo;
	}
	
}
