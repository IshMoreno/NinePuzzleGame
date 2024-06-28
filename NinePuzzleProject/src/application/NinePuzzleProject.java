package application;

import java.util.Random;

import java.util.Scanner;

public class NinePuzzleProject {
	
	public static void main(String[] args) {
		
		// Initializing scanner to read user input
		Scanner in = new Scanner(System.in);
		
		// Generating puzzle
		generatePuzzle();

		int space = -1;
		
		// Loop until user choose to quit

		while (space != 0) {

			runPuzzle();

			System.out.print("Next move: ");
			
			// Getting choice
			space = in.nextInt();

			if (space != 0 && space!= BLANK) {
				
				// Making move
				MoveNumber(space);

			}

		}

	}
	
	static int[][] puzzle;
	
	static final int BLANK = -1;
	
	static void generatePuzzle() {
		
		// Creating Array
		puzzle = new int[3][3];
		
		// Creating temporary array to store all values in the puzzle
		// Empty space is defined by -1
		int numbers[] = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, BLANK };
		
		// Randomize numbers
		Random rdm = new Random();
		
		for (int i = 0; i < numbers.length; i++) {

			int index = rdm.nextInt(i + 1);
			
			// Swapping values at index and i
			int temp = numbers[index];

			numbers[index] = numbers[i];

			numbers[i] = temp;

		}

		int index = 0;
		
		// Adding the values in numbers array to the 3*3 array
		for (int i = 0; i < puzzle.length; i++) {

			for (int j = 0; j < puzzle[i].length; j++) {

				puzzle[i][j] = numbers[index];

				index++;

			}

		}

	}

	// Running puzzle
	static void runPuzzle() {

		for (int i = 0; i < puzzle.length; i++) {

			for (int j = 0; j < puzzle[i].length; j++) {

				if (puzzle[i][j] == BLANK) {

					System.out.printf("%2s", " ");

				} else {
					
					// Otherwise printing the number
					System.out.printf("%2s", puzzle[i][j]);

				}

			}

			System.out.println();

		}

	}
	
	// Making a move
	static void MoveNumber(int number) {
		
		// Finding the position of number
		for (int i = 0; i < puzzle.length; i++) {

			for (int j = 0; j < puzzle[i].length; j++) {

				if (puzzle[i][j] == number) {
					
					// Found, getting the empty spot from this position
					int emptySpot = findslot(i, j);

					if (emptySpot == 1) {
						
						// Moving up
						puzzle[i - 1][j] = number;

						puzzle[i][j] = BLANK;

					} else if (emptySpot == 2) {
						
						// Moving down
						puzzle[i + 1][j] = number;

						puzzle[i][j] = BLANK;

					} else if (emptySpot == 3) {
						
						// Moving left
						puzzle[i][j - 1] = number;

						puzzle[i][j] = BLANK;

					} else if (emptySpot == 4) {
						
						// Moving right
						puzzle[i][j + 1] = number;

						puzzle[i][j] = BLANK;

					}

					return;

				}

			}

		}

	}
	
	// Finding nearest empty spot one step away from row and column 
	// Return 1 if up location is empty, 2 if down, 3 if left, 4 if right
	// Return -1 if no empty spaces from this location
	static int findslot(int row, int column) {
		
		// Validating row and column
		if (row >= 0 && row < puzzle.length && column >= 0

				&& column < puzzle[row].length) {
			
			// Checking if top spot is valid and empty
			if ((row - 1) >= 0 && puzzle[row - 1][column] == BLANK) {

				return 1;

			}
			
			// Checking if bottom spot is valid and empty
			if ((row + 1) < puzzle.length

					&& puzzle[row + 1][column] == BLANK) {

				return 2;

			}
			
			// Checking if left spot is valid and empty
			if ((column - 1) >= 0 && puzzle[row][column - 1] == BLANK) {

				return 3;

			}
			
			// Checking if right spot is valid and empty
			if ((column + 1) < puzzle[row].length

					&& puzzle[row][column + 1] == BLANK) {

				return 4;

			}

		}
		// Returning -1 if no empty spaces from the location
		return -1;

	}

}