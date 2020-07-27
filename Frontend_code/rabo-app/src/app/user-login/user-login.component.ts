import { Component, OnInit, ViewChild, Output, EventEmitter } from '@angular/core';
import { NgForm } from '@angular/forms';
import {Router} from '@angular/router';
import { UsersService } from '../service/users.service';
import { Subscription } from 'rxjs';
import { User } from '../model/User.model';
import { HttpClient } from '@angular/common/http';
import { AuthenticateService } from '../service/authenticate.service';


@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.css']
})
export class UserLoginComponent implements OnInit {
public userData$: Subscription;
public invalidUserMessage: String;
user: User = new User();

constructor(private userservice: UsersService, private router: Router,private authenticateService: AuthenticateService) {
  sessionStorage.setItem('username',null);
      sessionStorage.setItem('token',null);
}

  ngOnInit(): void {
  }

  onSubmit(): void{
    //var returnvalue = this.authenticateService.authenticate(this.user.userName,this.user.password);
    this.authenticateService.authenticate(this.user.userName,this.user.password).subscribe(responseData => {
      let tokenStr= 'Bearer '+responseData['token'];
      
      if(responseData['token'] != null){
        sessionStorage.setItem('username',responseData['username']);
        sessionStorage.setItem('token',tokenStr);
        console.log("Login screen token=="+sessionStorage.getItem('token'));
        sessionStorage.setItem('userrole',responseData['userrole']);
        let userDtl = new User();
        userDtl.userName = sessionStorage.getItem('username');
        console.log("user role=="+sessionStorage.getItem('userrole'));
        if(sessionStorage.getItem('userrole') === 'admin'){
          userDtl.isAdminUser = true;
        }else{
          userDtl.isAdminUser = false;
        }
        userDtl.isLoggedIn = true;
        this.userservice.setUserData(userDtl);
        this.userservice.setCurrentScreen('search');
        this.router.navigate(['/search'], { queryParams: { isLoggedIn: userDtl.isLoggedIn, isAdmin: userDtl.isAdminUser } });

      }else{
        this.invalidUserMessage = 'Invalid Username/Password';
       }
    }, errorMessage =>{
      console.log("Error Message"+errorMessage.errorCode);
      this.invalidUserMessage = 'Application not currently avaliable. Please try again later';
    });
  }
}
