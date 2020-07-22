import { Injectable } from '@angular/core';
import {CustomerDetails} from '../model/CustomerDetails.model';
import { ngModuleJitUrl } from '@angular/compiler';
import {SearchResult} from '../model/Search.model';
import { DataService } from './data.service';

@Injectable({
  providedIn: 'root'
})
export class SearchService {


  constructor(){
    const dataService = new DataService();
    dataService.setCustomerLoan();
    dataService.setInitialCustomerDetailData();
  }


  public saveLoanDetails(customerDetails: CustomerDetails): void{
    const dataService = new DataService();
    dataService.loan.push(customerDetails);
  }

  public searchLoanDetail(firstName: String,lastName: String,loanNumber: number): Array<SearchResult>{
    const resultList  = new Array<SearchResult>();
    const dataService = new DataService();
    let searchResult = null;
    for(let loan of dataService.loan){
      if(loan.firstName === firstName || loan.lastName === lastName || loan.loanNumber === loanNumber){
        searchResult = new SearchResult();
        searchResult.firstName = loan.firstName;
        searchResult.lastName = loan.lastName;
        searchResult.amount = loan.amount;
        searchResult.loanNumber = loan.loanNumber;
        searchResult.city = loan.city;
        resultList.push(searchResult);
      }
    }
    return resultList;
  }
}
