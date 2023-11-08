package battleship;

public class Destroyer extends Ship {
	
	//instance variables
	static final int length = 2;
	
	static final String type = "destroyer";
	
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