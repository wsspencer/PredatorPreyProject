package edu.ncsu.csc216.simulation.environment;
 
import edu.ncsu.csc216.simulation.actor.Animal;
import edu.ncsu.csc216.simulation.environment.utils.Location;

public class Ecosystem implements EcoGrid {  
	
	private int maxRows;
	
	private int maxCols;
	
	Animal[][] grid; 
	
	public Ecosystem(int maxRows, int maxCols) {
		this.maxRows = maxRows;
		this.maxCols = maxCols;
		this.grid = new Animal[this.maxRows][this.maxCols];
	}
	
	public boolean isEmpty(Location location) {
		if (this.getItemAt(location) == null) {
			return true;
		} 

		return false;
	}
	
	public Animal getItemAt(Location location) {
		return this.getMap()[location.getRow()][location.getCol()];
	}
	
	public void remove(Location location) {
		this.grid[location.getRow()][location.getCol()] = null;
	}
	
	public void add(Animal x, Location location) {
		if (this.isEmpty(location)) {
			this.grid[location.getRow()][location.getCol()] = x;
		}
	}
	
	public Location findFirstEmptyNeighbor(Location position, int startDirection) {
		
		for (int i = 0; i <= 3; i++) {
			//initilized direction checks
			boolean zeroCheck = false;
			boolean oneCheck = false;
			boolean twoCheck = false;
			boolean threeCheck = false;
			
			if (startDirection == 0) {
				if (isEmpty(new Location(position.getRow() - 1, position.getCol()))) {
					return new Location(position.getRow() - 1, position.getCol());
				}
				zeroCheck = true;
			}

			if (startDirection == 1) {
				if (isEmpty(new Location(position.getRow(), position.getCol() - 1))) {
					return new Location(position.getRow(), position.getCol() - 1);
				}		
				oneCheck = true;
			}

			if (startDirection == 2) {
				if (isEmpty(new Location(position.getRow() + 1, position.getCol()))) {
					return new Location(position.getRow() + 1, position.getCol());
				}			
				twoCheck = true;
			}
			
			if (startDirection == 3) {
				if (isEmpty(new Location(position.getRow(), position.getCol() + 1))) {
					return new Location(position.getRow(), position.getCol() + 1);
				}	
				threeCheck = true;
				//resets startDirection to start the clockwise counting
				startDirection = -1;
			}
			//increments startDirection to re-run loop (in the case that startDirection was initially 
			//greater than 0
			startDirection++;
			
			//personal short circuit to exit loop if each of the directions has been checked for empty
			if (zeroCheck && oneCheck && twoCheck && threeCheck) {
				startDirection = 4;
			}
		}
		return null;
	}
	
	public Location dueNorth(Location x) {
		return new Location(x.getRow(), x.getCol() - 1);
	}
	
	public Location dueSouth(Location x) {
		return new Location(x.getRow(), x.getCol() + 1);
	}
	
	public Location dueEast(Location x) {
		return new Location(x.getRow() - 1, x.getCol());
	}
	
	public Location dueWest(Location x) {
		return new Location(x.getRow() + 1, x.getCol());
	}
	
	public Animal[][] getMap() {
		return grid;
	}
	
	public void enableTheLiving() {
		
	}
	
	public void buryTheDead() {
		
	}

}
