package edu.ncsu.csc216.simulation.environment.utils;

/**
 * A class for the location used to traverse and locate things on the grid
 * @author Scott Spencer
 *
 */
public class Location {
	
	/** 
	 * An integer instance variable for a row index on the grid
	 */
	private int row;
	
	/**
	 * An integer instance variable for a column index on the grid
	 */
	private int column;
	
	/**
	 * A constructor method for constructing a point on the grid
	 * @param row an integer for the row index for this location
	 * @param col an integer for the column index for this location
	 */
	public Location(int row, int col) {
		this.row = row; 
		this.column = col;
	} 
	
	/**
	 * An integer getter method for the row index of this location
	 * @return integer for the row index of this location
	 */
	public int getRow() {
		return this.row; 
	}
	
	/**
	 * An integer getter method for the column index of this location
	 * @return integer for the row column index of this location
	 */
	public int getCol() {
		return this.column;
	}
}
