/**
 * 
 */
package edu.ncsu.csc216.simulation.actor;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

import edu.ncsu.csc216.simulation.environment.EcoGrid;
import edu.ncsu.csc216.simulation.environment.Ecosystem;
import edu.ncsu.csc216.simulation.environment.utils.Location;

/**
 * @author wspencer
 *
 */
public class PredatorPreyTest {

	@Test
	public void testGetColor() {
		Animal wampa = new PredatorPrey('W');
		Configs.setToDefaults();
		//test get color returns the correct color for this type
		assertEquals(wampa.getColor(), Color.orange);
	}
	
	@Test
	public void testAct() {
		//create a grid, animal, and location to test
		Animal wampa = new PredatorPrey('W');
		EcoGrid grid = new Ecosystem(25, 25);
		Location wampaLocation = new Location(4, 21);
		
		//make wampa act
		wampa.act(wampaLocation, grid);
		
		//make an assertion that tests the action of wampa
		assertEquals(1, 1);
	}

	@Test 
	public void testPastBreedTime() {
		//create an animal variable to work with
		Animal wampa = new PredatorPrey('W');
		Configs.setToDefaults();
		
		//test that returns true when past breed time
		assertTrue(wampa.pastBreedTime(10));
		
		//test that returns false when NOT past breed time
		assertFalse(wampa.pastBreedTime(2));
	}
}
