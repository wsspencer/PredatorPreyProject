package edu.ncsu.csc216.simulation.actor;

import java.awt.Color;

/** 
 * This is a class representation of our ecosystem's configurations.
 * @author Scott Spencer
 *
 */
public class Configs { 
	
	/**
	 * An integer array instance variable storing our default food chain ranks.
	 */
	private static final int[] DEFAULT_FOOD_CHAIN_RANK = {0, 10, 20};
	
	/**
	 * A color array instance variable for storing the default colors.
	 */
	private static final Color[] DEFAULT_COLORS = {Color.green, Color.orange, Color.red};

	/**
	 * A color array instance variable for storing the non-default colors.
	 */
	private static Color[] PLAYER_COLORS = {};

	/**
	 * An integer array instance variable for storing the default starve times.
	 */
	private static final int[] DEFAULT_STARVE_TIME = {10, 6, 5};

	/**
	 * An integer array instance variable for storing the non-default starve times.
	 */
	private static int[] STARVE_TIME = {};

	/**
	 * An integer array instance variable for storing the default breed times. 
	 */
	private static final int[] DEFAULT_BREED_TIME = {1, 7, 15};

	/**
	 * An integer array instance variable for storing the non-default breed times.
	 */
	private static int[] BREED_TIME = {}; 
	
	/**
	 * A voided method for initiating custom ecosystem configurations.
	 * @param c a color array for our configuration colors
	 * @param s an integer array for our starve times
	 * @param b an integer array for our breed times
	 */
	public static void initConfigs(Color[] c, int[] s, int[] b) {
		if (c != null && s != null && b != null) {
			PLAYER_COLORS = c;
			STARVE_TIME = s; 
			BREED_TIME = b;
		}
		else {
			setToDefaults();
		}
		 
	}
	
	/**
	 * A voided method for setting the color array, starve time array, and breed time array to defaults.
	 */
	public static void setToDefaults() {
		PLAYER_COLORS = DEFAULT_COLORS;
		STARVE_TIME = DEFAULT_STARVE_TIME;
		BREED_TIME = DEFAULT_BREED_TIME;
	}
	
	/**
	 * A Color method for getting the PurePrey's color
	 * @return Color of the purePrey
	 */
	public static Color getPreyColor() {
		return PLAYER_COLORS[0];
	}
	
	/**
	 * A Color method for getting the PredatorPrey's color
	 * @return Color of the PredatorPrey
	 */
	public static Color getMiddleColor() {
		return PLAYER_COLORS[1];
	}
	
	/**
	 * A Color method for getting the PurePredator's color
	 * @return Color of the PurePrey
	 */
	public static Color getPredatorColor() {
		return PLAYER_COLORS[2];
	}
	
	/**
	 * An integer getter method for the PurePrey's food chain rank
	 * @return integer of the PurePrey's food chain rank
	 */
	public static int getPreyFoodChainRank() {
		return DEFAULT_FOOD_CHAIN_RANK[0];
	}
	
	/**
	 * An integer getter method for the PredatorPrey's food chain rank
	 * @return integer of PredatorPrey's food chain rank
	 */
	public static int getMiddleFoodChainRank() {
		return DEFAULT_FOOD_CHAIN_RANK[1];
	}
	
	/**
	 * An integer getter method for the PurePredator's food chain rank
	 * @return integer of PurePredator's food chain rank
	 */
	public static int getPredatorFoodChainRank() {
		return DEFAULT_FOOD_CHAIN_RANK[2];
	}
	
	/**
	 * An integer getter method for the PurePrey's starve time
	 * @return integer of Prey's starve time.
	 */
	public static int getPreyStarveTime() {
		return STARVE_TIME[0];
	}
	
	/**
	 * An integer getter method for PredatorPrey's starve time
	 * @return integer of PredatorPrey's starve time
	 */
	public static int getMiddleStarveTime() {
		return STARVE_TIME[1];
	}
	
	/**
	 * An integer getter method for PurePredator's starve time
	 * @return integer of PurePredator's starve time
	 */
	public static int getPredatorStarveTime() {
		return STARVE_TIME[2];
	}
	
	/**
	 * An integer getter method for PurePrey's breed time
	 * @return integer of PurePrey's breed time
	 */
	public static int getPreyBreedTime() {
		return BREED_TIME[0];
	}
	
	/**
	 * An integer getter method for PredatorPrey's breed time
	 * @return integer of PredatorPrey's breed time
	 */
	public static int getMiddleBreedTime() {
		return BREED_TIME[1];
	}
	
	/**
	 * An integer getter method for PurePredator's breed time
	 * @return integer of PurePredator's breed time
	 */
	public static int getPredatorBreedTime() {
		return BREED_TIME[2];
	}
}
