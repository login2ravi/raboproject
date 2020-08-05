package com.lms.search.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.search.entity.LoanDetail;
import com.lms.search.model.SearchRequest;
import com.lms.search.repo.LoanRepo;

@Service
public class LoanManagementService {
	Logger logger = LoggerFactory.getLogger(LoanManagementService.class);
	
	@Autowired
	private LoanRepo loanRepo;
	
	public List<LoanDetail> search(SearchRequest searchRequest) {
		return loanRepo.findByFirstNameAndLastNameAndLoanNumber(searchRequest.getFirstName(), searchRequest.getLastName(), searchRequest.getLoanNumber()); 
		
	}
	

}
