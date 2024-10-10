package model;

import java.time.LocalDate;
import java.util.List;;

public class ExpensesOperations {
    private ElementExpense dataExpense;
    private List<ElementExpense> expenses;

    //EFFECTS: constructs ExpenseOperations class that returns values
    // from the List<ElementExpense> after performing various operations 
    public ExpensesOperations() {
        //stub
    }

    public List<ElementExpense> getExpensesList() {
        return null;
    }


    //MODIFIES: this
    //EFFECTS: adds the element to the dataExpense list
    public void addExpense(ElementExpense expense) {
        //stub
    }

    //REQUIRES: expence>0
    //MODIFIES: this
    //EFFECTS: returns total expenses after adding all the expenses on a particular date
    public double totalDaily(LocalDate date) {
        return 0;
    } 

    //REQUIRES: expence>0
    //MODIFIES: this
    //EFFECTS: returns total expenses in a category after adding the expenses 
    public double totalDailyPerCategory(LocalDate date, String category) {
        return 0;
    }

    //REQUIRES: expence>0
    //MODIFIES: this
    //EFFECTS: returns total expenses after adding all the expenses in that month
    public double totalMonthly(int year, int month) {
        return 0;
    }

    //REQUIRES: expence>0
    //MODIFIES: this
    //EFFECTS: returns total expenses in a category after adding the expenses in that month
    public double totalMonthlyPerCategory(int year, int month, String category) {
        return 0;
    }

}
