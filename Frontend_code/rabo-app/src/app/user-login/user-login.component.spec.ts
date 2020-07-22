import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserLoginComponent } from './user-login.component';
import { NgForm, FormBuilder, FormsModule } from '@angular/forms';
import { User } from '../model/User.model';
import { UsersService } from '../service/users.service';
import { RouterTestingModule } from '@angular/router/testing';
import {Observable} from 'rxjs';

const userServiceSpy = jasmine.createSpyObj<UsersService>('UsersService', ['validateUserDetails', 'setUserData','setCurrentScreen']);

describe('UserLoginComponent', () => {
  let component: UserLoginComponent;
  let fixture: ComponentFixture<UserLoginComponent>;
  let usersService: UsersService;


  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [RouterTestingModule,FormsModule],
      declarations: [ UserLoginComponent ],
      providers: [
        {provide: UsersService,
        useValue: userServiceSpy}
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


  it('should create with components', () => {
    fixture.detectChanges();
    component.user.userName = 'admin';
    component.user.password = 'admin123'
  //  let result = component.onSubmit();
    //expect(result.isAdmin).toBeTruthy();

   // let myuser = {
   //   User: {userName: string, password: string}();
   // }
   

   let user: User
    user = new User();
    user.isAdminUser = true;
    user.userName='admin';
    user.password='admin123456';
    user.isLoggedIn=true;
    //console.log("User admin =="+user.isAdminUser)
//    let service = fixture.debugElement.injector.get(UsersService);
    userServiceSpy.validateUserDetails.and.returnValue(user);
    let myservice = new UsersService();
    let spy: any;
    spy = spyOn(myservice, 'validateUserDetails').and.returnValue(user);



    component.user.userName='admin';
    component.user.password='admin123';
    component.user.isAdminUser=false;
    component.user.isLoggedIn=false;
    component.onSubmit();
    fixture.detectChanges();
    //expect(component.user.isAdminUser).toEqual(user.isAdminUser);    
    console.log("*********"+component.user.isAdminUser);
    console.log("******spy***"+spy);
    //expect(component.invalidUserMessage).toEqual('');    
    //expect(userServiceSpy.validateUserDetails).toHaveBeenCalled();


  });

  
});
