import { Component, OnInit, EventEmitter, NgModule } from '@angular/core';
import { NgForm } from '@angular/forms';
import {Router, ActivatedRoute} from '@angular/router';
import {SearchService} from '../service/search.service'
import {SearchResult} from '../model/Search.model'
import { UsersService } from '../service/users.service';
import { User } from '../model/User.model';
import {DataService} from '../service/data.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})



export class SearchComponent implements OnInit {
  resultList: Array<SearchResult>;
  isLoggedIn  = false;
  isAdminUser = false;
  msg: User;
  constructor( private userservice: UsersService,
               private searchService: SearchService,
               private router: Router,
               private route: ActivatedRoute) { }

  ngOnInit(): void {
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

     this.resultList = this.searchService.searchLoanDetail(form.value.searchData.firstName,
      form.value.searchData.lastName, form.value.searchData.loanNumber);
     //this.router.navigate(['/search/search-result']);
  }

  onEdit(loanNumber: number): void{
    this.router.navigate(['/update-loan'],
    { queryParams: { loanNumber, isLoggedIn: this.isLoggedIn, isAdmin: this.isAdminUser  } });
  }

}
