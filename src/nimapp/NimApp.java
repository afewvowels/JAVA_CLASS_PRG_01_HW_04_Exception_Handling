/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nimapp;

import java.util.*;
import exceptions.*;

/**
 *
 * @author John Sullins
 */
public class NimApp {
    
    // Main application to run command line version of game
    public static void main(String[] args) {        
       
        boolean repeat = true;
        
        int row = 0, sticks = 0;
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();
        
        // Construct new game object with 3, 5, and 7 sticks
        NimGame nim = new NimGame(new int[]{3, 5, 7});
        
        // Keep track of whose turn it is
        int player = 1;
        
        // Draw the initial board
        draw(nim);
    
        // Loop until game object reports done
        while (!nim.isOver()) {
            
            repeat = true;
            
            // Print whose turn it is
            System.out.println("Player "+player+" turn.");
                        
            // Loop while repeat flag is set to true to allow for user to
            // re-enter data if bad data is provided and an exception is
            // thrown
            while(repeat) {
                // Player 1: Human Turn
                if(player == 1) {
                   try {
                    // Prompt player for row and number of sticks to remove
                    System.out.print("Which row to remove sticks from: ");
                    row = scanner.nextInt();
                    // Call validate after each data entry to force
                    // exception checking after each data entry instead
                    // of allow user to enter bad data twice in a row.
                    // Done to ensure proper exception is throw, i.e. cannot
                    // enter a bad row and a bad # of sticks and only get one
                    // exception, now will always throw exactly the right
                    // exception and provide the correct error message.
                    nim.validateRow(row);
                    nim.validateEmptyRow(row);
                    
                    // Prompt player for number of sticks to remove
                    System.out.print("How many sticks to remove: ");
                    sticks = scanner.nextInt();
                    // Validate input.
                    nim.validateSticks(sticks);
                    nim.validateMove(row, sticks);
                    
                    // Send that information to the game object for validation
                    nim.play(row, sticks);
                    
                    // Set repeat flag to false if no exceptions are thrown
                    repeat = false;
                    }
                    // Catch exceptions thrown and provide user with usable
                    // information to indicate how data was bad
                    catch (NoSuchRowException | ArrayIndexOutOfBoundsException ex) {
                        System.out.println("Please enter a valid row.");
                    }
                    catch (RowIsEmptyException ex) {
                        System.out.println("That row is already empty.");
                    }
                    catch (IllegalSticksException ex) {
                        System.out.println("Please enter a valid number of sticks.");
                    }
                    catch (NotEnoughSticksException ex) {
                        System.out.println("Not enough sticks in that row.");
                    } 
                }
                // Player 2: Computer turn
                else {
                    try {
                        // Randomly generate a row and validate the input
                        row = rand.nextInt(3);
                        nim.validateRow(row);

                        // Randomly generate a number of sticks and validate
                        // the input
                        sticks = (rand.nextInt(3) + 1);
                        nim.validateSticks(sticks);

                        // Validate the row and sticks together 
                        nim.validateMove(row,sticks);

                        // If valid, play the move
                        nim.play(row,sticks);

                        // If this is reached, a valid move has been made
                        // so set the loop flag to false to advance turn
                        repeat = false;
                    }
                    // No error message because computer turn may throw many
                    // errors before a valid move is chosen, since the result
                    // is the same for all errors (show no message)
                    // a multi-catch was used
                    catch (NoSuchRowException | ArrayIndexOutOfBoundsException |
                            RowIsEmptyException | IllegalSticksException |
                            NotEnoughSticksException ex) {
                    } 
                }
            }
                        
            // Change the player number to the next player
            if (player == 1) {
                player = 2;
            }
            else {
                player = 1;
            }
            
            // Check whether the game is over, and print who won.
            if (nim.isOver()) {
                System.out.println("Player "+player+" wins!");
            }
            // Otherwise, redraw the board for the next turn
            else {
                draw(nim);
            }
            
//             DEBUG: USED FOR DEBUG PURPOSES
//             System.out.println(nim.toString());
            
        }
        
    }
     
    // Utility function to redraw game
    private static void draw(NimGame nim) {
        for (int row = 0; row < 3; row++) {
            String sticks = "";
            for (int j = 0; j < nim.getRow(row); j++) {
                sticks += "|   ";
            }
            System.out.println(sticks);
        }
    } 
    
}