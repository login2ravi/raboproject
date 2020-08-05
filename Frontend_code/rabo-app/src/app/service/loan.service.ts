import { Injectable } from '@angular/core';
import { CustomerDetails } from '../model/CustomerDetails.model';
import { SearchService } from './search.service';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})



export class LoanService{

  searchService: SearchService;
  constructor(private httpClient: HttpClient) {}

  updateLoan(customerDetails: CustomerDetails){

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


  getLoanDetails(loanNumber): Observable<CustomerDetails>{

    const token = sessionStorage.getItem('token');
    const httpOptions = {
      headers: { 'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': 'http://localhost:4200/*',
       'Authorization': token}

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

    return this.httpClient.post<CustomerDetails>('http://localhost:7003/lmsLoanService/secure/addloan',
    customerDetails,
    {headers});
  }



}
