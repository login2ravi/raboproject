import { Injectable } from '@angular/core';
import { CustomerDetails } from '../model/CustomerDetails.model';
import { SearchService } from './search.service';
import { LoanDetails } from '../model/LoanDetails.model';
import { removeSummaryDuplicates } from '@angular/compiler';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})



export class LoanService{

  searchService: SearchService;
  constructor(private httpClient: HttpClient) {}

  saveLoanDetails(customerDetails: CustomerDetails): void{
   }


  updateLoan(customerDetails: CustomerDetails)  {

    const token = sessionStorage.getItem('token');

    const headers = { 
                      'Access-Control-Allow-Origin': 'http://localhost:4200/*',
                       'Access-Control-Allow-Methods': 'GET, POST, PATCH, PUT, DELETE, OPTIONS,TOKEN',
                       'Access-Control-Allow-Headers':'Origin, Content-Type, X-Auth-Token',
                       'Authorization' : token};

    return this.httpClient.put('http://localhost:7003/lmsLoanService/secure/updateloan',
     customerDetails,
    {headers});
  }


  getLoanDetails(loanNumber){

    const token = sessionStorage.getItem('token');
    const httpOptions = {
      headers: { 'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': 'http://localhost:4200/*',
       'Authorization' : token}

    };



    return this.httpClient.get<CustomerDetails>('http://localhost:7003/lmsLoanService/secure/getloandetails/' + loanNumber, httpOptions);

  }


  addLoan(customerDetails: CustomerDetails)  {

    const token = sessionStorage.getItem('token');

    const headers = {
                      'Access-Control-Allow-Origin': 'http://localhost:4200/*',
                       'Access-Control-Allow-Methods': 'GET, POST, PATCH, PUT, DELETE, OPTIONS,TOKEN',
                       'Access-Control-Allow-Headers':'Origin, Content-Type, X-Auth-Token',
                       'Authorization':token};

    return this.httpClient.post<any>('http://localhost:7003/lmsLoanService/secure/addloan',
    customerDetails,
    {headers});
  }



}
