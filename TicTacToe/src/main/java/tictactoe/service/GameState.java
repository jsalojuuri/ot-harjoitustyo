package tictactoe.service;

/**
 *
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
    
    public boolean isX(int i, int j) {
        if (gameBoard[i][j].equals("X")) {
            return true;
        } else {
            return false;
        }  
    }
    
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
    
    
    public String checkGameStatus() {
        String previous = "";
        String current = "";
        int count = 0;
        
        return checkCols(previous, current, count);  
    }
    
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
