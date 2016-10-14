/**
 * 
 */
package edu.ncsu.csc216.simulation.environment.utils;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

/**
 * @author wspencer
 *
 */
public class PaintedLocationTest {

	/**
	 * This is a test method for testing painted location
	 */
	@Test
	public void testPaintedLocation() {
		PaintedLocation testPL = new PaintedLocation(3, 2, Color.green, 'T');
		
		assertEquals(testPL.getColor(), Color.green);
		assertEquals(testPL.getSymbol(), 'T');
	}

}
