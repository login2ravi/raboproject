import { Component, OnInit, ElementRef, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { LoanService } from '../service/loan.service';
import { LoanDetails } from '../model/LoanDetails.model';


@Component({
  selector: 'app-update-loan',
  templateUrl: './update-loan.component.html',
  styleUrls: ['./update-loan.component.css']
})
export class UpdateLoanComponent implements OnInit {
loanNumber: number;
loanType: String;
loanTerm: number;
loan: number;
isLoggedIn  = false;
isAdminUser = false;
loanDetails: LoanDetails;
msg: string;

  constructor(private router: Router ,
              private loanService: LoanService,
              private route: ActivatedRoute) { }

  ngOnInit(): void {

    this.route.queryParams.subscribe(params => {
      this.loanNumber = params['loanNumber'];
      this.isLoggedIn = params['isLoggedIn'];
      this.isAdminUser = params['isAdmin'];
    });


    this.loanDetails = this.loanService.getLoanDetails(this.loanNumber);

  }

  onSubmit(form: NgForm): void{
    let isSucessful = false;
    isSucessful = this.loanService.updateLoanDetails(this.loanDetails);
    if (isSucessful){
      this.msg = 'Sucessfully updated';
    }else{
      this.msg = 'Not updated please try again later.';
    }
    }
}
