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
    
    @Test
    public void gameBoardExistsAndGameSqauresAreSetCorrect() {
        assertNotNull(gameState.getGameBoard());
        gameState.setGameSquare(0, 0, "X");
        assertEquals("X", gameState.getGameBoard()[0][0]);
        assertEquals(true, gameState.isX(0, 0));
        gameState.setGameSquare(0, 0, "O");
        assertEquals(false, gameState.isX(0, 0));
    }
    
    @Test
    public void gameStatusCheckWorksAsPlanned() {
        assertEquals("", gameState.checkGameStatus());
        gameState.setGameSquare(0, 0, "X");
        gameState.setGameSquare(0, 1, "X");
        gameState.setGameSquare(0, 2, "X");
        gameState.setGameSquare(0, 3, "X");
        gameState.setGameSquare(0, 4, "X");
        assertEquals("X", gameState.checkGameStatus());
        gameState.setGameSquare(0, 0, "O");
        gameState.setGameSquare(1, 0, "O");
        gameState.setGameSquare(2, 0, "O");
        gameState.setGameSquare(3, 0, "O");
        gameState.setGameSquare(4, 0, "O");
        assertEquals("O", gameState.checkGameStatus());
    }
}
