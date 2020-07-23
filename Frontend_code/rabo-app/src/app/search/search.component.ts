import { Component, OnInit, EventEmitter, NgModule } from '@angular/core';
import { NgForm } from '@angular/forms';
import {Router, ActivatedRoute} from '@angular/router';
import {SearchService} from '../service/search.service'
import {SearchResult} from '../model/Search.model'
import { UsersService } from '../service/users.service';
import { User } from '../model/User.model';
import {DataService} from '../service/data.service';
import { CustomerDetails } from '../model/CustomerDetails.model';
import { AuthenticateService } from '../service/authenticate.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})



export class SearchComponent implements OnInit {
  resultList: Array<CustomerDetails>;
  isLoggedIn  = false;
  isAdminUser = false;
  msg: User;
  childTest = 'Checking child test ';
  constructor( private userservice: UsersService,
               private searchService: SearchService,
               private router: Router,
               private route: ActivatedRoute,
               private authenticateService:AuthenticateService) { }

  ngOnInit(): void {
    console.log("Search session=="+sessionStorage.getItem('token'));
    this.route.queryParams.subscribe(params => {
      this.isLoggedIn = params['isLoggedIn'];
      this.isAdminUser = params['isAdmin'];
    });

    this.userservice.setCurrentScreen('search');
  }



  onSubmit(form: NgForm): void{
     this.userservice.getUserData().subscribe(
       userData$ => {
        this.msg = userData$;
      });

     this.isAdminUser = this.msg.isAdminUser;

     this.authenticateService.getSearchDetails(form.value.searchData.firstName,
      form.value.searchData.lastName, form.value.searchData.loanNumber);
     this.resultList = this.searchService.searchLoanDetail(form.value.searchData.firstName,
      form.value.searchData.lastName, form.value.searchData.loanNumber);
     this.searchService.setSearchResult(this.resultList);
     this.router.navigate(['/search', 'search-result'],
     { queryParams: { isLoggedIn: this.isLoggedIn, isAdmin: this.isAdminUser } });
  }

  
}
