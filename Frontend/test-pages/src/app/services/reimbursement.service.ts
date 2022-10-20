import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { NewReimb } from '../models/new-reimb';
import { Reimbursement } from '../models/reimbursement';
import { UpdateReimb } from '../models/update-reimb';

@Injectable({
  providedIn: 'root'
})
export class ReimbursementService {

  constructor(private http:HttpClient) { }

  // reimbursement:Reimbursement = { }

  getAll(path:string):Observable<HttpResponse<Reimbursement>> {
    return this.http.get("http://localhost:5000/reimb/reimb/" + path) as Observable<HttpResponse<Reimbursement>>;
  }

  searchById(path:string):Observable<HttpResponse<Reimbursement>> {
    return this.http.get("http://localhost:5000/reimb/reimb/" + path) as Observable<HttpResponse<Reimbursement>>;
  }

  searchByStatus(path:string):Observable<HttpResponse<Reimbursement>> {
    return this.http.get("http://localhost:5000/reimb/reimb/status/" + path) as Observable<HttpResponse<Reimbursement>>;
  }

  createRequest(newReimb:NewReimb) {
    return this.http.post("http://localhost:5000/reimb/reimb/", newReimb);
  }

  update(path:string, updateInfo:UpdateReimb) {
    return this.http.put("http://localhost:5000/reimb/reimb/" + path, updateInfo);
  }
  

  getReimbursement(path:string):Observable <HttpResponse<Reimbursement>>{
    return this.http.get("http://localhost:5000/reimb/reimb/" +path ) as Observable<HttpResponse<Reimbursement>>;
  }
  // getAll(path:string):Observable<HttpResponse<Reimbursement>> {
  //   return this.http.get("http://localhost:5000/reimb/reimb/" + path) as Observable<HttpResponse<Reimbursement>>;
  // }

  updateReimbursement(updateReimbursement:Reimbursement) {
    return this.http.put("http://localhost:5000/reimb", updateReimbursement);
  }

  createReq(newReq:NewReimb) {
    return this.http.post("http://localhost:5000/reimb/reimb", newReq);
  }

}
