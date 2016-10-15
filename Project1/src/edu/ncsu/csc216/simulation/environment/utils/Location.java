package edu.ncsu.csc216.simulation.environment.utils;

public class Location {
	
	private int row;
	
	private int column;
	
	public Location(int row, int col) {
		this.row = row; 
		this.column = col;
	} 
	
	public int getRow() {
		return row; 
	}
	
	public int getCol() {
		return column;
	}
}
