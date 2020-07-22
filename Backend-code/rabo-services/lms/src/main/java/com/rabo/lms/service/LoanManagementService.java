package com.rabo.lms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rabo.lms.entity.LoanDetail;
import com.rabo.lms.model.AddLoanRequest;
import com.rabo.lms.model.SearchRequest;
import com.rabo.lms.model.UpdateRequest;
import com.rabo.lms.repo.LoanRepo;

@Service
public class LoanManagementService {
	
	@Autowired
	private LoanRepo loanRepo;
	
	public List<LoanDetail> search(SearchRequest searchRequest) {
		List<LoanDetail> loanDetailsList = loanRepo.find(searchRequest.getFirstName(), searchRequest.getLastName(), searchRequest.getLoanNumber()); 
		return loanDetailsList;
	}
	
	public void addLoan(AddLoanRequest loanRequest) {
		
		LoanDetail newLoanDetail = new LoanDetail();
		//newLoanDetail.setLoanDetailId(1002);
		newLoanDetail.setFirstName(loanRequest.getFirstName());
		newLoanDetail.setLastName(loanRequest.getLastName());
		newLoanDetail.setLoanNumber(loanRequest.getLoanNumber());
		newLoanDetail.setAddress1(loanRequest.getAddress1());
		newLoanDetail.setAddress2(loanRequest.getAddress2());
		newLoanDetail.setCity(loanRequest.getCity());
		
		loanRepo.save(newLoanDetail);
	}
	
	
public void updateLoan(UpdateRequest updateRequest) {
		
		LoanDetail loanDetail = loanRepo.findByLoanNumber(updateRequest.getLoanNumber());
	
		loanDetail.setLoanTerm(updateRequest.getLoanTerm());
		loanDetail.setLoanType(updateRequest.getLoanType());
		loanRepo.save(loanDetail);
	}
	
	
	

}
