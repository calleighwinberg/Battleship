package battleship;

import java.util.Random;

public class Ocean {
	
	private Ship[][]ships = new Ship[10][10];
	
	private int shotsFired;
	
	private int hitCount;
	
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
	 * @return
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
		
		Random random = new Random();
		
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
		
		Ship[] shipsToPlace = {battleship1, cruiser1, cruiser2, destroyer1, destroyer2, destroyer3, 
				submarine1, submarine2, submarine3, submarine4};
		
		  for (Ship ship : shipsToPlace) {
		        boolean placed = false;
		        
		        while (!placed) {
		            int row = random.nextInt(10);
		            int column = random.nextInt(10);
		            boolean horizontal = random.nextBoolean();
		            
		            
		            if (ship.okToPlaceShipAt(row, column, horizontal, this)) {
		                ship.placeShipAt(row, column, horizontal, this);
		                placed = true;
		            }
		        }
		        
		    }
		
		/*
		//ArrayList<Ship> ships = new ArrayList<Ship>();
		
		Ship[] myShips = {battleship1};
		
		Random random = new Random();
		
		//iterate through the array of ships
		for(int i=0; i < myShips.length; i++) {
			
			Ship ship = myShips[i];
			
			System.out.println("What is length in ocean:" + ship.getLength());
			
			while(true) { 
				
				int randomRow = random.nextInt(10);
				
				int randomCol = random.nextInt(10);
				
				boolean horizontal = random.nextBoolean();
				
				if (ship.okToPlaceShipAt(randomRow, randomCol, horizontal, this)) {	
					
					ship.placeShipAt(randomRow, randomCol, horizontal, this);
								
					System.out.println("Success!" + randomRow + randomCol + horizontal);
					
					break;
				}
				
			}
			
		}
		
		*/		
		
	}
	
	/**
	 * Returns true if the given location contains a ship, false if it does not
	 * @param row
	 * @param column
	 * @return
	 */
	boolean isOccupied(int row, int column) {
		
		return !(ships[row][column] instanceof EmptySea);
		
		/*
		if(this.ships[row][column] instanceof EmptySea) {
			return false;
		}
		return true; 
		*/
	}
	
	
	boolean shootAt(int row, int column) {
		
		shotsFired++;
		if (isOccupied(row, column) && !ships[row][column].isSunk()) {
			if (ships[row][column].shootAt(row, column)) {
				hitCount++;
				if (ships[row][column].isSunk()) {
					shipsSunk++;
				}
				
			}
			return true;
		}
		return false;
	}
	
	
	//getter methods 
	
	
	int getShotsFired() {
		
		return this.shotsFired;
	}
	
	
	int getHitCount() {
		
		return this.hitCount;
	}
	
	
	int getShipsSunk() {
		
		int sunkCount = 0;
	    for (Ship[] row : ships) {
	        for (Ship ship : row) {
	            // Check if it's a real ship and not an EmptySea object,
	            // and if it's sunk.
	            if (!(ship instanceof EmptySea) && ship.isSunk()) {
	                sunkCount++;
	            }
	        }
	    }
	    // Return the number of sunk ships.
	    return sunkCount;
	}
	
	boolean isGameOver() {
		
		return shipsSunk == 10;
	}
	
	/**
	 * Returns the 10x10 array of Ships. The methods in the Ship class that take an
	Ocean parameter need to be able to look at the contents of this array; the
	placeShipAt() method even needs to modify it. While it is undesirable to
	allow methods in one class to directly access instance variables in another class,
	sometimes there is just no good alternative.
	 * @return
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

				if(!(this.ships[i][j] instanceof EmptySea)) { //if this location has been shot at, print the string for that ship extension type 
					System.out.print(this.ships[i][j]);	
					System.out.println("test");
				}
				else {
					
					//if the location hasn't been shot at, print a period indicating you haven't shot at this spot yet
					System.out.print(". ");
					
				}
			}
			System.out.println();		
		}
		
	}
	
	
	/**
	 * USED FOR DEBUGGING PURPOSES ONLY.
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
