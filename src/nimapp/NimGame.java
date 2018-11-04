/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nimapp;

import exceptions.*;
/**
 *
 * @author bluebackdev
 * This support class provides the game logic for the NimApp application.
 * This support class throws exceptions based on input data to indicate where
 * in the application errors are occurring. NimGame is a game involving a
 * number of rows (initially set to 3) with an arbitrary number of sticks
 * (initially set to 3, 5, and 7). Players take turns removing between 1 and 3
 * sticks from a player-chosen row. Players cannot remove more sticks than
 * are present in the row. The player that picks up a stick last loses.
 * 
 */
public class NimGame {
    
    private final int NUM_ROWS = 3;
    private final int MAX_STICKS = 10;
    
    /**
     * Main sticks array for the NimGame support class, holds the number of
     * sticks per row, initialized at 3 but can be easily modified.
     */
    public int sticksArr[] = new int[this.NUM_ROWS];
    
    /**
     * Main constructor for NimGame takes in an array of integers to determine
     * number of sticks per row. i.e. an array int[3] {1,5,7} creates a stick
     * count of this.sticksArr[0] = 1, this.sticksArr[1] = 5,
     * this.sticksArr[2] = 7. Performs data validation on numbers passed in to
     * ensure no negative numbers are provided and that an arbitrary limit
     * this.MAX_STICKS is not exceeded (keeps game play time reasonable).
     * @param initialSticks Integer array used to determine number of sticks
     * per row in for the game. 
     * @exception IllegalStartingSticksCountException Exception thrown if
     * values are negative or above arbitrary limit set above.
     */
    public NimGame(int[] initialSticks) {
        for(int i = 0 ; i < this.NUM_ROWS ; i++) {
            if(initialSticks[i] < 1 || initialSticks[i] > this.MAX_STICKS) {
                throw new IllegalStartingSticksCountException();
            }
            this.sticksArr[i] = initialSticks[i];
        }
    }

    /**
     * Returns a number from the this.sticksArr array based on a user input
     * which is used as the array index to pull the value (number of sticks
     * in that row) that is returned.
     * @param r Input row from user, validated and exceptions thrown if input
     * is not valid
     * @exception NoSuchRowException validation performed to determine that
     * the row provided is within the range 0-2 (# of rows in this.sticksArr).
     * @return Returns an integer value stored at array element r in
     * this.sticksArr
     */
    public int getRow(int r) {
        validateRow(r);
        
        return this.sticksArr[r];
    }
    
    /**
     * This method is used to modify the main game array that holds the number
     * of sticks per row, extensive data validation is performed here and
     * several different types of exception can be thrown by this method.
     * @param r Row value passed in by user, validated in play()
     * @param s Sticks value passed in by user, validated in play()
     * @exception NoSuchRowException First check that row is legal (i.e. within
     * 0 and 2 (r < this.NUM_ROWS)
     * @exception IllegalSticksException Checks to make sure that the value
     * passed in for number of sticks to remove is between 1 and 3
     * @exception NotEnoughSticksException Uses the validated r and s values
     * to check that a sufficient number of sticks exist in that row for s
     * sticks to be removed from that row (minimum s sticks for
     * this.sticksArr[r])
     */
    public void play(int r, int s) {
        validateRow(r);
        validateSticks(s);
        validateMove(r,s);
        
        this.sticksArr[r] -= s;
    }
    
    /**
     * This method watches the number of sticks total in the this.sticksArr
     * array and returns true (ie game is over) if the number of sticks == 0
     * and returns false if count != 0.
     * @return True/false is the game over (ie has a player removed the last
     * stick on the board
     */
    public boolean isOver() {
        int count = 0;
        
        for(int i = 0 ; i < this.NUM_ROWS ; i++) {
            count += this.sticksArr[i];
        }
        
        if(count != 0) {
            return false;
        }
        else if(count == 0) {
            return true;
        }
        else {
            throw new IllegalStickCountException();
        }
    }
    
    /**
     * This method is used to validate that a row contains sticks based on an
     * r value provided by the user.
     * @param r Row value / index value provided by user
     * @exception RowIsEmptyException Thrown if the row entered by the user
     * has a stick count of 0 (this.sticksArr[r] == 0)
     */
    public void validateEmptyRow(int r) {
        if(this.sticksArr[r] == 0) {
            throw new RowIsEmptyException();
        }
    }
    
    /**
     * This method is used to validate that an index value row r provided by
     * the user is within the bounds provided by this.sticksArr.length
     * @param r Row value / index value provided by user
     * @exception NoSuchRowException Thrown if the r value provided by the user
     * is <= 0 or >= this.sticksArr.length
     */
    public void validateRow(int r) {
        if(r < 0 || r > 2) {
            throw new NoSuchRowException();
        }
    }
    
    /**
     * This method is used to validate that the number of sticks entered by
     * the user for removal is within legal limits (1, 2, or 3).
     * @param s Number of sticks provided by user.
     * @exception IllegalSticksException Thrown if the number of sticks s
     * provided by the user is not within legal bounds (1, 2, or 3).
     */
    public void validateSticks(int s) {
        if(s < 1 || s > 3) {
            throw new IllegalSticksException();
        }
    }
    
    /**
     * This method is used to validate that there are a sufficient number of
     * sticks in row r for s sticks to be removed from that row
     * (s <= this.sticksArr[r])
     * @param r
     * @param s 
     */
    public void validateMove(int r, int s) {
        if(this.sticksArr[r] < s) {
            throw new NotEnoughSticksException();
        }
    }
        
    /**
     * This method is used for debug purposes, it prints out the number of
     * sticks in a row as an integer value.
     * @return 
     */
    public String toString() {
        String string = new String();
        
        string += "Number of sticks per row:\n";
        for(int i = 0 ; i < this.NUM_ROWS ; i++) {
            string += "ROW " + (i+1) + ": " + this.sticksArr[i] + "\n";
        }
        
        return string;
    }
}
