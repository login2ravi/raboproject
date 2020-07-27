import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserLoginComponent } from './user-login.component';
import { NgForm, FormBuilder, FormsModule } from '@angular/forms';
import { User } from '../model/User.model';
import { UsersService } from '../service/users.service';
import { RouterTestingModule } from '@angular/router/testing';
import {Observable} from 'rxjs';
import {BrowserModule , By} from '@angular/platform-browser';
import { DebugElement } from '@angular/core';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { AuthenticateService } from '../service/authenticate.service';

//const userServiceSpy = jasmine.createSpyObj<UsersService>('UsersService', ['validateUserDetails', 'setUserData','setCurrentScreen']);

describe('UserLoginComponent', () => {
  let component: UserLoginComponent;
  let fixture: ComponentFixture<UserLoginComponent>;
  let usersService: UsersService;
  let authenticateService : AuthenticateService;
  


  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [RouterTestingModule,FormsModule,HttpClientModule],
      declarations: [ UserLoginComponent ],
      providers: [
        {provide: [UsersService, authenticateService]}
      ]

    })
    .compileComponents();
  }));

  beforeEach(() => {
    usersService = new UsersService();
    fixture = TestBed.createComponent(UserLoginComponent);
    component = fixture.componentInstance;
  });

  it('should create', () => {
    fixture.detectChanges();
    expect(component).toBeTruthy();
  });

  
  it('should component valiated', () => {
    //fixture.detectChanges();
    //let firstNameValidationError: DebugElement;
  console.log("before detect changes")    
    //fixture.detectChanges(); // run change detection

    console.log("After*** detect changes")    
   // firstNameValidationError = fixture.debugElement.query(By.css('text-danger'));

    // the validation error should be found:
    //expect(firstNameValidationError).toBeTruthy();
    expect(component).toBeTruthy();

  });

  
});
