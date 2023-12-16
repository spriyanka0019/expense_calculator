import { HttpClient } from '@angular/common/http';
import { Component, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthenticationService } from '../service/authentication.service';
import { Router } from '@angular/router';
import { User } from '../model/user';
import { FormBuilder } from '@angular/forms';
import { AuthenticationResponse } from '../model/authenticationResponse';

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.css']
})

@Injectable({
  providedIn: 'root'
})
export class SigninComponent {
  public userDetails: User[] = [];
  public authenticationResponse: AuthenticationResponse[] = [];

  private tokenKey = 'token';
  private userId = 'userId'

  constructor(private router: Router, 
    private authenTication: AuthenticationService,
    private formBuilder: FormBuilder){

  }
  

  public login(username: string, password: string) : void {
    this.authenTication.login(username, password).subscribe((authenticationResponse: AuthenticationResponse) => {
      window.localStorage.setItem(this.tokenKey, authenticationResponse.token);
      window.localStorage.setItem(this.userId, authenticationResponse.userId);

      this.router.navigate(['/dashboard'])
    })
  }

  public register(firstname: string, lastname:string, password: string, email:string) : void {
    this.authenTication.register(firstname, lastname, password, email).subscribe((authenticationResponse: AuthenticationResponse) => {
      window.localStorage.setItem(this.tokenKey, authenticationResponse.token);
      window.localStorage.setItem(this.userId, authenticationResponse.userId);
      this.router.navigate(['/dashboard'])
    });
  }

  public logout() {
    localStorage.removeItem(this.tokenKey);
    localStorage.removeItem(this.userId);

    this.router.navigate(['/login'])
  }

  public isLoggedIn(): boolean {
    let token = localStorage.getItem(this.tokenKey);
    return token!=null && token.length>0;
  }

  public getToken(): string | null {
    return this.isLoggedIn() ? localStorage.getItem(this.tokenKey) : null
  }
}
