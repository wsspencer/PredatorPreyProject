package edu.ncsu.csc216.simulation.simulator;
 
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.ncsu.csc216.simulation.actor.Animal;
import edu.ncsu.csc216.simulation.actor.Configs;
import edu.ncsu.csc216.simulation.actor.PredatorPrey;
import edu.ncsu.csc216.simulation.actor.PurePredator;
import edu.ncsu.csc216.simulation.actor.PurePrey;
import edu.ncsu.csc216.simulation.environment.EcoGrid;
import edu.ncsu.csc216.simulation.environment.Ecosystem;
import edu.ncsu.csc216.simulation.environment.utils.Location;
import edu.ncsu.csc216.simulation.environment.utils.PaintedLocation;

/**
 * This is the engine of our automata simulator
 * @author Scott Spencer
 *
 */
public class AutomataSimulator implements SimulatorInterface {
	
	/**
	 * An instance variable for the size of a dimension of our grid
	 */
	private static final int SIZE = 20;
	
	/**
	 * An instance variable for the threshold (minimum) animal ranks value.
	 */
	private static final int THRESHOLD = 2;
	
	/**
	 * An instance variable for the size error message
	 */
	private static final String SIZE_ERROR_MESSAGE = "";
	
	/**
	 * An instance variable for the threshold error message
	 */
	private static final String THRESHOLD_ERROR_MESSAGE = "";
	
	/**
	 * An instance variable for an integer representing the number of names in this grid
	 */
	private int numberOfNames = 0;
	
	/**
	 * An instance variable for the names of animals in our grid (String array)
	 */
	private String[] names; 
	
	/**
	 * An instance variable for the symbols of animals in our grid (char array)
	 */
	private char[] symbol;
	
	/**
	 * An instance variable for the char representation of an empty cell
	 */
	private static final char EMPTY = '.';
	 
	private EcoGrid simpleSystem = new Ecosystem(SIZE, SIZE); 
	
	/**
	 * Constructor method for reading in initial file which is styled as a line for number of species; then a line with the 
	 * symbol and name of each species; followed by the outline of the initial state of the ecosystem (with the symbol of a 
	 * species representing that species and a "." representing an empty space.
	 * @param initFileName the filename of the initial file the program needs to run
	 */
	public AutomataSimulator(String initFileName) {
		char[][] animals = new char[SIZE][SIZE]; 
		int count = 0;
		int gridCount = 0;
		 
		Configs.setToDefaults();
		
		try { 
			File initFile = new File(initFileName);
			Scanner fileScanner = new Scanner(initFile);
		
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
			
 
			
			while (fileScanner.hasNextLine() && count < numberOfNames  && numberOfNames > THRESHOLD) {
				
				String line = fileScanner.nextLine();
					
				symbol[count] = line.charAt(0);
				names[count] = line.substring(1, line.length()).trim();
					
				count++;	
			}
			
			//is using size okay? will grid always be 20x20?
			while (fileScanner.hasNextLine()) {
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

		//add in new animals to the ecoSystem grid
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if (animals[j][i] == symbol[0])	{
					this.simpleSystem.add(new PurePredator(animals[j][i]), new Location(j, i)); 
				}
				if (animals[j][i] == symbol[1]) {
					this.simpleSystem.add(new PredatorPrey(animals[j][i]), new Location(j, i));
				}
				if (animals[j][i] == symbol[2]) {
					this.simpleSystem.add(new PredatorPrey(animals[j][i]), new Location(j, i));
				}
				if (animals[j][i] == symbol[3]) {
					this.simpleSystem.add(new PurePrey(animals[j][i]), new Location(j, i));
				}
				if (animals[j][i] == EMPTY) {
					this.simpleSystem.add(null, new Location(j, i));
				}
			}
		}
		this.simpleSystem.getMap();
	}
	
	/**
	 * The AutomataSimulator constructor when there are two passed files to be read
	 * @param initFileName the initial file to construct our grid 
	 * @param configFileName the configuration file to customize certain constraints 
	 */
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
			Configs.initConfigs(colors, starve, breed);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException();
		} 
	}

	/**
	 * Step method for a single step of automata simulation.
	 */
	public void step() {
		//make a animal array out of simpleSystem and enable each of its members
		Animal[][] creature = this.simpleSystem.getMap();
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if (creature[j][i] != null && creature[j][i].isAlive()) {
					creature[j][i].enable();
				}
			}
		}
		//go through and have each animal in the grid act.  Burying the dead along the way
		for (int i = 0; i < creature.length; i++) {
			for (int j = 0; j < creature.length; j++) {
				if (creature[j][i] != null && creature[j][i].isAlive()) {
					creature[j][i].act(new Location(j, i), this.simpleSystem);
				}
			}
		} 
		//do a third grid traversal and clear out dead animals that haven't already been eaten
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				this.simpleSystem.buryTheDead();
			}
		}
		
	}

	/**
	 * A PaintedLocation[][] method to return our "view" of the grid (a 2D paintedlocation array)
	 */
	public PaintedLocation[][] getView() {
		PaintedLocation[][] view = new PaintedLocation[SIZE][SIZE];

		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if (this.simpleSystem.getItemAt(new Location(j, i)) == null) {
					view[j][i] = new PaintedLocation(j, i, Color.black, ' ');
				}
				else if (this.simpleSystem.getItemAt(new Location(j, i)).getSymbol() == this.symbol[0]) {
					view[j][i] = new PaintedLocation(j, i, Configs.getPredatorColor(), this.symbol[0]);
				}
				else if (this.simpleSystem.getItemAt(new Location(j, i)).getSymbol() == this.symbol[1]) {
					view[j][i] = new PaintedLocation(j, i, Configs.getMiddleColor(), this.symbol[1]);
				}
				else if (this.simpleSystem.getItemAt(new Location(j, i)).getSymbol() == this.symbol[2]) {
					view[j][i] = new PaintedLocation(j, i, Configs.getMiddleColor(), this.symbol[2]);
				}
				else if (this.simpleSystem.getItemAt(new Location(j, i)).getSymbol() == this.symbol[3]) {
					view[j][i] = new PaintedLocation(j, i, Configs.getPreyColor(), this.symbol[3]);
				}
			}
		}
		return view;
	}

	/**
	 * The getNames method (a method for returning the names of animals in the ecosystem as a string array)
	 */
	public String[] getNames() {
		return names;
	}

}
 