package edu.ncsu.csc216.simulation.environment.utils;

import java.awt.Color;

/**
 * A class for the color location for our location for the graphic panel.  Extends the superclass Location.
 * @author Scott Spencer
 *
 */
public class PaintedLocation extends Location {
	
	/**
	 * An instance variable for the color at this location
	 */
	private Color tint;
	
	/**
	 * An instance variable for the symbol of the animal at this location 
	 */
	private char symbol;
	
	/**
	 * Constructor method for the painted location
	 * @param row integer for the row index here.
	 * @param column integer for the column index here.
	 * @param tint Color for the cell color on the panel at this location
	 * @param symbol char for the symbol of the object at this location
	 */
	public PaintedLocation(int row, int column, Color tint, char symbol) {
		super(row, column);
		this.tint = tint; 
		this.symbol = symbol;
	}
	
	/**
	 * Getter method for the color of the cell at this location.
	 * @return Color the color of the cell at this location.
	 */
	public Color getColor() {
		return tint;
	}
	
	public char getSymbol() {
		return symbol;
	}
}
