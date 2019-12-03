package tictactoe.dao;

import tictactoe.domain.Player;
import java.util.*;
import java.sql.*;

/**
 *
 * @author salojuur
 */

public class H2PlayerDao implements Dao<Player, Integer> {
    
    public H2PlayerDao() {
        
    }
    
    
    @Override
    public Player read(Integer key) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:h2:/tictactoe", "sa", "");
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
    public void create(Player player) throws SQLException {
        
        Connection conn = DriverManager.getConnection("jdbc:h2:/tictactoe", "sa", "");
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO Player (nimi) VALUES (?)");
        stmt.setString(1, player.getName());
        
        stmt.executeUpdate();
        stmt.close();
        conn.close();
    }


    @Override
    public Player update(Player object) throws SQLException {
        // not implemented
        return null;
    }
    
    @Override
    public void delete(Integer key) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:h2:/tictactoe", "sa", "");
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM Player WHERE id = ?");
        
        stmt.setInt(1, key);
        stmt.executeUpdate();
        
        stmt.close();
        conn.close();
    }
    
    @Override
    public List<Player> list() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:h2:tictactoe", "sa", "");
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Player");
        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }
        
        List<Player> players = new ArrayList<>();
        while (hasOne) {
            players.add(new Player(rs.getInt("id"), rs.getString("name"), rs.getInt("wins"), rs.getInt("ties"), rs.getInt("losses")));
        }
        
        return players;
    }
}
