package battleship;

/**
 * Represents the cruiser subclass of Ship. 
 */
public class Cruiser extends Ship {
	
	//instance variables
	
	/**
	 * The hard-coded length of a cruiser.
	 */
	static final int length = 3;
	
	/**
	 * The hard-coded type of a cruiser.
	 */
	static final String type = "cruiser";
	
	//constructor
	/**
	 * Creates an instance of a cruiser with length 3 by calling constructor in Ship class.
	 */
	public Cruiser() {
		super(length);
	}

    /**
     * Overrides the method in the Ship class and returns 'cruiser' when called.
     */
	@Override
	public String getShipType() {
		
		return (type);
	}

}