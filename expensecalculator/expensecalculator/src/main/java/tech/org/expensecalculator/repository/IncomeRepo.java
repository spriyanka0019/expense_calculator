package tech.org.expensecalculator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tech.org.expensecalculator.model.Expense;
import tech.org.expensecalculator.model.Income;

import java.util.List;
import java.util.Optional;

public interface IncomeRepo extends JpaRepository<Income, Long> {
    void deleteIncomeByIncomeId(Long id);
    Optional<Income> findIncomeByIncomeId(Long id);

    @Query(value = "SELECT SUM(amount) from income where Month(date) = ?1 and user_id=?2", nativeQuery = true)
    Optional<Integer> findTotalIncomeByMonth(int month, Long user_id);

    @Query(value = "select i.* from income i join user u on u.user_id = i.user_id where i.user_id = ?1", nativeQuery = true)
    List<Income> findListOfIncomesByUserId(Long user_id);
}
