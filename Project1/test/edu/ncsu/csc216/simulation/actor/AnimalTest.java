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
		//creates small ecosystem grid
		EcoGrid grid = new Ecosystem(5, 5);
		//creates new locations for our two animals
		Location mate1Location = new Location(3, 3);
		Location mate2Location = new Location(2, 3);
		//Declares, initializes, and adds our two animals to the grid
		Animal lion1 = new PurePredator('L');
		grid.add(lion1, mate1Location);
		Animal lion2 = new PurePredator('L');
		grid.add(lion2, mate2Location);
		
		//tests that the will breed
		assertTrue(lion1.breed(mate2Location, grid));
		
		
		//adds another animal of a different type
		//tests that the two will NOT breed
	}

}
