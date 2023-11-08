package battleship;

public class Submarine extends Ship {
    
	//instance variables
	static final int length = 1;
	
	static final String type = "submarine";
	
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
