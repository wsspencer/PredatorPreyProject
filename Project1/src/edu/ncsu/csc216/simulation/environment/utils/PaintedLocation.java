package edu.ncsu.csc216.simulation.environment.utils;

import java.awt.Color;

public class PaintedLocation extends Location {
	
	private Color tint;
	
	private char symbol;
	
	public PaintedLocation(int row, int column, Color tint, char symbol) {
		super(row, column);
		this.tint = tint; 
		this.symbol = symbol;
	}
	
	public Color getColor() {
		return tint;
	}
	
	public char getSymbol() {
		return symbol;
	}
}
