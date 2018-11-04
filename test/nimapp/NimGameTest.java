/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nimapp;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import exceptions.*;

/**
 * These are the tests for NimApp and NimGame support class.
 * @author bluebackdev
 */
public class NimGameTest {
    
    public NimGameTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getRow method, of class NimGame, uses 3 separate tests to check
     * all aspects of the getRow() method. Test 1: NoSuchRowException,
     * Test 2: Test value returned by getRow() method, Test 3: Test exception
     * thrown by getRow() method.
     */
    @Test
    public void testGetRow() {
        System.out.println("getRow");
        
        // Create an array for use with NimGame constructor.
        // Creates an array: int[] {1,2,3};
        final int ARR_SIZE = 3;
        int r[] = new int[3];
        for(int i = 0; i < ARR_SIZE; i++) {
            r[i] = (i + 1);
        }
        
        // Create a NimGame with the above array
        NimGame instance = new NimGame(r);
        
        // Test 1: Valid function call.
        
        // Loop through and test .getRow(i) at each index (size 3 array
        // so testing index values 0, 1, and 2). Throw a NoSuchRowException
        // if invalid row is passed through.
        for(int i = 0; i < ARR_SIZE; i++) {
            try {
                instance.getRow(i);
            }
            catch (NoSuchRowException e) {
                fail("Failed at row " + i);
            }
        }
        
        // END TEST 1
        
        // Test 2: Validate result returned from method call
        
        // Now loop through and test result returned from .getRow(i) method.
        // Compare actual and expected result using assert equals, catch
        // NoSuchRowException here too but more likely to fail the assertEquals
        // declaration.
        for(int i = 0; i < ARR_SIZE; i++) {
            try {
                int result =  instance.getRow(i);
                int expResult = (i + 1);
                assertEquals(expResult, result);
            }
            catch (NoSuchRowException e) {
                fail("Invalid row passed through [" + i + "]");
            }
        }
        
        // END TEST 2
        
        // Test 3: Invalid index exception test
        
        // Invalid index exception test, pass through invalid row and catch
        // exception. Test fails if exception is not thrown.
        int row = 3;
        try {
            int result = instance.getRow(row);
            fail("Should have thrown exception");
        }
        catch (ArrayIndexOutOfBoundsException | NoSuchRowException e) {
            assertEquals(1,1);
        }
        
        // END TEST 3
    }

    /**
     * Test of play method, of class NimGame, has 4 separate tests in the
     * method. These four tests include testing 1) A legal move with catches
     * for three separate exceptions, 2) An invalid row index, 3) An illegal
     * number of sticks (out of bounds), 4) An illegal number of sticks (greater
     * than existing sticks in row). **NOTE** SEE TOSTRING() METHOD FOR LEGAL
     * PLAY METHOD CALL VALIDATION
     */
    @Test
    public void testPlay() {
        System.out.println("play");
        
        // Create integer array for use with creation of new NimGame object.
        // Int [] {1, 2, 3};
        final int ARR_SIZE = 3;
        int r[] = new int[3];
        for(int i = 0; i < ARR_SIZE; i++) {
            r[i] = (i + 1);
        }
        
        // Create row and sticks to remove for use with play() method
        int row = 0;
        int sticks = 1;
        
        // Create NimGame instance with above integer array int [] {1, 2, 3};
        NimGame instance = new NimGame(r);
        
        // Test 1: Try legal move, catch illegal expressions in single catch
        // block because it's expected that none will be caught. Use
        // e.getClass() to show specific exception that is thrown if one is
        // thrown.
        try {
            instance.play(row, sticks);
            int result = instance.getRow(row);
            int expected = 0;
            assertEquals(expected, result);
        }
        catch (NoSuchRowException | IllegalSticksException |
                NotEnoughSticksException e) {
            fail("Exception thrown during move " + e.getClass());
        }
        
        // Re-create integer array since .play(r,s) method modifies the array r
        r = new int[3];
        for(int i = 0; i < ARR_SIZE; i++) {
            r[i] = (i + 1);
        }
        
        // Re-define legal move parameters.
        row = 0;
        sticks = 1;
        
        // Re-create NimGame instance with reset integer array
        instance = new NimGame(r);
        
        // END TEST 1
        
        // Test 2: Pass through illegal row to test NoSuchRowException.
        try {
            instance.play(3, sticks);
            fail("Should have thrown NoSuchRowException");
        }
        catch (NoSuchRowException e) {
            
        }
        
        // END TEST 2
        
        // Test 3: Pass through illegal number of sticks (-1) to test
        // IllegalSticksException.
        try {
            instance.play(row, -1);
            fail("Should have thrown IllegalSticksException");
        }
        catch (IllegalSticksException e) {
            
        }
        
        // END TEST 3
        
        // Test 4: Pass through a number of sticks greater than is in the
        // indicated row to test NotEnoughSticksException
        try {
            instance.play(row, 2);
            fail("Should have thrown NotEnoughSticksException");
        }
        catch (NotEnoughSticksException e) {
            
        }
        
        // END TEST 4
    }

    /**
     * Test of isOver method, of class NimGame, contains 2 separate tests, one
     * for testing true and the second for testing false.
     */
    @Test
    public void testIsOver() {
        System.out.println("isOver");
        
        // Test 1: isOver() == false
        
        // Create integer array for use with creation of NimGame(r) instance
        // Integer array contains integer values int[] {1, 2, 3} so isOver()
        // should return false.
        final int ARR_SIZE = 3;
        int r[] = new int[ARR_SIZE];
        for(int i = 0; i < ARR_SIZE; i++) {
            r[i] = (i + 1);
        }
        
        // Create NimGame instance with above array
        NimGame instance = new NimGame(r);
        
        // Define expected result as false and define result actual with
        // method call to .isOver()
        boolean expResult = false;
        boolean result = instance.isOver();
        
        // Compare expected result and actual result
        assertEquals(expResult, result);
        
        // END TEST 1
        
        // Test 2: isOver() == true
        
        // Re-define integer array r for second test. Create empty array
        // int[] {1, 1, 1} which should return .isOver() == true. Creating
        // an array int[] {0, 0, 0} throws a IllegalStartingSticksException.
        r = new int[ARR_SIZE];
        for(int i = 0; i < ARR_SIZE; i++) {
          r[i] = 1;
        }
       
        // Re-create NimGame instance with empty array
        instance = new NimGame(r);
        
        // Make a play for each row and remove one stick from each row.
        // Should result in r[] {0, 0, 0}
        for(int i = 0; i < ARR_SIZE; i++) {
            instance.play(i, 1);
        }
       
        // Re-define expected result to true, re-define result actual
        // with method call to .isOver()
        expResult = true;
        result = instance.isOver();
       
        // Compare the two results
        assertEquals(expResult, result);
        
        // END TEST 2
    }

    /**
     * Test of validateEmptyRow method, of class NimGame.
     */
    @Test
    public void testValidateEmptyRow() {
        System.out.println("validateEmptyRow");
        
        final int ARR_SIZE = 3;
        int r[] = new int[3];
        for(int i = 0; i < ARR_SIZE; i++) {
            r[i] = (i + 1);
        }
        
        int row = 0;
        
        NimGame instance = new NimGame(r);
        
        instance.play(row, 1);
        
        try {
            instance.validateEmptyRow(row);
            fail("Should have thrown RowIsEmptyException");
        }
        catch (RowIsEmptyException e) {
            
        }
    }

    /**
     * Test of validateRow method, of class NimGame.
     */
    @Test
    public void testValidateRow() {
        System.out.println("validateRow");        
        
        final int ARR_SIZE = 3;
        int r[] = new int[3];
        for(int i = 0; i < ARR_SIZE; i++) {
            r[i] = (i + 1);
        }
        
        int row = 0;
        int sticks = 1;
        
        NimGame instance = new NimGame(r);
        
        try {
            instance.validateRow(3);
            fail("Should have thrown NoSuchRowException");
        }
        catch (ArrayIndexOutOfBoundsException | NoSuchRowException e) {
            
        }
    }

    /**
     * Test of validateSticks method, of class NimGame.
     */
    @Test
    public void testValidateSticks() {
        System.out.println("validateSticks");
        
        final int ARR_SIZE = 3;
        int r[] = new int[3];
        for(int i = 0; i < ARR_SIZE; i++) {
            r[i] = (i + 1);
        }
        
        NimGame instance = new NimGame(r);
        
        int s = -1;
        
        try {
            instance.validateSticks(s);
            fail("Should have thrown IllegalSticksException");
        }
        catch (IllegalSticksException e) {
            
        }
    }

    /**
     * Test of validateMove method, of class NimGame.
     */
    @Test
    public void testValidateMove() {
        System.out.println("validateMove");
        
        final int ARR_SIZE = 3;
        int r[] = new int[3];
        for(int i = 0; i < ARR_SIZE; i++) {
            r[i] = (i + 1);
        }
        
        NimGame instance = new NimGame(r);
        
        int row = 1;
        int sticks = 3;
        
        try {
            instance.validateMove(row, sticks);    
            fail("Should have thrown NotEnoughSticksException");
        }
        catch (NotEnoughSticksException e) {
            
        }
    }

    /**
     * Test of toString method, of class NimGame, checks the toString() method
     * by creating a NimGame, making a move, and check the result against
     * a desired output result.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        
        // Create the array for use in creation of NimGame instance.
        // Creates an int[] {1, 2, 3};
        final int ARR_SIZE = 3;
        int r[] = new int[3];
        for(int i = 0; i < ARR_SIZE; i++) {
            r[i] = (i + 1);
        }
        
        // Define a row and number of sticks to remove
        int row = 0;
        int sticks = 1;
        
        // Create the NimGame object with integer array r
        NimGame instance = new NimGame(r); 
        
        // Call the play method to modify NimGame
        instance.play(row, sticks);
        
        // Define the expected result.
        String expResult = "Number of sticks per row:\n" +
                            "ROW 1: 0\n" +
                            "ROW 2: 2\n" +
                            "ROW 3: 3\n";
        // Define the actual result with a method call
        String result = instance.toString();
        // Compare the expected and actual results
        assertEquals(expResult, result);
    }
    
}
