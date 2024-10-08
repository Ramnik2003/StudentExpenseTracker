package model;

public class ElementExpense {
    private double expense;
    private String category;
    private String date;

    //EFFECTS: constructs a new element expense with a date and a category.
    public ElementExpense(double expense, String category, String date) {
        this.expense=expense;
        this.category=category;
        this.date=date;
    }

    public double getExpense() {
        return expense;
    }
    public String getDate() {
        return date;
    }

    public String getCategory() {
        return category;
    }


}
