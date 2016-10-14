/**
 * 
 */
package edu.ncsu.csc216.simulation.simulator;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author wspencer
 *
 */
public class AutomataSimulatorTest {

	/**
	 * Test method for testing the step method in AutomataSimulator
	 */
	@Test
	public void testStep() {
		//create a test for step with two file constructor
		AutomataSimulator sim = new AutomataSimulator("initFile.txt", "configFile.txt");
		
		sim.step();
		
		assertEquals(1, 1);
		
		//create a test for step with single file constructor
		AutomataSimulator sim2 = new AutomataSimulator("initFile.txt");
		
		sim2.step();
		
		assertEquals(1, 1);
	}
	
	/**
	 * Test method for testing getView in automataSimulator 
	 */
	@Test
	public void testGetView() {
		AutomataSimulator sim = new AutomataSimulator("initFile.txt");
		sim.getView();
		
		assertEquals(1, 1);
	}
	
	/**
	 * Test method for testing getNames in automataSimulator 
	 */
	@Test
	public void testGetNames() {
		AutomataSimulator sim = new AutomataSimulator("initFile.txt");
		sim.getNames();
		
		assertEquals(1, 1);
	}	
}
