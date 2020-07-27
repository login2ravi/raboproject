import { Injectable } from '@angular/core';
import { CustomerDetails } from '../model/CustomerDetails.model';
import { LoanDetails } from '../model/LoanDetails.model';

@Injectable({
  providedIn: 'root'
})
export class DataService {
  static instance: DataService;

  loan: Array<CustomerDetails> = new Array();
  loanDetailsAry: Array<LoanDetails> = new Array();


  constructor() {
    return DataService.instance = DataService.instance || this;
  }

  setInitialCustomerDetailData(): void{
    let customerDetails = new CustomerDetails();
    customerDetails.firstName = 'rahul';
    customerDetails.lastName = 'dravid';
    customerDetails.loanNumber = '1001';
    customerDetails.address1 = 'addr1';
    customerDetails.address2 = 'addr2';
    customerDetails.city = 'chennai';
    customerDetails.amount = 1000;
    this.loan.push(customerDetails);

    customerDetails = new CustomerDetails();
    customerDetails.firstName = 'sahin';
    customerDetails.lastName = 'tendulkar';
    customerDetails.loanNumber = '1002';
    customerDetails.address1 = 'addr1';
    customerDetails.address2 = 'addr2';
    customerDetails.city = 'mumbai';
    customerDetails.amount = 5000;
    this.loan.push(customerDetails);
  }

  setCustomerLoan(): void{
    let loanDetails = new LoanDetails();
    loanDetails.loanNumber = 1001;
    loanDetails.loanTerm = 15;
    loanDetails.loanType = 'Home Loan';

    this.loanDetailsAry.push(loanDetails);


    loanDetails = new LoanDetails();
    loanDetails.loanNumber = 1002;
    loanDetails.loanTerm = 15;
    loanDetails.loanType = 'Home Loan';
    this.loanDetailsAry.push(loanDetails);

  }
}
