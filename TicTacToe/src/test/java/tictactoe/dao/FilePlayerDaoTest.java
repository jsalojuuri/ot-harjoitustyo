package tictactoe.dao;

import java.io.FileInputStream;
import java.util.Properties;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author salojuur
 */
public class FilePlayerDaoTest {
    
    private Properties properties;
    private String userTestFile;
    private Dao dao;
    
    @Before
    public void setUp() throws Exception {
        properties = new Properties();
        properties.load(new FileInputStream("config.properties"));
        userTestFile = properties.getProperty("userTestFile");
        dao = new FilePlayerDao(userTestFile);
    }
    
    @Test
    public void constructorWorks() {
        assertNotNull(dao);
    }
    
}
