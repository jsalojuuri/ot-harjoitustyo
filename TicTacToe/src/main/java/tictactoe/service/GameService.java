package tictactoe.service;

import java.util.ArrayList;
import java.util.List;
import tictactoe.dao.*;
import tictactoe.domain.Player;

/**
 *
 * @author salojuur
 */

public class GameService {
    
    private Dao playerDao;
    private GameState gameState;
    
    public GameService(int boardWidth, Dao playerDao) {
        initGameBoard(boardWidth);
        this.playerDao = playerDao;
    }
      
    /**
    * Create new player
    * @param   player   pelaajan nimi
    */   
    public boolean createPlayer(String playerName) {
        
        List<Player> players = new ArrayList<>();
        
        try {
            players = playerDao.list();
            for (Player p: players) {
                if (p.getName().equals(playerName)) {
                    return false;
                }
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }

        
        Player player = new Player(playerName);
        try {
            playerDao.create(player);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        
        return true;
    }
    
    /**
    * Initalise gameboard
    * @param   width   game board width that will also be its height
    */  
    public void initGameBoard(int width) {        
        this.gameState = new GameState(width);
    }
    
    /** Get game board */
    public String[][] getGameBoard() {
        return gameState.getGameBoard();
    }
    
    /** Set game board */
    public void setGameBoard(String[][] gameBoard) {
        gameState.setGameBoard(gameBoard);
    }
    
    public void setGameSquare(int i, int j, String chip) {
        gameState.setGameSquare(i, j, chip);
    }
    
    public boolean isTurnX() {
        return gameState.isTurnX();
    }
    
    public void changeTurn() {
        if (gameState.isTurnX()) {
            gameState.setTurnX(false);
        } else {
            gameState.setTurnX(true);
        }
    }
    
    public String checkStatus() {
        return gameState.checkGameStatus();
    }
    
    public boolean login(String playerName) {
        try {
            List<Player> players = playerDao.list();
            for (Player player: players) {
                if (player.getName().equals(playerName)) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("Exception: " +e);
        }
        return false;
    }
    
    public List<Player> getPlayers() {
        
        List<Player> players = new ArrayList<>();
        
        try {
            players = playerDao.list();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return players;
    }
    
    public void setPlayerX(String playerX) {
        gameState.setPlayerX(playerX);
    }
    
    public void setPlayerO(String playerO) {
        gameState.setPlayerX(playerO);
    }
    
    public String getPlayerX() {
        return gameState.getPlayerX();
    }
    
    public String getPlayerO() {
        return gameState.getPlayerO();
    }
    
}
