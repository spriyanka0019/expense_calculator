package tech.org.expensecalculator.model;

public enum IncomeCategoryType {
    Job("Job"),
    Side_Hustle("Side Hustle");

    private String label;
    IncomeCategoryType(String category) {
        this.label = category;
    }

    public String getLabel(){
        return label;
    }
}
