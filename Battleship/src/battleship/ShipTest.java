package battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShipTest {

	Ocean ocean;
	Ship ship;
	
	@BeforeEach
	void setUp() throws Exception {
		ocean = new Ocean();
	}

	@Test
	void testGetLength() {
		ship = new Battleship();
		assertEquals(4, ship.getLength());
		
		//TODO
		//More tests
		
		//test the length of a cruiser
		Ship cruiser = new Cruiser();
		assertEquals(3, cruiser.getLength());
		
		//test the length of a destroyer
		Ship destroyer = new Destroyer();
		assertEquals(2, destroyer.getLength());
		
		//test the length of a submarine
		Ship submarine = new Submarine();
		assertEquals(1, submarine.getLength());
	}

	@Test
	void testGetBowRow() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, battleship.getBowRow());
		
		//TODO
		//More tests
		
		//test  cruiser get BowRow
		Ship cruiser = new Cruiser();
		row = 3;
		column = 1;
		horizontal = false;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, cruiser.getBowRow());
		
		//test something else? idk what 
		
		
	}

	@Test
	void testGetBowColumn() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		battleship.setBowColumn(column);
		assertEquals(column, battleship.getBowColumn());	
		
		//TODO
		//More tests
	}

	@Test
	void testGetHit() {
		ship = new Battleship();
		boolean[] hits = new boolean[4];
		assertArrayEquals(hits, ship.getHit());
		assertFalse(ship.getHit()[0]);
		assertFalse(ship.getHit()[1]);
		
		//TODO
		//More tests
		
		//test the hit array after shooting one spot on the ship
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		ship.placeShipAt(row, column, horizontal, ocean);
		ocean.shootAt(0, 4);
		assertTrue(ship.getHit()[0]);
		assertFalse(ship.getHit()[3]);
		
		//test the hit array of an emptySea ship after it has been shot at 
		ocean.shootAt(0, 9);
		assertTrue(ocean.getShipArray()[0][9].getHit()[0]);
		
		//test shooting at every remaining space on the battleship 
		ocean.shootAt(0, 3);
		ocean.shootAt(0, 2);
		ocean.shootAt(0, 1);
		assertTrue(ship.getHit()[1]);
		assertTrue(ship.getHit()[2]);
		assertTrue(ship.getHit()[3]);
		
	}
	
	
	
	@Test
	void testGetShipType() {
		ship = new Battleship();
		assertEquals("battleship", ship.getShipType());
		
		//TODO
		//More tests
		
		//test cruiser 
		Ship cruiser = new Cruiser();
		assertEquals("cruiser", cruiser.getShipType());
	}
	
	@Test
	void testIsHorizontal() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertTrue(battleship.isHorizontal());
		
		//TODO
		//More tests	
	}
	
	@Test
	void testSetBowRow() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.setBowRow(row);
		assertEquals(row, battleship.getBowRow());
		
		//TODO
		//More tests
	}

	@Test
	void testSetBowColumn() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.setBowColumn(column);
		assertEquals(column, battleship.getBowColumn());
		
		//TODO
		//More tests
	}

	@Test
	void testSetHorizontal() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.setHorizontal(horizontal);
		assertTrue(battleship.isHorizontal());
		
		//TODO
		//More tests
	}

	@Test
	void testOkToPlaceShipAt() {
		
		//test when other ships are not in the ocean
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		boolean ok = battleship.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok, "OK to place ship here.");
		
		//TODO
		//More tests
		
		//test 2: test that you cannot place a ship where part of the ship will not be within the bounds of the ocean
		Ship destroyer = new Destroyer();
		row = 0;
		column = 0;
		horizontal = true;
		ok = destroyer.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok);
		
		
		//test 3: test that a ship cannot be placed at indexes that don't exist in the constraints of the ocean.
		Ship submarine = new Submarine();
		row = 11;
		column = 12;
		ok = submarine.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok);
		
		//test 4: test that a ship can be placed in a corner 
		Ship cruiser = new Cruiser();
		row = 9;
		column = 9;
		horizontal = false;
		ok = cruiser.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok);

		
	}
	
	@Test
	void testOkToPlaceShipAtAgainstOtherShipsOneBattleship() {
		
		//test when other ships are in the ocean
		
		//place first ship
		Battleship battleship1 = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		boolean ok1 = battleship1.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok1, "OK to place ship here.");
		battleship1.placeShipAt(row, column, horizontal, ocean);

		//test second ship
		Battleship battleship2 = new Battleship();
		row = 1;
		column = 4;
		horizontal = true;
		boolean ok2 = battleship2.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok2, "Not OK to place ship vertically adjacent below.");
		
		//TODO
		//More tests
		
		//test 2: test that you cannot place a ship in a space where it overlaps with another ship
		Ship cruiser = new Cruiser();
		row = 2;
		column = 3;
		horizontal = false;
		boolean ok3 = cruiser.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok3);
		
		
		//test 3: test that you cannot place a ship diagonal to another ship. In this case, trying to place the destroyer diagonal from 
		//the battleship will not be allowed  
		Ship destroyer = new Destroyer();
		row = 1;
		column = 6;
		horizontal = true;
		boolean ok4 = destroyer.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok4);
		
		//however if I move the column over one space, then the destroyer can be placed
		column = 7;
		ok4 = destroyer.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok4);	

		
		//test 3: test that a ship cannot be placed directly at the front of the battleship
		Ship submarine = new Submarine();
		row = 0;
		column = 5;
		boolean ok5 = submarine.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok5);

		
		
		
	}

	@Test
	void testPlaceShipAt() {
		
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, battleship.getBowRow());
		assertEquals(column, battleship.getBowColumn());
		assertTrue(battleship.isHorizontal());
		
		assertEquals("empty", ocean.getShipArray()[0][0].getShipType());
		assertEquals(battleship, ocean.getShipArray()[0][1]);
		

		//TODO
		//More tests
		
		//test 2: test placing a ship vertically. Test every location that the ship should occupy 
		Ship cruiser = new Cruiser();
		row = 4;
		column = 3;
		horizontal = false;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		assertEquals(cruiser, ocean.getShipArray()[4][3]);
		assertEquals(cruiser, ocean.getShipArray()[3][3]);
		assertEquals(cruiser, ocean.getShipArray()[2][3]);
		
		//test 3: test that you're able to override the location of a ship with a new ship. Although this would never happen in the game and
		//be caught by okToPlaceShip, I want to ensure that this method can override another ship type besides EmptySea
		Ship destroyer = new Destroyer();
		row = 4;
		column = 3;
		horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		//now, the two locations should be a destroyer and one location should be a cruiser 
		assertEquals(destroyer, ocean.getShipArray()[4][3]);
		assertEquals(destroyer, ocean.getShipArray()[3][3]);
		assertEquals(cruiser, ocean.getShipArray()[2][3]);
		
		
	
	}

	@Test
	void testShootAt() {
		
		Ship battleship = new Battleship();
		int row = 0;
		int column = 9;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		
		assertFalse(battleship.shootAt(1, 9));
		boolean[] hitArray0 = {false, false, false, false};
		assertArrayEquals(hitArray0, battleship.getHit());
		
		//TODO
		//More tests
		
		//test 2: test shooting at all locations on the battleship
		assertTrue(battleship.shootAt(0, 9));
		hitArray0[0] = true;
		ocean.shootAt(0, 8);
		hitArray0[1] = true;
		ocean.shootAt(0, 7);
		hitArray0[2] = true;
		assertArrayEquals(hitArray0, battleship.getHit());
		
		ocean.shootAt(0, 6);
		hitArray0[3] = true;
		assertArrayEquals(hitArray0, battleship.getHit());
		
		//now test that shooting at any spot in the battleship is now false since the ship has been sunk 
		assertFalse(battleship.shootAt(0, 9));
		assertFalse(battleship.shootAt(0, 8));
		assertFalse(battleship.shootAt(0, 7));
		assertFalse(battleship.shootAt(0, 6));
		
		
		
		//test 3: test shooting at an empty space. Because all the shootAt method in the ship class does is return false if the location 
		//isn't a part of the given class, the hit array for that EmptySea object should still be false
		assertFalse(battleship.shootAt(3, 9));
		assertFalse(ocean.getShipArray()[3][9].getHit()[0]);
		
		
		
		//test 4: test a vertical ship of a different type 
		Ship cruiser = new Cruiser();
		row = 4;
		column = 4;
		horizontal = false;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		
		assertFalse(cruiser.shootAt(9, 0));
		
		boolean[] hitArrayC = {false, false, false};
		assertArrayEquals(hitArrayC, cruiser.getHit());
		
		assertTrue(cruiser.shootAt(4, 4));
		assertTrue(cruiser.shootAt(3, 4));
		assertTrue(cruiser.shootAt(2, 4));
		
		hitArrayC[0] = true;
		hitArrayC[1] = true;
		hitArrayC[2] = true;
		assertArrayEquals(hitArrayC, cruiser.getHit());
		
		
		
		//ocean.print();
		ocean.printWithShips();


	}
	
	@Test
	void testIsSunk() {
		
		Ship submarine = new Submarine();
		int row = 3;
		int column = 3;
		boolean horizontal = true;
		submarine.placeShipAt(row, column, horizontal, ocean);
		
		assertFalse(submarine.isSunk());
		assertFalse(submarine.shootAt(5, 2));
		assertFalse(submarine.isSunk());
		
		//TODO
		//More tests
		
	}

	@Test
	void testToString() {
		
		Ship battleship = new Battleship();
		assertEquals("x", battleship.toString());
		
		int row = 9;
		int column = 1;
		boolean horizontal = false;
		battleship.placeShipAt(row, column, horizontal, ocean);
		battleship.shootAt(9, 1);
		assertEquals("x", battleship.toString());
		
		//TODO
		//More tests
		
		
	}

}

