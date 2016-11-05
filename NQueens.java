//Andrew Trice
//Homework 4,8,10,12 N-Queens Backtracking Implementation
//Created using pseudocode from Foundations of Algorithms and 
//N-Queens example from Algorithms II 4th Edition from 
//Princeton University
package csci310;
import java.util.Arrays;
import java.math.*;


public class NQueens {
	static void buildBoard(int queeni, int[] board) {
	    int n = board.length;
	    if (queeni == n) {// Valid
	      System.out.println("|"+Arrays.toString(board)+"|");
	    } else {
	      //Place ith Queen (Qi) in all of the columns
	      for (int column = 1; column < n+1; column++) {
	    	  //if validPlacement is true
	        if (validPlacement(column, queeni, board)) {
	        	//place Queen into column
	          board[queeni] = column;
	          //Place remaining queens.
	          buildBoard(queeni + 1, board);
	          //BACKTRACK
	          board[queeni] = -1;
	        }
	      }
	    }
	  }
	 
	  //check column for valid placement queen[i] (ith Queen)
	  //method declared as boolean returns false if invalid placement
	  //returns true otherwise
	   static boolean validPlacement(int column, int queeni, int[] board) {
	 
	    //check for all previously placed queens
	    for (int i = 0; i < queeni; i++) {
	      if (board[i] == column) { // the last Queen is in the same column
	        return false;
	      }
	      //Check Diagonals
	      if (Math.abs(board[i] - column) == Math.abs(i - queeni)) {
	        return false;
	      }
	    }
	    return true;
	  }
	  public static void main(String args[]) {
		    int n1 = 4;
		    int n2 = 8;
		    int n3 = 10;
		    int n4 = 12;
		    int[] board1 = new int[n1]; //hold the column position of 8 queens
		    System.out.println("Queens for n = 4");
		    System.out.println(" ------------------------");
		    buildBoard(0, board1);
		    System.out.println(" ------------------------");
		    int[] board2 = new int[n2];
		    System.out.println("Queens for n = 8");
		    System.out.println(" ------------------------");
		    buildBoard(0, board2);
		    System.out.println(" ------------------------");
		    int[] board3 = new int[n3];
		    System.out.println("Queens for n = 10");
		    System.out.println(" ------------------------");
		    buildBoard(0, board3);
		    System.out.println(" ------------------------");
		    int[] board4 = new int[n4];
		    System.out.println("Queens for n = 12");
		    System.out.println(" ------------------------");
		    buildBoard(0, board4);
		    System.out.println(" ------------------------");
		  }
	}


