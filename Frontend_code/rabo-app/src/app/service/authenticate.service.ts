import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { map } from 'rxjs/operators';

class LoginUser{
  username: string;
  password: string;
}

@Injectable({
  providedIn: 'root'
})
export class AuthenticateService {



  constructor( private httpClient: HttpClient) { }


  authenticate(username, password)  {
    const headers = { 
                      'Access-Control-Allow-Origin': 'http://localhost:4200/*',
                       'Access-Control-Allow-Methods': 'GET, POST, PATCH, PUT, DELETE, OPTIONS,TOKEN',
                       'Access-Control-Allow-Headers':'Origin, Content-Type, X-Auth-Token' };

    return this.httpClient.post<string>('http://localhost:7000/login',
    {"userName":username,"password":password},{headers});
    
    
  }


  getSearchDetails(firstName,lastName,loanNumber){
    const headers = { 
      'Access-Control-Allow-Origin': 'http://localhost:4200/*'
     };

    console.log("search token="+sessionStorage.getItem('token'));
    let token = sessionStorage.getItem('token');
    const httpOptions = {
      headers: { 'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': 'http://localhost:4200/*',
       'Authorization':token},
      params: {'firstName': firstName,'lastName' : lastName,'loanNumber': loanNumber}
    };



this.httpClient.get('http://localhost:7000/secure/search',httpOptions).subscribe(
  responseData => {
    console.log("Response data =="+responseData);
  });


  }

}