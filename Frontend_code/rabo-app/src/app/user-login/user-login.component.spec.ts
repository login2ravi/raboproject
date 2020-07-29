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
    fixture.detectChanges();
  });

  it('should create', () => {
    //fixture.detectChanges();
    expect(component.ngForm.valid).toBeFalsy();
  });

  it('form invalid when empty', () => {  
    //fixture.debugElement.componentInstance.
    var username_Ele = fixture.debugElement.query(By.css('#username'));
    let element = fixture.debugElement.nativeElement;
    
    console.log("Checking1111 ngform =="+component.ngForm);
    
    console.log("***AFt**"+element.querySelector('#username').className);  
    console.log("***AFT**"+element.querySelector('#username_error').className);
    element.querySelector('#username').value = 'admin';
    
    fixture.detectChanges();
    element.querySelector('#username').value = undefined;
    fixture.detectChanges();
    console.log("***AFT2222**"+element.querySelector('#username').className);  
    console.log("*AFT2222**"+element.querySelector('#username_error').className);
  

    expect(element.querySelector('#username_error').className).not.toContain('d-none');
    
  });
  
});
