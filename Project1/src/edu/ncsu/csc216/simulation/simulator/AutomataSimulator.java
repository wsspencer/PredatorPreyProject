package edu.ncsu.csc216.simulation.simulator;
 
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.ncsu.csc216.simulation.actor.Configs;
import edu.ncsu.csc216.simulation.environment.EcoGrid;
import edu.ncsu.csc216.simulation.environment.Ecosystem;
import edu.ncsu.csc216.simulation.environment.utils.PaintedLocation;

public class AutomataSimulator implements SimulatorInterface {
	
	private static final int SIZE = 0;
	
	private static final int THRESHOLD = 0;
	
	private static final String SIZE_ERROR_MESSAGE = "";
	
	private static final String THRESHOLD_ERROR_MESSAGE = "";
	
	private int numberOfNames;
	
	private String[] names;
	
	private char[] symbol;
	
	private static final char EMPTY = '.';
	
	private EcoGrid simpleSystem; 
	
	/**
	 * Constructor method for reading in intial file which is styled as a line for number of species; then a line with the 
	 * symbol and name of each species; followed by the outline of the initial state of the ecosystem (with the symbol of a 
	 * species representing that species and a "." representing an empty space.
	 * @param initFileName the filename of the initial file the program needs to run
	 */
	public AutomataSimulator(String initFileName) {
		try {
			Scanner filereader = new Scanner(new File(initFileName));
			String line;
			
			//checks that (and sets numberOfNames to it if so) the file's first line is an integer
			if (filereader.hasNextInt()) {
				numberOfNames = filereader.nextInt();
			}
		 
			//runs loop "numberOfNames" amount of times, and sets symbol and name arrays at index of loopcount to the 
			//two tokens of this line
			names = new String[numberOfNames];
			symbol = new char[numberOfNames];
		
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
			filereader.close();
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException();
		}
	}
	
	public AutomataSimulator(String initFileName, String configFileName) {
		this(initFileName);
		
		Scanner configReader = new Scanner(configFileName);
		String line;
		Color[] colors = new Color[3];
		int[] starve = new int[3];
		int[] breed = new int[3];
		
		//Reads in the three lines of the configuration file.  Sets to defaults if they're awry
		for (int i = 0; i < numberOfNames; i++) {
			if (configReader.hasNext()) {
				colors[i] = Color.decode("0x" + configReader.next());	
			}
			else {
				Configs.setToDefaults();
			}
		}
		if (configReader.next().equals("colors")) {
			configReader.nextLine();
		}
		
		for (int j = 0; j < numberOfNames; j++) {
			if (configReader.hasNextInt()) {
				starve[j] = configReader.nextInt();
			}
			else {
				Configs.setToDefaults();
			}
		}
		if (configReader.next().equals("starve")) {
			configReader.nextLine();
		}
		
		for (int k = 0; k < numberOfNames; k++) {
			if (configReader.hasNextInt()) {
				breed[k] = configReader.nextInt();
			}
			else {
				Configs.setToDefaults();
			}
		}
		if (configReader.next().equals("breed")) {
			configReader.nextLine();
		}
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
