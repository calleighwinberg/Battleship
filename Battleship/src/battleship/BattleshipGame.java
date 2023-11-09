package battleship;

import java.util.Scanner;


/**
 * Represents the class running a game of Battleship. Contains the main method.
 */
public class BattleshipGame {

	//instance variables
	Ocean ocean;
	Scanner scanner;
	
	//constructor
	public BattleshipGame() {
		//create new ocean and scanner
		ocean = new Ocean();
		scanner = new Scanner(System.in);
	}
	
	/**
	 * Setup the Battleship Game by placing ships randomly.
	 */
	private void setupGame() {
		ocean.placeAllShipsRandomly();
	}
	
	/**
	 * Controls the actions taken during each turn.
	 */
	private void takeTurn() {
		
		//prompt player for input
		//need to update for valid inputs, etc.
		System.out.println("Enter row, column: ");
		int row = scanner.nextInt();
		int column = scanner.nextInt();
		boolean hit = ocean.shootAt(row, column);
		
		//if the player hits a ship, inform the player and check if the ship is now sunk
		if (hit) {
			System.out.println("Hit!");
			System.out.println("");
			if(ocean.getShipArray()[row][column].isSunk()) {
				System.out.println("You sank a " + ocean.getShipArray()[row][column].getShipType()+"!");
				System.out.println("");
			}
		
		//if the player does not hit a ship, inform user that it's a miss
		} else {
			System.out.println("Miss");
			System.out.println("");
		}
		
		//print the updated ocean
		ocean.print();
		System.out.println("Hit count: " + ocean.getHitCount());
		System.out.println("Shot count: " + ocean.getShotsFired()); //remove before submission
		System.out.println("");
	}
	
	/**
	 * Controls the game play flow.
	 */
	private void playGame() {
		//call setupGame to place the ships
		setupGame();
		//print friendly message to the player
		System.out.println("Welcome to Battleship!");
		System.out.println("");
		ocean.printWithShips(); //remove before submission
		
		//enter loop until all ships are sunk
		while (!ocean.isGameOver()) {
			takeTurn();
		}
		
		//print friendly message to player when game is over and provide number of shots fired
		System.out.println("Game over! You took " + ocean.getShotsFired() + " shots.");
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		BattleshipGame game = new BattleshipGame();
		
		game.playGame();
		
		/*
		Ocean oceanTest = new Ocean(); 
		
		
		
		//oceanTest.placeAllShipsRandomly();
		Ship ship = new Battleship();
		ship.placeShipAt(1, 6, true, oceanTest);
		
		oceanTest.print();
		
		System.out.println();
		
		oceanTest.printWithShips();
		
		//oceanTest.shootAt(0, 9);
		//System.out.println(oceanTest.getShipArray()[0][9].getHit()[0]);
		
		oceanTest.shootAt(1, 6);
		oceanTest.shootAt(1, 5);
		oceanTest.shootAt(1, 4);
		oceanTest.shootAt(1, 3);
		oceanTest.shootAt(0, 9);
		oceanTest.print();
		*/
		

		
		

	}

}
