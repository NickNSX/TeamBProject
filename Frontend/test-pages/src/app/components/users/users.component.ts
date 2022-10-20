import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

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
  showAll:boolean = true;
  showNonActive:boolean = true;
  showActive:boolean = true;
  showEmployee:boolean = true;
  showAdmin:boolean = true;
  showFinance:boolean = true;

  message:string = "";
  id:string = "";
  errorMessage:string = "";
  idToSearch:string = "";
  displayMessage:string = "";
  
  falseN:number = 0;
  trueN:number = 0;
  employeeN:number = 0;
  adminN:number = 0;
  financeN:number = 0;
  
  inActiveUser:any = [];
  activeUser:any = [];
  userArray:any = [];
  employeeUser:any = [];
  adminUser:any = [];
  financeUser:any = [];

  updateInfo:User = { }

  constructor(private us:UserService) { }

  ngOnInit(): void {

    if (this.role == "Admin") {
      this.adminAccess = false;
      this.loggedIn = false;
      this.falseNumb();
      this.sort();
      this.message = "Welcome " + this.givenName + "!";
    } else if (this.role != "Admin" && this.role != undefined) {
      this.adminAccess = true;
      this.loggedIn = false;
      this.message = "Welcome " + this.givenName + "!";
    } else {
      this.adminAccess = true;
      this.loggedIn = true;
      this.message = "Please Log In!";
    }
    
  }
  
  search() {

    this.showActive = true;
    this.showEmployee = true;
    this.showAdmin = true;
    this.showFinance = true;
    this.showNonActive = true;
    this.option1 = true;
    this.showAll = false;

    for (let i = this.userArray.length - 1; i >= 0; i--) {
      this.userArray.pop();
    };

    if (this.idToSearch.length > 0) {
      this.displayMessage = "Searched User:";
    } else {
      this.displayMessage = "Searched Users:"
    }

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
        this.falseN ++;
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
        this.falseN --;
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
    this.sort();
  }

  searchByUserId() {
    this.showNonActive = true;
    this.showActive = true;
    this.showEmployee = true;
    this.showAdmin = true;
    this.showFinance = true;
    this.showAll = false;
    this.displayMessage = "";
    if (this.userArray.length > 0) {
      for (let i = this.userArray.length - 1; i >= 0; i--) {
        this.userArray.pop();
      };
    }
    if (this.option1 != false) {
      setTimeout(() => {
        this.option1 = false}, 50);
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
    if (this.inActiveUser.length > 0) {
      for (let i = this.inActiveUser.length; i > 0; i--) {
        this.inActiveUser.pop();
      }
    }
    if (this.activeUser.length > 0) {
      for (let i = this.activeUser.length; i > 0; i--) {
        this.activeUser.pop();
      }
    }
    this.us.search(this.idToSearch).subscribe(
      (data:any) => {
        for (let i = 0; i < data.length; i++) {
          if(data[i].isActive === false) {
            this.inActiveUser.push(data[i]);
            this.falseN ++;
          } else {
            this.activeUser.push(data[i]);
            this.trueN ++;
          }
        }
      }
      );
  }

  sort() {
    if (this.employeeUser.length > 0) {
      for (let i = this.employeeUser.length; i > 0; i--) {
        this.employeeUser.pop();
      }
    }
    if (this.adminUser.length > 0) {
      for (let i = this.financeUser.length; i > 0; i--) {
        this.adminUser.pop();
      }
    }
    if (this.financeUser.length > 0) {
      for (let i = this.financeUser.length; i > 0; i--) {
        this.financeUser.pop();
      }
    }
    this.us.search(this.idToSearch).subscribe(
      (data:any) => {
        for (let i = 0; i < data.length; i++) {
          if (data[i].role === "Employee") {
            this.employeeUser.push(data[i]);
            this.employeeN ++;
          } else if (data[i].role === "Admin") {
            this.adminUser.push(data[i]);
            this.adminN ++;
          } else {
            this.financeUser.push(data[i]);
            this.financeN ++;
          }
        }
      }
      );
  }
    
  showInActive() {
      this.showAll = true;
      this.showActive = true;
      this.showEmployee = true;
      this.showAdmin = true;
      this.showFinance = true;
      this.showNonActive = false;
      if (this.falseN > 1) {
        this.displayMessage = "In Active Users:";
      } else {
        this.displayMessage = "In Active User:"
      }
  }

  showAllActive() {
    this.showAll = true;
    this.showNonActive = true;
    this.showEmployee = true;
    this.showAdmin = true;
    this.showFinance = true;
    this.showActive = false;
    if (this.falseN > 1) {
      this.displayMessage = "Active Users:";
    } else {
      this.displayMessage = "Active User:"
    }
  }

  showAllEmployee() {
    this.showAll = true;
    this.showNonActive = true;
    this.showActive = true;
    this.showAdmin = true;
    this.showFinance = true;
    this.showEmployee = false;
    if (this.employeeN > 1) {
      this.displayMessage = "Employees:";
    } else {
      this.displayMessage = "Employee:"
    }
  }

  showAllAdmin() {
    this.showAll = true;
    this.showNonActive = true;
    this.showActive = true;
    this.showEmployee = true;
    this.showFinance = true;
    this.showAdmin = false;
    if (this.adminN > 1) {
      this.displayMessage = "Admins:";
    } else {
      this.displayMessage = "Admin:"
    }
  }

  showAllFinance() {
    this.showAll = true;
    this.showNonActive = true;
    this.showActive = true;
    this.showEmployee = true;
    this.showAdmin = true;
    this.showFinance = false;
    if (this.financeN > 1) {
      this.displayMessage = "Finance Managers:";
    } else {
      this.displayMessage = "Finance Manager:"
    }
  }

}
