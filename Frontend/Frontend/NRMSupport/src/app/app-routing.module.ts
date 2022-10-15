import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
<<<<<<< HEAD
import { ReimbursementsComponent } from './components/reimbursements/reimbursements.component';
=======
import { UserComponent } from './components/user/user.component';
>>>>>>> 5f6a0137a8cc6d19b373626bd4505fde78a905dc

const routes: Routes = [

  {path:"register",component:RegisterComponent},
  {path:"",component:LoginComponent},
<<<<<<< HEAD
  
  {path:"reimbursements",
  component:ReimbursementsComponent}
=======
  {path:"user",component:UserComponent},
>>>>>>> 5f6a0137a8cc6d19b373626bd4505fde78a905dc
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
