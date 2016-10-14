/**
 * 
 */
package edu.ncsu.csc216.simulation.environment.utils;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author wspencer
 *
 */
public class LocationTest {

	/**
	 * This is a test case for testing location
	 */
	@Test
	public void testLocation() {
		Location location = new Location(3, 4);
		assertEquals(location.getCol(), 4);
		assertEquals(location.getRow(), 3);
	}

}
