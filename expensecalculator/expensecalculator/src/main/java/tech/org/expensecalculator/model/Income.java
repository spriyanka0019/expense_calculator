package tech.org.expensecalculator.model;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Entity
public class Income implements Serializable {
    @Id
    @GeneratedValue
    @Column(nullable = false, updatable = false, name = "income_id")
    private Long incomeId;
    private String date;
    private IncomeCategoryType category;

    /**
     * Many(Expense) to One(User)
     * There could be many income for one user..
     */
    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    public Long getIncomeId() {
        return incomeId;
    }

    public void setIncomeId(Long id){
        this.incomeId = id;
    }

    private int amount;

    public Income() {
        super();
    }

    public Income(String date, String category, int amount, User user) {
        this.date = date;
        this.category = convertLabelToString(category);
        this.amount = amount;
        this.user = user;
    }

//    public Income(String date, String category, int amount) {
//        this.date = date;
//        this.category = convertLabelToString(category);
//        this.amount = amount;
//    }

//    public Timestamp convertStringToTimeStamp(String dateToConvert){
//        Timestamp timestamp = null;
//        try {
//            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
//            Date parsedDate = dateFormat.parse(dateToConvert);
//            timestamp = new java.sql.Timestamp(parsedDate.getTime());
//        } catch(Exception e) {
//            System.out.println("Exception Occured");
//        }
//        return timestamp;
//    }

    public IncomeCategoryType convertLabelToString(String value){
        if(Objects.equals(value, IncomeCategoryType.Job.getLabel())){
            this.category = IncomeCategoryType.Job;
        }
        else{
            this.category = IncomeCategoryType.Side_Hustle;
        }
        return this.category;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public IncomeCategoryType getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = convertLabelToString(category);
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
}
