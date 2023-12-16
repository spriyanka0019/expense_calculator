package tech.org.expensecalculator.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.org.expensecalculator.exception.UserNotFoundException;
import tech.org.expensecalculator.model.Expense;
import tech.org.expensecalculator.model.ExpenseCategoryType;
import tech.org.expensecalculator.model.Income;
import tech.org.expensecalculator.model.IncomeCategoryType;
import tech.org.expensecalculator.service.IncomeService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("v1/income")
public class IncomeResource {
    private final IncomeService incomeService;

    public IncomeResource(IncomeService incomeService){
        this.incomeService = incomeService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Income>> getAllIncomes() {
        List<Income> incomes = incomeService.findAllIncomes();
        return new ResponseEntity<>(incomes, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Income> getExpenseById(@PathVariable("id")  Long id) {
        Income income = incomeService.findIncomeById(id);
        return new ResponseEntity<>(income, HttpStatus.OK);
    }

//    @PostMapping("/add")
//    public ResponseEntity<Income> addExpense(@RequestBody Income income){
//        Income newIncome = incomeService.addIncome(income);
//        return new ResponseEntity<>(newIncome, HttpStatus.CREATED);
//    }

    @PostMapping("/add/{userId}")
    public ResponseEntity<Income> addIncome(@RequestBody Income income, @PathVariable("userId") Long userId) {
        Income newIncome = incomeService.addIncome(income, userId);
        return new ResponseEntity<>(newIncome, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Income> updateIncome(@RequestBody Income income){
        Income updateIncome = incomeService.updateIncome(income);
        return new ResponseEntity<>(updateIncome, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteIncome(@PathVariable("id") Long id){
        incomeService.deleteIncome(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/sum/{userId}/{month}")
    public ResponseEntity<Integer> getIncomeSumByUserInMonth(@PathVariable("month") int month,
                                                             @PathVariable("userId") Long userId) {
        try {
            int totalIncomeByMonth = incomeService.getIncomeSumByUserInMonth(month, userId);
            return new ResponseEntity<>(totalIncomeByMonth, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException(e.getLocalizedMessage());
        }
    }

    @GetMapping("/getcategory")
    public ResponseEntity<List<String>> getAllIncomeCategory() {
        List<String> categoryTypes = Stream.of(IncomeCategoryType.values()).
                map(IncomeCategoryType::getLabel).collect(Collectors.toList());
        return new ResponseEntity<>(categoryTypes, HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<Income>> findIncomeByUserId(@PathVariable("id") Long id) {
        try {
            List<Income> income = incomeService.findIncomeByUserId(id);
            return new ResponseEntity<>(income, HttpStatus.OK);
        }catch (UserNotFoundException e){
            throw new UserNotFoundException(e.getLocalizedMessage());
        }
    }


}
