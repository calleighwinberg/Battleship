package battleship;

/**
 * Represents the battleship subclass of Ship.
 */
public class Battleship extends Ship {
	
	//instance variables
	/**
	 * The hard-coded length of the battleship.
	 */
	static final int length = 4;
	
	/**
	 * The hard-coded type of the battleship.
	 */
	static final String type = "battleship";
	
	//constructor
	/**
	 * Creates an instance of a battleship with length 4 by calling constructor in Ship class.
	 */
	public Battleship() {
		super(length);
		// TODO Auto-generated constructor stub
	}

    /**
     * Overrides the method in the Ship class and returns 'battleship' when called.
     */
	@Override
	public String getShipType() {
		
		return (type);
	}

}
