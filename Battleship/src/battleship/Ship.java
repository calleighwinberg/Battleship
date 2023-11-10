package battleship;

/**
 * abstract class Ship
 */
public abstract class Ship {
	
	//instance variables 
	
	/**
	 * Represents the row position of the ship's bow.
	 */
	private int bowRow;
	
	/**
	 * Represents the column position of the ship's bow.
	 */
	private int bowColumn;
	
	/**
	 * Represents the length of the ship.
	 */
	private int length;
	
	/**
	 * Represents the ship's orientation. true = horizontal, false = vertical
	 */
	private boolean horizontal;
	
	/**
	 * Represents an array of booleans for hits on parts of a ship.
	 */
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
	 * Gets the row corresponding to the position of the bow.
	 * @return the bowRow
	 */
	public int getBowRow() {
		return bowRow;
	}



	/**
	 * Sets the value of bowRow
	 * @param bowRow the bowRow to set
	 */
	public void setBowRow(int bowRow) {
		this.bowRow = bowRow;
	}



	/**
	 * Returns the bow column location.
	 * @return the bowColomn location
	 */
	public int getBowColumn() {
		return bowColumn;
	}



	/**
	 * Sets the value of bowColumn.
	 * @param bowColomn the bowColomn to set
	 */
	public void setBowColumn(int bowColumn) {
		this.bowColumn = bowColumn;
	}



	/**
	 * Returns whether the ship is horizontal or not.
	 * @return boolean
	 */
	public boolean isHorizontal() {
		return horizontal;
	}



	/**
	 * Sets the value of the instance variable horizontal.
	 * @param horizontal the horizontal to set
	 */
	public void setHorizontal(boolean horizontal) {
		this.horizontal = horizontal;
	}



	/**
	 * Returns the ship length.
	 * @return the length
	 */
	public int getLength() {
		return length;
	}



	/**
	 * Returns the hit array.
	 * @return the hit array.
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
	 * @param row of ship bow
	 * @param column of ship bow
	 * @param horizontal true if horizontal; false if vertical
	 * @param ocean
	 * @return boolean
	 */
	boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {
		// Check if the ship can be placed on the board given orientation and row/column
		if (horizontal) {
		    if (column - length + 1 < 0) return false;
		}
		
		if (!horizontal) {
		    if (row - length + 1 < 0) return false;
		}
		
		
		
		
		for (int i = 0; i < length; i++) {
	        //use ternary conditional operator '?' for condensed if/else statement
			int currentRow = horizontal ? row : row - i;
	        int currentColumn = horizontal ? column - i : column;

	        // Check if the current position is within bounds
	        if (currentRow < 0 || currentRow >= ocean.getShipArray().length ||
	            currentColumn < 0 || currentColumn >= ocean.getShipArray()[0].length) {
	            return false; // Out of bounds
	        }

	        // Check if the current position is already occupied
	        if (!(ocean.getShipArray()[currentRow][currentColumn] instanceof EmptySea)) {
	            return false; // Position occupied
	        }

	        // Check adjacent positions (above, below, left, right, and diagonals)
	        for (int dr = -1; dr <= 1; dr++) {
	            for (int dc = -1; dc <= 1; dc++) {
	                int adjacentRow = currentRow + dr;
	                int adjacentColumn = currentColumn + dc;

	                // Check if adjacent position is within bounds
	                if (adjacentRow >= 0 && adjacentRow < ocean.getShipArray().length &&
	                    adjacentColumn >= 0 && adjacentColumn < ocean.getShipArray()[0].length) {
	                    
	                    // Check if the adjacent position is occupied by a ship
	                    if (!(ocean.getShipArray()[adjacentRow][adjacentColumn] instanceof EmptySea)) {
	                        return false; // Adjacent position occupied
	                    }
	                }
	            }
	        }
	    }
	    return true; // All checks passed, it's ok to place the ship
		

	}
	
	/**
	 * “Puts” the ship in the ocean. This involves giving values to the bowRow,bowColumn, and horizontal instance variables in 
	 * the ship, and it also involves putting a reference to the ship in each of 1 or more locations (up to 4) in the ships array 
	 * in the Ocean object. (Note: This will be as many as four identical references; you can’t refer to a ”part” of a ship, only
	 *  to the whole ship.)
	 * @param row of ship bow
	 * @param column of ship bow
	 * @param horizontal true if horizontal; false if vertical
	 * @param ocean
	 */
	void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {
		
		//set the row and column for ship's bow and ship's orientation.
		this.setBowRow(row);
		this.setBowColumn(column);
		this.setHorizontal(horizontal);
		
		//store array of ships
		Ship[][] ships = ocean.getShipArray();

        // Place a reference to the ship in the ships array at each location the ship occupies.
        for (int i = 0; i < this.length; i++) {
            if (horizontal) {
                ships[row][column - i] = this;
            } else {
                ships[row - i][column] = this;
            }
        }
			
			
}
	
	
	
	/**
	 * If a part of the ship occupies the given row and column, and the ship hasn’t been sunk, mark that part of the ship as “hit”
	 *  (in the hit array, index 0 indicates the bow) and return true; otherwise return false.
	 * @param row of shot location
	 * @param column of shot location
	 * @return boolean
	 */
	boolean shootAt(int row, int column) {
		
		//ensure ship is not already sunk	
		if (!isSunk()) {
			//check ship orientation for horizontal-true
			if (this.horizontal) {
				//ensure shot is on correct row of ship
				if (row == this.getBowRow()) {
					int hitIndex = this.bowColumn - column; // Index for horizontal
	                this.hit[hitIndex] = true; //update the hit array
	                return true;
				}
			}
			//ship orientation is vertical
			else {
				//ensure shot is on correct column of ship
				if (column == this.getBowColumn()) {
					int hitIndex = this.bowRow - row; // Index for vertical
	                this.hit[hitIndex] = true; //update the hit array
	                return true;
				}
			}
		}

		return false;
		
	}
	
	
	
	/**
	 * Return true if every part of the ship has been hit, false otherwise
	 * @return boolean
	 */
	boolean isSunk() {
		
		//iterate over hit array
		for (boolean partHit : hit) {
			//check if any index not true
			if(!partHit) {
				return false;
			}
		}
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
		
		if (this.isSunk()) {
			return "s";
			
		} else {
			return "x";
		}
		

		
	}

}
