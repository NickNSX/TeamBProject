import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  user:User = { }

  constructor(private http:HttpClient) { }

  login(userCreds:User):Observable<HttpResponse<User>> {
    return this.http.post("http://localhost:5000/reimb/auth/", userCreds) as Observable<HttpResponse<User>>;
  }

  // login(userCreds:User):Observable<HttpResponse<User>> {
  //   return this.http.post("http://nrmapi-env.eba-yk2s3wvg.us-west-1.elasticbeanstalk.com/reimb/auth", userCreds) as Observable<HttpResponse<User>>;
  // }

  search(idToSearch:string):Observable<HttpResponse<User>> {
    return this.http.get("http://localhost:5000/reimb/users/" + idToSearch) as Observable<HttpResponse<User>>;
  }

  deactivate(userId:string) {
    return this.http.delete("http://localhost:5000/reimb/users/" + userId);
  }

  updateUser(updateUser:User) {
    return this.http.put("http://localhost:5000/reimb/users/", updateUser);
  }

  // register(newUser:User) {
  //   return this.http.post("http://nrmapi-env.eba-yk2s3wvg.us-west-1.elasticbeanstalk.com/reimb/users/", newUser);
  // }
  register(newUser:User) {
    return this.http.post("http://localhost:5000/reimb/users/", newUser);
  }

  logOut () {
    this.user = { }
  }
  
}
