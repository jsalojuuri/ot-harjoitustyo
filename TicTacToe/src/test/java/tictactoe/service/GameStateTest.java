package tictactoe.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author salojuur
 */
public class GameStateTest {
    
    private GameState gameState;
    
    @Before
    public void setUp() throws Exception {
        gameState = new GameState(10);
        
    }
    
    @Test
    public void constructorOneWorks() {
        assertNotNull(gameState);
    }
    
    @Test
    public void playerSetterAndGettersWork() {
        gameState.setPlayerX("X");
        gameState.setPlayerO("O");
        assertEquals("X", gameState.getPlayerX());
        assertEquals("O", gameState.getPlayerO());
    }
    
    @Test
    public void playerTurnIsCorrect() {
        gameState.setTurnX(false);
        assertEquals(false,gameState.isTurnX());
    }
}
