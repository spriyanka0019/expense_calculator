import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthenticationService } from '../service/authentication.service';
import { SigninComponent } from '../signin/signin.component';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  public loginForm!:FormGroup;
  
  constructor(
    private authenticationService: AuthenticationService,
    private signInService: SigninComponent){

  }

  ngOnInit(){
    this.loginForm = new FormGroup({
      username: new FormControl('', Validators.required),
      password: new FormControl('', Validators.required)
    });
  }

  public onSubmit(){
    this.signInService.login(
      this.loginForm.get('username')!.value,  this.loginForm!.get('password')!.value
    ); 
  }

}
