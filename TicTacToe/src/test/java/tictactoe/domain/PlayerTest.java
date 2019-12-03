package tictactoe.domain;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author salojuur
 */
public class PlayerTest {
    private Player player1;
    private Player player2;
    
    @Before
    public void setUp() throws Exception {
        player1 = new Player("pelaaja");
        player2 = new Player(1, "player", 1, 2, 3);
    }
    
    @Test
    public void constructorOneWorks() {
        assertNotNull(player1);
    }
    
    @Test
    public void constructorTwoWorks() {
        assertNotNull(player2);
    }
    
    @Test
    public void gettesAndSettersWork() {
        player1.setName("nimi");
        player1.setWins(5);
        player1.setTies(6);
        player1.setLosses(7);
        assertEquals("nimi", player1.getName());
        assertEquals(new Integer(5), player1.getWins());
        assertEquals(new Integer(6), player1.getTies());
        assertEquals(new Integer(7), player1.getLosses());
    }
    
}
