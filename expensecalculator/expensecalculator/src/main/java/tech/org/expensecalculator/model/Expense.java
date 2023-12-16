package tech.org.expensecalculator.model;


import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;


@Entity
@Table(name="expense")
@Data

public class Expense implements Serializable {
   @Id
   @GeneratedValue
   @Column( nullable = false, updatable = false, name="expense_id")
   private Long expenseId;
   private String date;
   private ExpenseCategoryType category;
   private int amount;

   /**
    * Many(Expense) to One(User)
    * There could be many Expense for one user..
    */
   @ManyToOne
   @JoinColumn(name = "user_id",nullable = false)
   private User user;

   public Expense(Long id, String date, String categoryLabel, int amount, User user) {
      this.expenseId = id;
      this.date = date;
      this.category = convertLabelToString(categoryLabel);
      this.amount = amount;
      this.user = user;
   }

   public Expense(){
//      super();
   }

//   public Expense(String username, String date, String categoryLabel, int amount) {
//      this.date = date;
//      this.category = convertLabelToString(categoryLabel);
//      this.amount = amount;
//   }

   public ExpenseCategoryType convertLabelToString(String value){
        if(Objects.equals(value, ExpenseCategoryType.Food.getLabel())){
           this.category = ExpenseCategoryType.Food;
        }
      else if(Objects.equals(value, ExpenseCategoryType.Travel.getLabel())){
         this.category = ExpenseCategoryType.Travel;
      }
      else{
         this.category = ExpenseCategoryType.GroceryShopping;
      }
      return this.category;
   }

   public Timestamp convertStringToTimeStamp(String dateToConvert){
      Timestamp timestamp = null;
      try {
         SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
         Date parsedDate = dateFormat.parse(dateToConvert);
         timestamp = new java.sql.Timestamp(parsedDate.getTime());
      } catch(Exception e) {
         System.out.println("Exception Occured");
      }
      return timestamp;
   }

   public Long getExpenseId() {
      return expenseId;
   }

   public void setExpenseId(Long id){
      this.expenseId = id;
   }

   public String getDate() {
      return this.date;
   }

   public void setDate(String dateToConvert) {
      this.date =  dateToConvert;
   }

   public ExpenseCategoryType getCategory() {
      return category;
   }

   public void setCategory(String categoryLabel) {
      this.category = convertLabelToString(categoryLabel);
   }

   public int getAmount() {
      return amount;
   }

   public void setAmount(int amount) {
      this.amount = amount;
   }

   public User getUser() {
      return user;
   }

   public void setUser(User user) {
      this.user = user;
   }

   @Override
   public String toString() {
      return "Expense{" +
              "id=" + this.expenseId +
              ", date=" + this.date +
              ", category=" + this.category +
              ", Amount=" + this.amount +
              '}';
   }
}
