import { Injectable } from '@angular/core';
import { CustomerDetails } from '../model/CustomerDetails.model';
import { SearchService } from './search.service';
import { LoanDetails } from '../model/LoanDetails.model';
import { DataService } from './data.service';
import { removeSummaryDuplicates } from '@angular/compiler';


@Injectable({
  providedIn: 'root'
})



export class LoanService{

  searchService: SearchService;
  constructor() {}

  saveLoanDetails(customerDetails: CustomerDetails): void{
   const dataService = new DataService();
   dataService.loan.push(customerDetails);
  }

  updateLoanDetails(loanDetails: LoanDetails): boolean{
    const dataService = new DataService();
    for(let dtl of dataService.loanDetailsAry){
      if(dtl.loanNumber == loanDetails.loanNumber){
        dtl.loanTerm = loanDetails.loanTerm;
        dtl.loanType = loanDetails.loanType;
      }
    }

    for(let loan of dataService.loan){
      if(loan.loanNumber == loanDetails.loanNumber){
        loan.amount = loanDetails.amount;
      }
    }
    return true;
  }

  getLoanDetails(loanNumber: number): LoanDetails{
    const dataService = new DataService();
    let result = new LoanDetails();
    for(let dtl of dataService.loanDetailsAry){ 
      if(dtl.loanNumber == loanNumber){
       result = dtl;
      }
    }

    for(let loan of dataService.loan){
      if (loan.loanNumber == loanNumber){
        result.amount = loan.amount;
      }
    }

    return result;

  }


}
