package battleship;

public class Cruiser extends Ship {

	static final int length = 5;
	
	static final String type = "cruiser";
	
	
	public Cruiser() {
		super(length);
	}


	@Override
	public String getShipType() {
		
		return (type);
	}

}