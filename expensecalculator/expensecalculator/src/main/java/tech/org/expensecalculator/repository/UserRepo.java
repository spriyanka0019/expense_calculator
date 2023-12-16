package tech.org.expensecalculator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.org.expensecalculator.model.Expense;
import tech.org.expensecalculator.model.User;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
   public User findUserByUserId(Long Id);

   Optional<User> findByEmail(String email);

}
