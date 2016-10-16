/**
 * 
 */
package edu.ncsu.csc216.environment;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.simulation.actor.Animal;
import edu.ncsu.csc216.simulation.actor.PredatorPrey;
import edu.ncsu.csc216.simulation.actor.PurePredator;
import edu.ncsu.csc216.simulation.simulator.AutomataSimulator;
import edu.ncsu.csc216.simulation.actor.PurePrey;
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
		assertEquals(grid.findFirstEmptyNeighbor(emptyLocation, 0).getRow(), grid.dueWest(emptyLocation).getRow());
		assertEquals(grid.findFirstEmptyNeighbor(emptyLocation, 0).getCol(), grid.dueWest(emptyLocation).getCol());
		assertEquals(grid.findFirstEmptyNeighbor(emptyLocation, 1).getRow(), grid.dueNorth(emptyLocation).getRow());
		assertEquals(grid.findFirstEmptyNeighbor(emptyLocation, 1).getCol(), grid.dueNorth(emptyLocation).getCol());
		assertEquals(grid.findFirstEmptyNeighbor(emptyLocation, 2).getCol(), grid.dueEast(emptyLocation).getCol());
		assertEquals(grid.findFirstEmptyNeighbor(emptyLocation, 3).getRow(), grid.dueWest(emptyLocation).getRow());
		
		//finds first empty neighbor when all but one space is taken up
		grid.add(wampa, new Location(1, 2)); 
		grid.add(wampa, new Location(2, 3));
		
		assertEquals(grid.findFirstEmptyNeighbor(emptyLocation, 0).getCol(), grid.dueWest(emptyLocation).getCol());
		assertEquals(grid.findFirstEmptyNeighbor(emptyLocation, 1).getCol(), grid.dueWest(emptyLocation).getCol());
		assertEquals(grid.findFirstEmptyNeighbor(emptyLocation, 2).getCol(), grid.dueWest(emptyLocation).getCol());
		assertEquals(grid.findFirstEmptyNeighbor(emptyLocation, 3).getCol(), grid.dueWest(emptyLocation).getCol());
		assertEquals(grid.findFirstEmptyNeighbor(emptyLocation, 0).getRow(), grid.dueWest(emptyLocation).getRow());
		assertEquals(grid.findFirstEmptyNeighbor(emptyLocation, 1).getRow(), grid.dueWest(emptyLocation).getRow());
		assertEquals(grid.findFirstEmptyNeighbor(emptyLocation, 2).getRow(), grid.dueWest(emptyLocation).getRow());
		assertEquals(grid.findFirstEmptyNeighbor(emptyLocation, 3).getRow(), grid.dueWest(emptyLocation).getRow());
	} 
	
	/**
	 * test method for testing the due directions
	 */
	@Test
	public void testDueDirections() {
		Animal rancor = new PurePredator('R');
		EcoGrid grid = new Ecosystem(3, 3);
		Location location = new Location(1, 1);
		grid.add(rancor, location);
		
		assertEquals(grid.dueEast(location).getRow(), 1);
		assertEquals(grid.dueEast(location).getCol(), 2);
		assertEquals(grid.dueNorth(location).getRow(), 0);
		assertEquals(grid.dueNorth(location).getCol(), 1);
		assertEquals(grid.dueWest(location).getRow(), 1);
		assertEquals(grid.dueWest(location).getCol(), 0);
		assertEquals(grid.dueSouth(location).getRow(), 2);
		assertEquals(grid.dueSouth(location).getCol(), 1);
		
		//tests that dueDirection will return its own index at a column or row if it is on the edge of the grid in the 
		//direction in question.
		EcoGrid gridMax = new Ecosystem(3, 3);
		Location northNorth = new Location(0, 1);
		assertEquals(gridMax.dueNorth(northNorth).getRow(), 2);
		Location southSouth = new Location(2, 1);
		assertEquals(gridMax.dueSouth(southSouth).getRow(), 0);
		Location eastEast = new Location(1, 2);
		assertEquals(gridMax.dueEast(eastEast).getCol(), 0);
		Location westWest = new Location(1, 0);
		assertEquals(gridMax.dueWest(westWest).getCol(), 2);
		
	}
	
	/**
	 * test method for testing buryTheDead
	 */
	@Test
	public void testBuryTheDead() {
		new AutomataSimulator("test-files/initFile", "test-files/configFile");
		EcoGrid grid = new Ecosystem(5, 5);
		Animal tribble = new PurePrey('T');
		Location position = new Location(3, 3);
		grid.add(tribble, position);
		Animal rancor = new PurePredator('R');
		Location predPosition = new Location(3, 4);
		grid.add(rancor, predPosition);

		rancor.enable();
		rancor.act(predPosition, grid);
		tribble.enable();
		tribble.act(position, grid);
		
		grid.buryTheDead();
	
		assertTrue(tribble.isAlive());
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
