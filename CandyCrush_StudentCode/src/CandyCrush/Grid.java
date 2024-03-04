package CandyCrush;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;

import javax.swing.SwingUtilities;

public class Grid {
	private Candy[][] grid;
	
	public Grid() {
		grid = new Candy[10][10];
		fillGrid();
	}
	
	/**
	 * Uses the randoColor helper method to generate a Candy from the available
	 * image files and add it to the grid.
	 */
	private void fillGrid() { 
		//Write a method to fill out the 2D array with randomly colored squares
		/* to be implemented by student */
		
		//write nested for-loop to traverse the grid
		//2D array that we have to fill with object!
		for(int row = 0; row < grid.length; row++) {
			for(int col = 0; col < grid[row].length; col++) {
				
				grid[row][col] = new Candy(this.randomColor());
				
			}
		}
		
	}
	
	//*******************************************
	//*          Complete methods here          *  
	//*******************************************
	/**
	 * Write a method to find the maximum length sequence of 
	 * the same color candies in a row
	 * @param candies
	 * @return a 2-length array where the first element is the max sequence length
	 * and the second element is the index of the maximum sequence
	 * 	returns an array {0, -1} if array is empty or null
	 */
	public int[] findMaximum(Candy[] candies) { 
		//Write a method to find the maximum length sequence of the same color candies in a row
		//The method returns an integer array formatted in the following way
		//output[0] = length of maximum sequence
		//output[1] = index in array when the maximum sequence starts
		
		
		
		if (candies == null || candies.length <= 0) {
            // Handle edge case where the array is empty or null
            return new int[]{0, -1}; 	//<--- Finish
        }

		//Suggested variables - update how they are initialized
        int maxLength 		= -1;	//length of longest sequence
        int currentLength 	= 1;	//current length of sequence
        int startIndex 		= -1;	//starting index of longest sequence so far
        int currentStartIndex = -1; //temp var for tracking current starting index
        
        for (int i = 0; i < candies.length - 1; i++) {
        	
        	if(candies[i].getColor().equals(candies[i + 1].getColor())) {
        		
        		currentStartIndex = i;
        		
            	while(candies[i].getColor().equals(candies[i + 1].getColor())){
            		if(i < candies.length) {
            			i++;
            		}
            		currentLength++;
            	}
            	
        	}
        	
        	if(currentLength > maxLength) {
        		maxLength = currentLength;
        		startIndex = currentStartIndex;
        	}
        	
        	currentLength = 1;
        	
        }
        
        //Return the result 
        int[] result = {maxLength, startIndex}; //maxLength, startIndex
        
        return result; //<--- replace with yours after you have determined the
        					//required 1d array
        
        
        
	}
	
	/**
	 * //Write a method to check if two positions in a 2D array are adjacent to each other
	 * @param row1, col1 
	 * @param row2, col2 
	 * @return true if adjacent (not diagonal)
	 */
	public boolean isAdjacent(int row1, int col1, int row2, int col2) {
		
		if(row1 + 1 == row2 || row1 - 1 == row2) {
			if(col1 == col2) {
				return true;
			}else {
				return false;
			}
		}
		else if(row1 == row2) {
			if(col1 + 1 == col2 || col1 - 1 == col2) {
				return true;
			}else {
				return false;
			}
		}
		
		return false;
	}
	
	/**
	 * Write a method that swaps the positions of two Candy objects given 
	 * their x and y position values in the 2d array
	 * @param r1, c1 
	 * @param r2, c2
	 */
	public void swapSpaces(int r1, int c1, int r2, int c2) {
		
		/* to be implemented */
		/* the 2D array is the instance variable at the beginning of this class */
		Candy temp = grid[r1][c1];
		grid[r1][c1] = grid[r2][c2];
		grid[r2][c2] = temp;
		
	}
	
	
	/**
	* Write a method to check if the grid contains a row with 3 
	* or more of the same color candies in a row then 
	* return the index of the row
	* 
	* Must use the findMaximum method
	* Returns -1 if there are zero rows with 3 or more candies in a row
	*/
	public int inARow() {

		//traverse the grid row-by-row
		//use the findMaximum method that you wrote to get the max sequence
		//of the row
		//return the row if sequence is 3 or more otherwise check the next row
		
		for(int row = 0; row < grid.length-1; row++) {
			if(findMaximum(grid[row])[0] >= 3) {
				return row;
			}
		}
		
        return -1;
	}
	
	/**
	 * Return an array made up of the Candy objects down a given column
	 * @param col
	 * @return 1D array of Candy object representing a column
	 */
	public Candy[] getColumn(int col) {
       
		Candy[] arr = new Candy[grid[0].length]; //array with length the same as number of columns
		
		for(int row = 0; row < grid.length; row++) {
			arr[row] = grid[row][col];
		}
		
        return arr;
		
	}
	
	/**
	 * Write a method to check if the grid contains a column with 3 
	 * or more of the same color candies in a row
	 * Then return the index of the column
	 * Returns -1 if there are zero rows with 3 or more candies in a row
	 */
	public int inAColumn() {
		
//		for(int col = 0; col < grid[0].length; col++) {
//			if(findMaximum(getColumn(col))[0] >= 3)  {
//				return col;
//			}
//		}
		
        return -1;
	}
	
	
	
	
	
	
	
	
	
	
	
	//Getters and setters
	public int getLength()           {return grid.length;}
	public int getHeight()           {return grid[0].length;}
	public Candy getValue(int r, int c)   {return grid[r][c];}
	
	public int xOffset = 65;
	public int yOffset = 80;
	public int squareSize = 65;
	public String animationOverride = "";
	
	public void paint(Graphics g) {
		paintGridOutline(g);
	}
	
	public Candy[] getRow(int r) {
		return grid[r]; 
	}
	

	
	private void paintGridOutline(Graphics g) {
		for (int a = 0; a < getLength();a++) 
		{
			g.drawLine(xOffset, yOffset+(squareSize)*a, xOffset+(squareSize)*getLength(), 
					yOffset+(squareSize)*a);
			
			g.drawLine(xOffset+(squareSize)*a, yOffset, xOffset+(squareSize)*a, 
					yOffset+(squareSize)*getHeight());
		}
		g.drawLine(xOffset+(squareSize)*getLength(), yOffset,xOffset+(squareSize)*getLength(),
				yOffset+(squareSize)*getHeight());
		g.drawLine(xOffset, yOffset+(squareSize)*getHeight(),xOffset+(squareSize)*getLength(),
				yOffset+(squareSize)*getHeight());
	}
	
	public boolean checkForEmptySpaces() {
		for (int r = 0; r < grid.length; r++) {
			for (int c = 0; c < grid[r].length; c++) {
				if (grid[r][c] == null) {
					return true;
				}
			}
		}
		return false;
	}
	
	public void replaceEmpty() {
		while (checkForEmptySpaces()) {
			for (int c = 0; c < grid.length;c++) {
					if (grid[0][c] == null) {
						animationOverride = "replace";
						grid[0][c] = new Candy(randomColor());
					}
			}
			for (int r = 0; r < grid.length; r++) {
				for (int c = 0; c < grid[r].length;c++) {
					if (grid[r][c] != null && r+1<10 && grid[r+1][c] == null) {
						swapSpaces(r,c,r+1,c);
					}
				}
			}
		}
		animationOverride = "";
	}
	
	private String randomColor() {
		int rand = (int)(Math.random()*6);
		if (rand == 0) {
			return "Purple";
		}
		if (rand == 1) {
			return "Green";
		}
		if (rand == 2) {
			return "Red";
		}
		if (rand == 3) {
			return "Orange";
		}
		if (rand == 4) {
			return "Blue";
		}
		if (rand == 5) {
			return "Yellow";
		}
		return "Invalid";
	}
	
	public String toString() {
		String output = "";
		for (int r = 0;r <  grid.length; r++) {
			for (int c = 0; c < grid[r].length;c++) {
				if (grid[r][c] != null) {
					output += grid[r][c].getColor();
					output += " ";
				}
			}
			output += "\n";
		}
		return output;
	}
	
	public void clearRow(int startIndex, int endIndex, int rowIndex) {
		for (int i = startIndex; i <= endIndex && i < grid[0].length; i++) {
            grid[rowIndex][i] = null;
        }
	}
	
	public void clearColumn(int startIndex, int endIndex, int columnIndex) {
		for (int i = startIndex; i <= endIndex && i < grid.length; i++) {
            grid[i][columnIndex] = null;
        }
	}
	
	public boolean checkSwappedPositions(int r1, int c1, int r2, int c2) {
		Candy[][] checker = grid;
		Candy temp = checker[r1][c1];
		checker[r1][c1] = checker[r2][c2];
		checker[r2][c2] = temp;
		if (inARow() != -1 || inAColumn() != -1) {
			return false;
		}
		return true;
	}
	
	public void generateNew() {
		
	}
}