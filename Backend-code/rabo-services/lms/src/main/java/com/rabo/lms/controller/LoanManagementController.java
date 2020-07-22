package com.rabo.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rabo.lms.entity.LoanDetail;
import com.rabo.lms.model.AddLoanRequest;
import com.rabo.lms.model.SearchRequest;
import com.rabo.lms.model.UpdateRequest;
import com.rabo.lms.service.LoanManagementService;

@RestController
@RequestMapping("/secure")
public class LoanManagementController {
	@Autowired
	LoanManagementService loanManagementService;
	
	

	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
    List<LoanDetail> search(@RequestBody SearchRequest searchRequest) {
        
		System.out.println("first Name=="+searchRequest.getFirstName());
		System.out.println("Last Name=="+searchRequest.getLastName());
		System.out.println("Loan Number=="+searchRequest.getLoanNumber());
        
		return loanManagementService.search(searchRequest);
		
		
        
    }

	@PostMapping("/addloan")
    String addLoan(@RequestBody AddLoanRequest addLoanRequest) {
        
		System.out.println("first Name=="+addLoanRequest.getFirstName());
		System.out.println("Last Name=="+addLoanRequest.getLastName());
		System.out.println("Loan Number=="+addLoanRequest.getLoanNumber());
		System.out.println("Address 1r=="+addLoanRequest.getAddress1());
		System.out.println("Address 2=="+addLoanRequest.getAddress2());
		System.out.println("City=="+addLoanRequest.getCity());
		System.out.println("amount=="+addLoanRequest.getAmount());
        
		loanManagementService.addLoan(addLoanRequest);
		
        return "sucess";
    }
	
	@PutMapping("/updateloan")
    String updateLoan(@RequestBody UpdateRequest updateRequest) {
		loanManagementService.updateLoan(updateRequest);
		return "updated Sucessfully";
	}

	
}
