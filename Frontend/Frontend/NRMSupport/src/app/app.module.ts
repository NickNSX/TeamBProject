import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ReimbursementsComponent } from './components/reimbursements/reimbursements.component';
import { RegisterComponent } from './components/register/register.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { LoginComponent } from './components/login/login.component';
import { UserComponent } from './components/user/user.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { InputboxComponent } from './components/inputbox/inputbox.component';

@NgModule({
  declarations: [
    AppComponent,
    ReimbursementsComponent,
    RegisterComponent,
    LoginComponent,
    UserComponent,
    NavbarComponent,
    InputboxComponent,
    

  ],


  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule

  ],

  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
