import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private us:UserService) { }

  ngOnInit(): void {
  }

  message:string = "Login";
  invalidCred:boolean = true;

  user:User = { };

  submitData () {
    
    this.us.login(this.user).subscribe(

      (data:any) => {
        
        this.us.user = data;
        this.route.navigate(['users']);
      },

      () => {
        this.message = "Failed";
        this.invalidCred = false;
        this.user.username = "";
        this.user.password = "";
      }
    );
  }

}
