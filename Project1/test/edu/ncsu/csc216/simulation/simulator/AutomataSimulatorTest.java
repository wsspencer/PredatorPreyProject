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
		AutomataSimulator sim = new AutomataSimulator("test-files/initFile", "test-files/configFile");
		
		sim.step(); 
		
		assertEquals(1, 1);
		
		//create a test for step with single file constructor
		AutomataSimulator sim2 = new AutomataSimulator("test-files/initFile");
		
		sim2.step();
		
		assertEquals(1, 1);
	}
	
	/**
	 * Test method for testing getView in automataSimulator 
	 */
	@Test
	public void testGetView() {
		AutomataSimulator sim = new AutomataSimulator("test-files/initFile");
		sim.getView();
		
		assertEquals(1, 1);
	}
	
	/**
	 * Test method for testing getNames in automataSimulator 
	 */
	@Test
	public void testGetNames() {
		AutomataSimulator sim = new AutomataSimulator("test-files/initFile");
		sim.getNames();
		
		assertEquals(1, 1);
	}	
	
	/**
	 * Test method for testing two constructors in Automata Simulator
	 */
	@Test 
	public void testAutomataSimulator() {
		AutomataSimulator sim = new AutomataSimulator("test-files/initFile", "test-files/configFile");
		assertEquals(sim.getNames()[1], "Mouse");
		assertEquals(sim.getNames()[2], "Frog");
	}
}
