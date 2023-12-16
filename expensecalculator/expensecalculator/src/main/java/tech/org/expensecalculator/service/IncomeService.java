package tech.org.expensecalculator.service;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.org.expensecalculator.exception.MonthNotFoundException;
import tech.org.expensecalculator.exception.UserNotFoundException;
import tech.org.expensecalculator.model.Expense;
import tech.org.expensecalculator.model.Income;
import tech.org.expensecalculator.model.User;
import tech.org.expensecalculator.repository.IncomeRepo;
import tech.org.expensecalculator.repository.UserRepo;

import java.util.List;

@Service
@Transactional
public class IncomeService {
    private final IncomeRepo incomeRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    public IncomeService(IncomeRepo incomeRepo) {
        this.incomeRepo = incomeRepo;
    }

    public Income addIncome(Income income, Long userId){
        User user = userRepo.findUserByUserId(userId);

        Income income1 = new Income();
        income1.setAmount(income.getAmount());
        income1.setCategory(income.getCategory().getLabel());
        income1.setDate(income.getDate());
        income1.setUser(user);

        return incomeRepo.save(income1);
    }

    public List<Income> findAllIncomes(){
        return incomeRepo.findAll();
    }

    public Income updateIncome(Income expense){
        return incomeRepo.save(expense);
    }

    public void deleteIncome(Long expenseId){
        incomeRepo.deleteIncomeByIncomeId(expenseId);
    }

    public Income findIncomeById(Long incomeId){
        return incomeRepo.findIncomeByIncomeId(incomeId).
                orElseThrow(()-> new UserNotFoundException("Expense by id "+incomeId+ "was not found"));
    }

    public int getIncomeSumByUserInMonth(int month, Long userId){
        User user = userRepo.findUserByUserId(userId);
        if (user == null) {
            throw new UserNotFoundException("User not found");
        }
        return incomeRepo.findTotalIncomeByMonth(month, userId).orElse(0);
    }

    public List<Income> findIncomeByUserId(Long userId) {
        try {
            User user = userRepo.findUserByUserId(userId);
            if (user == null) {
                throw new UserNotFoundException("User not found");
            } else {
                return incomeRepo.findListOfIncomesByUserId(userId);
            }
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException("User not found");
        }
    }
}
