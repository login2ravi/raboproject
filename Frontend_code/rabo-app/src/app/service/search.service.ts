import { Injectable } from '@angular/core';
import {CustomerDetails} from '../model/CustomerDetails.model';
import { ngModuleJitUrl } from '@angular/compiler';
import {SearchResult} from '../model/Search.model';
import { DataService } from './data.service';
import { BehaviorSubject } from 'rxjs';
import { HttpClient , HttpHeaders} from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class SearchService {

  private searchResultArray: BehaviorSubject<Array<CustomerDetails>>;

  

  constructor(private httpClient: HttpClient){
    const dataService = new DataService();
    dataService.setCustomerLoan();
    dataService.setInitialCustomerDetailData();
    this.searchResultArray = new BehaviorSubject(null);
  }

  getSearchResult(){
    return this.searchResultArray.asObservable();
  }

  setSearchResult(searchResultList: Array<CustomerDetails>): void{
        this.searchResultArray.next(searchResultList);
  }

  public saveLoanDetails(customerDetails: CustomerDetails): void{
    const dataService = new DataService();
    dataService.loan.push(customerDetails);
  }

  public searchLoanDetail(firstName: String,lastName: String,loanNumber: string): Array<CustomerDetails>{
    const resultList  = new Array<CustomerDetails>();
    const dataService = new DataService();


    let searchResult = null;
    for(let loan of dataService.loan){
      if(loan.firstName === firstName || loan.lastName === lastName || loan.loanNumber == loanNumber){
        searchResult = new CustomerDetails();
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
