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
		int row;
		int column;
		System.out.println("Enter row: ");
		
		//enter while loop to ensure valid user input for row
		while (true) {
			//take user input as string
			String s = scanner.next();
			//try to cast string to integer and ensure integer is between 0 and 9 (inclusive)
			try {
				int x = Integer.parseInt(s);
				if (x >=0 && x <= 9) {
					row = x;
					break; //break out of while loop if valid integer input
				}
				//friendly message to user if integer is not between 0 and 9
				System.out.println("Please enter a number between 0 and 9!");
				System.out.println("Enter row: ");
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				//friendly message to user to input valid integer - stay in while loop
				System.out.println("Please enter a valid integer!");
				System.out.println("Enter row: ");
				
			}
				
		}
		//prompt player for input
		System.out.println("Enter column: ");
		
		//enter while loop to ensure valid user input for row
		while (true) {
			//take user input as string
			String t = scanner.next();
			//try to cast string to integer and ensure integer is between 0 and 9 (inclusive)
			try {
				int y = Integer.parseInt(t);
				if (y >=0 && y <= 9) {
					column = y;
					break; //break out of while loop if valid integer input
				}
				//friendly message to user if integer is not between 0 and 9
				System.out.println("Please enter a number between 0 and 9!");
				System.out.println("Enter column: ");
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				//friendly message to user to input valid integer - stay in while loop
				System.out.println("Please enter a valid integer!");
				System.out.println("Enter column: ");
				
			}
			
		}
			
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
		System.out.println("Your objective is to sink all 10 ships in the fleet with the least number of shots.");
		System.out.println("");
		ocean.print();
		//ocean.printWithShips(); --PRINTS THE SHIPS ON THE GRID--
		
		//enter loop until all ships are sunk
		while (!ocean.isGameOver()) {
			takeTurn();
		}
		
		//print friendly message to player when game is over and provide number of shots fired
		System.out.println("Game over! You took " + ocean.getShotsFired() + " shots.");
	}
	
	
	public static void main(String[] args) {
		
		BattleshipGame game = new BattleshipGame();
		
		game.playGame();
		

	}

}
