package tictactoe.service;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;
import tictactoe.dao.Dao;
import tictactoe.dao.FilePlayerDao;

/**
 *
 * @author salojuur
 */
public class GameServiceTest {
    
    private Properties properties;
    private String userTestFile;
    private Dao dao;
    private GameService gameService;
    
    
    @Before
    public void setUp() throws Exception {
        properties = new Properties();
        properties.load(new FileInputStream("config.properties"));
        userTestFile = properties.getProperty("userTestFile");
        dao = new FilePlayerDao(userTestFile);
        gameService = new GameService(10, dao);
    }
    
    @Test
    public void constructorWorksIfProperUserFileIsGiven() {
        assertNotNull(gameService);
    }
    
    @Test
    public void gameBoardIsInitalizedRight() {
        gameService.initGameBoard(25);
        assertEquals(25, gameService.getGameBoard().length);
    }
    
    @Test
    public void gameSquareIsSetCorrect() {
        gameService.setGameSquare(0, 0, "X");
        assertEquals("X", gameService.getGameBoard()[0][0]);
    }
    
    @Test
    public void playerTurnGetsChangedRight() {
        assertEquals(true, gameService.isTurnX());
    }
    
}
