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
public class PurePreyTest {

	@Test
	public void testGetColor() {
		Animal tribble = new PurePrey('T');
		Configs.setToDefaults();
		//test get color returns the correct color for this type
		assertEquals(tribble.getColor(), Color.green);
	}
	
	@Test
	public void testAct() {
		//create a grid, animal, and location to test
		Animal tribble = new PurePrey('T');
		EcoGrid grid = new Ecosystem(25, 25);
		Location tribbleLocation = new Location(4, 21);
		
		//make tribble act
		tribble.act(tribbleLocation, grid);
		
		//make an assertion that tests the action of tribble
		assertFalse(tribble.canAct());
	}

	@Test 
	public void testPastBreedTime() {
		//create an animal variable to work with
		Animal tribble = new PurePrey('S');
		Configs.setToDefaults();
		
		//test that returns true when past breed time
		assertTrue(tribble.pastBreedTime(2));
		
		//test that returns false when NOT past breed time
		assertFalse(tribble.pastBreedTime(0));
	}
	
	@Test
	public void testMakeNewBaby() {
		//create an animal variable to work with
		Animal tribble = new PurePrey('T');
		Configs.setToDefaults();
		
		//attempt to make new baby tribble/test that it occurred
		assertEquals(tribble.makeNewBaby().getSymbol(), tribble.getSymbol());
	}
	
	@Test 
	public void testGetFoodChainRank() {
		//create an animal variable to work with
		Animal tribble = new PurePrey('T');
		Configs.setToDefaults();
		
		//test that tribble returns the proper food chain rank
		assertEquals(tribble.getFoodChainRank(), 0);
	}
}
