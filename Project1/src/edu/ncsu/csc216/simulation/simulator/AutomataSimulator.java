package edu.ncsu.csc216.simulation.simulator;
 
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.ncsu.csc216.simulation.actor.Animal;
import edu.ncsu.csc216.simulation.actor.Configs;
import edu.ncsu.csc216.simulation.environment.EcoGrid;
import edu.ncsu.csc216.simulation.environment.Ecosystem;
import edu.ncsu.csc216.simulation.environment.utils.Location;
import edu.ncsu.csc216.simulation.environment.utils.PaintedLocation;

public class AutomataSimulator implements SimulatorInterface {
	
	private static final int SIZE = 20;
	
	private static final int THRESHOLD = 2;
	
	private static final String SIZE_ERROR_MESSAGE = "";
	
	private static final String THRESHOLD_ERROR_MESSAGE = "";
	
	private int numberOfNames = 0;
	
	private String[] names;
	
	private char[] symbol;
	
	private static final char EMPTY = '.';
	
	private EcoGrid simpleSystem; 
	
	/**
	 * Constructor method for reading in initial file which is styled as a line for number of species; then a line with the 
	 * symbol and name of each species; followed by the outline of the initial state of the ecosystem (with the symbol of a 
	 * species representing that species and a "." representing an empty space.
	 * @param initFileName the filename of the initial file the program needs to run
	 */
	public AutomataSimulator(String initFileName) {
		try {
			File initFile = new File(initFileName);
			Scanner fileScanner = new Scanner(initFile);
			char[][] animals = new char[SIZE][SIZE];
			int count = 0;
			int gridCount = 0;
		
			if (fileScanner.hasNextInt()) {
				numberOfNames = fileScanner.nextInt();
			}
			else {
				fileScanner.close();
				throw new IllegalArgumentException("File formatted incorrectly");
			}
			
			symbol = new char[numberOfNames];
			names = new String[numberOfNames];
			fileScanner.nextLine();
			
			while (fileScanner.hasNextLine() && count < numberOfNames) {
				
				String line = fileScanner.nextLine();
					
				symbol[count] = line.charAt(0);
				names[count] = line.substring(1, line.length()).trim();
					
				count++;	
			}
			while (fileScanner.hasNextLine() && count >= numberOfNames - 1) {
				String line = fileScanner.nextLine();
				
				for (int i = 0; i < line.length(); i++) {
					animals[i][gridCount] = line.charAt(i);
				}
				gridCount++;
			}
			fileScanner.close();
			
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException();
		}
		///do something with map created above
		simpleSystem = new Ecosystem(SIZE, SIZE);
		
	}
	
	public AutomataSimulator(String initFileName, String configFileName) {
		//call to other constructor to take care of initial file functionality
		this(initFileName);
		
		//read in the second (configuration) file
		try {
			Scanner configReader = new Scanner(new File(configFileName));
			Color[] colors = new Color[3];
			int[] starve = new int[3];
			int[] breed = new int[3];
		
			//Reads in the three lines of the configuration file.  Sets to defaults if they're awry
			for (int i = 0; i < 3; i++) {
				if (configReader.hasNext()) {
					//ATTN: need to change to just reading hexidecimal to color...can't figure it out yet...
					colors[i] = Color.decode("#" + configReader.next());
				}
				else {
					Configs.setToDefaults();
				}
			}
			configReader.nextLine();
		
			for (int j = 0; j < numberOfNames; j++) {
				if (configReader.hasNextInt()) {
					starve[j] = configReader.nextInt();
				}
				else {
					Configs.setToDefaults();
				}
			}
			configReader.nextLine();

			for (int k = 0; k < numberOfNames; k++) {
				if (configReader.hasNextInt()) {
					breed[k] = configReader.nextInt();
				}
				else {
					Configs.setToDefaults();
				}
			}
			configReader.nextLine();
			configReader.close();
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException();
		}
	}

	public void step() {
		//make a animal array out of simpleSystem and enable each of its members
		Animal[][] creature = simpleSystem.getMap();
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if (creature[j][i] != null) {
					creature[j][i].enable();
				}
			}
		}
		//go through and have each animal in the grid act.  Burying the dead along the way
		for (int i = 0; i < creature.length; i++) {
			for (int j = 0; j < creature.length; j++) {
				if (creature[j][i] != null && creature[i][j].isAlive()) {
					creature[j][i].act(new Location(i, j), simpleSystem);
				}
				simpleSystem.buryTheDead();
			}
		}
	}

	public PaintedLocation[][] getView() {
		return new PaintedLocation[SIZE][SIZE];
	}

	public String[] getNames() {
		return names;
	}

}
