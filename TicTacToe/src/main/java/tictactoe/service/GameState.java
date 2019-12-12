package tictactoe.service;

import java.util.HashMap;
import java.util.Map;

/**
 * Contains methods initalize, manipulate and check game state
 * @author salojuur
 */
public class GameState {
    
    private String playerX;
    private String playerO;    
    private final int boardWidth;
    private boolean turnX;
    private String [][] gameBoard;
    private  Map<String, Integer> marks;
    
    

    
    
    public GameState(int boardWidth) {
        this.playerX = "Player X";
        this.playerO = "Player O";
        this.boardWidth = boardWidth;
        turnX = true;
        gameBoard = new String[boardWidth][boardWidth];
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[0].length; j++) {
                gameBoard[i][j] = "";
            }
        } 
        //initialise 
        marks = new HashMap<>();
        marks.put("r", 0);
        marks.put("l", 0);
        marks.put("d1", 0);
        marks.put("d2", 0);
        
    }

    public String getPlayerX() {
        return playerX;
    }

    public void setPlayerX(String playerX) {
        this.playerX = playerX;
    }

    public String getPlayerO() {
        return playerO;
    }

    public void setPlayerO(String playerO) {
        this.playerO = playerO;
    }
    
    /**
     * Checks if certain game square is played by player X
     * @param i board row
     * @param j board column
     * @return true, is defined square is played by X.
     */
    public boolean isX(int i, int j) {
        if (gameBoard[i][j].equals("X")) {
            return true;
        } else {
            return false;
        }  
    }
    
    /** 
     * Checks if it's player X's turn
     * @return true, if player X's turn
     */
    public boolean isTurnX() {
        return turnX;
    }

    public void setTurnX(boolean turnX) {
        this.turnX = turnX;
    }
    
    public String[][] getGameBoard() {
        return gameBoard;
    }
    
    public void setGameSquare(int i, int j, String str) {
        this.gameBoard[i][j] = str;
    }
        
    public void setGameBoard(String[][] gameBoard) {
        this.gameBoard = gameBoard;
    }
    
    /**
     * Checks game status
     * @return empty string, if winner is not found, else "X" or "O", if winner is found
     */
    public String checkGameStatus() {
        if (!check("cols").equals("")) {
            return check("cols");
        }
        if (!check("rows").equals("")) {
            return check("rows");
        } 
        if (!check("diag1").equals("")) {
            return check("diag1");
        }
        if (!check("diag2").equals("")) {
            return check("diag2");
        }
        if (!check("diag3").equals("")) {
            return check("diag3");
        }
        if (!check("diag4").equals("")) {
            return check("diag4");
        }
        return "";
    }
    
    /**
     * Checks if a player has five consecutive marks on board columns, rows or diagonals
     * @param checkType rows, cols, diagUp or diagDown
     * @return empty string, if winner is not found, else "X" or "O", if winner is found
     */
    public String check(String checkType) {              
        
        if (checkTypeOK(checkType)) {
            String previous = "";
            String current = "";
            int count = 0;

            for (int i = 0; i < gameBoard.length; i++) {
                for (int j = 0; j < gameBoard[0].length; j++) {
                    previous = current;
                    if (checkType.contains("diag") && i+j >= gameBoard.length) {
                        continue;
                    } else {
                        current = checkCurrent(i,j,checkType);
                    }
                    if (!current.equals(previous)) {
                        count = 1;
                    } else {
                        count++;
                    }
                    if (count == 5 && !current.equals("")) {
                        return current;
                    }
                }
                current = "";
                count = 0;
            }
        }
        return "";
    }
    
    /**
     * Checks if it is allowed to check solution type
     * @param checkType row, col, diag1, diag2, diag3, diag4. 
     * @return true, if checktype is one of the allowed ones, else false
     */
    public boolean checkTypeOK(String checkType) {
        
        if (!checkType.equals("rows") 
                && !checkType.equals("cols") 
                && !checkType.equals("diag1")
                && !checkType.equals("diag2")
                && !checkType.equals("diag3")
                && !checkType.equals("diag4")) {
            return false;
        } else {
            return true;
        }
    }
    
    /**
     * Checks what is the current gameboard square for selected checktype
     * @param i for loop parameter i
     * @param j for loop parameter j
     * @param checkType rows, cols, diag1, diag2, diag3 or diag4
     * @return emptry string, if square is empty, otherwise "X" or "O"
     */
    public String checkCurrent(int i, int j, String checkType) {      
        
        if (checkType.equals("rows")) return gameBoard[j][i];      
        if (checkType.equals("cols")) return gameBoard[i][j];
        if (checkType.equals("diag1")) return gameBoard[i+j][j]; 
        if (checkType.equals("diag2")) return gameBoard[j][j+i]; 
        if (checkType.equals("diag3")) return gameBoard[gameBoard.length-(i+j+1)][j]; 
        if (checkType.equals("diag4")) return gameBoard[j][gameBoard.length-(i+j+1)]; 
        return "";  
    }
}
