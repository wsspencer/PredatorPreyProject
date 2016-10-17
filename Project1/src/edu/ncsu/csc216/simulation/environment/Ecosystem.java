package edu.ncsu.csc216.simulation.environment;
 
import edu.ncsu.csc216.simulation.actor.Animal;
import edu.ncsu.csc216.simulation.environment.utils.Location;

/**
 * This is the ecosystem class, its purpose is to establish what it means to be the grid of animals our program will
 * use.  It also implements  the interface "EcoGrid" 
 * @author Scott Spencer
 *
 */
public class Ecosystem implements EcoGrid {  
	 
	/**
	 * This is an integer instance variable for the maximum number of rows in our grid.
	 */
	private int maxRows;
	 
	/**
	 * This is an integer instance variable for the maximum number of columns in our grid.
	 */
	private int maxCols;
	
	/**
	 * This is a 2D animal array instance variable to hold the animals in our grid.
	 */
	private Animal[][] map; 
	
	/**
	 * This is the constructor method for establishing the ecosystem grid.
	 * @param maxRows the passed maximum rows for our grid
	 * @param maxCols the passed maximum columns for our grid
	 */
	public Ecosystem(int maxRows, int maxCols) {
		this.maxRows = maxRows; 
		this.maxCols = maxCols;
		this.map = new Animal[this.maxRows][this.maxCols];
	}
	
	/**
	 * This is a method for checking whether or not the location is empty.
	 * @param location the location we want to check
	 * @return boolean whether the location is empty
	 */
	public boolean isEmpty(Location location) {
		if (this.getItemAt(location) == null || this.getItemAt(location).getSymbol() == '.') {
			return true;
		} 
		return false;
	}
	
	/**
	 * This is a method for retrieving which animal (if any) is located in the parameterized location
	 * @param location the location we are looking in.
	 * @return Animal the animal at the given location
	 */
	public Animal getItemAt(Location location) {
		return this.map[location.getRow()][location.getCol()];
	}
	
	/**
	 * This is a method to remove the animal in the parameterized location.
	 * @param location the location we want to remove an animal from
	 */
	public void remove(Location location) {
		this.map[location.getRow()][location.getCol()] = null;
	}
	 
	/**
	 * This is a method used to add a parameterized animal to the parameterized location
	 * @param location the location we wish to put an animal into
	 * @param x the animal we wish to add
	 */
	public void add(Animal x, Location location) {
		if (this.isEmpty(location)) {
			this.map[location.getRow()][location.getCol()] = x;
		}
	}
	
	/**
	 * This is a method used to find the first empty cell neighboring the parameterized one
	 * @param startDirection the direction we want to start looking before moving counterclockwise
	 * @param position the location we want to search for empty neighbors around
	 * @return Location the first empty neighbor of our position
	 */
	public Location findFirstEmptyNeighbor(Location position, int startDirection) {
		//initilized direction checks
		boolean zeroCheck = false;
		boolean oneCheck = false;
		boolean twoCheck = false;
		boolean threeCheck = false;		
		
		for (int i = 0; i <= 3; i++) {
			
			if (startDirection == 0) {
				if (isEmpty(dueWest(position))) {  
					return dueWest(position);
				}
				zeroCheck = true;
			}

			if (startDirection == 1) {
				if (isEmpty(dueNorth(position))) {
					return dueNorth(position);
				}		
				oneCheck = true;
			}

			if (startDirection == 2) {
				if (isEmpty(dueEast(position))) {
					return dueEast(position);
				}			
				twoCheck = true;
			}
			
			if (startDirection == 3) {
				if (isEmpty(dueSouth(position))) {
					return dueSouth(position);
				}	
				threeCheck = true;
				//resets startDirection to start the clockwise counting
			}
			//increments startDirection to re-run loop (in the case that startDirection was initially 
			//greater than 0
			if (startDirection == 3) {
				startDirection = 0;
			}
			else {
				startDirection++;
			}
			
			//personal short circuit to exit loop if each of the directions has been checked for empty
			if (zeroCheck && oneCheck && twoCheck && threeCheck) {
				startDirection = 4;
			}
		}
		return null;
	}

	/**
	 * This is a method for returning the location directly north of the parameterized one.
	 * @param x the location we want the northern direct of.
	 * @return Location the location north
	 */
	//IMPORTANT CONCEPT:  The screen scrolls in all 4 directions.  So due North at the northernmost point will point to 
	//the block in the same column on the southern-most point. (Think OG arcade Mario Bros.)
	public Location dueNorth(Location x) {
		if (x.getRow() == 0) {
			return new Location(this.maxRows - 1, x.getCol());
		}
		return new Location(x.getRow() - 1, x.getCol());
	}
	
	/**
	 * This is a method for returning the location directly south of the parameterized one.
	 * @param x the location we want the southern direct of.
	 * @return Location the location South
	 */
	public Location dueSouth(Location x) {
		if (x.getRow() == this.maxRows - 1) {
			return new Location(0, x.getCol());
		}
		return new Location(x.getRow() + 1, x.getCol());
	}
	
	/**
	 * This is a method for returning the location directly east of the parameterized one.
	 * @param x the location we want the eastern direct of.
	 * @return Location the location east
	 */
	public Location dueEast(Location x) {
		if (x.getCol() == this.maxCols - 1) {
			return new Location(x.getRow(), 0);
		}
		return new Location(x.getRow(), x.getCol() + 1);
	}
	
	/**
	 * This is a method for returning the location directly west of the parameterized one.
	 * @param x the location we want the western direct of.
	 * @return Location the location west
	 */
	public Location dueWest(Location x) {
		if (x.getCol() == 0) {
			return new Location(x.getRow(), this.maxCols - 1);
		}
		return new Location(x.getRow(), x.getCol() - 1);
	}
	
	/**
	 * This is a method for returning an animal 2d array representation of our grid.
	 * @return Animal[][] the 2D animal array representation of our grid
	 */
	public Animal[][] getMap() {
		return this.map;
	}
	
	/**
	 * This is a voided method is used to enable our living animals as it traverses our grid.
	 */
	public void enableTheLiving() {
		for (int i = 0; i < this.maxCols; i++) {
			for (int j = 0; j < this.maxRows; j++) {
				if (this.map[j][i] != null && this.map[j][i].isAlive()) {
					this.map[j][i].enable();
				}
			}
		}		
	}
	
	/**
	 * This is a voided method for burying (removing) dead animals from our grid at the end of a step.
	 */
	public void buryTheDead() {
		for (int i = 0; i < this.maxCols; i++) {
			for (int j = 0; j < this.maxRows; j++) {
				if (this.map[j][i] == null || !this.map[j][i].isAlive()) { 
					this.remove(new Location(j, i));
				}
			}
		}
	}

}
