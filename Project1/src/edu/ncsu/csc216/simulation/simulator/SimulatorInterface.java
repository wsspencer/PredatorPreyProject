package edu.ncsu.csc216.simulation.simulator;

import edu.ncsu.csc216.simulation.environment.utils.PaintedLocation;

/**
 * An interface for our Automata Simulator
 * @author Scott Spencer
 *
 */
public interface SimulatorInterface {
	
	/**
	 * A voided method to ensure our AutomataSimulator has a step method
	 */
	public void step();
	
	/**
	 * A method to ensure our Automata has a getView method
	 * @return PaintedLocation[][] a 2D array of painted locations
	 */
	public PaintedLocation[][] getView();
	
	/**
	 * A method to ensure our AutomataSimulator has a getNames method
	 * @return String[] an array of names
	 */
	public String[] getNames();
}
