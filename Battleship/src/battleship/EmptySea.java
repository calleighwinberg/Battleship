package battleship;

public class EmptySea extends Ship {
	
	static final int length = 1;

	public EmptySea() {
		super(length);

	}

	@Override
	public String getShipType() {
		
		return "empty";
	}

}
