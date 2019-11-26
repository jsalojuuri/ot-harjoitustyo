/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.ui;

import java.util.Scanner;
import tictactoe.service.GameService;

/**
 *
 * @author salojuur
 */

public class TextUI implements UI {
    
    GameService gameService;
    Scanner scanner;
    
    public TextUI(Scanner scanner, GameService gameService) {
        this.scanner = scanner;
        this.gameService = gameService;
    }
    
    @Override
    public void start() {
        
        String player1 = "";
        
        System.out.println("Let's play some Tic-Tac-Toe!");

        
        while (true) {
            
            System.out.print("Options: \n[1] Start new game \n[2] Quit \nYour choice? ");
            String option = scanner.nextLine();
            
            if (option.equals("1")) {
                System.out.print("Set game board width (as squares): ");
                int width = scanner.nextInt();
                
                /*
                System.out.print("Who's playing? ");
                player1 = scanner.nextLine();
                try {
                    gameService.createPlayer(player1);
                    if (gameService.createPlayer(player1)) {
                        System.out.println("Welcome aboard " + player1 + "!");
                    } else {
                        System.out.println("Welcome back " +player1 + "!");
                    }
                    */
                } catch (Exception e) {
                    System.out.println("Something went wrong!");
                }
  
            }
            
            if (option.equals("2")) {
                if (player1.equals("")) {
                    System.out.println("Good bye!");
                    break;
                } else {
                    System.out.println("Thanks for playing " + player1 + "!");
                    break;
                }
            }
        }
    }
}
