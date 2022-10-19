import { Component, OnInit } from '@angular/core';
import { NewReimb } from 'src/app/models/new-reimb';
import { ReimbursementService } from 'src/app/services/reimbursement.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-new-reimb',
  templateUrl: './new-reimb.component.html',
  styleUrls: ['./new-reimb.component.css']
})
export class NewReimbComponent implements OnInit {

  constructor(private rs:ReimbursementService, private us:UserService) { }

  ngOnInit(): void {
    
    if (this.us.user.role == undefined) {
      this.loginIn = true;
      this.loginMessage = false;
    } else {
      this.loginIn = false;
      this.loginMessage = true;
    }
  }

  loginIn:boolean = true;
  loginMessage:boolean = false;

  userId:any = this.us.user.userId;
  submitMessage:string = "";
  reimbId:string = "";

  newReq:NewReimb = {
    userId: "",
    amount: 0,
    description: "",
    type: ""
  }

  submitFunction() {
    this.newReq.userId = this.userId;
    
    this.rs.createReq(this.newReq).subscribe(
      (data:any) => {
        this.submitMessage = "Your Reimbursement Request Has Been Submitted!";
        this.reimbId = data.resourceId;
      },

      err => {
        alert(err.error.message);
      }
    )
  }

}
