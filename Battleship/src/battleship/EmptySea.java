package battleship;

/**
 * Represents the EmptySea subclass of Ship.
 */
public class EmptySea extends Ship {
	
	//instance variables
	
	/**
	 * The hard-coded length of an EmptySea.
	 */
	static final int length = 1;
    
	//constructor
	/**
	 * Creates an instance of an EmptySea with length 1 by calling constructor in Ship class.
	 */
	public EmptySea() {
		super(length);

	}
	
	/**
	 * Overrides shootAt method in Ship class. Always returns false to indicate nothing was hit.
	 */
	@Override
	boolean shootAt(int row, int column) {
		
		return false;
	}
		
	/**
	 * Overrides isSunk method in Ship class. Always returns false to indicate you didn't sink anything.
	 */
	@Override
	boolean isSunk() {
		
		return false;
	}
	
	/**
	 * Overrides toString method in Ship class. Always returns '-'.
	 */
	@Override
	public String toString() {
		
		return "-";
	}
	
	/**
	 * Overrides method in Ship class. Always returns 'empty'.
	 */
	@Override
	public String getShipType() {
		
		return "empty";
	}

}
