import { CanActivateFn } from '@angular/router';
import { Component, Injectable, inject } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  CanActivate,
  Router,
  RouterStateSnapshot,
} from '@angular/router';
import { SigninComponent } from '../signin/signin.component';


@Injectable({
  providedIn: 'root',
})
class PermissionsService {

  constructor(private router: Router,
    private signIn: SigninComponent) {}

  canActivate(next: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
      if(!this.signIn.isLoggedIn()){
        this.router.navigate(['/login']);
      }
      return true;
  }
}
export const AuthGuard: CanActivateFn = (next: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean => {
  return inject(PermissionsService).canActivate(next, state);
}
