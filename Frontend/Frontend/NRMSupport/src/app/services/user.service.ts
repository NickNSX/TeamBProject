import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http:HttpClient) { }

  user:User = {
    username:"",
    password:""
  }

  login(userCreds:User):Observable<HttpResponse<User>> {
    return this.http.post("http://localhost:5000/reimb/auth/", userCreds) as Observable<HttpResponse<User>>;
  }

  search(idToSearch:string):Observable<HttpResponse<User>> {
    return this.http.get("http://localhost:5000/reimb/users/" + idToSearch) as Observable<HttpResponse<User>>;
  }

  deactivate(userId:string) {
    return this.http.delete("http://localhost:5000/reimb/users/" + userId);
  }

  updateUser(updateUser:User) {
    return this.http.put("http://localhost:5000/reimb/users/", updateUser);
  }

  register(newUser:User) {
    return this.http.post("http://localhost:5000/reimb/users/", newUser);
  }

  logOut () {
    this.user = { }
  }
}
