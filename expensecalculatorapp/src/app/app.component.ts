import { Component } from '@angular/core';
import { BnNgIdleService } from 'bn-ng-idle';
import { SigninComponent } from './signin/signin.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'expensecalculatorapp';

  constructor(private bnIdle: BnNgIdleService, private signIn : SigninComponent){
    this.bnIdle.startWatching(300).subscribe((res)=>{
      if(res){
        console.log("session expired")
        signIn.logout();
        
      }
    })
  }
}
