package battleship;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OceanTest {

	Ocean ocean;
	
	static int NUM_BATTLESHIPS = 1;
	static int NUM_CRUISERS = 2;
	static int NUM_DESTROYERS = 3;
	static int NUM_SUBMARINES = 4;
	static int OCEAN_SIZE = 10;
	
	@BeforeEach
	void setUp() throws Exception {
		ocean = new Ocean();
	}
	
	@Test
	void testEmptyOcean() {
		
		//tests that all locations in the ocean are "empty"
		
		Ship[][] ships = ocean.getShipArray();
		
		for (int i = 0; i < ships.length; i++) {
			for (int j = 0; j < ships[i].length; j++) {
				Ship ship = ships[i][j];
				
				assertEquals("empty", ship.getShipType());
			}
		}
		
		assertEquals(0, ships[0][0].getBowRow());
		assertEquals(0, ships[0][0].getBowColumn());
		
		assertEquals(5, ships[5][5].getBowRow());
		assertEquals(5, ships[5][5].getBowColumn());
		
		assertEquals(9, ships[9][0].getBowRow());
		assertEquals(0, ships[9][0].getBowColumn());
	}
	
	
	@Test
	void testPlaceAllShipsRandomly() {
		
		//tests that the correct number of each ship type is placed in the ocean
		
		ocean.placeAllShipsRandomly();

		Ship[][] ships = ocean.getShipArray();
		ArrayList<Ship> shipsFound = new ArrayList<Ship>();
		
		int numBattlehips = 0;
		int numCruisers = 0;
		int numDestroyers = 0;
		int numSubmarines = 0;
		int numEmptySeas = 0;
		
		for (int i = 0; i < ships.length; i++) {
			for (int j = 0; j < ships[i].length; j++) {
				Ship ship = ships[i][j];
				if (!shipsFound.contains(ship)) {
					shipsFound.add(ship);
				}
			}
		}
		
		for (Ship ship : shipsFound) {
			if ("battleship".equals(ship.getShipType())) {		
				numBattlehips++;
			} else if ("cruiser".equals(ship.getShipType())) {
				numCruisers++;
			} else if ("destroyer".equals(ship.getShipType())) {
				numDestroyers++;
			} else if ("submarine".equals(ship.getShipType())) {
				numSubmarines++;
			} else if ("empty".equals(ship.getShipType())) {
				numEmptySeas++;
			}
		}
		
		assertEquals(NUM_BATTLESHIPS, numBattlehips);
		assertEquals(NUM_CRUISERS, numCruisers);
		assertEquals(NUM_DESTROYERS, numDestroyers);
		assertEquals(NUM_SUBMARINES, numSubmarines);
		
		//calculate total number of available spaces and occupied spaces
		int totalSpaces = OCEAN_SIZE * OCEAN_SIZE; 
		int occupiedSpaces = (NUM_BATTLESHIPS * 4)
				+ (NUM_CRUISERS * 3)
				+ (NUM_DESTROYERS * 2)
				+ (NUM_SUBMARINES * 1);
		
		//test number of empty seas, each with length of 1
		assertEquals(totalSpaces - occupiedSpaces, numEmptySeas);
	}
	

	@Test
	void testIsOccupied() {

		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		Ship submarine = new Submarine();
		row = 0;
		column = 0;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.isOccupied(1, 5));
		
		//TODO
		//More tests
		
		//test 2: every other space in the ocean besides the three places that are occupied by a submarine and destroyer should return false.
		// the final count of empty spaces should be 100-3
		int count = 0;
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				if(!(ocean.isOccupied(i, j))) {
					count++;
				}
			}
		}
		assertEquals(count, (100-3));
		
		//test 3: After i call ocean.placeAllShipsRandomly(), 20 more spots in the ocean should be filled. So the new count would be 100-20-3=77
		ocean.placeAllShipsRandomly();
		
		int count2 = 0;
		for(int k = 0; k < 10; k++) {
			for(int l = 0; l < 10; l++) {
				if(!(ocean.isOccupied(k, l))) {
					count2++;
				}
			}
		}
		assertEquals(count2, (100-20-3));
		
		//test 4: after calling ocean.placeAllShipsRandomly(), the original submarine in (0,0) should still have no ships surrounding it
		assertTrue(!(ocean.isOccupied(1, 1)));
		assertTrue(!(ocean.isOccupied(1, 0)));
		assertTrue(!(ocean.isOccupied(0, 1)));		
	}
	

	@Test
	void testShootAt() {
	
		assertFalse(ocean.shootAt(0, 1));
		
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertTrue(ocean.shootAt(0, 5));
		
		//TODO
		//More tests
		
		//test 2: Once a ship has been sunk, in this case a submarine, shooting at that location again should return false. 
		Submarine submarine = new Submarine();
		row = 0;
		column = 0;
		submarine.placeShipAt(row, column, horizontal, ocean);
		assertTrue(ocean.shootAt(0, 0));
		assertTrue(submarine.isSunk());
		assertFalse(ocean.shootAt(0, 0));
		
		//test 3: shooting at the same location twice should return true both times, so long as the ship hasn't been sunk yet. 
		Battleship battleship = new Battleship();
		row = 4;
		column = 6;
		horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertTrue(ocean.shootAt(4, 6));
		assertTrue(ocean.shootAt(4, 6));
		
		//test 4: Test that shootAt successfully updated hitCount and shotsFired. 7 shots have been fired so far. Two shots have been shot at empty 
		//places. Once at an emptySea and once at the location that the submarine used to be. Therefore, the hit count should only be 5 at this point.
		assertEquals(ocean.getHitCount(), 5);
		assertEquals(ocean.getShotsFired(), 7);
	
	}
	

	@Test
	void testGetShotsFired() {
		
		//should be all false - no ships added yet
		assertFalse(ocean.shootAt(0, 1));
		assertFalse(ocean.shootAt(1, 0));
		assertFalse(ocean.shootAt(3, 3));
		assertFalse(ocean.shootAt(9, 9));
		assertEquals(4, ocean.getShotsFired());
		
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		Ship submarine = new Submarine();
		row = 0;
		column = 0;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertTrue(ocean.shootAt(0, 5));
		assertTrue(destroyer.isSunk());
		assertEquals(6, ocean.getShotsFired());
		
		//TODO
		//More tests
		
		//test 2: test that shooting at the same location will increase the shot count
		Battleship battleship = new Battleship();
		row = 4;
		column = 6;
		horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(4, 6));
		assertTrue(ocean.shootAt(4, 5));
		assertTrue(ocean.shootAt(4, 4));
		assertTrue(ocean.shootAt(4, 4));
		assertFalse(battleship.isSunk());
		assertEquals(10, ocean.getShotsFired());
		assertTrue(ocean.shootAt(4, 3));
		assertEquals(11, ocean.getShotsFired());

		//test 3: test that only calling the shootAt in the ocean class will increase the shot count, not calling the shootAt method in ship class
		//the below will return false because the battleship has already been sunk 
		assertFalse(battleship.shootAt(row, column));
		assertEquals(11, ocean.getShotsFired());
		assertFalse(ocean.shootAt(row, column));
		assertEquals(12, ocean.getShotsFired());
	}
	
	

	@Test
	void testGetHitCount() {
		
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertEquals(1, ocean.getHitCount());
		
		//TODO
		//More tests
		
		//test 2: test sinking the destroyer and then shooting at the location that it once was. HitCount should only count hits
		//when there is still a ship there
		assertTrue(ocean.shootAt(0, 5));
		//the count should now be two and the destroyer should be sunk 
		assertEquals(2, ocean.getHitCount());
		assertTrue(destroyer.isSunk());
		//if we shoot at this location again, we should get false and the hit count should not increase 
		assertFalse(ocean.shootAt(1, 5));
		assertEquals(2, ocean.getHitCount());
		
		//test 3: test that shooting at the same location on a ship increases the hitCount so long as the ship as not been sunk 
		Battleship battleship = new Battleship();
		row = 4;
		column = 6;
		horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertTrue(ocean.shootAt(4, 6));
		assertTrue(ocean.shootAt(4, 6));
		assertTrue(ocean.shootAt(4, 6));
		//the hitCount should have gone up 3, even though we shot at same location 
		assertEquals(5, ocean.getHitCount());
		//check again that once ship is sunk, hitCount does not increase 
		assertTrue(ocean.shootAt(4, 5));
		assertTrue(ocean.shootAt(4, 4));
		assertTrue(ocean.shootAt(4, 3));
		assertEquals(8, ocean.getHitCount());
		
		//test 4: test that shooting at an emptySea location will not increase hitCount 
		assertFalse(ocean.shootAt(9, 9));
		assertEquals(8, ocean.getHitCount());

	}
	
	
	
	@Test
	void testGetShipsSunk() {
		
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertEquals(1, ocean.getHitCount());
		assertEquals(0, ocean.getShipsSunk());
		
		//TODO
		//More tests
		
		//test 2: test that once we have hit all the locations on the destroyer, the sunk count will increase to 1
		assertTrue(ocean.shootAt(0, 5));
		assertTrue(destroyer.isSunk());
		assertEquals(2, ocean.getHitCount());
		assertEquals(1, ocean.getShipsSunk());

		
		//test 3: test that the count will correctly update after placing and shooting two more ships 
		Battleship battleship = new Battleship();
		row = 4;
		column = 6;
		horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertTrue(ocean.shootAt(4, 6));
		assertTrue(ocean.shootAt(4, 5));
		assertTrue(ocean.shootAt(4, 4));
		assertTrue(ocean.shootAt(4, 3));
		assertTrue(battleship.isSunk());
		assertEquals(2, ocean.getShipsSunk());
		
		Submarine submarine = new Submarine();
		row = 0;
		column = 0;
		submarine.placeShipAt(row, column, horizontal, ocean);
		assertTrue(ocean.shootAt(0, 0));
		assertTrue(submarine.isSunk());
		assertFalse(ocean.shootAt(0, 0));
		assertEquals(3, ocean.getShipsSunk());
	}

	
	
	@Test
	void testGetShipArray() {
		
		Ship[][] shipArray = ocean.getShipArray();
		assertEquals(OCEAN_SIZE, shipArray.length);
		assertEquals(OCEAN_SIZE, shipArray[0].length);
		
		assertEquals("empty", shipArray[0][0].getShipType());
		
		//TODO
		//More tests
		
		//test 2: test that every location is empty to begin with 
		for (int i = 0; i < shipArray.length; i++) {
			for (int j = 0; j < shipArray[i].length; j++) {
				Ship ship = shipArray[i][j];
				
				assertEquals("empty", ship.getShipType());
			}
		}
		
		//test 3: test placing a ship and then getting that location and ensuring it's updated with a ship 
		Battleship battleship = new Battleship();
		int row = 8;
		int column = 8;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertEquals("battleship", shipArray[8][8].getShipType());
		assertEquals("battleship", shipArray[8][7].getShipType());
		assertEquals("battleship", shipArray[8][6].getShipType());
		assertEquals("battleship", shipArray[8][5].getShipType());
		
		//Test 4: now test shooting at that ship and then getting the location. It should STILL be battleship 
		assertTrue(ocean.shootAt(8, 8));
		assertTrue(ocean.shootAt(8, 7));
		assertTrue(ocean.shootAt(8, 6));
		assertTrue(ocean.shootAt(8, 5));
		assertEquals("battleship", shipArray[8][8].getShipType());
		assertEquals("battleship", shipArray[8][7].getShipType());
		assertEquals("battleship", shipArray[8][6].getShipType());
		assertEquals("battleship", shipArray[8][5].getShipType());
		
		//test 5: test overriding the location of the battleship with another ship. the array should update to reflect the new ship
		//this situation would never happen but it's good to check that the array can clearly be updated 
		Ship cruiser = new Cruiser();
		row = 8;
		column = 8;
		horizontal = true;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		assertEquals("cruiser", shipArray[8][8].getShipType());
		assertEquals("cruiser", shipArray[8][7].getShipType());
		assertEquals("cruiser", shipArray[8][6].getShipType());
		assertEquals("battleship", shipArray[8][5].getShipType());
	}

}

