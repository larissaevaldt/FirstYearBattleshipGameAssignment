package com.larissaevaldt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Player {

	private String name;
	private int age;
	private String email;
	private int attempts = 0;
	private int playerMisses = 0;
	private int playerHits = 0;
	private int playerScore = 0;

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	// method to get data from the user and then create player
	public void createPlayer() throws IOException {
		int myCharacter; // used to store index position of space character first and . character later
		boolean valid = false;
		// GET NAME
		try {
			do {
				System.out.println("Full Name of player?");
				String name = br.readLine();
				myCharacter = name.indexOf(" ");
				// indexOf will return -1 if there's no space, and if there's no space it's an
				// invalid name
				if (myCharacter != -1) {
					this.setName(name);
					valid = true;
				} else {
					System.out.println("Invalid Name. Enter name and surname!");
					valid = false;
				}
			} while (!valid);
		} catch (Exception e) {
		}

		// GET AGE
		do {
			try {
				System.out.println("Age of the player?");
				int age = Integer.parseInt(br.readLine());
				if (age >= 12 && age < 100) {
					this.setAge(age);
					valid = true;

				} else {
					System.out.println("Invalid entry. Age must be over 12 and under 100.");
					valid = false;
				}
			} catch (NumberFormatException e) {
				// catch makes sure user doesn't enter a word instead of numbers.
				System.out.println("You must enter a number! Try again:");
				valid = false;
			}

		} while (valid == false);

		// GET EMAIL ADDRESS
		try {
			do {
				System.out.println("Enter player's email address:");
				String email = br.readLine();
				myCharacter = email.indexOf(".");
				char toCheck = '@';
				int count = 0;
				// for loop to check how many @ the string has, if the character of the position
				// we are
				// checking is equal to @, then we add one to the count.
				for (int i = 0; i < email.length(); i++) {
					if (email.charAt(i) == toCheck) {
						count++;
					}
				}
				// if my character is equal to -1, it means there is no dot, so it's invalid,
				// and if count is
				// equal to 1, it means there is only one @
				if (myCharacter != -1 && count == 1) {
					this.setEmail(email);
					valid = true;
				} else if (count < 1 || count > 1 || myCharacter == -1) {
					System.out.println("Invalid email. Email must have only one '@', and at least one '.'");
					valid = false;
				}
			} while (valid == false);
		} catch (Exception e) {
			System.out.println("Something went wrong");
		}

	}

	// getters
	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public String getEmail() {
		return email;
	}

	public int getAttempts() {
		return attempts;
	}

	public int getPlayerMisses() {
		return playerMisses;
	}

	public int getPlayerHits() {
		return playerHits;
	}

	public int getPlayerScore() {
		return playerScore;
	}

	// setters

	// Player detail setters are private because I don't want anyone to be
	// able to set name, age, and email out of the create user method.
	private void setName(String name) {
		this.name = name;
	}

	private void setAge(int age) {
		this.age = age;
	}

	private void setEmail(String email) {
		this.email = email;
	}

	// methods to update attempts, misses, hits, score

	// every time player plays, add one to attempts
	public void changeAttempts() {
		this.attempts += 1;
	}

	// if the player hits a ship, add one to playerHits
	public void changeHits() {
		this.playerHits += 1;
	}
	
	public void addOneToScore() {
		this.playerScore += 1;
	}

	// if the player doesn't hit a ship, add one to playerMisses
	public void changeMisses() {
		this.playerMisses += 1;
	}

	// the score setter is equal to the score plus what was asked in the
	// instructions because
	// the player that sank the boat has a 1, and we don't want to overwrite that.
	public void setPlayerScore() {
		this.playerScore = this.playerScore + (playerHits - (playerMisses * 2));
	}

}
