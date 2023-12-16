import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs'
import { Income } from '../model/income';
import { environments } from './environment.service';

@Injectable({
    providedIn:'root'
})

export class IncomeService {
    // private apiServerUrl = environments.API_BASE


    // private apiServerUrl = window.location.origin;
    private apiServerUrl = 'http://lb-spring-267487076.us-east-2.elb.amazonaws.com';

    constructor(private http: HttpClient){
    }

    public getAllSourceOfIncome():Observable<Income[]>{
        return this.http.get<any>(this.apiServerUrl+`/v1/income/all`)
    }

    public addIncome(income: Income, userId: number):Observable<Income>{
        return this.http.post<Income>(this.apiServerUrl+`/v1/income/add/${userId}`, income)
    }

    public updateIncome(income: Income):Observable<Income>{
        return this.http.put<Income>(this.apiServerUrl+`/v1/income/update/`, income)
    }

    public deleteIncome(incomeId: number ):Observable<void>{
        return this.http.delete<void>(this.apiServerUrl+`/v1/income/delete/${incomeId}`)
    }

    public getIncomeForAMonth(date: number, userId:number):Observable<number>{
        return this.http.get<any>(this.apiServerUrl+`/v1/income/sum/${userId}/${date}`)
    }

    public getIncomeCategory():Observable<String[]>{
        return this.http.get<any>(this.apiServerUrl+`/v1/income/getcategory`)
    }


}