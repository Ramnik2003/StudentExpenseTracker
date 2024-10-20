package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;


import java.time.LocalDate;

public class TestExpenseOperations {
    private ExpensesOperations expenses;
    private ElementExpense expense1;
    private ElementExpense expense2;
    private ElementExpense expense3;
    private ElementExpense expense4;

    @BeforeEach
    void runBefore() {
        expenses = new ExpensesOperations();
        expense1 = new ElementExpense(5, "Coffee", LocalDate.of(2024,10,10));
        expense2 = new ElementExpense(10, "Meal", LocalDate.of(2024,10,10));
        expense3 = new ElementExpense(5, "Coffee", LocalDate.of(2024,9,10));
        expense4 = new ElementExpense(15, "Meal", LocalDate.of(2024,9,10));
    }

    @Test
    void testConstructor() {
        assertEquals(5, expense1.getExpense(),0.01);
        assertEquals("Coffee", expense1.getCategory());
        assertEquals(LocalDate.of(2024,10,10), expense1.getDate());

    }

    @Test
    void testAddExpenseSingle() {
        expenses.addExpense(expense1);
        assertEquals(1, expenses.getExpensesList().size());
    }

    @Test 
    void testMultiAddExpense() {
        expenses.addExpense(expense1);
        expenses.addExpense(expense2);
        assertEquals(2, expenses.getExpensesList().size());
        expenses.addExpense(expense3);
        expenses.addExpense(expense4);
        assertEquals(4, expenses.getExpensesList().size());
    }

    @Test
    void testSingleTotalDaily() {
        expenses.addExpense(expense1);
        assertEquals(5, expenses.totalDaily(LocalDate.of(2024, 10, 10)), 0.01);
    }

    @Test
    void testMultipleTotalDailyFirst() {
        expenses.addExpense(expense1);
        expenses.addExpense(expense2);
        assertEquals(15, expenses.totalDaily(LocalDate.of(2024, 10, 10)), 0.01);
    }

    @Test
    void testMultipleTotalDailySecond() {
        expenses.addExpense(expense3);
        expenses.addExpense(expense4);
        assertEquals(20, expenses.totalDaily(LocalDate.of(2024, 9, 10)), 0.01);
    }
        

    @Test 
    void testTotalDailyPerSingleCategory() {
        expenses.addExpense(expense1);
        assertEquals(5, expenses.totalDailyPerCategory(LocalDate.of(2024, 10, 10), "Coffee"), 0.01);
    }

    @Test 
    void testTotalDailyPerDifferentCategory() {
        expenses.addExpense(expense1);
        expenses.addExpense(expense2);
        assertEquals(5, expenses.totalDailyPerCategory(LocalDate.of(2024, 10, 10), "Coffee"), 0.01);
        assertEquals(10, expenses.totalDailyPerCategory(LocalDate.of(2024, 10, 10), "Meal"), 0.01);
    }

    @Test
    void testTotalMonthlySingleDate() {
        expenses.addExpense(expense1);
        expenses.addExpense(expense2);
        assertEquals(15, expenses.totalMonthly(2024, 10), 0.01);



    }

    @Test
    void testTotalMonthlyDifferentDate() {
        expenses.addExpense(expense1);
        expenses.addExpense(expense2);
        assertEquals(15, expenses.totalMonthly(2024, 10), 0.01);
        expenses.addExpense(expense3);
        expenses.addExpense(expense4);
        assertEquals(15, expenses.totalMonthly(2024, 10), 0.01);
        assertEquals(20, expenses.totalMonthly(2024, 9), 0.01);


    }

    @Test
    void testTotalMonthlyPerCategory() {
        expenses.addExpense(expense1);
        expenses.addExpense(expense2);
        assertEquals(5, expenses.totalMonthlyPerCategory(2024, 10,"Coffee"), 0.01);
        assertEquals(10, expenses.totalMonthlyPerCategory(2024, 10, "Meal"), 0.01);

        expenses.addExpense(expense3);
        expenses.addExpense(expense4);
        assertEquals(5, expenses.totalMonthlyPerCategory(2024, 10,"Coffee"), 0.01);
        assertEquals(15, expenses.totalMonthlyPerCategory(2024, 9, "Meal"), 0.01);
        assertEquals(5, expenses.totalMonthlyPerCategory(2024, 9, "Coffee"), 0.01);
        assertEquals(10, expenses.totalMonthlyPerCategory(2024, 10, "Meal"), 0.01);


    }

    @Test
    public void testDailyExpenseFilteredAccurately() {
        expenses.addExpense(expense2);
      
        List<ElementExpense> filteredDailyExpenses = expenses.getExpensesList().stream()
                .filter(e -> e.getDate().equals(LocalDate.of(2024, 10, 10)))
                .collect(Collectors.toList());

        assertEquals(10, filteredDailyExpenses.get(0).getExpense(),0.01);

    }

    @Test
    public void testDailyExpenseFilteredAccuratelyCategories() {
        expenses.addExpense(expense2);
        expenses.addExpense(expense1);
      
        List<ElementExpense> filteredDailyExpenses = expenses.getExpensesList().stream()
                .filter(e -> e.getDate().equals(LocalDate.of(2024, 10, 10)) && e.getCategory().equals("Coffee"))
                .collect(Collectors.toList());

        assertEquals(5, filteredDailyExpenses.get(0).getExpense(),0.01);

    }

    @Test
    public void testMonthlyExpenseFilteredAccurately() {
        expenses.addExpense(expense2);
        expenses.addExpense(expense3);

        List<ElementExpense> filteredExpenses = expenses.getExpensesList().stream()
                .filter(e -> e.getDate().getYear() == 2024 && e.getDate().getMonthValue() == 9)
                .collect(Collectors.toList());

        assertEquals(5, filteredExpenses.get(0).getExpense(),0.01);

    }

    @Test
    public void testMonthlyExpenseFilteredAccuratelyCategories() {
        expenses.addExpense(expense4);
        expenses.addExpense(expense3);
        expenses.addExpense(expense2);

      
        List<ElementExpense> filteredMonthlyExpenses = expenses.getExpensesList().stream()
                .filter(e -> e.getDate().getYear() == 2024 
                            && e.getDate().getMonthValue() == 9 
                            && e.getCategory().equals("Meal"))
                .collect(Collectors.toList());

        assertEquals(15, filteredMonthlyExpenses.get(0).getExpense(),0.01);

    }






}
