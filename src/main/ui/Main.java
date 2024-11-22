package ui;

import javax.swing.SwingUtilities;

//REFERENCE: I refered to Youtube chanel "Java Code Junkie" to learn about GUI 
// https://www.youtube.com/watch?v=1vVJPzVzaK8&list=PL3bGLnkkGnuV699lP_f9DvxyK5lMFpq6U 

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ExpenseTrackerGUI().setVisible(true));
        
    }


}
