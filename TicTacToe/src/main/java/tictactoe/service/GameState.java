package tictactoe.service;

/**
 *
 * @author salojuur
 */
public class GameState {
    
    private final int boardWidth;
    private boolean turnX;
    private String [][] gameBoard;
    
    public GameState(int boardWidth) {
        this.boardWidth = boardWidth;
        turnX = true;
        gameBoard = new String[boardWidth][boardWidth];
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[0].length; j++) {
                gameBoard[i][j] = "";
            }
        } 
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

        int rowX = 0;
        int colX = 0;
        int rowO = 0;
        int colO = 0;
        
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[0].length; j++) {
                
                // check cols
                if (gameBoard[i][j].equals("X")) {
                    rowO = 0;
                    rowX++;
                } 
                if (gameBoard[i][j].equals("O")) {
                    rowX = 0;
                    rowO++;
                }
                
                // check rows
                if (gameBoard[j][i].equals("X")) {
                    colO = 0;
                    colX++;
                } 
                if (gameBoard[j][i].equals("O")) {
                    colX = 0;
                    colO++;
                }
                
                
                if (rowX == 5 || colX == 5) {
                    return "X";
                }
                if (rowO == 5 || colO == 5) {
                    return "O";
                }        
            }
        } 
        return "Winner not found yet!";
    }
    
    
}
