import {
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { SigninComponent } from '../signin/signin.component';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {
    constructor(public signIn:SigninComponent){

    }
    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        if(this.signIn.isLoggedIn()) {
            let newRequest = request.clone({
                setHeaders: {
                    Authorization: `Bearer ${this.signIn.getToken()}`
                },

            });

            return next.handle(newRequest);
        }

        return next.handle(request);
    }

}