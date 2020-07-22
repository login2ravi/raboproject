import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import {LoanService} from '../service/loan.service'
import {CustomerDetails} from '../model/CustomerDetails.model'
import { DataService } from '../service/data.service';
import { UsersService } from '../service/users.service';

@Component({
  selector: 'app-add-loan',
  templateUrl: './add-loan.component.html',
  styleUrls: ['./add-loan.component.css']
})
export class AddLoanComponent implements OnInit {
  customerDetailModel: CustomerDetails;
  isLoggedIn = false;
  isAdminUser = false;


  constructor(private userservice: UsersService,
              private loanService: LoanService,
              private route: ActivatedRoute)  { }


  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.isLoggedIn = params['isLoggedIn'];
      this.isAdminUser = params['isAdmin'];
    });

    this.userservice.setCurrentScreen('addloan');

  }

  onSubmit(form: NgForm): void{
    const dataService = new DataService();

    this.customerDetailModel = new CustomerDetails();
    this.customerDetailModel.firstName = form.value.addLoanData.firstName;
    this.customerDetailModel.lastName = form.value.addLoanData.lastName;
    this.customerDetailModel.loanNumber = form.value.addLoanData.loanNumber;
    this.customerDetailModel.address1 = form.value.addLoanData.address1;
    this.customerDetailModel.address2 = form.value.addLoanData.address2;
    this.customerDetailModel.city = form.value.addLoanData.city;

    this.loanService.saveLoanDetails(this.customerDetailModel);
  }

}
