import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CreateReimb } from '../models/create-reimb';
import { Reimbursements } from '../models/reimbursements';

@Injectable({
  providedIn: 'root'
})
export class ReimbursementServiceTsService {



  constructor(private http:HttpClient) { }

  reimbursement:Reimbursements = {
    amount: "",
    description: "",
    reimbId: "",
    submitted: "",
    resolved: "",
    authorId: "",
    resolverId: "",
    statusId: "",
    typeId: ""
  }

  updateReimbursement(updateReimbursement:Reimbursements) {
    return this.http.put("http://localhost:5000/reimb", updateReimbursement);
  }

  createReq(newReq:CreateReimb) {
    return this.http.post("http://localhost:5000/reimb/reimb", newReq);
  }

}
