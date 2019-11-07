/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.ui;

import java.util.Scanner;

/**
 *
 * @author salojuur
 */
public class TextUI implements UI {
    
    private Scanner scanner;
    
    public TextUI(Scanner scanner) {
        this.scanner = scanner;
    }
    
    @Override
    public void start() {
        
        String player1 = "";
        
        System.out.println("Let's play some Tic-Tac-Toe!");

        
        while (true) {
            
            System.out.print("Options: \n[1] Start new game \n[2] Quit \nYour choice? ");
            String option = scanner.nextLine();
            
            if (option.equals("2")) {
                break;
            }
            
            System.out.print("Who's playing? ");
            player1 = scanner.nextLine();
            break;
        }
        
        if (player1.equals("")) {
            System.out.println("Good bye!");
        } else {
            System.out.println("Thanks for playing " + player1 + "!");

        }
    }
}
