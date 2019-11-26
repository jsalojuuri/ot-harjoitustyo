/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.dao;

import java.io.File;
import java.io.FileWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import tictactoe.domain.Player;

/**
 *
 * @author salojuur
 */
public class FilePlayerDao implements Dao<Player, Integer>  {
    
    private List<Player> players;
    private String file;

    public FilePlayerDao(String file) throws Exception {
        players = new ArrayList<>();
        this.file = file;
        try {
            Scanner scanner = new Scanner(new File(file));
            while (scanner.hasNextLine()) {
                String name = scanner.nextLine();
                Player player = new Player(name);
                players.add(player);
            }
        } catch (Exception e) {
            FileWriter writer = new FileWriter(new File(file));
            writer.close();
        }
    }
    

     
    
    
    @Override
    public void create(Player player) throws SQLException {
        players.add(player);
        try (FileWriter writer = new FileWriter(new File(file))) {
            for (Player p: players) {
                writer.write(p.getName() + "\n");
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }
    
    @Override
    public Player read(Integer key) throws SQLException {
        return null;
    }
    
    @Override
    public Player update(Player player) throws SQLException {
        return null;
    }
    
    @Override
    public void delete(Integer key) throws SQLException {
        
    }
    
    @Override
    public List<Player> list() throws SQLException {
        return players;
    }
    
}
