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
	
	public void remove(Location place) {
		
	}
	
	public void add(Animal x, Location location) {
		if (this.isEmpty(location)) {
			this.grid[location.getRow()][location.getCol()] = x;
		}
	}
	
	public Location findFirstEmptyNeighbor(Location position, int startDirection) {
		return new Location(0, 0);
	}
	
	public Location dueNorth(Location x) {
		return new Location(0, 0);
	}
	
	public Location dueSouth(Location x) {
		return new Location(0, 0);
	}
	
	public Location dueEast(Location x) {
		return new Location(0, 0);
	}
	
	public Location dueWest(Location x) {
		return new Location(0, 0);
	}
	
	public Animal[][] getMap() {
		return grid;
	}
	
	public void enableTheLiving() {
		
	}
	
	public void buryTheDead() {
		
	}

}
