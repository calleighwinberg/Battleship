package battleship;

public class BattleshipGame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Ocean oceanTest = new Ocean(); 
		
		oceanTest.print();
		
		oceanTest.placeAllShipsRandomly();
		
		oceanTest.printWithShips();

	}

}
