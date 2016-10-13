/**
 * 
 */
package edu.ncsu.csc216.simulation.actor;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.simulation.environment.EcoGrid;
import edu.ncsu.csc216.simulation.environment.Ecosystem;
import edu.ncsu.csc216.simulation.environment.utils.Location;

/**
 * @author wspencer
 *
 */
public class AnimalTest {

	@Test
	public void testBreed() {
		EcoGrid grid = new Ecosystem(4, 4);
		Location location = new Location(3, 3);
		Animal lion = new PurePredator('L');
		
		assertFalse(lion.breed(location, grid));
	}

}
