package battleship;

/**
 * Represents the submarine subclass of Ship.
 */
public class Submarine extends Ship {
    
	//instance variables
	
	/**
	 * The hard-coded length of a submarine.
	 */
	static final int length = 1;
	
	/**
	 * The hard-coded type of a submarine.
	 */
	static final String type = "submarine";
	
	//constructor
	/**
	 * Creates an instance of a submarine with length 1 by calling constructor in Ship class.
	 */
	public Submarine() {
		super(length);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Overrides the method in the Ship class and returns 'submarine' when called.
	 */
	@Override
	public String getShipType() {
		
		return (type);
	}

}
