package battleship;

import java.util.Random;

/**
 * Represents an 'ocean' containing a 10x10 array of ships and various methods.
 */
public class Ocean {
	
	//instance variables
	
	/**
	 * Represents a 10x10 array of Ships.
	 */
	private Ship[][]ships = new Ship[10][10];
	
	/**
	 * Represents the number of shots fired during the game.
	 */
	private int shotsFired;
	
	/**
	 * Represents the number of hits landed on ships during the game.
	 */
	private int hitCount;
	
	/**
	 * Represents the number of ships sunk during the game.
	 */
	private int shipsSunk;
	
	
	//constructor
	/**
	 * Creates an ”empty” ocean (and fills the ships array with EmptySea objects). You could create a private helper method to 
	 * do this. Also initializes any game variables, such as how many shots have been fired.
	 */
	public Ocean() {
		
		//initialize the game variables 
		this.hitCount = 0;
		this.shotsFired = 0;
		this.shipsSunk = 0;
		
		//iterate through every space in the ships array
		for(int i = 0; i <this.ships.length; i ++) {
			
			for(int j=0; j<this.ships[i].length; j++) {
				
				//put a new emptySea object in the specified location
				ships[i][j] = this.newEmptySeaObj();
				
				//set the bowRow and bowColumn to match the location
				ships[i][j].setBowRow(i);
				ships[i][j].setBowColumn(j);
				
			}
		}	
	}
	
	
	
	/**
	 *  **private helper method** creates a new empty sea object so the ships array can be filled with emptySea objects 
	 * @return newEmptySea
	 */
	private EmptySea newEmptySeaObj() {
		EmptySea newEmptySea = new EmptySea();
		
		return newEmptySea;
	}
	
	
	//methods 
	
	/**
	 * Place all ten ships randomly on the (initially empty) ocean. Place larger ships
	before smaller ones, or you may end up with no legal place to put a large ship.
	You will want to use the Random class in the java.util package, so look that
	up in the Java API.
	o To help you write the code for this method, reference the printWithShips()
	method below. It will allow you to see where ships are actually being placed in the
	Ocean while you are writing and debugging your program.
	 */
	void placeAllShipsRandomly() {
		
		//Initialize a new random variable
		Random random = new Random();
		
		//create the fleet of ships
		Battleship battleship1 = new Battleship();
		
		Cruiser cruiser1 = new Cruiser();
		Cruiser cruiser2 = new Cruiser();
		
		Destroyer destroyer1 = new Destroyer();
		Destroyer destroyer2 = new Destroyer();
		Destroyer destroyer3 = new Destroyer();
		
		Submarine submarine1 = new Submarine();
		Submarine submarine2 = new Submarine();
		Submarine submarine3 = new Submarine();
		Submarine submarine4 = new Submarine();
		
		//store the fleet of ships in an array in descending order of size
		Ship[] shipsToPlace = {battleship1, cruiser1, cruiser2, destroyer1, destroyer2, destroyer3, 
				submarine1, submarine2, submarine3, submarine4};
		  
		  //iterate of the shipsToPlace array
		  for (Ship ship : shipsToPlace) {
		        boolean placed = false; //set flag to false until given ship is placed
		        
		        //enter while loop for each ship placement
		        while (!placed) {
		            //generate random integers for row and column of ships placement (bow) and random boolean for orientation
		        	int row = random.nextInt(10);
		            int column = random.nextInt(10);
		            boolean horizontal = random.nextBoolean();
		            
		            //check if the ship can be placed given random ints and orientation above
		            if (ship.okToPlaceShipAt(row, column, horizontal, this)) {
		                ship.placeShipAt(row, column, horizontal, this); //if okay, place the ship at given location
		                placed = true;//set flag to true and repeat until all ships are placed
		            }
		        }
		        
		    }
		
	}
	
	/**
	 * Returns true if the given location contains a ship, false if it does not
	 * @param row
	 * @param column
	 * @return boolean
	 */
	boolean isOccupied(int row, int column) {
		
		return !(ships[row][column] instanceof EmptySea);
		
	}
	
	
	/**
	 * Returns true if the given location contains a ”real” ship, still afloat, (not an EmptySea), false if it does not. In addition, this method 
	 * updates the number of shots that have been fired, and the number of hits. If a location contains a “real” ship, shootAt should return true 
	 * every time the user shoots at that same location. Once a ship has been ”sunk”, additional shots at its location should return false.
	 * @param row
	 * @param column
	 * @return boolean
	 */
	boolean shootAt(int row, int column) {
		
		shotsFired++;
		//check if given row & column has a ship and the ship is not sunk
		if (isOccupied(row, column) && !ships[row][column].isSunk()) {
			
			//call the shootAt method to record the hit
			ships[row][column].shootAt(row, column);
			
			//increment the successful hit in hitCount
			hitCount++;
			
			//check if given hit sunk the ship; if so, increment shipsSunk
			if (ships[row][column].isSunk()) {
				shipsSunk++;
			}
			return true;
		}
		//if you hit EmptySea object, sets hit array to true
		if(!isOccupied(row, column)) {	
			ships[row][column].getHit()[0] = true;
		}
		
		return false;
		
		
		
		
	}
	
	
	//getter methods 
	
	/**
	 * Get the number of shots fired.
	 * @return shots fired
	 */
	int getShotsFired() {
		
		return this.shotsFired;
	}
	
	/**
	 * Get the current hit count.
	 * @return hit count
	 */
	int getHitCount() {
		
		return this.hitCount;
	}
	
	/**
	 * Get the number of ships sunk.
	 * @return sunk ships count
	 */
	int getShipsSunk() {
		
		return shipsSunk;
	}
		
	
	/**
	 * Returns true when all the ships are sunk, otherwise false.
	 * @return boolean
	 */
	boolean isGameOver() {
		
		return shipsSunk == 10;
	}
	
	/**
	 * Returns the 10x10 array of Ships. The methods in the Ship class that take an
	Ocean parameter need to be able to look at the contents of this array; the
	placeShipAt() method even needs to modify it. While it is undesirable to
	allow methods in one class to directly access instance variables in another class,
	sometimes there is just no good alternative.
	 * @return array of Ships
	 */
	Ship[][] getShipArray() {
		
		return ships;
	}
	
	
	/**
	 * Prints the Ocean. To aid the user, row numbers should be displayed along the
	left edge of the array, and column numbers should be displayed along the top.
	Numbers should be 0 to 9, not 1 to 10.
	The top left corner square should be 0, 0.
	‘x’: Use ‘x’ to indicate a location that you have fired upon and hit a (real) ship.
	‘-’: Use ‘-’ to indicate a location that you have fired upon and found nothing
	there. (reference the description of toString in the EmptySea class)
	‘s’: Use ‘s’ to indicate a location containing a sunken ship. (reference the
	description of toString in the Ship class)
	‘.’: and use ‘.’ (a period) to indicate a location that you have never fired upon
	This is the only method in the Ocean class that does any input/output, and it is
	never called from within the Ocean class, only from the BattleshipGame class.
	 */
	void print() {
		
		System.out.println("  0 1 2 3 4 5 6 7 8 9");
		
		for(int i = 0; i <this.ships.length; i ++) {
			
			System.out.print(i + " ");
			
			for(int j=0; j < this.ships[i].length; j++) {
				
				
				/*if(this.ships[i][j].isSunk()) {
					System.out.print(this.ships[i][j] + " ");
				}*/ 
				
				
				if(this.ships[i][j].isHorizontal()) {
					//this if statement searches the hit array for the bow column - j, which returns the correct index of j in the hit array. 
					if(this.ships[i][j].getHit()[this.ships[i][j].getBowColumn() - j]) {
						System.out.print(this.ships[i][j] + " ");
					}
					else {
						//if the location hasn't been shot at, print a period indicating you haven't shot at this spot yet
						System.out.print(". ");
					}
				}
				
				else {
					if(this.ships[i][j].getHit()[this.ships[i][j].getBowRow() - i]) {
						System.out.print(this.ships[i][j] + " ");
					}
					else {
						//if the location hasn't been shot at, print a period indicating you haven't shot at this spot yet
						System.out.print(". ");
					}
				}
			}
			System.out.println();
		}
	}
					
	
	/**
	 * USED FOR DEBUGGING PURPOSES ONLY. Similar to the print method above, but displays the ships' locations on the console.
	 */
	void printWithShips() {
		
		System.out.println("  0 1 2 3 4 5 6 7 8 9");
			
		for(int i = 0; i <this.ships.length; i ++) {
			
			System.out.print(i + " ");
			
			for(int j=0; j < this.ships[i].length; j++) {
	
				if(!(this.ships[i][j] instanceof EmptySea)) { //if this location has been shot at, print the string for that ship extension type 
					//System.out.println("x");
					if (this.ships[i][j].getShipType().equals("battleship")) {
						System.out.print("b ");	
					} else if (this.ships[i][j].getShipType().equals("destroyer")) {
						System.out.print("d ");
					} else if (this.ships[i][j].getShipType().equals("cruiser")) {
						System.out.print("c ");
					} else if (this.ships[i][j].getShipType().equals("submarine")) {
						System.out.print("s ");
					}
				}
				else {
					
					//if the location hasn't been shot at, print a period indicating you haven't shot at this spot yet
					System.out.print(". ");
					
				}
			}
			System.out.println();		
	}
		
	}
	


}
