import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { ReimbursementsComponent } from './components/reimbursements/reimbursements.component';

const routes: Routes = [

  {path:"register",component:RegisterComponent},
  {path:"",component:LoginComponent},
  
  {path:"reimbursements",
  component:ReimbursementsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
