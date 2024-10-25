package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import model.ElementExpense;
import model.ExpensesOperations;

class JsonWriterTest extends JsonTest {

    @Test 
    void testWriterInvalidFile() {
        try {
            ExpensesOperations expenses = new ExpensesOperations();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            //pass
        }

    }

    @Test
    public void testWriterEmptyExpensesOperations() {
        try {
            ExpensesOperations expenses = new ExpensesOperations();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyExpensesOperations.json");
            writer.open();
            writer.write(expenses);  
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyExpensesOperations.json");
            ExpensesOperations expensesReader = reader.read();
            assertEquals(0, expensesReader.getExpensesList().size());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test 
    void testWriterGeneralExpensesOperations() {

        try {
            ExpensesOperations expenses = new ExpensesOperations();
            expenses.addExpense(new ElementExpense(15, "meal",LocalDate.of(2024,10,5)));
            expenses.addExpense(new ElementExpense(5, "coffee",LocalDate.of(2024,10,6)));
            
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(expenses);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkroom.json");
            expenses = reader.read();
            List<ElementExpense> expensesList = expenses.getExpensesList();
            assertEquals(2, expensesList.size()); 
            
            checkExpense(15,"Food", LocalDate.of(2024, 10, 15), expensesList.get(0));
            checkExpense(10,"Coffee", LocalDate.of(2024, 10, 14), expensesList.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}
