package model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class TestDailyExpense {
    private ElementExpense expense1;
    private ElementExpense expense2;
    private ElementExpense expense3;
    private ElementExpense expense4;
    private DailyExpense daily;
    //private List<ElementExpense> dailyExpenses;

    @BeforeEach
    void runBefore() {
        // dailyExpenses = new ArrayList<>();
        daily = new DailyExpense();
        expense1 = new ElementExpense(5, "Coffee", "5-10-2024");
        expense2 = new ElementExpense(10, "Meal", "5-10-2024");
        expense3 = new ElementExpense(7, "Coffee", "5-10-2024");
        expense4 = new ElementExpense(11, "Meal", "5-10-2024");
        daily.addExpense(expense1);
        daily.addExpense(expense2);
        daily.addExpense(expense3);
        daily.addExpense(expense4);
    
    }


    @Test
    void testConstructor(){
        //daily.addExpense(expense1);
        assertEquals(expense1, daily.getDailyExpenses().get(0));
        
        //daily.addExpense(expense2);
        assertEquals(expense2, daily.getDailyExpenses().get(1));
       
        //daily.addExpense(expense3);
        assertEquals(expense3, daily.getDailyExpenses().get(2));
        
        //daily.addExpense(expense4);
        assertEquals(expense4, daily.getDailyExpenses().get(3));
        
        
    }

    @Test 
    void testTotalSeperateCategories() {
        assertEquals(12, daily.totalSeperateCategories("Coffee"),0.05);
        assertEquals(21, daily.totalSeperateCategories("Meal"),0.05);
    }

    @Test 
    void testCombinedExpenses() {
        assertEquals(33, daily.combineExpenses("5-10-2024"));
    }

    @Test 
    void testInvalidEntry() {
        assertEquals(0, daily.combineExpenses("6-10-2024"));
    }


}
