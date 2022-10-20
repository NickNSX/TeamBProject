import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { NewReimbComponent } from './components/new-reimb/new-reimb.component';
import { RegisterComponent } from './components/register/register.component';
import { ReimbursementComponent } from './components/reimbursement/reimbursement.component';
import { UsersComponent } from './components/users/users.component';

const routes: Routes = [

  // add routes
  {path: "users", component: UsersComponent},
  {path: "", component: LoginComponent},
  {path: "register", component: RegisterComponent},
  {path: "reimbursement", component: ReimbursementComponent},
  {path: "newreimbursement", component: NewReimbComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
