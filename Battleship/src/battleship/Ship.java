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
		
		/*
		
		//boolean okToPlace = false;
		
		//System.out.println(row + " " + column);
		/*
		if (horizontal) {
			if (column + length > ocean.getShipArray().length) return false;
		} else {
			if (row + length > ocean.getShipArray().length) return false;
		}
		*/
		
		
		/*

		//if horizonal, and length == 5, then we have to place higher than column (index) 3  .  3,6. we need to make sure index 2-7 are empty
		// and we need to make sure index row 2, column 2-7 are empty and index row 4, column 2-7 are empty 

		if(horizontal) {

			if(column+1 < this.length) {

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
		}
		*/

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
		
		this.setBowRow(row);
		this.setBowColumn(column);
		this.setHorizontal(horizontal);
		
		//System.out.print(ocean.getShipArray()[row][column] = this);
		
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
		//Ship[][] ships = ocean.getShipArray();
		
		/*
		if(horizontal) {
			
			System.out.println("What is length in ship:" + this.getLength());
			
			for(int i = (column - this.getLength()+1); i < this.getLength()+1; i++) {
				System.out.println("do we even enter" + i);
				ocean.getShipArray()[row][i] = this;
			}
		}
			
		*/
		
		
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
		
		
		
		
		
		//i'm not sure why but this was preventing any other places on the ship from being hit, besides the bow 
		
		/*if (!isSunk()) {
	        if (this.horizontal) {
	            if (row == this.bowRow && column >= this.bowColumn && column < this.bowColumn + this.length) {
	                int hitIndex = column - this.bowColumn; // Index for horizontal
	                this.hit[hitIndex] = true; 
	                return true;
	            }
	        } else {
	            if (column == this.bowColumn && row <= this.bowRow && row > this.bowRow - this.length) {
	                int hitIndex = this.bowRow - row; // Index for vertical
	                this.hit[hitIndex] = true; 
	                return true;
	            }
	        }
	    }
	    return false;*/
		
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
		
		/*if (this.isSunk()) {
			return "s";
		} else {
			for (boolean partHit : hit) {
				if (partHit) {
					return "x";
				}
			}
		}
		return "x";*/
		
		
		
		
		
		if (this.isSunk()) {
			return "s";
			
		} else {
			return "x";
		}
		

		
	}

}
