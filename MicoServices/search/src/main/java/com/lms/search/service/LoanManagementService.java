package com.lms.search.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.lms.search.entity.LoanDetail;
import com.lms.search.exception.BusinessException;
import com.lms.search.exception.ErrorCode;
import com.lms.search.model.CustomerDetails;
import com.lms.search.model.SearchRequest;
import com.lms.search.repo.LoanRepo;

@Service
public class LoanManagementService {
	Logger logger = LoggerFactory.getLogger(LoanManagementService.class);
	
	@Autowired
	private LoanRepo loanRepo;
	
	public List<LoanDetail> search(SearchRequest searchRequest) {
		List<LoanDetail> loanDetailsList = loanRepo.findByFirstNameAndLastNameAndLoanNumber(searchRequest.getFirstName(), searchRequest.getLastName(), searchRequest.getLoanNumber()); 
		return loanDetailsList;
	}
	
	public LoanDetail getLoanDetails(String loanNumber) {
		LoanDetail loanDetails = loanRepo.findByLoanNumber(loanNumber); 
		
		return loanDetails;
	}
	
	public LoanDetail addLoan(CustomerDetails loanRequest) throws BusinessException{
		
		try {
			
			  LoanDetail newLoanDetail =
			  LoanDetail.builder().firstName(loanRequest.getFirstName())
			  .lastName(loanRequest.getLastName()) .loanNumber(loanRequest.getLoanNumber())
			  .address1(loanRequest.getAddress1()) .address2(loanRequest.getAddress2())
			  .city(loanRequest.getCity())
			  .build();
			 
			
			newLoanDetail = loanRepo.save(newLoanDetail);
			logger.info("Add loan saved sucessfully");
			return newLoanDetail;
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
