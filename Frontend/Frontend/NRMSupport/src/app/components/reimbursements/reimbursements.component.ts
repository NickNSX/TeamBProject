import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-reimbursements',
  templateUrl: './reimbursements.component.html',
  styleUrls: ['./reimbursements.component.css']
})
export class ReimbursementsComponent implements OnInit {


  constructor() { }


  ngOnInit(): void {
  }


  ngOnInit(): void { }

  submitMessage:string = "";

  submit:any = document.getElementById("submit");

  domFunction() {
    this.submitMessage = "Your Reimbursement Request Has Been Submitted!";
  }
 


}
