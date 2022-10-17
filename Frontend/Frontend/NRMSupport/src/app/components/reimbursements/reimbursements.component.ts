import { Component, OnInit } from '@angular/core';
import { CreateReimb } from 'src/app/models/create-reimb';
import { ReimbursementServiceTsService } from 'src/app/services/reimbursement.service.ts.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-reimbursements',
  templateUrl: './reimbursements.component.html',
  styleUrls: ['./reimbursements.component.css']
})
export class ReimbursementsComponent implements OnInit {


  constructor(private us:UserService, private rs:ReimbursementServiceTsService) { }

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

  newReq:CreateReimb = {
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
