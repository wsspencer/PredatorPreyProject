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
public class PurePredatorTest {

	@Test
	public void testGetColor() {
		Animal sarlacc = new PurePredator('S');
		Configs.setToDefaults();
		//test get color returns the correct color for this type
		assertEquals(sarlacc.getColor(), Color.red);
	}
	
	@Test
	public void testAct() {
		//create a grid, animal, and location to test
		Animal sarlacc = new PurePredator('S');
		EcoGrid grid = new Ecosystem(25, 25);
		Location sarlaccLocation = new Location(4, 21);
		
		//make sarlacc act
		sarlacc.act(sarlaccLocation, grid);
		
		//make an assertion that tests the action of sarlacc
		assertFalse(sarlacc.canAct());
	}

	@Test 
	public void testPastBreedTime() {
		//create an animal variable to work with
		Animal sarlacc = new PurePredator('S');
		Configs.setToDefaults();
		
		//test that returns true when past breed time
		assertTrue(sarlacc.pastBreedTime(16));
		
		//test that returns false when NOT past breed time
		assertFalse(sarlacc.pastBreedTime(13));
	}
	
	@Test
	public void testMakeNewBaby() {
		//create an animal variable to work with
		Animal sarlacc = new PurePredator('S');
		Configs.setToDefaults();
		
		//attempt to make new baby sarlacc/test that it occurred
		assertEquals(sarlacc.makeNewBaby().getSymbol(), sarlacc.getSymbol());
	}
	
	@Test 
	public void testGetFoodChainRank() {
		//create an animal variable to work with
		Animal sarlacc = new PurePredator('S');
		Configs.setToDefaults();
		
		//test that sarlacc returns the proper food chain rank
		assertEquals(sarlacc.getFoodChainRank(), 20);
	}
}

