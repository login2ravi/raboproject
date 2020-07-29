package com.rabo.lms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@Entity
@Table(name="Loan_details")
public class LoanDetail {
	
	@Id  @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="loan_detail_id")
	private Long loanDetailId; 
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="loan_number")
	private String loanNumber;
	
	@Column(name="amount")
	private Long amount;
	
	@Column(name="address1")
	private String address1;
	
	@Column(name="address2")
	private String address2;
	
	@Column(name="city")
	private String city;
	
	@Column(name="loan_type")
	private String loanType;
	
	@Column(name="loan_term")
	private Long loanTerm;
	
	public LoanDetail(Long loanDetailId, String firstName, String lastName, String loanNumber, Long amount, String address1, 
			String address2, String city, String loanType, Long loanTerm) {
		
		this.loanDetailId = loanDetailId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.loanNumber = loanNumber;
		this.amount = amount;
		this.address1 = address1;
		this.address2 = address2;
		this.city = city;
		this.loanType = loanType;
		this.loanTerm = loanTerm;
				
	}
	
}
