package com.larissaevaldt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BattleshipGame {

	// create an array list of player objects
	private ArrayList<Player> playerList = new ArrayList<Player>();
	private boolean win;
	private Board board = new Board();
	private int numberPlayers;
	
	public int shoot[];
	private int totalHits;

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public void start() throws IOException {
		readNumPlayer();
		createPlayers();
		printPlayerDetails(); // print values from array list to check if stored correctly
		readBoardSize();
		board.setBoard();
		board.seeBoard();
		//now that the players and board are created, we can start playing
		play();
		printResults();

	}

	// ask user how many players
	public void readNumPlayer() {

		try {
			boolean valid = false;
			do {
				System.out.println("How many people will be playing the game?");
				int numPlayers = Integer.parseInt(br.readLine());

				if (numPlayers >= 1 && numPlayers <= 4) {
					valid = true;
					this.numberPlayers = numPlayers;
				} else {
					valid = false;
					System.out.println("Number of players must be between 1-4. Try again:");
				}
			} while (valid == false);

		} catch (Exception e) {
			// if user doesn't enter a number, we show a message and call the method asking
			// him to enter again
			System.out.println("You must enter a number between 1-4. Try again:");
			readNumPlayer();
		}
	}

	// call the method create player the number of times the user chose
	// then add details to the playerList ArrayList
	public void createPlayers() throws IOException {
		for (int i = 0; i < numberPlayers; i++) {
			System.out.println("------------------- Enter details of player " + (i + 1) + " -------------------");
			Player p = new Player();
			p.createPlayer();
			playerList.add(p);
		}
	}

	// displays the name, age and email of every player in playerList
	public void printPlayerDetails() {
		System.out.println("-----------------------------------------------------------------");
		for (int i = 0; i < playerList.size(); i++) {
			Player p = playerList.get(i);
			System.out.println(
					"Details of player " + (i + 1) + ": " + p.getName() + ", " + p.getAge() + ", " + p.getEmail());
		}
		System.out.println("-----------------------------------------------------------------");
	}

	// ask user for the size of the board
	public void readBoardSize() throws IOException {

		boolean valid = false;

		System.out.println("Please enter the size of the grid - Minimum 10x10 - Maximum 20x20");

		// do while used to keep asking user until a valid number is entered
		do {

			try {

				System.out.println("Width: (between 10-20)");
				int width = Integer.parseInt(br.readLine());

				if (width >= 10 && width <= 20) {
					// if number entered is bigger or equal to 10, and smaller or equal to 20 then
					// we set width
					// and get out of loop
					this.board.setWidth(width);
					valid = true;
				} else {
					valid = false;
					System.out.println("Invalid width. Try again:");
				}
			} catch (NumberFormatException e) {
				// catch exception, in case user doesn't type numbers
				valid = false;
				System.out.println("You must enter a number. Try again:");
			}
		} while (valid == false);
		// do the same we did with the width, with the height.
		do {
			try {
				System.out.println("Height: (between 10-20)");
				int height = Integer.parseInt(br.readLine());
				if (height >= 10 && height <= 20) {
					this.board.setHeight(height);
					valid = true;
				} else {
					valid = false;
					System.out.println("Invalid height. Try again:");
				}
			} catch (NumberFormatException e) {
				valid = false;
				System.out.println("You must enter a number. Try again:");
			}
		} while (valid == false);
	}
	
	// asks user which row and column they want to fire
		public void readRowColumn() throws IOException {
			this.shoot = new int[2];
			boolean valid = false;
			
			do {
				try {
					System.out.println("Select a row to fire in: ");
					this.shoot[0] = Integer.parseInt(br.readLine());
					// a row is valid if number entered is greater or equal to one or smaller or equal to the height user entered
					if (this.shoot[0] >= 1 && this.shoot[0] <= this.board.getHeight()) {
						valid = true;
						this.shoot[0]--; //because if the user enters 5 for example, we are actually looking to update index 4
					} else {
						System.out.println("Enter a valid row (1-" + this.board.getHeight() + ")");
						valid = false;
					}
				} catch (NumberFormatException e) {
					// catch in case user decides to enter a word instead of a number
					valid = false;
					System.out.println("You must enter a number (1-" + this.board.getHeight() + ")");
				}
			} while (valid == false);
			
			//do the same for column
			do {
				try {
					System.out.println("Select a column to fire in: ");
					this.shoot[1] = Integer.parseInt(br.readLine());
					if (this.shoot[1] >= 1 && this.shoot[1] <= this.board.getWidth()) {
						// if number enters is greater or equal to one or smaller or equal to the width
						// user entered
						valid = true;
						this.shoot[1]--;
					} else {
						System.out.println("Enter a valid column (1-" + this.board.getWidth() + ")");
						valid = false;
					}
				} catch (NumberFormatException e) {
					// catch in case user decides to enter a word instead of a number
					valid = false;
					System.out.println("You must enter a number (1-" + this.board.getWidth() + ")");
				}
			} while (valid == false);

		}
		
		public boolean shoot() throws IOException {
			int[][] board = this.board.getBoard();
			//shoot[0] is the row the user entered and shoot[1] the column
			switch (board[this.shoot[0]][this.shoot[1]]) {
			case 0:
				// every time the user shoots and doesn't hit we change the initial value of -1
				// to 0
				// so if trying to choose a position with value zero we print the message and I
				// chose to not
				// allow user to choose again
				System.out.println("This row/column has already been picked. Try again");
				readRowColumn();
				shoot();
				break;
			case 3:
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~ YAY, THAT WAS A HIT ~~~~~~~~~~~~~~~~~~~~~~");
				System.out.printf("You hit part of the ship located in (%d,%d)\n", shoot[0] + 1, shoot[1] + 1);
				board[this.shoot[0]][this.shoot[1]] = 1; // change value of position to 1 for when printing, prints a H instead of ~
				this.totalHits += 1;
				this.board.setBoard(board);
				return true;

			case -1:
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~ OH, YOU MISSED IT ~~~~~~~~~~~~~~~~~~~~~~~");
				board[this.shoot[0]][this.shoot[1]] = 0; // if it's -1 means there's no ship, ship is value 3. But we change it to 0, to print a M instead of ~
				this.board.setBoard(board);
				return false;
			default:
				break;
			}
			return false;
		}
	
	public void play() throws IOException {
		win = false;
		// here we start playing, and taking turns according to how many players user entered
		do {
			// for loop to take the turns, stops when the total of hits is equal to the size of the boat
			
			// adds one to player1 attempts then
			// if the shoot method returns true, it means user hit the boat, so we add 1 to
			// player1 hits
			// if the shoot method returns false, it means user missed the boat, so we add 1
			// to player1 misses
			for (int i = 0; i < playerList.size(); i++) {

				Player p = playerList.get(i);
				System.out.println("------------------------ Player " + (i + 1) + "'s turn ------------------------");
				readRowColumn(); // ask player to choose a row and column
				
				boolean hitBoat = shoot();
				board.seeBoard();
				
				p.changeAttempts();
				
				if (hitBoat) {
					p.changeHits();
				}
				if (!hitBoat) {
					p.changeMisses();
				}

				System.out.println("Player " + (i + 1) + ": " + p.getAttempts() + " attempts, " + p.getPlayerMisses()
						+ " misses, " + p.getPlayerHits() + " hits");
				// prints the attempts, hits, and misses of the player
				// here I just thought it'd be useful to know the size of the boat, also to make
				// sure It created it with the right size
				System.out.println("Boat size: " + board.getShipSize());
				System.out.println("Total Hits: " + this.totalHits);

				// when the total of hits is equal to the size of the ship it means the ship's
				// been sank
				if (this.totalHits == board.getShipSize()) {
					System.out.println("Congratulations!! Player " + (i + 1) + " sank the boat!");
					System.out.println("You took " + p.getAttempts() + " attempts.");
					// add one to winners score
					p.addOneToScore();
					// flag set to true so we get out of do while loop
					win = true;
					break;
					// break to get out of for loop
				} else {
					win = false;

				}

			}

		} while (win == false);
	}
	
	public void printResults() throws IOException {
		
		int winnerScore = Integer.MIN_VALUE; 
		int playerWin = 0;
		boolean draw = false;
		
		// print scores
		System.out.println("-------------------------- Final Results -------------------------");
		for (int i = 0; i < playerList.size(); i++) {
			Player p = playerList.get(i);
			p.setPlayerScore();
			System.out.println("Score player " + (i + 1) + " (" + p.getName() + "): " + p.getPlayerScore());

			// winner is at first the minimum value an integer can have, so the first player score will
			// for sure be bigger than that, then it changes winner to first player's score.
			// Next time the loop
			// runs it checks if first score was bigger than the second, or if they are equal it was a draw.

			if (p.getPlayerScore() > winnerScore) {
				winnerScore = p.getPlayerScore();
				playerWin = (i + 1); // playerWin is just to keep track of which player had that score to print after
			} else if (p.getPlayerScore() == winnerScore) {
				draw = true;
				System.out.println("--------------------- It's a draw ---------------------");
				System.out.println("Both players scored " + winnerScore);
			}
		}
		
		if(draw == false) {
			System.out.println("--------------------- Player " + playerWin + " won the game!! --------------------");
		}
		br.close();
	}
}
