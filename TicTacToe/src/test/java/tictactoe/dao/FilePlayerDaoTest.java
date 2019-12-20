package tictactoe.dao;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;
import tictactoe.service.Player;

/**
 *
 * @author salojuur
 */
public class FilePlayerDaoTest {
    
    private Properties properties;
    private String userTestFile;
    private Dao dao;
    private Player player;
    private Player player2;
    
    @Before
    public void setUp() throws Exception {
        properties = new Properties();
        properties.load(new FileInputStream("config.properties"));
        properties.setProperty("testPlayer", "userTestFile");
        userTestFile = properties.getProperty("userTestFile");
        dao = new FilePlayerDao("testfile");
        player = new Player("Player");
        player2 = new Player("Player2");
    }
    
    @Test
    public void constructorWorks() {
        assertNotNull(dao);
    }
    
    @Test
    public void createAndListAndDeletePlayersWorks() throws Exception {
        dao.create(player);
        dao.create(player2);
        List<Player> players = dao.list();
        assertEquals("Player", players.get(0).getName());
        dao.delete(player2);
        dao.delete(player);
        players = dao.list();
        assertEquals(true, players.isEmpty());
    }
    
    @After
    public void tearDown() throws Exception {
        dao.delete(player);
        
    }
    
}
