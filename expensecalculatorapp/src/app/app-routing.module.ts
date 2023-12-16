import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ExpenseComponent } from './expense/expense.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { IncomeComponent } from './income/income.component';
import { SigninComponent } from './signin/signin.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { AuthGuard } from './helpers/auth.guard';

const routes: Routes = [
  {path: '' , redirectTo:'login',pathMatch:'full'},
    {
      path:'',
      component: DashboardComponent,
      canActivate: [AuthGuard],
    },
    {
      path:'login',
      component: LoginComponent
    },
    { path: 'register', 
      component: RegisterComponent
    },
    {
      path: 'expense',
      component: ExpenseComponent
    },
    {
      path: 'income',
      component: IncomeComponent
    },
    {
      path: 'dashboard',
      component: DashboardComponent
    },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
 

 }
