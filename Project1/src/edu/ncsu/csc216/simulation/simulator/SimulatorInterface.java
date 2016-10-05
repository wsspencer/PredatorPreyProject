package edu.ncsu.csc216.simulation.simulator;

import edu.ncsu.csc216.simulation.environment.utils.PaintedLocation;

public interface SimulatorInterface {
	
	public void step();
	
	public PaintedLocation[][] getView();
	
	public String[] getNames();
}
