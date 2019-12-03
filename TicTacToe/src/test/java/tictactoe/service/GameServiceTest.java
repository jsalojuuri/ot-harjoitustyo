package tictactoe.service;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
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
    
}
