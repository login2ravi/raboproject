package com.rabo.lms.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.rabo.lms.controller.LoanManagementController;
import com.rabo.lms.entity.LoanDetail;
import com.rabo.lms.exception.BusinessException;
import com.rabo.lms.exception.ErrorCode;
import com.rabo.lms.model.CustomerDetails;
import com.rabo.lms.model.SearchRequest;
import com.rabo.lms.repo.LoanRepo;

@Service
public class LoanManagementService {
	Logger logger = LoggerFactory.getLogger(LoanManagementService.class);
	
	@Autowired
	private LoanRepo loanRepo;
	
	public List<LoanDetail> search(SearchRequest searchRequest) {
		List<LoanDetail> loanDetailsList = loanRepo.find(searchRequest.getFirstName(), searchRequest.getLastName(), searchRequest.getLoanNumber()); 
		return loanDetailsList;
	}
	
	public LoanDetail getLoanDetails(String loanNumber) {
		LoanDetail loanDetails = loanRepo.findByLoanNumber(loanNumber); 
		
		return loanDetails;
	}
	
	public void addLoan(CustomerDetails loanRequest) {
		
		try {
		
			LoanDetail newLoanDetail = new LoanDetail();
			newLoanDetail.setFirstName(loanRequest.getFirstName());
			newLoanDetail.setLastName(loanRequest.getLastName());
			newLoanDetail.setLoanNumber(loanRequest.getLoanNumber());
			newLoanDetail.setAddress1(loanRequest.getAddress1());
			newLoanDetail.setAddress2(loanRequest.getAddress2());
			newLoanDetail.setCity(loanRequest.getCity());
			
			loanRepo.save(newLoanDetail);
			logger.info("Add loan saved sucessfully");
		}catch(DataAccessException e) {
			logger.error("Exception in AddLoan ",e);
			throw new BusinessException(ErrorCode.INVALID_REQUEST, "Loan number already exists",e);
		}
		
	}
	
	
public void updateLoan(CustomerDetails updateRequest) {
		
		LoanDetail loanDetail = loanRepo.findByLoanNumber(updateRequest.getLoanNumber());
	
		loanDetail.setLoanTerm(updateRequest.getLoanTerm());
		loanDetail.setLoanType(updateRequest.getLoanType());
		
		loanDetail.setAmount(updateRequest.getAmount()); 
		loanRepo.save(loanDetail);
	}
	
	
	

}
