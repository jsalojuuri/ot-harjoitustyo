package tictactoe.dao;

import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;
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
    
    private Dao dao;
    
    @Before
    public void setUp() throws Exception {

        dao = new FilePlayerDao("fooFile");
    }
    
    @Test
    public void constructorWorks() {
        assertNotNull(dao);
    }
    
    @Test
    public void createAndListPlayersWorks() throws Exception {
        Player player = new Player("Player");
        dao.create(player);
        List<Player> players = dao.list();
        assertEquals("Player", players.get(0).getName());
    }
    
}
