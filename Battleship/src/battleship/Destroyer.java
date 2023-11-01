package battleship;

public class Destroyer extends Ship {

	static final int length = 5;
	
	static final String type = "destroyer";
	
	
	public Destroyer() {
		super(length);
		// TODO Auto-generated constructor stub
	}


	@Override
	public String getShipType() {
		
		return (type);
	}

}