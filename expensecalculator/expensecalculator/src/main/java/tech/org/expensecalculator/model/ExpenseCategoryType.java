package tech.org.expensecalculator.model;

public enum ExpenseCategoryType {

    Food("Food"),
    Travel("Travel"),
    GroceryShopping("Grocery Shopping");

    private String label;
    ExpenseCategoryType(String category) {
        this.label = category;
    }

    public String getLabel(){
        return label;
    }
}
