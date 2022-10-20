import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CreateReimb } from '../models/create-reimb';
import { Reimbursements } from '../models/reimbursements';

@Injectable({
  providedIn: 'root'
})
export class ReimbursementServiceTsService {



  constructor(private http:HttpClient) { }

  // reimbursement:Reimbursements = {
  //   amount: "",
  //   description: "",
  //   reimbId: "",
  //   submitted: "",
  //   resolved: "",
  //   authorId: "",
  //   resolverId: "",
  //   statusId: "",
  //   typeId: ""
  // }


  getReimbursement(path:string):Observable <HttpResponse<Reimbursements>>{
    return this.http.get("http://localhost:5000/reimb/reimb/" +path ) as Observable<HttpResponse<Reimbursements>>;
  }
  // getAll(path:string):Observable<HttpResponse<Reimbursement>> {
  //   return this.http.get("http://localhost:5000/reimb/reimb/" + path) as Observable<HttpResponse<Reimbursement>>;
  // }


  updateReimbursement(updateReimbursement:Reimbursements) {
    return this.http.put("http://localhost:5000/reimb", updateReimbursement);
  }

  createReq(newReq:CreateReimb) {
    return this.http.post("http://localhost:5000/reimb/reimb", newReq);
  }

}
