package edu.ncsu.csc216.simulation.environment;

import edu.ncsu.csc216.simulation.actor.Animal;
import edu.ncsu.csc216.simulation.environment.utils.Location;

public class Ecosystem implements EcoGrid {
	
	private int maxRows;
	
	private int maxCols;
	
	public Ecosystem(int maxRows, int maxCols) {
		this.maxRows = maxRows;
		this.maxCols = maxCols;
	}
	
	public boolean isEmpty(Location location) {
		return false;
	}
	
	public Animal getItemAt(Location location) {
		return null;
	}
	
	public void remove(Location place) {
		
	}
	
	public void add(Animal x, Location location) {
		
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
		return new Animal[0][0];
	}
	
	public void enableTheLiving() {
		
	}
	
	public void buryTheDead() {
		
	}

}
