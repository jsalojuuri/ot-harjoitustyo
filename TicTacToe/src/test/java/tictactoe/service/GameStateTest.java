package tictactoe.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for GameState class
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
        gameState.setGameSquare(1, 0, "X");
        gameState.setGameSquare(2, 1, "X");
        gameState.setGameSquare(3, 2, "X");
        gameState.setGameSquare(4, 3, "X");
        gameState.setGameSquare(5, 4, "X");
        assertEquals("X", gameState.checkGameStatus());
        gameState.setGameSquare(5, 4, "O");
        gameState.setGameSquare(4, 5, "O");
        gameState.setGameSquare(3, 6, "O");
        gameState.setGameSquare(2, 7, "O");
        gameState.setGameSquare(1, 8, "O");
        assertEquals("O", gameState.checkGameStatus());
    }
    
    @Test
    public void gameEndsTiedIfMovesCountMaxReached() {
        GameState gameState2 = new GameState(5);
        int counter = 0;
        while(counter < 25) {
            gameState2.incrementMovesCount();
            counter++;
        }
        assertEquals(25, gameState2.getMovesCount());
        assertEquals("N", gameState2.checkGameStatus());
    }
    
    @Test
    public void wrongTypeOfCheckTypeReturnsFalseOrEmptyString() {
        assertEquals(false,gameState.checkTypeOK("foo"));
        assertEquals("",gameState.checkCurrent(0,0,"foo"));
    }
    
    @Test
    public void setNewGameBoardWorks() {
        GameState gameState3 = new GameState(6);
        String[][] newGameBoard = gameState3.getGameBoard();
        gameState.setGameBoard(newGameBoard);
        assertEquals(6,gameState.getGameBoard().length);
    }
    
}
