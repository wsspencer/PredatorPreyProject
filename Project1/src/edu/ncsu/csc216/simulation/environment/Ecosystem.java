package edu.ncsu.csc216.simulation.environment;
 
import edu.ncsu.csc216.simulation.actor.Animal;
import edu.ncsu.csc216.simulation.environment.utils.Location;

public class Ecosystem implements EcoGrid {  
	
	private int maxRows;
	
	private int maxCols;
	
	private Animal[][] grid; 
	
	public Ecosystem(int maxRows, int maxCols) {
		this.maxRows = maxRows;
		this.maxCols = maxCols;
		this.grid = new Animal[this.maxRows][this.maxCols];
	}
	
	public boolean isEmpty(Location location) {
		if (this.getItemAt(location) == null || this.getItemAt(location).getSymbol() == '.') {
			return true;
		} 

		return false;
	}
	
	public Animal getItemAt(Location location) {
		return grid[location.getRow()][location.getCol()];
	}
	
	public void remove(Location location) {
		grid[location.getRow()][location.getCol()] = null;
	}
	
	public void add(Animal x, Location location) {
		if (this.isEmpty(location)) {
			grid[location.getRow()][location.getCol()] = x;
		}
	}
	
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
		if (x.getRow() == 0) {
			return new Location(x.getRow() + 1, x.getCol());
		}
		return new Location(x.getRow() - 1, x.getCol());
	}
	
	public Location dueSouth(Location x) {
		if (x.getRow() == this.maxRows - 1) {
			return new Location(x.getRow() - 1, x.getCol());
		}
		return new Location(x.getRow() + 1, x.getCol());
	}
	
	public Location dueEast(Location x) {
		if (x.getCol() == this.maxCols - 1) {
			return new Location(x.getRow(), x.getCol() - 1);
		}
		return new Location(x.getRow(), x.getCol() + 1);
	}
	
	public Location dueWest(Location x) {
		if (x.getCol() == 0) {
			return new Location(x.getRow(), x.getCol() + 1);
		}
		return new Location(x.getRow(), x.getCol() - 1);
	}
	
	public Animal[][] getMap() {
		return grid;
	}
	
	public void enableTheLiving() {
		
	}
	
	public void buryTheDead() {
		
	}

}
