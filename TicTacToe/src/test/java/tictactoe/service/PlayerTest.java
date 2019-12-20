package tictactoe.service;

import tictactoe.service.Player;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for Player class
 * @author salojuur
 */
public class PlayerTest {
    private Player player1;
    
    @Before
    public void setUp() throws Exception {
        player1 = new Player("pelaaja");
    }
    
    @Test
    public void constructorOneWorks() {
        assertNotNull(player1);
    }
    
    @Test
    public void playerNameGetterAndSetterWork() {
        player1.setName("nimi");
        assertEquals("nimi", player1.getName());
    }
    
}
