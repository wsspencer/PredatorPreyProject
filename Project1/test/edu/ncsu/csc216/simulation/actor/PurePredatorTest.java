package edu.ncsu.csc216.simulation.actor;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

import edu.ncsu.csc216.simulation.environment.EcoGrid;
import edu.ncsu.csc216.simulation.environment.Ecosystem;
import edu.ncsu.csc216.simulation.environment.utils.Location;

/**
 * Test class for testing PurePredator actors
 * @author wspencer
 * 
 */
public class PurePredatorTest {

	/**
	 * Test method for testing getColor method
	 */
	@Test
	public void testGetColor() {
		Animal sarlacc = new PurePredator('S');
		Configs.setToDefaults();
		//test get color returns the correct color for this type
		assertEquals(sarlacc.getColor(), Color.red);
	}
	
	/**
	 * Test method for testing the act() method
	 */
	@Test
	public void testAct() {
		//create a grid, animal, and location to test
		Animal sarlacc = new PurePredator('S'); 
		EcoGrid grid = new Ecosystem(25, 25);
		Location sarlaccLocation = new Location(4, 20);
		grid.add(sarlacc, sarlaccLocation);
		
		//make sarlacc act
		sarlacc.act(new Location(4, 21), grid);
		
		//make an assertion that tests the action of sarlacc
		assertFalse(sarlacc.canAct());
		
		//run through all the sarlacc's actions and see if it dies
		sarlacc.enable(); 
		sarlacc.act(new Location(4, 20), grid);
		sarlacc.die(); 
		
		assertFalse(sarlacc.isAlive());
		
		Animal tribble1 = new PurePrey('T');
		grid.add(tribble1, new Location(3, 3));
		tribble1.die();
		Animal rancor1 = new PurePredator('R');
		grid.add(rancor1, new Location(4, 3));
		Animal rancor2 = new PurePredator('R');
		grid.add(rancor2, new Location(3, 4));
		
		rancor1.enable();
		rancor1.act(new Location(3, 3), grid);
		rancor2.enable();
		rancor2.act(new Location(4, 4), grid);
		rancor1.enable();
		rancor2.enable();
		rancor2.act(new Location(4, 3), grid);
		rancor1.act(new Location(3, 3), grid);
		grid.buryTheDead();
		
		assertFalse(grid.getItemAt(new Location(3, 3)) == rancor1);
	}

	/**
	 * Test method for testing the pastBreedTime method
	 */
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
	
	/**
	 * Test method for testing the makeNewBaby method
	 */
	@Test
	public void testMakeNewBaby() {
		//create an animal variable to work with
		Animal sarlacc = new PurePredator('S');
		Configs.setToDefaults();
		
		//attempt to make new baby sarlacc/test that it occurred
		assertEquals(sarlacc.makeNewBaby().getSymbol(), sarlacc.getSymbol());
	}
	
	/**
	 * Test method for testing the getFoodChainRank method
	 */
	@Test 
	public void testGetFoodChainRank() {
		//create an animal variable to work with
		Animal sarlacc = new PurePredator('S');
		Configs.setToDefaults();
		
		//test that sarlacc returns the proper food chain rank
		assertEquals(sarlacc.getFoodChainRank(), 20);
	}
}

