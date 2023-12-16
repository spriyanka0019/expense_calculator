import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs'
import { Expense } from '../model/expense';
import { environments } from './environment.service';

@Injectable({
    providedIn:'root'
})

export class ExpenseService{
    // private apiServerUrl = environments.API_BASE

    private apiServerUrl = 'http://lb-spring-267487076.us-east-2.elb.amazonaws.com';

    // private apiServerUrl = window.location.origin;
 
    constructor(private http: HttpClient){

    }

    public getAllExpenses():Observable<Expense[]>{
        return this.http.get<any>(this.apiServerUrl+`/v1/expensecalc/all`)
    }

    public getExpenseCategory():Observable<String[]>{
        return this.http.get<any>(this.apiServerUrl+`/v1/expensecalc/getcategory`)
    }

    public addExpense(expense: Expense, userId: number):Observable<Expense>{
        console.log("ExpenseService: addExpense", expense)
        return this.http.post<Expense>(this.apiServerUrl+`/v1/expensecalc/add/${userId}`, expense)
    }

    public updateExpense(expense: Expense ):Observable<Expense>{
        return this.http.put<Expense>(this.apiServerUrl+`/v1/expensecalc/update/`, expense)
    }

    public deleteExpense(expenseId: number ):Observable<void>{
        return this.http.delete<void>(this.apiServerUrl+`/v1/expensecalc/delete/${expenseId}`)
    }

    public getExpensesForAMonth(date: number, userId: number):Observable<number>{
        return this.http.get<any>(this.apiServerUrl+`/v1/expensecalc/sum/${userId}/${date}`)
    }


}