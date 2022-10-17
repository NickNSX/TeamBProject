import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-reimbursements',
  templateUrl: './reimbursements.component.html',
  styleUrls: ['./reimbursements.component.css']
})
export class ReimbursementsComponent implements OnInit {


  constructor() { }

  ngOnInit(): void { }

  submitMessage:string = "";
  description:string = "";
  type:string = "";

  amount:number = 0;

  submit:any = document.getElementById("submit");

  submitFunction() {
    this.submitMessage = "Your Reimbursement Request Has Been Submitted!";
  }
 


}
