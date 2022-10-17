import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { ReimbursementsComponent } from './components/reimbursements/reimbursements.component';
import { UserComponent } from './components/user/user.component';

const routes: Routes = [

  {path:"register",component:RegisterComponent},
  {path:"",component:LoginComponent},
  {path:"user",component:UserComponent},
  
  {path:"reimbursements",
  component:ReimbursementsComponent},

  
  {path:"register", component:RegisterComponent},
  {path:"", component:LoginComponent},
  
  {path:"reimbursements", component:ReimbursementsComponent},
  {path:"user", component:UserComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
