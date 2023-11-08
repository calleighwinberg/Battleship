package battleship;

/**
 * Represents the class running a game of Battleship. Contains the main method.
 */
public class BattleshipGame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Ocean oceanTest = new Ocean(); 
		
		
		
		//oceanTest.placeAllShipsRandomly();
		Ship ship = new Battleship();
		ship.placeShipAt(1, 6, true, oceanTest);
		
		oceanTest.print();
		
		System.out.println();
		
		oceanTest.printWithShips();
		
		//oceanTest.shootAt(0, 9);
		//System.out.println(oceanTest.getShipArray()[0][9].getHit()[0]);
		
		oceanTest.shootAt(1, 6);
		oceanTest.shootAt(1, 5);
		oceanTest.shootAt(1, 4);
		oceanTest.shootAt(1, 3);
		oceanTest.shootAt(0, 9);
		oceanTest.print();
		
		

		
		

	}

}
