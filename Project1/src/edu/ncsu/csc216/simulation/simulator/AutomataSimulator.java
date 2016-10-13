package edu.ncsu.csc216.simulation.simulator;
 
import java.util.Scanner;

import edu.ncsu.csc216.simulation.environment.utils.PaintedLocation;

public class AutomataSimulator implements SimulatorInterface {
	
	private static final int SIZE = 0;
	
	private static final int THRESHOLD = 0;
	
	private static final String SIZE_ERROR_MESSAGE = "";
	
	private static final String THRESHOLD_ERROR_MESSAGE = "";
	
	private int numberOfNames;
	
	private String[] names = new String[numberOfNames];
	
	private char[] symbol = new char[numberOfNames];
	
	private static final char EMPTY = '.';
	
	/**
	 * Constructor method for reading in intial file which is styled as a line for number of species; then a line with the 
	 * symbol and name of each species; followed by the outline of the initial state of the ecosystem (with the symbol of a 
	 * species representing that species and a "." representing an empty space.
	 * @param initFileName the filename of the inital file the program needs to run
	 */
	public AutomataSimulator(String initFileName) {
		Scanner filereader = new Scanner(initFileName);
		String line;
		//checks that (and sets numberOfNames to it if so) the file's first line is an integer
		if (filereader.hasNextInt()) {
			numberOfNames = filereader.nextInt();
		}
		//runs loop "numberOfNames" amount of times, and sets symbol and name arrays at index of loopcount to the 
		//two tokens of this line
		for (int i = 0; i < numberOfNames; i++) {
			filereader.nextLine();
			symbol[i] = filereader.next().charAt(0);
			names[i] = filereader.next();
		}
		//runs loop while file has another line, and reads in the given ecosystem as it is written
		while (filereader.hasNextLine()) {
			line = filereader.nextLine();
			for (int j = 0; j < line.length(); j++) {
				line.charAt(j);
			}
		}
		
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
