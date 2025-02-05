//I used StackOverflow to learn more about the usage of .filter() method and LocalDate class
// .filter() usage reference: https://stackoverflow.com/questions/71888924/how-to-filter-element-from-a-list-using-java-8-streams
// LocalDate usage reference: https://docs.oracle.com/javase/8/docs/api/?java/time/LocalDate.html 

package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

//Represents different methods that perform arithemetical operations on the inputed expenses from the user
public class ExpensesOperations implements Writable {
    private List<ElementExpense> expensesList;
    

    //EFFECTS: constructs ExpenseOperations class that returns values
    // from the List<ElementExpense> after performing various operations 
    public ExpensesOperations() {
        this.expensesList = new ArrayList<>();
    }

    public List<ElementExpense> getExpensesList() {
        return expensesList;
    }


    //MODIFIES: this
    //EFFECTS: adds the element to the dataExpense list and logs the add expense event in the console when application closed
    public void addExpense(ElementExpense expense) {
        expensesList.add(expense);
        EventLog.getInstance().logEvent(new Event("New expense of amount " + expense.getExpense() + " of category " + expense.getCategory()+ "  on date "+ expense.getDate()+"added"));
    }

    //REQUIRES: expence>0
    //MODIFIES: this
    //EFFECTS: returns total expenses after adding all the expenses on a particular date
    //         logs the adding event in the console 
    public double totalDaily(LocalDate date) {
        List<ElementExpense> filteredExpenses = expensesList.stream()
                .filter(e -> e.getDate().equals(date))
                .collect(Collectors.toList());
        EventLog.getInstance().logEvent(new Event("All Expenses on " + date + " added"));
        return additionFilteredElements(filteredExpenses);

    } 

    //REQUIRES: expence>0
    //MODIFIES: this
    //EFFECTS: returns total expenses in a category after adding the expenses
    //         logs the event in the console 
    public double totalDailyPerCategory(LocalDate date, String category) {
        List<ElementExpense> filteredExpenses = expensesList.stream()
                .filter(e -> e.getDate().equals(date) && e.getCategory().equals(category))
                .collect(Collectors.toList());
        EventLog.getInstance().logEvent(new Event("All expenses on "+ date+ " added based on category:" + category));
        return additionFilteredElements(filteredExpenses);

    }

    //REQUIRES: expence>0
    //MODIFIES: this
    //EFFECTS: returns total expenses after adding all the expenses in that month
    //         logs the event in the console 
    public double totalMonthly(LocalDate date) {
        List<ElementExpense> filteredExpenses = expensesList.stream()
                .filter(e -> e.getDate().getYear() == date.getYear() 
                             && e.getDate().getMonthValue() == date.getMonthValue())
                .collect(Collectors.toList());
        EventLog.getInstance().logEvent(new Event("All the expenses in the month of " + date.getMonth()+" added"));
        return additionFilteredElements(filteredExpenses);

    }

    //REQUIRES: expence>0
    //MODIFIES: this
    //EFFECTS: returns total expenses in a category after adding the expenses in that month
    //         logs the event in the console
    public double totalMonthlyPerCategory(LocalDate date, String category) {
        List<ElementExpense> filteredExpenses = expensesList.stream()
                .filter(e -> e.getDate().getYear() == date.getYear()
                             && e.getDate().getMonthValue() == date.getMonthValue()
                             && e.getCategory().equals(category))
                .collect(Collectors.toList());
        EventLog.getInstance().logEvent(new Event("All the expenses in the month of" + date.getMonth()+ " added based on category: " +category));
        return additionFilteredElements(filteredExpenses);
        
    }

    //MODIFIES: this
    //EFFECTS: adds all the filtered elements
    public double additionFilteredElements(List<ElementExpense> filteredExpenses) {
        double total = 0;
        for (int i = 0; i < filteredExpenses.size(); i++) {
            total += filteredExpenses.get(i).getExpense();
        }
        return total;

    }

    @Override 
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("expenses", expensesToJson());
        return json;
    }

    //EFFECTS: return expenses in expensesList as a JSoN array
    private JSONArray expensesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (ElementExpense expense : expensesList) {
            jsonArray.put(expense.toJson());
        }

        return jsonArray;
    }

    public void saveExpensesLog() {
        EventLog.getInstance().logEvent(new Event("Saves data "));
    }

    public void loadExpenseLog() {
        EventLog.getInstance().logEvent(new Event("Data loaded from the saved list"));

    }

    
}
