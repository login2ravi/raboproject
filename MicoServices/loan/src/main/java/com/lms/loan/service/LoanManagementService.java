package com.lms.loan.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.lms.loan.entity.LoanDetail;
import com.lms.loan.exception.BusinessException;
import com.lms.loan.exception.ErrorCode;
import com.lms.loan.model.CustomerDetails;
import com.lms.loan.repo.LoanRepo;

@Service
public class LoanManagementService {
	Logger logger = LoggerFactory.getLogger(LoanManagementService.class);
	
	@Autowired
	private LoanRepo loanRepo;
	
	
	
	public LoanDetail getLoanDetails(String loanNumber) {
		return loanRepo.findByLoanNumber(loanNumber); 
			
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
