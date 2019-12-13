package tictactoe.dao;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
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
    
    @Before
    public void setUp() throws Exception {
        properties = new Properties();
        properties.load(new FileInputStream("config.properties"));
        userTestFile = properties.getProperty("userTestFile");
        dao = new FilePlayerDao(userTestFile);
        player = new Player("Player");
    }
    
    @Test
    public void constructorWorks() {
        assertNotNull(dao);
    }
    
    @Test
    public void createAndListPlayersWorks() throws Exception {
        dao.create(player);
        List<Player> players = dao.list();
        assertEquals("Player", players.get(0).getName());
    }
    
    @After
    public void tearDown() throws Exception {
        dao.delete(player);
    }
    
}
