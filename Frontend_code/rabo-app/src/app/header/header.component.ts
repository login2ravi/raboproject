import { Component, OnInit } from '@angular/core';
import { UsersService } from '../service/users.service';
import { User } from '../model/User.model';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private userservice: UsersService) { }
  userLogin = '';
  isAdminUser = false;
  isloggin = false;
  currentScreen = '';

  setUserLogin(uname: string): void{
    this.userLogin = uname;
  }
  ngOnInit(): void {
    
     this.userservice.getUserData().subscribe(
      userData$ => {
        if (userData$ != null){
          this.setUserLogin(userData$.userName);
          this.isAdminUser = userData$.isAdminUser;
          this.isloggin = userData$.isLoggedIn;
        }
        return userData$;
      });



     this.userservice.getCurrentScreen().subscribe(
        currentScreen$ => {
          if (currentScreen$ != null){
            this.currentScreen = currentScreen$;
          }
        });
  }



  getcurrentScreen(element: string): string{
    if( this.currentScreen === element){
      return 'active';
    }else{
      return '';
    }
  }

}
