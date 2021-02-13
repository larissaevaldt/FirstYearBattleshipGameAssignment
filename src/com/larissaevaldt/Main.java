package com.larissaevaldt;

import java.io.IOException;

public class Main {
	
	public Main() throws IOException{
        welcome();
    }
	
	//just prints a welcome message and starts the game
    public void welcome() throws IOException{
        System.out.println("-----------------------------------------------------------------"+
        					"\n                Welcome to the BattleShip Game" + 
        					"\n               Allowed number of players: 1 to 4"+
        					"\n"+"-----------------------------------------------------------------");
        BattleshipGame game = new BattleshipGame();
        game.start();
    }

	public static void main(String[] args) throws IOException {
		new Main();
	}

}
