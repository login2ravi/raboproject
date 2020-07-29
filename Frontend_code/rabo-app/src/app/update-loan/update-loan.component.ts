import { Component, OnInit, ElementRef, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { LoanService } from '../service/loan.service';
import { CustomerDetails } from '../model/CustomerDetails.model';


@Component({
  selector: 'app-update-loan',
  templateUrl: './update-loan.component.html',
  styleUrls: ['./update-loan.component.css']
})
export class UpdateLoanComponent implements OnInit {
loanNumber: String;
loanType: String;
loanTerm: number;
loan: number;
isLoggedIn  = false;
isAdminUser = false;
loanDetails: CustomerDetails = new CustomerDetails();
msg: string;

  constructor(private router: Router ,
              private loanService: LoanService,
              private route: ActivatedRoute) { 

                console.log("Loan number in constructor==",this.loanNumber);
              }

  ngOnInit(): void {

    this.route.queryParams.subscribe(params => {
      this.loanNumber = params['loanNumber'];
      this.isLoggedIn = params['isLoggedIn'];
      this.isAdminUser = params['isAdmin'];
    });
    console.log("Loan number in oninit==",this.loanNumber);


    this.loanService.getLoanDetails(this.loanNumber).subscribe(responseData => {
      this.loanDetails = responseData;
    });


  }

  onSubmit(form: NgForm): void{
    let isSucessful = false;
    this.loanService.updateLoan(this.loanDetails).subscribe(responseData => {
      this.msg = 'Loan Updated Sucessfully';
    }, (errorMessage) =>{
      console.log("Error code"+errorMessage.status);
      console.log("Error Message"+errorMessage.error.code);
      
      if(errorMessage.status === 400){
        this.msg = errorMessage.error.message;
      }else if(errorMessage.status === 500){
      this.msg = 'Application not currently avaliable. Please try again later';
      }
      

  });
    }
}
