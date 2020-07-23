import { Injectable } from '@angular/core';
import {BehaviorSubject} from 'rxjs';
import {User} from '../model/User.model';
import {AuthenticateService} from './authenticate.service';


@Injectable({
  providedIn: 'root'
})
export class UsersService {


  userDetails =
[{
    Username : 'admin', Password : 'admin123', isAdminUser : false,
    isLoggedIn : false
  },
  {
    Username :'test', Password : 'test123', isAdminUser : false,
    isLoggedIn : false
  }
];

currentUser: User;

private userData$: BehaviorSubject<User>;
private currentScreen$: BehaviorSubject<string>;

  constructor( ) {
    this.userData$ = new BehaviorSubject(null);
    this.currentScreen$ = new BehaviorSubject(null);

   }

   getUserData(){
     return this.userData$.asObservable();
   }

   setUserData(userDetails: User): void{
     this.userData$.next(userDetails);
   }


   getCurrentScreen(){
    return this.currentScreen$.asObservable();
   }

   setCurrentScreen(currentScreen: string): void{
    this.currentScreen$.next(currentScreen);
  }


  public validateUserDetails( user: User): User{
    
    
    let token = sessionStorage.getItem('token');
    console.log("token from session ===="+token);
    const newUser = new User();
    for ( let usr of this.userDetails){
      if (usr.Username === user.userName && usr.Password === user.password){
        usr.isLoggedIn = true;
        if ( usr.Username === 'admin'){
          usr.isAdminUser = true;
        }
        newUser.userName = usr.Username;
        newUser.password = usr.Password;
        newUser.isAdminUser = usr.isAdminUser;
        newUser.isLoggedIn = usr.isLoggedIn;
        break;
      }else{
      }
    }
    return newUser;
  }



}
