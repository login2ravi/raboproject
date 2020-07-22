package com.rabo.lms.model;

public class UpdateRequest {
	
	private String loanNumber;
	private String loanType;
	private long loanTerm;
	private long amount;
	public String getLoanNumber() {
		return loanNumber;
	}
	public void setLoanNumber(String loanNumber) {
		this.loanNumber = loanNumber;
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
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}
	
	
	

}
