package com.rabo.lms.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.rabo.lms.entity.LoanDetail;
import com.rabo.lms.model.CustomerDetails;
import com.rabo.lms.model.SearchRequest;
import com.rabo.lms.repo.LoanRepo;


//@RunWith(MockitoJUnitRunner.class)

@ExtendWith(MockitoExtension.class)
public class LoanManagementServiceTest {

	@InjectMocks
	LoanManagementService loanManagementService;
	
	@Mock
	private LoanRepo loanRepo;
	
	@Test
	public void searchTest() {
		
		SearchRequest searchRequest = new SearchRequest();
		searchRequest.setFirstName("Rahul");
		searchRequest.setLastName("Dravid");
		searchRequest.setLoanNumber("HL-1001");
		
		List<LoanDetail> loanDetailList = getResultLoanList();
		Mockito.when(loanRepo.find(searchRequest.getFirstName(), searchRequest.getLastName(), searchRequest.getLoanNumber())).thenReturn(loanDetailList);
		List<LoanDetail> resultLoanList = loanManagementService.search(searchRequest);
		
		assertEquals(resultLoanList, loanDetailList);
		
	}
	
	@Test
	public void getLoanDetails() {
		LoanDetail loanDetail = getLoanDetail();
		Mockito.when(loanRepo.findByLoanNumber("HL-1001")).thenReturn(loanDetail);
		LoanDetail loanDetails = loanManagementService.getLoanDetails("HL-1001");
		assertEquals(loanDetails, loanDetail);
	}
	
	@Test
	public void addLoan() {
		CustomerDetails customerDetails =  new CustomerDetails();
		customerDetails.setLoanNumber("HL-1001");
				
		//Mockito.when(loanRepo.save(new LoanDetail())).thenThrow(new BusinessException(ErrorCode.INVALID_REQUEST, "Loan number already exists"));
		/*
		 * Assertions.assertThrows(BusinessException.class, () -> {
		 * loanManagementService.addLoan(customerDetails); });
		 */		
	
	}
	
	@Test
	public void updateLoan() {
		
		CustomerDetails customerDetails =  new CustomerDetails();
		customerDetails.setLoanNumber("HL-1001");
		
		LoanDetail loanDetail = getLoanDetail();
		Mockito.when(loanRepo.findByLoanNumber(customerDetails.getLoanNumber())).thenReturn(loanDetail);
		
		loanManagementService.updateLoan(customerDetails);
		verify(loanRepo,times(1)).save(loanDetail);
	}
	
	private List<LoanDetail> getResultLoanList(){
		List<LoanDetail> loanDetailList = new ArrayList<LoanDetail>();
		loanDetailList.add(getLoanDetail());
		return loanDetailList;
		
	}
	
	private LoanDetail getLoanDetail() {
		LoanDetail loanDetail = new LoanDetail();
		loanDetail.setFirstName("Rahul");
		loanDetail.setLastName("Dravid");
		loanDetail.setLoanNumber("HL-1001");
		loanDetail.setCity("Chennai");
		loanDetail.setAmount(50000L);
		return loanDetail;
				
		
	}
	
	
	
	
	
	
}
