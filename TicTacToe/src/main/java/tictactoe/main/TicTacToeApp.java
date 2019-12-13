package tictactoe.main;

import java.io.FileInputStream;
import java.util.Properties;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tictactoe.dao.Dao;
import tictactoe.dao.FilePlayerDao;
import tictactoe.service.GameService;

/**
 * JavaFX application for TicTacToe
 * @author salojuur
 */
public class TicTacToeApp extends Application {
    private GameService gameService;
    private Dao playerDao;
    
    private BorderPane appScreen;
    private VBox startPane;
    private VBox newPlayerPane;
    private VBox deletePlayerPane;
    private HBox startInputPane1;
    private HBox startInputPane2;
    private HBox startInputPane3;
    private HBox gameButtonPane;
    private GridPane gameBoard;
    
    private Scene startScene;
    private Scene newPlayerScene;
    private Scene deletePlayerScene;
    private Scene gameScene;

    private Label startLabel;
    private Label gameLabel;
    private String playerXName;
    private String playerOName;
    
    private Font font;
    private Font gameButtonFont;
    
    /**
     * Initialises game
     * @throws Exception 
     */
    @Override
    public void init() throws Exception { 
        
        Properties properties = new Properties();
        properties.load(new FileInputStream("./config.properties"));
        String userFile = properties.getProperty("userFile");
        playerDao = new FilePlayerDao(userFile);
        gameService = new GameService(10, playerDao);
        
        this.appScreen = new BorderPane();  
        this.gameBoard = new GridPane();
        this.startPane = new VBox(30);
        this.startInputPane1 = new HBox(10);
        this.startInputPane2 = new HBox(10);
        this.startInputPane3 = new HBox(10);
        this.gameButtonPane = new HBox(10);
        gameButtonPane.setPadding(new Insets(5));
        this.newPlayerPane = new VBox(10);
        this.deletePlayerPane = new VBox(10);
        this.font = new Font("Arial", 30);
        this.gameButtonFont = new Font("Arial", 20);
        
        this.gameLabel = new Label("");
        gameLabel.setFont(font);
        gameLabel.setText("Player X, please make your move");
        this.startLabel = new Label("Tic-Tac-Toe");
        startLabel.setFont(font);
        this.playerXName = "X";
        this.playerOName = "O";
        
    }
    
    /**
     * Starts application
     * @param primaryStage primary game stage 
     */
    @Override
    public void start(Stage primaryStage) {

        
        Text startText = new Text("PREGAME SETUP");
        
        // Start inputpanes: player inputs
        Label playerXLabel = new Label("Player X");
        Label playerOLabel = new Label("Player O");
        Label boardWidthLabel = new Label("Board width");
        TextField playerXInput = new TextField();
        TextField playerOInput = new TextField();
        TextField boardWidthInput = new TextField();
        Label playerXMessage = new Label();
        Label playerOMessage = new Label();
        Label boardWidthMessage = new Label();
        Button setPlayerXButton = new Button("Set player X");
        Button setPlayerOButton = new Button("Set player O");
        Button setBoardWidthButton = new Button("Set board size");
        
        setPlayerXButton.setOnAction(e->{
            String playerX = playerXInput.getText();
            if (gameService.login(playerX)) {
                this.playerXName = playerX;
                this.gameLabel.setText(playerX + ", please make your move");
                playerXMessage.setText(playerX + " set as Player X");
                playerXMessage.setTextFill(Color.GREEN);
                playerXInput.clear();
            } else {
                playerXMessage.setText(playerX + " does not exist");
                playerXMessage.setTextFill(Color.RED);
            }  
        }); 
        
        setPlayerOButton.setOnAction(e->{
            String playerO = playerOInput.getText();
            if (gameService.login(playerO)) {
                this.playerOName = playerO;
                playerOMessage.setText(playerO + " set as Player O");
                playerOMessage.setTextFill(Color.GREEN);
                playerOInput.clear();
            } else {
                playerOMessage.setText(playerO + " does not exist");
                playerOMessage.setTextFill(Color.RED);
            }  
        });
        
        setBoardWidthButton.setOnAction(e->{
            try {
                int boardWidth = Integer.parseInt(boardWidthInput.getText());
                if (boardWidth < 5) {
                    boardWidthMessage.setText("Minimum board size is 5");
                    boardWidthMessage.setTextFill(Color.RED);
                } else if (boardWidth > 15) {
                    boardWidthMessage.setText("Maximum board size is 15");
                    boardWidthMessage.setTextFill(Color.RED);
                } else {
                    this.gameService = new GameService(boardWidth, playerDao);
                    boardWidthMessage.setText("Board size set to " + boardWidth + " x " + boardWidth);
                    boardWidthMessage.setTextFill(Color.GREEN);
                    boardWidthInput.clear();
                }    
            } catch (NumberFormatException ex) {
                boardWidthMessage.setText("Board width must be an integer");
                boardWidthMessage.setTextFill(Color.RED);
            }
            
        }); 
        
        
        
        startInputPane1.getChildren().addAll(playerXLabel, playerXInput, setPlayerXButton, playerXMessage);
        startInputPane2.getChildren().addAll(playerOLabel, playerOInput, setPlayerOButton, playerOMessage);
        startInputPane3.getChildren().addAll(boardWidthLabel, boardWidthInput, setBoardWidthButton, boardWidthMessage);
        
        // Start pane: infomessage & buttons
        Label infoMessage = new Label();
        Button startGameButton = new Button("Start new game");
        Button createPlayerButton = new Button("Create new player");
        Button deletePlayerButton = new Button("Delete player");
 
        startGameButton.setOnAction(e->{
            setGameBoard(gameService.getGameBoard());
            gameService.setPlayerX(playerXName);
            gameService.setPlayerO(playerOName);
            primaryStage.setScene(gameScene);
        });  
        
        createPlayerButton.setOnAction(e->{
            primaryStage.setScene(newPlayerScene);   
        });
        
        deletePlayerButton.setOnAction(e->{
            primaryStage.setScene(deletePlayerScene);   
        });
        
        // Start scene
        startPane.getChildren().addAll(infoMessage, startText, startInputPane1, startInputPane2, startInputPane3, startGameButton, createPlayerButton, deletePlayerButton);
        startScene = new Scene(startPane, 700, 400);
        
        //New playername pane: input
        HBox newPlayerNamePane = new HBox(10);
        newPlayerNamePane.setPadding(new Insets(10));
        
        TextField newPlayerNameInput = new TextField(); 
        Label newPlayerNameLabel = new Label("Player name");
        newPlayerNameLabel.setPrefWidth(100);
        newPlayerNamePane.getChildren().addAll(newPlayerNameLabel, newPlayerNameInput);
        
        // New player pane: message & button
        Label playerCreationMessage = new Label();
        Button createNewPlayerButton = new Button("Create player");
        createNewPlayerButton.setPadding(new Insets(10));

        createNewPlayerButton.setOnAction(e->{
            String playerName = newPlayerNameInput.getText();
   
            if ( playerName.length()< 2 ) {
                playerCreationMessage.setText("Name too short");
                playerCreationMessage.setTextFill(Color.RED);              
            } else if ( gameService.createPlayer(playerName) ){
                playerCreationMessage.setText("");                
                infoMessage.setText("new player " + playerName + " created");
                infoMessage.setTextFill(Color.GREEN);
                primaryStage.setScene(startScene);
                playerXInput.clear();
                playerOInput.clear();
                boardWidthInput.clear();
            } else {
                playerCreationMessage.setText("Name already taken, please use another name");
                playerCreationMessage.setTextFill(Color.RED);        
            }
 
        });  
        
        newPlayerPane.getChildren().addAll(playerCreationMessage, newPlayerNamePane, createNewPlayerButton); 
       
        newPlayerScene = new Scene(newPlayerPane, 700, 400);
        
        
        // Delete player panes & scene
        HBox deleteAPlayerPane = new HBox(10);
        deleteAPlayerPane.setPadding(new Insets(10));
        
        TextField deleteAPlayerInput = new TextField(); 
        Label deleteAPlayerLabel = new Label("Player name");
        deleteAPlayerLabel.setPrefWidth(100);
        deleteAPlayerPane.getChildren().addAll(deleteAPlayerLabel, deleteAPlayerInput);
        
        
        Label playerDeleteMessage = new Label();
        Button deleteAPlayerButton = new Button("Delete player");
        deleteAPlayerButton.setPadding(new Insets(10));

        deleteAPlayerButton.setOnAction(e->{
            String playerName = deleteAPlayerInput.getText();
   
            if (!gameService.login(playerName)) {
                playerDeleteMessage.setText("Error, player " + playerName + " not found. Player deletion cancelled.");
                playerDeleteMessage.setTextFill(Color.RED);              
            } else {
                gameService.deletePlayer(playerName);
                playerDeleteMessage.setText("");                
                infoMessage.setText("player " + playerName + " deleted");
                infoMessage.setTextFill(Color.RED);
                primaryStage.setScene(startScene);
                playerXInput.clear();
                playerOInput.clear();
                boardWidthInput.clear();
            } 
        });  
        
        deletePlayerPane.getChildren().addAll(playerDeleteMessage, deleteAPlayerPane, deleteAPlayerButton); 
        deletePlayerScene = new Scene(deletePlayerPane, 700, 400);

        
        // Game view buttons setup
        Button newGameButton = new Button("New Game");
        Button startMenuButton = new Button("Start menu");
        startMenuButton.setFont(gameButtonFont);
        startMenuButton.setStyle("-fx-background-color: #00ff00");
        newGameButton.setFont(gameButtonFont);
        newGameButton.setStyle("-fx-background-color: #00ff00");
        
        newGameButton.setOnAction(e->{
            gameBoard.setDisable(false);
            gameService = new GameService(10, playerDao);
            gameService.setPlayerX(playerXName);
            gameService.setPlayerO(playerOName);
            setGameBoard(gameService.getGameBoard());
            gameLabel.setText("New game started. " + gameService.getPlayerX() + ", please make your move");
            primaryStage.setScene(gameScene);
        });
        
        startMenuButton.setOnAction(e->{
            gameBoard.setDisable(false);
            this.gameService = new GameService(10, playerDao);
            primaryStage.setScene(startScene); 
        });
        
        gameButtonPane.getChildren().addAll(newGameButton, startMenuButton);
        
        // Game view setup
        appScreen.setTop(gameLabel);
        appScreen.setCenter(gameBoard);
        
        gameScene = new Scene(appScreen, 1500, 1000);
        

        //primary stage
        primaryStage.setTitle("Tic-Tac-Toe");
        primaryStage.setScene(startScene);
        primaryStage.show();
        
    }
    
    
    /**
     * Sets game board
     * @param gameBoard game board 
     */
    public void setGameBoard(String[][] gameBoard) {
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[0].length; j++) {
                Button button = createButton(i,j);
                this.gameBoard.add(button, i, j);
            }
        }  
    }
    
    /**
     * Creates new button
     * @param i row position
     * @param j column position
     * @return created Button object
     */
    public Button createButton(int i , int j) {
        Button button = new Button();
        button.setPrefSize(80.0, 40.0);
        button.setFont(font);
        button = setStateFor(button, i, j);
        return button;
    }
    
    /**
     * Checks game status
     * @param player player 1
     * @param opponent player 2
     */
    public void checkStatus(String player, String opponent) {
        
        if (gameService.checkStatus().equals(player)) {
            if (player.equals("X")) {
                gameLabel.setText(gameService.getPlayerX() + " WON!");
            } else {
                gameLabel.setText(gameService.getPlayerO() + " WON!");
            }
            gameBoard.setDisable(true);
            appScreen.setBottom(gameButtonPane);
        } else {
            if (opponent.equals("X")) {
                gameLabel.setText(gameService.getPlayerX() + ", please make your move ");
            } else {
                gameLabel.setText(gameService.getPlayerO() + ", please make your move ");
            }
            
        } 
    }
    
    /**
     * Sets state for buttons
     * @param button button under inspection
     * @param i row position
     * @param j column position
     * @return Button object
     */
    public Button setStateFor(Button button, int i, int j) {
        button.setOnAction((event) -> {
            if (button.getText().isEmpty()) {
                if (gameService.isTurnX()) {
                    button.setText("X");
                    gameService.setGameSquare(i, j, "X");
                    gameService.changeTurn();
                    checkStatus("X", "O");
                    
                } else {
                    button.setText("O");
                    gameService.setGameSquare(i, j, "O");
                    gameService.changeTurn();
                    checkStatus("O", "X");
                }
            } 
        });
        return button;
    }
         
    public static void main(String[] args) {
        launch(args);
    }
    
}