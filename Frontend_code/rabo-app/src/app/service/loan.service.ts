import { Injectable } from '@angular/core';
import { CustomerDetails } from '../model/CustomerDetails.model';
import { SearchService } from './search.service';
import { LoanDetails } from '../model/LoanDetails.model';
import { DataService } from './data.service';
import { removeSummaryDuplicates } from '@angular/compiler';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})



export class LoanService{

  searchService: SearchService;
  constructor(private httpClient: HttpClient) {}

  saveLoanDetails(customerDetails: CustomerDetails): void{
   const dataService = new DataService();
   dataService.loan.push(customerDetails);
  }

  // updateLoanDetailsold(loanDetails: LoanDetails): boolean{
  //   const dataService = new DataService();
  //   for(let dtl of dataService.loanDetailsAry){
  //     if(dtl.loanNumber == loanDetails.loanNumber){
  //       dtl.loanTerm = loanDetails.loanTerm;
  //       dtl.loanType = loanDetails.loanType;
  //     }
  //   }

  //   for(let loan of dataService.loan){
  //     if(loan.loanNumber == loanDetails.loanNumber){
  //       loan.amount = loanDetails.amount;
  //     }
  //   }
  //   return true;
  // }

  // getLoanDetailsOld(loanNumber: number): LoanDetails{
  //   const dataService = new DataService();
  //   let result = new LoanDetails();
  //   for(let dtl of dataService.loanDetailsAry){ 
  //     if(dtl.loanNumber == loanNumber){
  //      result = dtl;
  //     }
  //   }

  //   for(let loan of dataService.loan){
  //     if (loan.loanNumber == loanNumber){
  //       result.amount = loan.amount;
  //     }
  //   }

  //   return result;

  // }

  updateLoan(customerDetails: CustomerDetails)  {

    let token = sessionStorage.getItem('token');
    console.log("Amount"+customerDetails.amount);
    const headers = { 
                      'Access-Control-Allow-Origin': 'http://localhost:4200/*',
                       'Access-Control-Allow-Methods': 'GET, POST, PATCH, PUT, DELETE, OPTIONS,TOKEN',
                       'Access-Control-Allow-Headers':'Origin, Content-Type, X-Auth-Token',
                       'Authorization':token};

    return this.httpClient.put('http://localhost:7000/secure/updateloan',
     customerDetails,
    {headers});
  }


  getLoanDetails(loanNumber){
    

    console.log("search token="+sessionStorage.getItem('token'));
    let token = sessionStorage.getItem('token');
    const httpOptions = {
      headers: { 'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': 'http://localhost:4200/*',
       'Authorization':token},
      params: {'loanNumber': loanNumber}
    };



return this.httpClient.get<CustomerDetails>('http://localhost:7000/secure/getloandetails',httpOptions);

  }


  addLoan(customerDetails: CustomerDetails)  {

    let token = sessionStorage.getItem('token');
    
    const headers = { 
                      'Access-Control-Allow-Origin': 'http://localhost:4200/*',
                       'Access-Control-Allow-Methods': 'GET, POST, PATCH, PUT, DELETE, OPTIONS,TOKEN',
                       'Access-Control-Allow-Headers':'Origin, Content-Type, X-Auth-Token',
                       'Authorization':token};

    return this.httpClient.post('http://localhost:7000/secure/addloan',
    customerDetails,
    {headers});
  }



}
