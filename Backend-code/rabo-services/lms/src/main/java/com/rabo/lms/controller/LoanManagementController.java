package com.rabo.lms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rabo.lms.entity.LoanDetail;
import com.rabo.lms.model.CustomerDetails;
import com.rabo.lms.model.SearchRequest;
import com.rabo.lms.service.LoanManagementService;

@RestController
@RequestMapping("/secure")
@CrossOrigin
public class LoanManagementController {
	
	 Logger logger = LoggerFactory.getLogger(LoanManagementController.class);
	
	@Autowired
	LoanManagementService loanManagementService;
	
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
    List<LoanDetail> search(SearchRequest searchRequest) {
		return loanManagementService.search(searchRequest);
    }
	
	
	@RequestMapping(value = "/getloandetails", method = RequestMethod.GET)
    LoanDetail getLoanDetails(@RequestParam String loanNumber) {
		return loanManagementService.getLoanDetails(loanNumber);
        
    }

	@PostMapping("/addloan")
    Map<String,String> addLoan(@RequestBody CustomerDetails CustomerDetails) {
        	
		loanManagementService.addLoan(CustomerDetails);
		Map<String,String> resultMap = new HashMap<String,String>(); 
		resultMap.put("Message", "Loan created Sucessfully");
        return resultMap;
    }
	
	@PutMapping("/updateloan")
	Map<String,String> updateLoan(@RequestBody CustomerDetails customerDetails) {
		loanManagementService.updateLoan(customerDetails);  
		Map<String,String> resultMap = new HashMap<String,String>(); 
		resultMap.put("Message", "Loan Updated Sucessfully");
        return resultMap;
	}
	
}
