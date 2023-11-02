package battleship;

public class Submarine extends Ship {

	static final int length = 1;
	
	static final String type = "submarine";
	
	
	public Submarine() {
		super(length);
		// TODO Auto-generated constructor stub
	}


	@Override
	public String getShipType() {
		
		return (type);
	}

}
