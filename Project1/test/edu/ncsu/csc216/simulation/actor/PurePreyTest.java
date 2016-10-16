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
		Animal tribble1 = new PurePrey('T');
		Animal tribble2 = new PurePrey('T');
		Animal tribble3 = new PurePrey('T');
		EcoGrid grid = new Ecosystem(25, 25);
		Location tribble1Location = new Location(4, 21);
		Location tribble2Location = new Location(5, 21);
		Location tribble3Location = new Location(6, 21);
		grid.add(tribble1, tribble1Location);
		grid.add(tribble2, tribble2Location);
		grid.add(tribble3, tribble3Location);
		
		//make tribbles breed and move
		tribble1.enable(); 
		tribble1.act(new Location(4, 22), grid);
		tribble2.enable();
		tribble2.act(new Location(5, 22), grid);
		tribble3.enable();
		tribble1.act(new Location(6, 22), grid);
		tribble1.enable();
		tribble1.act(new Location(4, 23), grid);
		tribble1.enable();
		tribble1.act(new Location(5, 23), grid);
		tribble1.enable();
		tribble1.act(new Location(5, 24), grid);
		tribble1.enable();
		tribble1.act(new Location(6, 24), grid);
		tribble1.enable();
		tribble1.act(new Location(7, 24), grid);
		tribble1.enable();
		tribble1.act(new Location(7, 23), grid);
		tribble1.enable();
		tribble1.act(new Location(8, 23), grid);
		tribble1.enable();
		tribble1.act(new Location(8, 22), grid);
		tribble1.enable();
		tribble1.act(new Location(8, 21), grid);
		tribble1.enable();
		tribble1.act(new Location(9, 21), grid);
		
		
		
		//test that tribble cannot act and is not past its breed time
		assertFalse(tribble1.canAct());
		assertTrue(tribble1.pastBreedTime(tribble1.getTimeSinceLastBreed()));
		
		//Test that the animal will breed when the time comes
		
		//enable tribble, move it away from the other, have it act (in this case,
		//it will move) and check that it is pastBreedTime now
		tribble1.enable();
		tribble1.act(new Location(2, 21), grid);
		
		assertTrue(tribble1.pastBreedTime(tribble1.getTimeSinceLastBreed()));
		
		//test bury the living since it's easier here
		Animal tribble4 = new PurePrey('T');
		Location deathSite = new Location(5, 5);
		grid.add(tribble4, deathSite);
		tribble4.die();
		
		grid.buryTheDead();
		assertTrue(grid.isEmpty(deathSite));
		
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
