import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UserLoginComponent } from './user-login/user-login.component';
import { SearchComponent } from './search/search.component';
import { HeaderComponent } from './header/header.component';
import { UsersService } from './service/users.service';
import {GlobalDataService} from './service/global-data.service';
import { LogoutComponent } from './logout/logout.component';
import { AddLoanComponent } from './add-loan/add-loan.component';
import { UpdateLoanComponent } from './update-loan/update-loan.component';
import { RouterModule, Router } from '@angular/router';



@NgModule({
  declarations: [
    AppComponent,
    UserLoginComponent,
    SearchComponent,
    HeaderComponent,
    LogoutComponent,
    AddLoanComponent,
    UpdateLoanComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule
  ],

  providers:[UsersService, GlobalDataService],
  bootstrap: [AppComponent]
})
export class AppModule { }
