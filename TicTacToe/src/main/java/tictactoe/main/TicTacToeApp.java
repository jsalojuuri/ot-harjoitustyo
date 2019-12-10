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
import tictactoe.dao.FilePlayerDao;
import tictactoe.service.GameService;

/**
 * JavaFX application for TicTacToe
 * @author salojuur
 */
public class TicTacToeApp extends Application {
    private GameService gameService;
    private BorderPane appScreen;
    private VBox startPane;
    private HBox startInputPane1;
    private HBox startInputPane2;
    private VBox newPlayerPane;
    private GridPane gameBoard;
    
    private Scene startScene;
    private Scene newPlayerScene;
    private Scene gameScene;

    private Label startLabel;
    private Label gameLabel;
    private String playerXName;
    private String playerOName;
    
    private Font font;
    
    /**
     * Initialises game
     * @throws Exception 
     */
    @Override
    public void init() throws Exception { 
        
        Properties properties = new Properties();
        properties.load(new FileInputStream("./config.properties"));
        String userFile = properties.getProperty("userFile");
        FilePlayerDao playerDao = new FilePlayerDao(userFile);

        this.gameService = new GameService(20, playerDao);
        this.appScreen = new BorderPane();  
        this.gameBoard = new GridPane();
        this.startPane = new VBox(30);
        this.startInputPane1 = new HBox(10);
        this.startInputPane2 = new HBox(10);
        this.newPlayerPane = new VBox(10);
        this.font = new Font("Arial", 30);
        
        this.gameLabel = new Label("");
        gameLabel.setFont(font);
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
        TextField playerXInput = new TextField();
        TextField playerOInput = new TextField();
        Label playerXMessage = new Label();
        Label playerOMessage = new Label();
        Button setPlayerXButton = new Button("Set player X");
        Button setPlayerOButton = new Button("Set player O");
        
        setPlayerXButton.setOnAction(e->{
            String playerX = playerXInput.getText();
            if (gameService.login(playerX)) {
                this.playerXName = playerX;
                this.gameLabel.setText(playerX + ", please make your move");
                playerXMessage.setText(playerX + " set as Player X");
                playerXMessage.setTextFill(Color.GREEN);
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
            } else {
                playerOMessage.setText(playerO + " does not exist");
                playerOMessage.setTextFill(Color.RED);
            }  
        });         
        
        
        
        startInputPane1.getChildren().addAll(playerXLabel, playerXInput, setPlayerXButton, playerXMessage);
        startInputPane2.getChildren().addAll(playerOLabel, playerOInput, setPlayerOButton, playerOMessage);
        
        // Start pane: infomessage & buttons
        Label infoMessage = new Label();
        Button startGameButton = new Button("Start new game");
        Button createPlayerButton = new Button("Create new player");
 
        startGameButton.setOnAction(e->{
            primaryStage.setScene(gameScene);
        });  
        
        createPlayerButton.setOnAction(e->{
            primaryStage.setScene(newPlayerScene);   
        });
        
        // Start scene
        startPane.getChildren().addAll(infoMessage, startText, startInputPane1, startInputPane2, startGameButton, createPlayerButton);
        startScene = new Scene(startPane, 600, 400);
        
        
        //New playername pane: input
        HBox newPlayerNamePane = new HBox(10);
        newPlayerNamePane.setPadding(new Insets(10));
        
        
        TextField newPlayerNameInput = new TextField(); 
        Label newPlayerNameLabel = new Label("Player name");
        newPlayerNameLabel.setPrefWidth(100);
        newPlayerNamePane.getChildren().addAll(newPlayerNameLabel, newPlayerNameInput);
        
        // New player pane: message & button
        Label playerCreationMessage = new Label();
        Button createNewPlayerButton = new Button("create");
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
            } else {
                playerCreationMessage.setText("Name already taken, please ue another name");
                playerCreationMessage.setTextFill(Color.RED);        
            }
 
        });  
        
        newPlayerPane.getChildren().addAll(playerCreationMessage, newPlayerNamePane, createNewPlayerButton); 
       
        newPlayerScene = new Scene(newPlayerPane, 600, 400);
        
        
        // Game scene
        appScreen.setTop(gameLabel);
        appScreen.setCenter(gameBoard);
        gameScene = new Scene(appScreen, 1000, 1000);
        setGameBoard(gameService.getGameBoard());

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
        button.setPrefSize(80, 40);
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
                gameLabel.setText(this.playerXName + " WON!");
            } else {
                gameLabel.setText(this.playerOName + " WON!");
            }
            gameBoard.setDisable(true);
        } else {
            if (opponent.equals("X")) {
                gameLabel.setText(this.playerXName + ", please make your move ");
            } else {
                gameLabel.setText(this.playerOName + ", please make your move ");
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