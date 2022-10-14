import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  constructor(private us:UserService) { }

  ngOnInit(): void {
  }

  error:boolean = true;
  success:boolean = true;
  errorMessage:string = "";
  idMessage:string = "";

  registerUser:User = { };

  register() {
    console.log(this.registerUser);
    this.us.register(this.registerUser).subscribe(
      (data:any) => {
        console.log(data);
        this.error = true;
        this.success = false;
        this.idMessage = data.resourceId;
      },
      err => {
        console.log(err.error.message);
        this.error = false;
        this.errorMessage = err.error.message + "!";
      }
      );
  }

}
