import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  constructor(private us:UserService) { }

  ngOnInit(): void {
    if (this.role == "Admin") {
      this.adminAccess = false;
      this.loggedIn = false;
      this.falseNumb();
      this.message = "Welcome " + this.givenName + "!";
    } else if (this.role != "Admin" && this.role != undefined) {
      this.adminAccess = true;
      this.loggedIn = false;
      this.message = "Welcome " + this.givenName + "!";
    } else {
      this.adminAccess = true;
      this.loggedIn = true;
      this.message = "Not Logged In";
    }
  }

  userId:any = this.us.user.userId;
  username:any = this.us.user.username;
  email:any = this.us.user.email;
  givenName:any = this.us.user.givenName;
  surname:any = this.us.user.surname;
  isActive:any = this.us.user.isActive;
  role:any = this.us.user.role;
  
  adminAccess:boolean = true;
  option1:boolean = true;
  showAllUsers:boolean = false;
  error:boolean = true;
  hideForm:boolean = true;
  loggedIn:boolean = true;
  
  message:string = "";
  id:string = "";
  errorMessage:string = "";
  idToSearch:string = "";
  
  falseN:number = 0;
  inActiveUser:any = [];
  userArray:any = [];

  updateInfo:User = { }


  search() {

    for (let i = this.userArray.length - 1; i >= 0; i--) {
      this.userArray.pop();
    };

    this.us.search(this.idToSearch).subscribe(
      (data:any) => {
        this.error = true;
        for (let i = 0; i < data.length; i++) {
          this.userArray.push(data[i]);
        }

        if (data.length == undefined) {
          this.userArray.push(data);
        }},
        err => {
          console.error(err.error.message);
          this.errorMessage = err.error.message + this.idToSearch;
          this.error=false;this.idToSearch="";
        }
        
      );
  }

  changeActive (userId:string, status:any) {
    
    if (status === true) {
      if (confirm("Confirm Deactivation?") == true) {
        this.us.deactivate(userId).subscribe();
        alert("User Deactivated.");
      } else {
        alert("User not deactivated.")
      }
    }
    if (status === false) {
      if (confirm("Confirm Activation?") == true) {
        this.updateInfo.userId = userId;
        this.updateInfo.isActive = true;
        this.us.updateUser(this.updateInfo).subscribe();
        this.updateInfo = { };
        alert("User Activated.");
      } else {
        alert("User not activated.")
      }
    }

    this.falseNumb();
  }

  update(userId:string) {

    if (confirm("Update Information?") == true) {
      this.updateInfo.userId = this.id;
      
      this.us.updateUser(this.updateInfo).subscribe(
        data => {alert("Updated");},
        err => {console.error(err.error.message);alert(err.error.message)}
      );
      this.closeForm();
      this.updateInfo = { };
    }
  }

  searchByUserId() {

    if (this.userArray.length > 0) {
      for (let i = this.userArray.length - 1; i >= 0; i--) {
        this.userArray.pop();
      };
    }
    if (this.option1 != false) {
      setTimeout(() => {
        this.option1 = false}, 100);
    }
  }


  openForm(userId:string) {
    this.hideForm = false;
    this.id = userId;
  }
  
  closeForm() {
    this.hideForm = true;
  }

  falseNumb() {
    this.falseN = 0;
    this.us.search(this.idToSearch).subscribe(
      (data:any) => {
        for (let i = 0; i < data.length; i++) {
          if(data[i].isActive === false) {
            this.inActiveUser.push(data[i]);
            this.falseN ++;
          }
        }
      }
    )
  }
}
