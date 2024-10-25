package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

import model.ElementExpense;
import model.ExpensesOperations;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

class JsonReaderTest extends JsonTest {
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            ExpensesOperations expenses = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyExpensesOperations() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyWorkRoom.json");
        try {
            ExpensesOperations expenses = reader.read();
            assertEquals(0, expenses.getExpensesList().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralExpensesOperations() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralWorkRoom.json");
        try {
            ExpensesOperations expenses = reader.read();
            List<ElementExpense> expensesList = expenses.getExpensesList();
            assertEquals(2, expenses.getExpensesList().size());
            checkExpense(5.0, "Coffee", LocalDate.of(2024,10,10), expensesList.get(0));
            checkExpense(15.0, "Meal", LocalDate.of(2024,10,11), expensesList.get(1));

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }


}
