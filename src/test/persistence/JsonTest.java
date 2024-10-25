package persistence;

import model.ElementExpense;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class JsonTest {

    protected void checkExpense(double expense, String category, LocalDate date, ElementExpense elementExpense) {
        assertEquals(expense, elementExpense.getExpense(), 0.01);
        assertEquals(category, elementExpense.getCategory());
        assertEquals(date, elementExpense.getDate());
    }
}
