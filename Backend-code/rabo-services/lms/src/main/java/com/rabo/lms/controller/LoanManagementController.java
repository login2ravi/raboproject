package com.rabo.lms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.rabo.lms.model.AddLoanRequest;
import com.rabo.lms.model.SearchRequest;
import com.rabo.lms.model.UpdateRequest;
import com.rabo.lms.service.LoanManagementService;

@RestController
@RequestMapping("/secure")
@CrossOrigin
public class LoanManagementController {
	@Autowired
	LoanManagementService loanManagementService;
	
	

	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
    List<LoanDetail> search(SearchRequest searchRequest) {
        
		System.out.println("first Name=="+searchRequest.getFirstName());
		System.out.println("Last Name=="+searchRequest.getLastName());
		System.out.println("Loan Number=="+searchRequest.getLoanNumber());
        
		return loanManagementService.search(searchRequest);
		
		
        
    }

	@PostMapping("/addloan")
    Map<String,String> addLoan(@RequestBody AddLoanRequest addLoanRequest) {
        
		
		loanManagementService.addLoan(addLoanRequest);
		Map<String,String> resultMap = new HashMap<String,String>(); 
		resultMap.put("Message", "Loan created Sucessfully");
        return resultMap;
    }
	
	@PutMapping("/updateloan")
	Map<String,String> updateLoan(@RequestBody UpdateRequest updateRequest) {
		loanManagementService.updateLoan(updateRequest);
		Map<String,String> resultMap = new HashMap<String,String>(); 
		resultMap.put("Message", "Loan created Sucessfully");
        return resultMap;
	}

	
}
