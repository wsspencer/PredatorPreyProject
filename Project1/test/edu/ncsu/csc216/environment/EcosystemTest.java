/**
 * 
 */
package edu.ncsu.csc216.environment;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.simulation.actor.Animal;
import edu.ncsu.csc216.simulation.actor.PredatorPrey;
import edu.ncsu.csc216.simulation.actor.PurePredator;
import edu.ncsu.csc216.simulation.environment.EcoGrid;
import edu.ncsu.csc216.simulation.environment.Ecosystem;
import edu.ncsu.csc216.simulation.environment.utils.Location;

/**
 * @author wspencer
 *
 */
public class EcosystemTest {

	/**
	 * test method for finding the first empty neighbor of the animal in question
	 */
	@Test
	public void testFindFirstEmptyNeighbor() {
		//initialize the states of this test
		EcoGrid grid = new Ecosystem(5, 5);
		Animal wampa = new PredatorPrey('W');
		Location emptyLocation = new Location(2, 2);
		Location wampaLocation = new Location(3, 2);
		grid.add(wampa, wampaLocation);
		
		//finds first empty neighbor when only one space is taken up
		assertEquals(grid.findFirstEmptyNeighbor(emptyLocation, 0).getCol(), new Location(1, 2).getCol());
		assertEquals(grid.findFirstEmptyNeighbor(emptyLocation, 1).getCol(), new Location(2, 1).getCol());
		assertEquals(grid.findFirstEmptyNeighbor(emptyLocation, 2).getCol(), new Location(2, 3).getCol());
		assertEquals(grid.findFirstEmptyNeighbor(emptyLocation, 3).getCol(), new Location(2, 3).getCol());
		assertEquals(grid.findFirstEmptyNeighbor(emptyLocation, 0).getRow(), new Location(1, 2).getRow());
		assertEquals(grid.findFirstEmptyNeighbor(emptyLocation, 1).getRow(), new Location(2, 1).getRow());
		assertEquals(grid.findFirstEmptyNeighbor(emptyLocation, 2).getRow(), new Location(2, 3).getRow());
		assertEquals(grid.findFirstEmptyNeighbor(emptyLocation, 3).getRow(), new Location(2, 3).getRow());
		
		//finds first empty neighbor when all but one space is taken up
		grid.add(wampa, new Location(1, 2));
		grid.add(wampa, new Location(2, 3));
		
		assertEquals(grid.findFirstEmptyNeighbor(emptyLocation, 0).getCol(), new Location(2, 1).getCol());
		assertEquals(grid.findFirstEmptyNeighbor(emptyLocation, 1).getCol(), new Location(2, 1).getCol());
		assertEquals(grid.findFirstEmptyNeighbor(emptyLocation, 2).getCol(), new Location(2, 1).getCol());
		assertEquals(grid.findFirstEmptyNeighbor(emptyLocation, 3).getCol(), new Location(2, 1).getCol());
		assertEquals(grid.findFirstEmptyNeighbor(emptyLocation, 0).getRow(), new Location(2, 1).getRow());
		assertEquals(grid.findFirstEmptyNeighbor(emptyLocation, 1).getRow(), new Location(2, 1).getRow());
		assertEquals(grid.findFirstEmptyNeighbor(emptyLocation, 2).getRow(), new Location(2, 1).getRow());
		assertEquals(grid.findFirstEmptyNeighbor(emptyLocation, 3).getRow(), new Location(2, 1).getRow());
	} 
	
	/**
	 * test method for testing the due directions
	 */
	@Test
	public void testDueDirections() {
		Animal rancor = new PurePredator('R');
		EcoGrid grid = new Ecosystem(5, 5);
		Location location = new Location(2, 2);
		
		assertEquals(grid.dueNorth(location).getCol(), 1);
		assertEquals(grid.dueNorth(location).getRow(), 2);
		assertEquals(grid.dueEast(location).getCol(), 2);
		assertEquals(grid.dueEast(location).getRow(), 3);
		assertEquals(grid.dueSouth(location).getCol(), 3);
		assertEquals(grid.dueSouth(location).getRow(), 2);
		assertEquals(grid.dueWest(location).getCol(), 2);
		assertEquals(grid.dueWest(location).getRow(), 1);
		
	}
	
	/**
	 * test method for testing buryTheDead
	 */
	@Test
	public void testBuryTheDead() {
		EcoGrid grid = new Ecosystem(5, 5);
		Animal rancor = new PurePredator('R');
		Animal wampa = new PredatorPrey('W');
		Location preyPosition = new Location(3, 3);
		Location predPosition = new Location(2, 3);
		grid.add(rancor, predPosition);
		grid.add(wampa, preyPosition);
		grid.buryTheDead();
	
		assertTrue(grid.isEmpty(preyPosition));
	}
	
	/**
	 * test method for testing enableTheLiving
	 */
	@Test
	public void enableTheLiving() {
		EcoGrid grid = new Ecosystem(5, 5);
		Animal rancor = new PurePredator('R');
		Location location = new Location(2, 3);
		grid.add(rancor, location);
		
		grid.enableTheLiving();
		
		assertTrue(rancor.isAlive());
	}
}
