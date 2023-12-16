package tech.org.expensecalculator.exception;

public class MonthNotFoundException extends RuntimeException{
    public MonthNotFoundException(String message){
        super(message);
    }
}
