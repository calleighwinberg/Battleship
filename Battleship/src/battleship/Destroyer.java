package battleship;

public class Destroyer extends Ship {

	static final int length = 2;
	
	static final String type = "destroyer";
	
	
	public Destroyer() {
		super(length);
	}


	@Override
	public String getShipType() {
		
		return (type);
	}

}