package tictactoe.service;

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
     * @return eventually returns empty string, if winner is not found, else "X" or "O", if winner is found
     */
    public String checkGameStatus() {
        String previous = "";
        String current = "";
        int count = 0;
        
        return checkCols(previous, current, count);  
    }
    
    /**
     * Checks if a player has five consecutive marks on board cols
     * @param previous "X", "O" or empty string
     * @param current "X", "O" or empty string
     * @param count keeps track on how many similar consecutive marks there are for the moment
     * @return eventually returns empty string, if winner is not found, else "X" or "O", if winner is found
     */
    public String checkCols(String previous, String current, int count) {
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[0].length; j++) {
                previous = current;
                current = gameBoard[i][j];
                if (!current.equals(previous)) {
                    count = 1;
                } else {
                    count++;
                }      
                if (count == 5 && !current.equals("")) {
                    return current;
                }
            }
            previous = "";
            current = "";
            count = 0;
        }
        return checkRows(previous, current, count);
    }
    
    /**
     * Checks if a player has five consecutive marks on board rows
     * @param previous "X", "O" or empty string
     * @param current "X", "O" or empty string
     * @param count keeps track on how many similar consecutive marks there are for the moment
     * @return eventually returns empty string, if winner is not found, else "X" or "O", if winner is found
     */
    public String checkRows(String previous, String current, int count) {
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[0].length; j++) {
                previous = current;
                current = gameBoard[j][i];
                if (!current.equals(previous)) {
                    count = 1;
                } else {
                    count++;
                }
                if (count == 5 && !current.equals("")) {
                    return current;
                }
            }
            previous = "";
            current = "";
            count = 0;
        }
        return "";
    } 
}
