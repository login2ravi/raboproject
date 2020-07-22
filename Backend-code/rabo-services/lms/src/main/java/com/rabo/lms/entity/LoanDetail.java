package com.rabo.lms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Loan_details")
public class LoanDetail {
	
	@Id  @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="loan_detail_id")
	private long loanDetailId; 
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="loan_number")
	private String loanNumber;
	
	@Column(name="amount")
	private Integer amount;
	
	@Column(name="address1")
	private String address1;
	
	@Column(name="address2")
	private String address2;
	
	@Column(name="city")
	private String city;
	
	@Column(name="loan_type")
	private String loanType;
	
	@Column(name="loan_term")
	private long loanTerm;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getLoanNumber() {
		return loanNumber;
	}
	public void setLoanNumber(String loanNumber) {
		this.loanNumber = loanNumber;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public long getLoanDetailId() {
		return loanDetailId;
	}
	public void setLoanDetailId(long loanDetailId) {
		this.loanDetailId = loanDetailId;
	}
	public String getLoanType() {
		return loanType;
	}
	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}
	public long getLoanTerm() {
		return loanTerm;
	}
	public void setLoanTerm(long loanTerm) {
		this.loanTerm = loanTerm;
	}
	
	
	

}
