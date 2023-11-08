package battleship;

/**
 * Represents the destroyer subclass of Ship.
 */
public class Destroyer extends Ship {
	
	//instance variables
	
	/**
	 * The hard-coded length of a destroyer.
	 */
	static final int length = 2;
	
	/**
	 * The hard-coded type of a destroyer.
	 */
	static final String type = "destroyer";
	
	//constructor
	/**
	 * Creates an instance of a destroyer with length 2 by calling constructor in Ship class.
	 */
	public Destroyer() {
		super(length);
	}

    /**
     * Overrides the method in the Ship class and returns 'destroyer' when called.
     */
	@Override
	public String getShipType() {
		
		return (type);
	}

}