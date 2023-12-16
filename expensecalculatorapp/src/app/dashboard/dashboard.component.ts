import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { ExpenseService } from '../service/expense.service';
import { IncomeService } from '../service/income.service';
import { SigninComponent } from '../signin/signin.component';


@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
})
export class DashboardComponent implements OnInit {
  constructor(private expenseService: ExpenseService,
    private incomeService: IncomeService,
    public signIn:SigninComponent,
    private formBuilder: FormBuilder){
    
  }
  totalExpenses: number = 0;
  totalIncome: number = 0;
  monthNameForExpense: string = "";
  monthNameForIncome: string = "";
  showExpenses: boolean = false;
  showIncomes: boolean = false;
  totalIncomeForSelectedMonth: number = 0;
  totalExpensesForSelectedMonth: number = 0;

  myForm = this.formBuilder.group({
    date: '',
  });

  myIncomeForm = this.formBuilder.group({
    date: '',
  });

  currentDate = new Date();

  ngOnInit() : void{
    this.showCurrentExpenses()
    this.showCurrentIncome()
    console.log("Show Exprense" , this.showExpenses)
  }


  public showExpensesForAMonth(date: any) : void {
      var month = date.split('-')[1];

      let newDate = new Date(date);
      const monthNames = ["January", "February", "March", "April", "May", "June",
      "July", "August", "September", "October", "November", "December"
      ];

     this.monthNameForExpense = monthNames[newDate.getMonth()];
      console.log("Date is " + date + " and Month is " + this.monthNameForExpense);
      var userId = Number(window.localStorage.getItem('userId'));
      this.expenseService.getExpensesForAMonth(month, userId).subscribe({
       next: (data: any) => {
        this.totalExpensesForSelectedMonth = data
        this.showExpenses = true
        this.myForm.reset();
       },
       error: (err : any) => {
        console.log(err);
       }
      })
   }


   public showTotalIncomeInAMonth(date: any) : void {
    var month = date.split('-')[1];

    let newDate = new Date(date);
    const monthNames = ["January", "February", "March", "April", "May", "June",
    "July", "August", "September", "October", "November", "December"
    ];

   this.monthNameForIncome = monthNames[newDate.getMonth()];
    console.log("Date is " + date + " and Month is " + this.monthNameForIncome);
    var userId = Number(window.localStorage.getItem('userId'));

    this.incomeService.getIncomeForAMonth(month,userId).subscribe({
     next: (data: any) => {
      this.totalIncomeForSelectedMonth = data
      this.showIncomes = true
      this.myForm.reset();
     },
     error: (err : any) => {
      console.log(err);
     }
    })
 }

 public showCurrentExpenses(): void {
   var  month = this.currentDate.getMonth()+1
   console.log(this.currentDate)
   console.log(month)
   var userId = Number(window.localStorage.getItem('userId'));

   this.expenseService.getExpensesForAMonth(month, userId).subscribe({
    next: (data: any) => {
     this.totalExpenses = data;
     this.myForm.reset();
    },
    error: (err : any) => {
     console.log(err);
    }
   })
 }

 public showCurrentIncome(): void {
  var  month = this.currentDate.getMonth()+1
  console.log(this.currentDate)
  console.log(month)
  var userId = Number(window.localStorage.getItem('userId'));

  this.incomeService.getIncomeForAMonth(month, userId).subscribe({
   next: (data: any) => {
    this.totalIncome = data;
    this.myForm.reset();
   },
   error: (err : any) => {
    console.log(err);
   }
  })
}

}
