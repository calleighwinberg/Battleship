package battleship;

public class Battleship extends Ship {
	
	//instance variables
	static final int length = 4;
	
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
