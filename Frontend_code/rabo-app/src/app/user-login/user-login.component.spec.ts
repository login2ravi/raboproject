import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserLoginComponent } from './user-login.component';
import { FormBuilder, FormsModule } from '@angular/forms';
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
      imports: [RouterTestingModule, HttpClientModule, FormsModule],
      declarations: [ UserLoginComponent ],
      providers: [
        UsersService, AuthenticateService,
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

  it('form invalid when empty', () => {
    //fixture.debugElement.componentInstance.
    var username_Ele = fixture.debugElement.query(By.css('#username'));
    let element = fixture.debugElement.nativeElement;
    element.querySelector('#username').value = 'admin';
    console.log("**Before username****"+element.querySelector('#username'));
    fixture.detectChanges();
    //element.querySelector('#username').value = 'admin';

    console.log("***4444444**"+element.querySelector('#username').className);
    console.log("Username error===144444=="+element.querySelector('#username_error').className);

    let usernameTxt = element.querySelector('#username');
    
    expect(element.querySelector('#username_error')).not.toBeDefined();
    
  });
  
});
