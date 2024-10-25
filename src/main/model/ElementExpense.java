package model;

import java.time.LocalDate;

import org.json.JSONObject;

import persistence.Writable;

// Represents an expense with a specific date and category
public class ElementExpense implements Writable  {
    private double expense;
    private String category;
    //private String date;
    private LocalDate date;

    //EFFECTS: constructs a new element expense with a date and a category.
    public ElementExpense(double expense, String category, LocalDate date) {
        this.expense = expense;
        this.category = category;
        this.date = date;
    }

    public double getExpense() {
        return expense;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public String getCategory() {
        return category;
    }

    @Override 
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("expense", expense);
        json.put("category", category);
        json.put("date", date);
        return json;
    }

}
