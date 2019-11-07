package tictactoe.dao;

import tictactoe.domain.Player;
import tictactoe.domain.Database;
import java.util.*;
import java.sql.*;

/**
 *
 * @author salojuur
 */
public class PlayerDao implements Dao<Player, Integer> {
    
    private Database db;
    
    public PlayerDao(Database db) {
        this.db = db;
    }
    
    public Player findOne(Integer key) throws SQLException {
        Connection conn = db.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Player WHERE id = ?");
        stmt.setInt(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        Player p = new Player(rs.getInt("id"), rs.getString("name"), rs.getInt("wins"), rs.getInt("ties"), rs.getInt("losses"));
  
        stmt.close();
        rs.close();

        conn.close();

        return p;
    }
    
    @Override
    public void create(Player object) throws SQLException {
        // not implemented
    }

    @Override
    public Player read(Integer key) throws SQLException {
        // not implemented
        return null;
    }

    @Override
    public Player update(Player object) throws SQLException {
        // not implemented
        return null;
    }
    
    @Override
    public void delete(Integer key) throws SQLException {
        Connection conn = db.getConnection();
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM Player WHERE id = ?");
        
        stmt.setInt(1, key);
        stmt.executeUpdate();
        
        stmt.close();
        conn.close();
    }
    
    @Override
    public List<Player> list() throws SQLException {
	      // not implemented
	      return null;
    }
}
