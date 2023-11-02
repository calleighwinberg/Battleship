package battleship;

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
		
	}
	
	/**
	 * Returns true if the given location contains a ship, false if it does not
	 * @param row
	 * @param column
	 * @return
	 */
	boolean isOccupied(int row, int column) {
		
		return true; 
	}
	
	
	int getShotsFired() {
		
		return this.shotsFired;
	}
	
	
	int getHitCount() {
		
		return this.hitCount;
	}
	
	
	int getShipsSunk() {
		
		return this.shipsSunk;
	}
	
	boolean isGameOver() {
		
		return false;
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
		
	}
	
	
	void printWithShips() {
		
	}
	
	
	
	
	
	
	
	
	
	

}
