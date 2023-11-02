package battleship;

public class EmptySea extends Ship {
	
	static final int length = 1;

	public EmptySea() {
		super(length);

	}
	
	@Override
	boolean shootAt(int row, int column) {
		
		return false;
	}
	

	
	
	@Override
	boolean isSunk() {
		
		return false;
	}
	
	
	@Override
	public String toString() {
		
		return "-";
	}
	
	
	
	@Override
	public String getShipType() {
		
		return "empty";
	}
	
	
	
	
	
	
	
	
	

}
