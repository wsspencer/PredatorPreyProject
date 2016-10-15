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
	
	private String[] names = {};
	
	private char[] symbol = {};
	
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
				if (filereader.nextInt() >= THRESHOLD) {
					numberOfNames = filereader.nextInt();
				}
				else {
					filereader.close();
					throw new IllegalArgumentException();
				}
			}
			else {
				filereader.close();
				throw new IllegalArgumentException();
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
			char[][] map = new char[20][20];
			while (filereader.hasNextLine()) {
				line = filereader.nextLine();
				//divide by 20 for the column because it will return a whole integer and it's a 20x20 grid
				for (int j = 0; j < line.length(); j++) {
					map[j][j / 20] = line.charAt(j);
				}
			}
			filereader.close();
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
			for (int i = 0; i < numberOfNames; i++) {
				if (configReader.hasNext()) {
					//ATTN: need to change to just reading hexidecimal to color...can't figure it out yet...
					colors[i] = Color.decode("0x" + configReader.next());
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
