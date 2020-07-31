package com.lms.search.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lms.search.entity.LoanDetail;
import com.lms.search.model.CustomerDetails;
import com.lms.search.model.SearchRequest;
import com.lms.search.service.LoanManagementService;

@RestController
@RequestMapping("/secure")
@CrossOrigin
public class LoanManagementController {
	
	 Logger logger = LoggerFactory.getLogger(LoanManagementController.class);
	
	@Autowired
	LoanManagementService loanManagementService;
	
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
    List<LoanDetail> search(SearchRequest searchRequest) {
		logger.info("Inside Search method");
		return loanManagementService.search(searchRequest);
    }
	
	
	@GetMapping("/getloandetails/{loanNumber}")
    LoanDetail getLoanDetails(@PathVariable String loanNumber) {
		return loanManagementService.getLoanDetails(loanNumber);
        
    }

	@PostMapping("/addloan")
	ResponseEntity<?> addLoan(@RequestBody CustomerDetails CustomerDetails) {
        	
		loanManagementService.addLoan(CustomerDetails);
		
		return new ResponseEntity<>(HttpStatus.OK);
        
    }
	
	@PutMapping("/updateloan")
	ResponseEntity<?> updateLoan(@RequestBody CustomerDetails customerDetails) {
		loanManagementService.updateLoan(customerDetails);  
		 
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
