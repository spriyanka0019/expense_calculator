package tech.org.expensecalculator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.org.expensecalculator.exception.UserNotFoundException;
import tech.org.expensecalculator.model.Expense;
import tech.org.expensecalculator.model.ExpenseCategoryType;
import tech.org.expensecalculator.service.ExpenseService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("v1/expensecalc")
public class ExpenseResource {
    private final ExpenseService expenseService;

    @Autowired
    public ExpenseResource(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Expense>> getAllExpenses() {
        List<Expense> expenses = expenseService.findAllExpenses();
        return new ResponseEntity<>(expenses, HttpStatus.OK);
    }

    @GetMapping("/getcategory")
    public ResponseEntity<List<String>> getAllExpenseCategory() {
        List<String> categoryTypes = Stream.of(ExpenseCategoryType.values()).
                map(ExpenseCategoryType::getLabel).collect(Collectors.toList());
        return new ResponseEntity<>(categoryTypes, HttpStatus.OK);
    }

    @GetMapping("/sum/{userId}/{month}")
    public ResponseEntity<Integer> getExpenseByMonth(@PathVariable("month") int month,
                                                     @PathVariable("userId") Long userId) {
        int totalExpenseByMonth = expenseService.findExpenseSumByUserinMonth(month, userId);
        return new ResponseEntity<>(totalExpenseByMonth, HttpStatus.OK);
    }

    @PostMapping("/add/{id}")
    public ResponseEntity<Expense> addExpense(@RequestBody Expense expense, @PathVariable("id") Long id) {
        Expense newExpense = expenseService.addExpense(expense, id);
        return new ResponseEntity<>(newExpense, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Expense> updateExpense(@RequestBody Expense expense) {
        Expense updateExpense = expenseService.updateExpense(expense);
        return new ResponseEntity<>(updateExpense, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteExpense(@PathVariable("id") Long id) {
        expenseService.deleteExpense(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<Expense>> findExpenseByUserId(@PathVariable("id") Long id) {
        try {
            List<Expense> expenses = expenseService.findExpenseByUserId(id);
            return new ResponseEntity<>(expenses, HttpStatus.OK);
        }catch (UserNotFoundException e){
            throw new UserNotFoundException(e.getLocalizedMessage());
        }
    }


}
