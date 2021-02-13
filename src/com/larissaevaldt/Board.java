package com.larissaevaldt;

public class Board {
	private int height;
	private int width;
	private int[][] board;
	private int shipSize;

	// create a matrix(board) with the size entered by the user and add the ship
	public void setBoard() {
		board = new int[this.getHeight()][this.getWidth()];
		// Add -1 values to each position of the array to represent water
		// because when printing the board -1 prints water/not used yet position(~)
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = -1;
			}
		}		
		setShip();
	}

	// prints board to the screen
	public void seeBoard() {

		System.out.println("----------------------- Meaning of symbols -----------------------");
		System.out.println("               ~ - water,     H - Hit,     M - Miss");

		// switch case to add equivalent numbers on top of the board, representing the
		// columns
		switch (this.getWidth()) {
		case 10:
			System.out.println("\t1  2  3  4  5  6  7  8  9  10");
			System.out.println("\t-------------------------------");
			break;
		case 11:
			System.out.println("\t1  2  3  4  5  6  7  8  9  10  11");
			System.out.println("\t-----------------------------------");
			break;
		case 12:
			System.out.println("\t1  2  3  4  5  6  7  8  9  10 11 12");
			System.out.println("\t------------------------------------");
			break;
		case 13:
			System.out.println("\t1  2  3  4  5  6  7  8  9  10 11 12 13");
			System.out.println("\t---------------------------------------");
			break;
		case 14:
			System.out.println("\t1  2  3  4  5  6  7  8  9  10 11 12 13 14");
			System.out.println("\t-------------------------------------------");
			break;
		case 15:
			System.out.println("\t1  2  3  4  5  6  7  8  9  10 11 12 13 14 15");
			System.out.println("\t---------------------------------------------");
			break;
		case 16:
			System.out.println("\t1  2  3  4  5  6  7  8  9  10 11 12 13 14 15 16");
			System.out.println("\t------------------------------------------------");
			break;
		case 17:
			System.out.println("\t1  2  3  4  5  6  7  8  9  10 11 12 13 14 15 16 17");
			System.out.println("\t----------------------------------------------------");
			break;
		case 18:
			System.out.println("\t1  2  3  4  5  6  7  8  9  10 11 12 13 14 15 16 17 18");
			System.out.println("\t------------------------------------------------------");
			break;
		case 19:
			System.out.println("\t1  2  3  4  5  6  7  8  9  10 11 12 13 14 15 16 17 18 19");
			System.out.println("\t---------------------------------------------------------");
			break;
		case 20:
			System.out.println("\t1  2  3  4  5  6  7  8  9  10 11 12 13 14 15 16 17 18 19 20");
			System.out.println("\t------------------------------------------------------------");
			break;
		default:
			System.out.println("Invalid number");
		}

		for (int row = 0; row < board.length; row++) {
			// prints the number of the row and gives a tab just to separate
			System.out.print((row + 1) + "|" + "\t");
			for (int column = 0; column < board[row].length; column++) {
				switch (board[row][column]) {
				case -1:
					// -1 is the value we initiated the matrix, means it hasn't been picked yet
					System.out.print("~" + "  ");
					break;
				case 0: // every time user picks and it's a miss, we change the value of that position
						// to 0
					System.out.print("M" + "  ");
					break;
				case 1: // when the user hits, we change the value of the position from 3 to one and
						// then prints H(hit)
					System.out.print("H" + "  ");
					break;
				case 3: // the ship is here but we don't want to show the user where it is
					System.out.print("~" + "  ");
					break;
				default:
					break;
				}
			}
			System.out.print("\n");
		}

	}

	// set the size of ship as number of columns divided by 3 and randomly places
	// ship in the matrix
	public void setShip() {
		shipSize = Math.round(getWidth() / 3);
		int col = (int) (Math.random() * (this.getWidth() - shipSize));
		int row = (int) (Math.random() * (this.getHeight() - shipSize));
		
		if (Math.random() < 0.5) {
			// horizontal		
			for (int i = 0; i < shipSize; i++) {
				board[row][col + i] = 3; //boats will mean 3, and -1 no boat
			}
		} else {
			// vertical
			for (int i = 0; i < shipSize; i++) {
				board[row + i][col] = 3;
			}
		}

	}

	// getters

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public int[][] getBoard() {
		return board;
	}
	
	public int getShipSize() {
		return shipSize;
	}

	// setters
	public void setHeight(int height) {
		this.height = height;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setBoard(int[][] board) {
		this.board = board;
	}

}
