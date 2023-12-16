import { Component } from '@angular/core';
import { Income } from '../model/income';
import { FormBuilder } from '@angular/forms';
import { IncomeService } from '../service/income.service';


@Component({
  selector: 'app-income',
  templateUrl: './income.component.html',
  styleUrls: ['./income.component.css']
})
export class IncomeComponent {
  public incomes: Income[] = [];
  public incomeCategory: String[] = [];


  constructor(private incomeService: IncomeService,
    private formBuilder: FormBuilder){
  }


  myForm = this.formBuilder.group({
    category: '',
    date: '',
    amount: ''
  });
  

  ngOnInit(): void {
    this.getExpenseCategories();
    this.getIncomes();
  }

  public getIncomes(): void {
    this.incomeService.getAllSourceOfIncome().subscribe({
      next:(response : Income[]) => {
        this.incomes = response;
      },

      error: (err : any) => {
        console.log(err);
      }
    })
  }

  public addIncome() : void {
    console.log(this.myForm.value);

    var userId = Number(window.localStorage.getItem('userId'));

    this.incomeService.addIncome(this.myForm.value, userId).subscribe({
      next: () => {
        this.getIncomes();
        this.myForm.reset();
      },
      error: (err : any) => {
        console.log(err);
      }
    })
  }

  public getExpenseCategories():void{
    this.incomeService.getIncomeCategory().subscribe({
     next: (data: any) => {
       this.incomeCategory = data;
       console.log(this.incomeCategory);
     }
    })
 }

 update(value: string) : void{
  this.myForm.value.category = value
  console.log(this.myForm.value.category )
}
}
