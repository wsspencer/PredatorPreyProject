package edu.ncsu.csc216.simulation.simulator;
 
import edu.ncsu.csc216.simulation.environment.utils.PaintedLocation;

public class AutomataSimulator implements SimulatorInterface {
	
	private static final int SIZE = 0;
	
	private static final int THRESHOLD = 0;
	
	private static final String SIZE_ERROR_MESSAGE = "";
	
	private static final String THRESHOLD_ERROR_MESSAGE = "";
	
	private String[] names;
	
	private int numberOfNames;
	
	private char[] symbol;
	
	private static final char EMPTY = '.';
	
	
	public AutomataSimulator(String initFileName) {
		
	}
	
	public AutomataSimulator(String initFileName, String configFileName) {
		
	}
	
	public void step() {
		
	}
	
	public PaintedLocation[][] getView() {
		return new PaintedLocation[0][0];
	}
	
	public String[] getNames() {
		return names;
	}

}
