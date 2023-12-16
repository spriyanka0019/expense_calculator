import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../model/user';
import { AuthenticationResponse } from '../model/authenticationResponse';
import { environments } from './environment.service';


@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private apiServerUrl = 'http://lb-spring-267487076.us-east-2.elb.amazonaws.com';
      // private apiServerUrl = 'http://localhost:8080'

 
    constructor(private http: HttpClient){}

    public login(username:string, password: string): Observable<AuthenticationResponse> {
      const payload = {
        'email': username,
        'password': password
      }
      console.log("This has been called", this.apiServerUrl);
      // return this.http.post<any>(this.apiServerUrl+`/v1/auth/authenticate`, payload)
      return this.http.post<any>(this.apiServerUrl+ `/v1/auth/authenticate`, payload)
    }

    public register(firstname:string, lastname:string, email: string,  password:string,): Observable<AuthenticationResponse> {
      const payload = {
        'firstname': firstname,
        'lastname': lastname,
        'email':  email,
        'password': password
      }
      console.log("Called me", payload)
      // return this.http.post<any>(this.apiServerUrl+`/v1/auth/register`, payload)
      return this.http.post<any>(this.apiServerUrl+`/v1/auth/register`, payload)
    }
}
