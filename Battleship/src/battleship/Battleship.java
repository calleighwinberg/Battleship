package battleship;

public class Battleship extends Ship {

	static final int length = 4;
	
	static final String type = "battleship";
	
	
	public Battleship() {
		super(length);
		// TODO Auto-generated constructor stub
	}


	@Override
	public String getShipType() {
		
		return (type);
	}

}
