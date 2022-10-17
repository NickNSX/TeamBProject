import { Component, OnInit } from '@angular/core';
import { Reimbursements } from 'src/app/models/reimbursements';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-reimbursements',
  templateUrl: './reimbursements.component.html',
  styleUrls: ['./reimbursements.component.css']
})
export class ReimbursementsComponent implements OnInit {


  constructor(private us:UserService) { }

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

  submitMessage:string = "";

  createReq:Reimbursements = {
    reimb_id: "",
    amount: "",
    submitted: "",
    resolved: "",
    description: "",
    author_id: "",
    resolver_id: "",
    status_id: "",
    type_id: ""
  }

  submitFunction() {
    this.submitMessage = "Your Reimbursement Request Has Been Submitted!";
    this.createReq.author_id = this.us.user.userId;
    console.log(this.createReq);
    
  }
 


}
