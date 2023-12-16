import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Expense } from '../model/expense';
import { ExpenseService } from '../service/expense.service';
import { FormBuilder } from '@angular/forms';


@Component({
  selector: 'app-expense',
  templateUrl: './expense.component.html',
  styleUrls: ['./expense.component.css']
})
export class ExpenseComponent implements OnInit {
  public expenses: Expense[] = [];
  public expenseCategory: String[] = [];

  constructor(private expenseService: ExpenseService,
    private formBuilder: FormBuilder){
    
  }

  myForm = this.formBuilder.group({
    category: '',
    date: '',
    amount: '',
  });
  
  ngOnInit(): void {
    this.getExpenseCategories();
    this.getExpenses();
  }

  update(value: string) : void{
    this.myForm.value.category = value
    console.log(this.myForm.value.category )
  }

 

  public getExpenseCategories():void{
     this.expenseService.getExpenseCategory().subscribe({
      next: (data: any) => {
        this.expenseCategory = data;
        console.log(this.expenseCategory);
      }
     })
  }

  public getExpenses(): void {
    this.expenseService.getAllExpenses().subscribe({
      next:(response : Expense[]) => {
        this.expenses = response;
      },

      error: (err : any) => {
        console.log(err);
      }
    })
  }

  public addExpense() : void {
    console.log(this.myForm.value);
    var userId = Number(window.localStorage.getItem('userId'));

    this.expenseService.addExpense(this.myForm.value, userId).subscribe({
      next: () => {
        this.getExpenses();
        this.myForm.reset();
      },
      error: (err : any) => {
        console.log(err);
      }
    })
  }
}
