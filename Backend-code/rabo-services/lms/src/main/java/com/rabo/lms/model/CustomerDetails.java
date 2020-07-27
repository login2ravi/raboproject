package com.rabo.lms.model;

public class CustomerDetails {
	
	
	private long loanDetailId; 
	
	
	private String firstName;
	
	
	private String lastName;
	
	
	private String loanNumber;
	
	
	private Long amount;
	
	
	private String address1;
	
	
	private String address2;
	
	
	private String city;
	
	
	private String loanType;
	
	
	private long loanTerm;


	public long getLoanDetailId() {
		return loanDetailId;
	}


	public void setLoanDetailId(long loanDetailId) {
		this.loanDetailId = loanDetailId;
	}


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


	public Long getAmount() {
		return amount;
	}


	public void setAmount(Long amount) {
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
