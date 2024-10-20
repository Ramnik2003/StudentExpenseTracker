package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class TestElementExpense {
    private ElementExpense expense1;


    @BeforeEach
    void runBefore() {
        expense1 = new ElementExpense(5.99, "Coffee",LocalDate.of(2024,10,5));
    }

    @Test
    void testConstructor() {
        assertEquals(5.99, expense1.getExpense(), 0.05);
        assertEquals("Coffee", expense1.getCategory());
        assertEquals(LocalDate.of(2024,10,5), expense1.getDate());
    
    }
}

