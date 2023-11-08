package battleship;

public class Cruiser extends Ship {
	
	//instance variables
	static final int length = 3;
	
	static final String type = "cruiser";
	
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