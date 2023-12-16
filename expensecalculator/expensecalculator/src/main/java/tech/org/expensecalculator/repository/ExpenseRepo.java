package tech.org.expensecalculator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tech.org.expensecalculator.model.Expense;

import java.util.List;
import java.util.Optional;

public interface ExpenseRepo extends JpaRepository<Expense, Long> {
    void deleteExpenseByExpenseId(Long id);
    Optional<Expense> findExpenseByExpenseId(Long id);


    @Query(value = "SELECT SUM(amount) from expense where Month(date) = ?1 and user_id=?2", nativeQuery = true)
    Optional<Integer> findTotalExpenseByUserInMonth(int month, Long user_id);

    @Query(value = "select e.amount, e.user_id, e.expense_id, e.category, e.date from expense e join user u on u.user_id = e.user_id where e.user_id = ?1", nativeQuery = true)
    List<Expense> findListOfExpensesByUserId(Long user_id);

}
