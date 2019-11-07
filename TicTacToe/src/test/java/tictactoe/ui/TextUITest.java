package tictactoe.ui;

import java.util.Scanner;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author salojuur
 */
public class TextUITest {
    
    @Before
    public void setUp() {
        Scanner scanner = new Scanner(System.in);
        UI ui = new TextUI(scanner);
    }
    
    @Test
    public void constructorWorksForTextUI() {
        Scanner scanner = new Scanner(System.in);
        UI ui = new TextUI(scanner);
        assertTrue(ui!=null);
        
    }
    

}
