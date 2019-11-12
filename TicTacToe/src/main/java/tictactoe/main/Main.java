/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.main;

import java.util.Scanner;
import tictactoe.ui.*;
import java.sql.*;

/**
 *
 * @author salojuur
 */
public class Main {
    
    public static void main(String[] args) throws Exception {
        
        Connection connection = DriverManager.getConnection("jdbc:sqlite:tictactoe.db");

        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("SELECT 1");

        if (resultSet.next()) {
            System.out.println("Connected to database");
        } else {
            System.out.println("Connection to database failed");
        }
        
        
        Scanner scanner = new Scanner(System.in);
        
        UI ui = new TextUI(scanner);
        ui.start();
    }
    
}
