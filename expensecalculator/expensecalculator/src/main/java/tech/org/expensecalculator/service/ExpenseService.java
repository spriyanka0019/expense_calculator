package tech.org.expensecalculator.service;

import jakarta.transaction.Transactional;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.org.expensecalculator.exception.MonthNotFoundException;
import tech.org.expensecalculator.exception.UserNotFoundException;
import tech.org.expensecalculator.model.Expense;
import tech.org.expensecalculator.model.User;
import tech.org.expensecalculator.repository.ExpenseRepo;
import tech.org.expensecalculator.repository.UserRepo;

import java.util.List;

@Service
@Transactional
public class ExpenseService {

    private final ExpenseRepo expenseRepo;

    @Autowired
    public ExpenseService(ExpenseRepo expenseRepo) {
        this.expenseRepo = expenseRepo;
    }

    @Autowired
    UserRepo userRepo;

    public Expense addExpense(Expense expense, Long userId){
//        return expenseRepo.save(expense);
        User user = userRepo.findUserByUserId(userId);
        Expense expense1 = new Expense();
        expense1.setAmount(expense.getAmount());
        expense1.setCategory(expense.getCategory().getLabel());
        expense1.setDate(expense.getDate());
        expense1.setUser(user);
        expense1.setExpenseId(expense1.getExpenseId());
        System.out.println(expense1);

        return  expenseRepo.save(expense1);
    }

    public List<Expense> findAllExpenses(){
        return expenseRepo.findAll();
    }

    public Expense updateExpense(Expense expense){
        return expenseRepo.save(expense);
    }

    public void deleteExpense(Long expenseId){
       expenseRepo.deleteExpenseByExpenseId(expenseId);
    }

    public Expense findExpenseById(Long expenseId){
        return expenseRepo.findExpenseByExpenseId(expenseId).
                orElseThrow(()-> new UserNotFoundException("Requested date not found"));
    }

    public int findExpenseSumByUserinMonth(int month, Long userId){
        User user = userRepo.findUserByUserId(userId);
        if(user == null){
            throw new UserNotFoundException("User not found");
        }
        return expenseRepo.findTotalExpenseByUserInMonth(month, userId).orElse(0);
    }

    public List<Expense> findExpenseByUserId(Long userId) {
        try {
            User user = userRepo.findUserByUserId(userId);
            if (user == null) {
                throw new UserNotFoundException("User not found");
            } else {
                return expenseRepo.findListOfExpensesByUserId(userId);
            }
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException("User not found");
        }
    }
}
