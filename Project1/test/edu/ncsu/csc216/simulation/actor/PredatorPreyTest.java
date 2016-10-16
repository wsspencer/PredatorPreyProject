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
		assertFalse(wampa.canAct());
		
		
		Animal tribble1 = new PurePrey('T');
		grid.add(tribble1, new Location(3, 3));
		tribble1.die();
		Animal wampa1 = new PredatorPrey('R');
		grid.add(wampa1, new Location(4, 3));
		Animal wampa2 = new PredatorPrey('R');
		grid.add(wampa2, new Location(3, 4));
		
		wampa1.enable();
		wampa1.act(new Location(3, 3), grid);
		wampa2.enable();
		wampa2.act(new Location(4, 4), grid);
		wampa1.enable();
		wampa2.enable();
		wampa2.act(new Location(4, 3), grid);
		wampa1.act(new Location(3, 3), grid);
		grid.buryTheDead();
		
		assertFalse(grid.getItemAt(new Location(3, 3)) == wampa1);
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
	
	@Test
	public void testMakeNewBaby() {
		//create an animal variable to work with
		Animal wampa = new PredatorPrey('W');
		Configs.setToDefaults();
		
		//attempt to make new baby wampa/test that it occurred
		assertEquals(wampa.makeNewBaby().getSymbol(), wampa.getSymbol());
	}
	
	@Test 
	public void testGetFoodChainRank() {
		//create an animal variable to work with
		Animal wampa = new PredatorPrey('W');
		Configs.setToDefaults();
		
		//test that wampa returns the proper food chain rank
		assertEquals(wampa.getFoodChainRank(), 10);
	}
}
