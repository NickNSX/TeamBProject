import { Component, OnInit } from '@angular/core';
import { Reimbursement } from 'src/app/models/reimbursement';
import { ReimbursementService } from 'src/app/services/reimbursement.service';
import { UserService } from 'src/app/services/user.service';
import { UpdateReimb } from 'src/app/models/update-reimb';

@Component({
  selector: 'app-reimbursement',
  templateUrl: './reimbursement.component.html',
  styleUrls: ['./reimbursement.component.css']
})
export class ReimbursementComponent implements OnInit {

  constructor(private rs: ReimbursementService, private us: UserService) { }

  ngOnInit(): void {

    this.hiddenGrid = true;
    this.loggedIn = true;

    if (this.us.user.role != undefined) {
      this.loggedIn = false;
    }

    if (this.us.user.role == 'Finance Manager') {
      this.statusUpdate = false;
    } else {
      this.statusUpdate = true;
    }

    if (this.us.user.role == undefined) {
      this.noReimb = false;
    }
  }

  loggedIn:boolean = true;
  noReimb: boolean = true;
  hideForm: boolean = true;
  statusUpdate: boolean = true;
  ownerUpdate: boolean = true;

  hiddenGrid: boolean = true;

  idToSearch: string = "";
  statusToSearch: string = "";

  allReimb: any = [];
  reimb: Reimbursement = {};
  updateInfo: UpdateReimb = { reimbId: "" }

  remiArray: any = [];
  ReimSingle: Reimbursement = {};

  getAll() {
    this.noReimb = true;
    this.hiddenGrid = false;


    if (this.allReimb.length > 0) {
      for (let i = this.allReimb.length - 1; i >= 0; i--) {
        this.allReimb.pop();
      }
    }

    if (this.us.user.role == "Finance Manager") {

      this.rs.getAll("/manager").subscribe(
        
        (data: any) => {
          console.log("Getting all Reimb");
          for (let i = 0; i < data.length; i++) {
            this.allReimb.push(data[i]);
          }
        },

        err => {
          if (err.error.statusCode == 404) {
            console.error("No Information Found");
            this.noReimb = false;
          }
        }

      )

    } else if (this.us.user.role != "Finance Manager" && this.us.user.role != undefined) {

      this.rs.getAll("/employee/" + this.us.user.userId).subscribe(

        (data: any) => {
          console.log("Getting all reimb for employee");
          for (let i = 0; i < data.length; i++) {
            this.allReimb.push(data[i]);
          }
        },

        err => {
          if (err.error.status == 404) {
            console.error("No Information Found");
            this.noReimb = false;
          }
        }

      )
    } else {
      console.log("Not Login in");
    }
  }

  searchById() {

    if (this.idToSearch.length <= 0) {
      alert("Enter id to search");
      return;
    }

    this.noReimb = true;
    this.hiddenGrid = false;

    if (this.allReimb.length > 0) {
      for (let i = this.allReimb.length - 1; i >= 0; i--) {
        this.allReimb.pop();
      }
    }

    this.rs.searchById(this.idToSearch).subscribe(

      (data: any) => {
        console.log("Searching by id.");
        
        for (let i = 0; i < data.length; i++) {
          this.allReimb.push(data[i]);
        }

        if (data.length == undefined) {
          this.allReimb.push(data);
        }
      },

      err => {
        if (err.error.status == 404) {
          console.error("No Information Found");
          this.noReimb = false;
        }
      }
    )
  }

  searchByStatus() {

    if (this.statusToSearch.length <= 0) {
      alert("Enter status to search");
      return;
    }

    this.noReimb = true;
    this.hiddenGrid = false;

    if (this.allReimb.length > 0) {
      for (let i = this.allReimb.length - 1; i >= 0; i--) {
        this.allReimb.pop();
      }
    }

    this.rs.searchByStatus(this.statusToSearch).subscribe(

      (data: any) => {
        
        if (this.us.user.role != "Finance Manager") {
          console.log("Reimb by status for Finance Manger");
          
          for (let i = 0; i < data.length; i++) {
            if (this.us.user.userId == data[i].authorId) {
            this.allReimb.push(data[i]);
            }
          }
        } else {
          console.log("Reimb by status for employee and admin");
          for (let i = 0; i < data.length; i++) {
            this.allReimb.push(data[i]);
          }
  
          if (data.length == undefined) {
            this.allReimb.push(data);
          }
        }
      },

      err => {
        if (err.error.status == 404) {
          console.error("No information found.");
          this.noReimb = false;
        }
        console.error(err.error.message);
      }
    )
  }

  openForm(reimbId: string, authorId: string) {
    this.hideForm = false;
    this.updateInfo.reimbId = reimbId;

    if (authorId == this.us.user.userId) {
      this.ownerUpdate = false;
      this.statusUpdate = true;
    } else {
      this.statusUpdate = false;
      this.ownerUpdate = true;
    }
  }

  closeForm() {
    this.hideForm = true;
    this.updateInfo = { reimbId: "" }
  }

  update() {
    console.log(this.updateInfo);
    if (this.updateInfo.status == undefined) {
      console.log("Employee");
      console.log(this.updateInfo);
      this.rs.update("/employee", this.updateInfo).subscribe();
    } else if (this.updateInfo.status == "Approved" || this.updateInfo.status == "Denied") {
      console.log("Manger");
      this.updateInfo.resolverId = this.us.user.userId;
      console.log(this.updateInfo);
      this.rs.update("/manager", this.updateInfo).subscribe();
    } else {
      console.log("Nothing");
    }
    this.closeForm();

  }

  showReim() {
    this.hiddenGrid = false;
  }

  getAllReim() {

    if (this.remiArray.length > 0) {
      for (let i = this.remiArray.length; i >= 0; i--) {
        this.remiArray.pop()
      }
    }
    if (this.us.user.role == "Finance Manager") {
      this.rs.getReimbursement("/manager").subscribe(
        (data: any) => {
          console.log(data)
          for (let i = 0; i < data.length; i++) {
            this.remiArray.push(data[i]);
          }
        }
      )
    } else if (this.us.user.role != "Finance Manager" && this.us.user.role != undefined) {
      this.rs.getReimbursement("/employee/" + this.us.user.userId).subscribe(
        (data: any) => {
          console.log(data);
          for (let i = 0; i < data.length; i++) {
            this.remiArray.push(data[i]);
          }
        }
      )
    }
  }

}
