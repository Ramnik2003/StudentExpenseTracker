//I used StackOverflow to learn more about the usage of .filter() method and LocalDate class
// .filter() usage reference: https://stackoverflow.com/questions/71888924/how-to-filter-element-from-a-list-using-java-8-streams
// LocalDate usage reference: https://docs.oracle.com/javase/8/docs/api/?java/time/LocalDate.html 
package model;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;


public class ExpensesOperations {
    private List<ElementExpense> expensesList;
    

    //EFFECTS: constructs ExpenseOperations class that returns values
    // from the List<ElementExpense> after performing various operations 
    public ExpensesOperations() {
        this.expensesList=new ArrayList<>();

    }

    public List<ElementExpense> getExpensesList() {
        return expensesList;
    }


    //MODIFIES: this
    //EFFECTS: adds the element to the dataExpense list
    public void addExpense(ElementExpense expense) {
        expensesList.add(expense);
    }

    //REQUIRES: expence>0
    //MODIFIES: this
    //EFFECTS: returns total expenses after adding all the expenses on a particular date
    public double totalDaily(LocalDate date) {
        List<ElementExpense> filteredExpenses = expensesList.stream()
        .filter(e -> e.getDate().equals(date))
        .collect(Collectors.toList());
        
        return additionFilteredElements(filteredExpenses);

    } 

    //REQUIRES: expence>0
    //MODIFIES: this
    //EFFECTS: returns total expenses in a category after adding the expenses 
    public double totalDailyPerCategory(LocalDate date, String category) {
        List<ElementExpense> filteredExpenses = expensesList.stream()
        .filter(e -> e.getDate().equals(date) && e.getCategory().equals(category) )
        .collect(Collectors.toList());
       
        return additionFilteredElements(filteredExpenses);

    }

    //REQUIRES: expence>0
    //MODIFIES: this
    //EFFECTS: returns total expenses after adding all the expenses in that month
    public double totalMonthly(int year, int month) {
        List<ElementExpense> filteredExpenses = expensesList.stream()
        .filter(e -> e.getDate().getYear() == year && e.getDate().getMonthValue() == month)
        .collect(Collectors.toList());
    
        return additionFilteredElements(filteredExpenses);

    }

    //REQUIRES: expence>0
    //MODIFIES: this
    //EFFECTS: returns total expenses in a category after adding the expenses in that month
    public double totalMonthlyPerCategory(int year, int month, String category) {
        List<ElementExpense> filteredExpenses = expensesList.stream()
        .filter(e -> e.getDate().getYear() == year && e.getDate().getMonthValue() == month && e.getCategory() == category)
        .collect(Collectors.toList());
        return additionFilteredElements(filteredExpenses);
        
    }

    //MODIFIES: this
    //EFFECTS: adds all the filtered elements
    public double additionFilteredElements(List<ElementExpense> filteredExpenses){
        double total = 0;
        for (int i = 0; i < filteredExpenses.size(); i++) {
            total += filteredExpenses.get(i).getExpense();
        }
        return total;

    }

    
}
