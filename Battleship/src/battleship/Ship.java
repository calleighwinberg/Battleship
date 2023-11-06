package battleship;

/**
 * abstract class Ship
 */
public abstract class Ship {
	
	//instance variables 
	private int bowRow;
	
	private int bowColumn;
	
	private int length;
	
	private boolean horizontal;
	
	private boolean[] hit;
	
	
	
	
	//constructor 
	/**
	 * This constructor sets the length property of the particular ship and initializes the hit array based on that length
	 * @param length
	 */
	public Ship(int length) {
		
		this.length = length;
		
		hit = new boolean[length];
		
	}
		
		
	//getters and setters
	
	/**
	 * @return the bowRow corresponding to the position of the bow
	 */
	public int getBowRow() {
		return bowRow;
	}



	/**
	 * @param bowRow the bowRow to set
	 */
	public void setBowRow(int bowRow) {
		this.bowRow = bowRow;
	}



	/**
	 * @return the bowColomn location
	 */
	public int getBowColumn() {
		return bowColumn;
	}



	/**
	 * @param bowColomn the bowColomn to set
	 */
	public void setBowColomn(int bowColomn) {
		this.bowColumn = bowColomn;
	}



	/**
	 * @return the horizontal
	 */
	public boolean isHorizontal() {
		return horizontal;
	}



	/**
	 * @param horizontal the horizontal to set
	 */
	public void setHorizontal(boolean horizontal) {
		this.horizontal = horizontal;
	}



	/**
	 * @return the length
	 */
	public int getLength() {
		return length;
	}



	/**
	 * @return the hit
	 */
	public boolean[] getHit() {
		return hit;
	}


	
	
	/**
	 * Returns the type of ship as a String. Every specific type of Ship (e.g. BattleShip, Cruiser, etc.) has to override 
	 * and implement this method and return the corresponding ship type.
	 * @return
	 */
	public abstract String getShipType();
	
	
	/**
	 * Based on the given row, column, and orientation, returns true if it is okay to put a ship of this length with its bow in 
	 * this location; false otherwise. The ship must not overlap another ship, or touch another ship (vertically, horizontally, 
	 * or diagonally), and it must not ”stick out” beyond the array. Does not actually change either the ship or the Ocean - 
	 * it just says if it is legal to do so.
	 * @param row
	 * @param column
	 * @param horizontal
	 * @param ocean
	 * @return
	 */
	boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {

		//boolean okToPlace = false;
		
		System.out.println("these are passed into okToPlaceShip" +row + " " + column + " " + horizontal);
		
		int endColumn;
		int endRow;


		//if horizonal, and length == 5, then we have to place higher than column (index) 3  .  3,6. we need to make sure index 2-7 are empty
		// and we need to make sure index row 2, column 2-7 are empty and index row 4, column 2-7 are empty 

		if(horizontal) {
			
			endRow = row;
			endColumn = column - this.length +1;
			
			//check that the back of the boat would still be within the constraints of ocean
			if(endColumn >= 0) {
				
				//check if any space we want to place the ship already has a ship placed
				for(int i = endColumn; i < column+1; i++) {
					if(ocean.isOccupied(endRow, i)) {
						return false;
					}
				}
				
				// TODO: check the ship's surrounding space for another ship
				return true;
			}
			
			else {
				return false;
			}
		}
		
		else {
			
			endRow = row - this.length + 1;
			endColumn = column;
			
			//check that the back of the boat would still be within the constraints of ocean
			if(endRow >= 0) {
				
				//check if any space we want to place the ship already has a ship placed
				for(int j = endRow; j < row+1; j++) {
					if(ocean.isOccupied(j, endColumn)) {
						return false;
					}
				}
				
				// TODO: check the ship's surrounding space for another ship
				return true;

			}
			
			else {
				return false;
			}	
		}
	}
			
			
			
			

			/*if(column+1 < this.length) {

				return false;	
			}
			else {

				for(int col1 = (column - this.length - 1); col1 <= this.length+1; col1++) {
					
					System.out.println(col1);
					//if(ocean.getShipArray()[row-1][col1])

					if(ocean.isOccupied(row-1, col1) || ocean.isOccupied(row, col1) || ocean.isOccupied(row+1, col1)) {

						return false;
					}	
				}
				return true;
			}


		else {

			if(row+1 < this.length) {

				return false;

			} 

			else {

				for(int row1 = (row - this.length - 1); row1 <= this.length+1; row1++) {

					//if(ocean.getShipArray()[row-1][col1])

					if(ocean.isOccupied(row1, column-1) || ocean.isOccupied(row1, column) || ocean.isOccupied(row1, column+1)) {

						return false;
					}
				}
				return true;
			}
		}*/

		
	

		
	
	
	/**
	 * “Puts” the ship in the ocean. This involves giving values to the bowRow,bowColumn, and horizontal instance variables in 
	 * the ship, and it also involves putting a reference to the ship in each of 1 or more locations (up to 4) in the ships array 
	 * in the Ocean object. (Note: This will be as many as four identical references; you can’t refer to a ”part” of a ship, only
	 *  to the whole ship.)
	 * @param row
	 * @param column
	 * @param horizontal
	 * @param ocean
	 */
	void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {
		
		this.setBowRow(row);
		this.setBowColomn(column);
		this.setHorizontal(horizontal);
		
		//System.out.print(ocean.getShipArray()[row][column] = this);
		System.out.print(ocean.getShipArray()[row][column]);
		
		//Ship[][] ships = ocean.getShipArray();
		
		if(horizontal) {
			
			System.out.println("What is length in ship:" + this.getLength());
			
			for(int i = (column - this.getLength()+1); i < column+1; i++) {
				System.out.println("do we even enter" + i);
				ocean.getShipArray()[row][i] = this;
			}
		}
			
			
		
		
		//ocean.getShipArray()[row][column] = this;
		
		
		
		
		//if(this.getShipType().equals("battleship")) {
			/* This means, if you place a horizontal battleship at location (9, 8) in the
				ocean, the bow is at location (9, 8) and the rest of the ship occupies
				locations: (9, 7), (9, 6), (9, 5).
				▪ If you place a vertical cruiser at location (4, 0) in the ocean, the bow is at
				location (4, 0) and the rest of the ship occupies locations: (3, 0), (2, 0).*/
			
			
		}
		
		
		
	
	
	
	/**
	 * If a part of the ship occupies the given row and column, and the ship hasn’t been sunk, mark that part of the ship as “hit”
	 *  (in the hit array, index 0 indicates the bow) and return true; otherwise return false.
	 * @param row
	 * @param column
	 * @return
	 */
	boolean shootAt(int row, int column) {
		
		return true;
	}
	
	
	
	/**
	 * Return true if every part of the ship has been hit, false otherwise
	 * @return
	 */
	boolean isSunk() {
		
		
		return true;
		
	}
	
	
	
	
	/**
	 * Returns a single-character String to use in the Ocean’s print method. This method should return ”s” if the ship has been 
	 * sunk and ”x” if it has not been sunk. This method can be used to print out locations in the ocean that have been shot at; 
	 * it should not be used to print locations that have not been shot at. Since toString behaves exactly the same for all ship 
	 * types, it is placed here in the Ship class
	 */
	@Override
	public String toString() {
		
		return "s ";
		
	}

}
