package com.rabo.lms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.rabo.lms.entity.LoanDetail;

public interface LoanRepo extends JpaRepository<LoanDetail, Integer>{
	
	//@Query("SELECT c FROM LoanDetail c WHERE LOWER(c.firstName) = LOWER(:firstName) and LOWER(c.lastName) = LOWER(:lastName) and LOWER(c.loanNumber) = LOWER(:loanNumber)")
	List<LoanDetail> findByFirstNameAndLastNameAndLoanNumber(String firstName,
						String lastName,
						String loanNumber);
	
	
	LoanDetail findByLoanNumber(String loanNumber);

	
}
