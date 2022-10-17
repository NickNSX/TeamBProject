import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ReimbursementServiceTsService {



  constructor(private http:HttpClient) { }

  reimbursement:Reimbursement = {
    amount:"",
    description:""
  }

  updateReimbursement(updateReimbursement:Reimbursement) {
    return this.http.put("http://localhost:5000/reimb", updateReimbursement);
  }

}
