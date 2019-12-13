/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import tictactoe.service.Player;

/**
 * Methods to manipulate player records stored in a file
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
    
    /** 
     * Writes player's information on file
     * @param player player to be created
     * @throws SQLException 
     */
    @Override
    public void create(Player player) throws Exception {
        players.add(player);
        try (FileWriter writer = new FileWriter(new File(file))) {
            for (Player p: players) {
                writer.write(p.getName() + "\n");
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File " + file + " not found, exception message: " +ex);
        }
    }
    
    /**
     * Updates player records
     * @param player identifies player as unique. 
     * @return updated player
     * @throws SQLException 
     */
    @Override
    public Player update(Player player) throws Exception {
        return null;
    }
    
    /**
     * Deletes player records
     * @param player identifies player as unique
     * @throws SQLException 
     */
    @Override
    public void delete(Player player) throws Exception {
        
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).equals(player)) {
                players.remove(i);
            }
        }
        
        try (FileWriter writer = new FileWriter(new File(file))) {
            for (Player p: players) {
                writer.write(p.getName() + "\n");
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File " + file + " not found, exception message: " +ex);
        }
    }
    
    /**
     * Lists all players in userfile
     * @return list of players
     * @throws SQLException 
     */
    @Override
    public List<Player> list() throws SQLException {
        return players;
    }
    
}
