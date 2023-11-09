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
		
		//test 2: test cruiser getBowRow
		Ship cruiser = new Cruiser();
		row = 3;
		column = 1;
		horizontal = false;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, cruiser.getBowRow());
		
		//test 3: test a destroyer getBowRow. then change it and check if the change works 
		Ship destroyer = new Destroyer();
		row = 4;
		column = 8;
		horizontal = true;
		destroyer.placeShipAt(row, column, horizontal, ocean);	
		assertEquals(row, destroyer.getBowRow());
		row = 5;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, destroyer.getBowRow());
		
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
		
		//test 2: test a destroyer getBowColumn. Then update the column and check that it updates 
		Ship destroyer = new Destroyer();
		row = 4;
		column = 8;
		horizontal = true;
		destroyer.placeShipAt(row, column, horizontal, ocean);	
		assertEquals(column, destroyer.getBowColumn());
		row = 5;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		assertEquals(column, destroyer.getBowColumn());
		
		//test 3: override the ship with a new ship and check that that location is now the bow of the new ship and the old ship 
		//this scenario would never happen but i want to make sure it still works 
		Ship destroyer2 = new Destroyer();
		row = 4;
		column = 8;
		horizontal = true;
		destroyer2.placeShipAt(row, column, horizontal, ocean);	
		assertEquals(column, destroyer2.getBowColumn());
		assertEquals(column, destroyer.getBowColumn());	
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
		
		//test 2: test the hit array after shooting one spot on the ship
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		ship.placeShipAt(row, column, horizontal, ocean);
		ocean.shootAt(0, 4);
		assertTrue(ship.getHit()[0]);
		assertFalse(ship.getHit()[3]);
		
		//test 3: test the hit array of an emptySea ship before and after it has been shot at 
		assertFalse(ocean.getShipArray()[0][9].getHit()[0]);
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
		
		//test destroyer
		Ship destroyer = new Destroyer();
		assertEquals("destroyer", destroyer.getShipType());
		
		//test emtptySea
		Ship emptySea = new EmptySea();
		assertEquals("empty", emptySea.getShipType());
		
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
		
		// test a vertical ship 
		Ship destroyer = new Destroyer();
		row = 4;
		column = 8;
		horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		assertFalse(destroyer.isHorizontal());
		
		//test that changing the destroyer to be horizontal and placing again will correclty update the ship 
		horizontal = true; 
		destroyer.placeShipAt(row, column, horizontal, ocean);
		assertTrue(destroyer.isHorizontal());
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
		//test 2: test a destroyer setBowRow. Then update the row and check that it updates 
		Ship destroyer = new Destroyer();
		row = 4;
		column = 8;
		horizontal = true;
		destroyer.setBowRow(row);	
		assertEquals(row, destroyer.getBowRow());
		row = 5;
		destroyer.setBowRow(row);	
		assertEquals(row, destroyer.getBowRow());
		
		//test 3: give another ship the same row and assert that both ships can exist on the same row 
		Ship destroyer2 = new Destroyer();
		row = 5;
		column = 8;
		horizontal = true;
		destroyer2.setBowRow(row);	
		assertEquals(row, destroyer2.getBowRow());
		assertEquals(row, destroyer.getBowRow());

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
		
		//test that the method works with a cruiser ship 
		Ship cruiser = new Cruiser();
		row = 3;
		column = 1;
		horizontal = false;
		cruiser.setBowColumn(column);
		assertEquals(column, cruiser.getBowColumn());
		
		
		//test that you can set a ship and then change it to an updated number 
		Ship battleship2 = new Battleship();
		row = 5;
		column = 9;
		horizontal = true;
		battleship2.setBowColumn(column);
		assertEquals(column, battleship2.getBowColumn());
		column = 6;
		battleship2.setBowColumn(column);
		assertEquals(column, battleship2.getBowColumn());
		
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
		
		//test 2:  test setting to not horizontal 
		Ship cruiser = new Cruiser();
		row = 3;
		column = 1;
		horizontal = false;
		cruiser.setHorizontal(horizontal);
		assertFalse(cruiser.isHorizontal());
		
		//test 3: test setting horizontal and then updating to vertical
		Ship destroyer = new Destroyer();
		row = 4;
		column = 8;
		horizontal = true;
		destroyer.setHorizontal(horizontal);
		assertTrue(destroyer.isHorizontal());
		horizontal = false;
		destroyer.setHorizontal(horizontal);
		assertFalse(destroyer.isHorizontal());
		
		
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
		
		//test 2: test is sunk for an emptySea object. Should always return false 
		Ship emptySea = new EmptySea();
		row = 0;
		column = 0;
		horizontal = true;
		emptySea.placeShipAt(row, column, horizontal, ocean);
		assertFalse(emptySea.isSunk());
		
		//test that even if you shoot at an emptySea, it will still return false 
		ocean.shootAt(row, column);
		assertFalse(emptySea.isSunk());
		
		//test 3: test isSunk for a longer ship (battleship)
		Ship battleship = new Battleship();
		row = 0;
		column = 9;
		horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertFalse(battleship.isSunk());
		assertFalse(battleship.shootAt(1, 4));
		assertTrue(battleship.shootAt(0, 9));
		assertTrue(battleship.shootAt(0, 8));
		assertTrue(battleship.shootAt(0, 7));
		assertTrue(battleship.shootAt(0, 6));
		assertTrue(battleship.isSunk());
		
		
		
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
		
		//test 2: test sinking the battleship and then calling toString
		assertTrue(battleship.shootAt(8,1));
		assertTrue(battleship.shootAt(7,1));
		assertTrue(battleship.shootAt(6,1));
		assertTrue(battleship.isSunk());
		
		assertEquals("s", battleship.toString());
		
		//test 3: test the toString for the emptySea
		Ship emptySea = new EmptySea();
		row = 0;
		column = 0;
		horizontal = true;
		assertEquals("-", emptySea.toString());
		
		//test 4: test a submarine before and after sinking 
		Ship submarine = new Submarine();
		row = 2;
		column = 2;
		horizontal = true;
		assertFalse(submarine.toString() == "s");
		assertTrue(submarine.toString() == "x");
		
		submarine.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(submarine.shootAt(2, 2));
		assertEquals("s", submarine.toString());
		
		
		
	}

}

