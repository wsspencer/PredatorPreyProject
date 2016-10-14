package edu.ncsu.csc216.simulation.actor;

import java.awt.Color;

public class Configs {
	private static final int[] DEFAULT_FOOD_CHAIN_RANK = {0, 10, 20};
	private static final Color[] DEFAULT_COLORS = {Color.green, Color.orange, Color.red};
	private static Color[] PLAYER_COLORS = {};
	private static final int[] DEFAULT_STARVE_TIME = {10, 6, 5};
	private static int[] STARVE_TIME = {};
	private static final int[] DEFAULT_BREED_TIME = {1, 7, 15};
	private static int[] BREED_TIME = {};
	
	public Configs() {
		
	}
	
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
	
	public static void setToDefaults() {
		PLAYER_COLORS = DEFAULT_COLORS;
		STARVE_TIME = DEFAULT_STARVE_TIME;
		BREED_TIME = DEFAULT_BREED_TIME;
	}
	
	public static Color getPreyColor() {
		return PLAYER_COLORS[0];
	}
	
	public static Color getMiddleColor() {
		return PLAYER_COLORS[1];
	}
	
	public static Color getPredatorColor() {
		return PLAYER_COLORS[2];
	}
	
	public static int getPreyFoodChainRank() {
		return DEFAULT_FOOD_CHAIN_RANK[0];
	}
	
	public static int getMiddleFoodChainRank() {
		return DEFAULT_FOOD_CHAIN_RANK[1];
	}
	
	public static int getPredatorFoodChainRank() {
		return DEFAULT_FOOD_CHAIN_RANK[2];
	}
	
	public static int getPreyStarveTime() {
		return STARVE_TIME[0];
	}
	
	public static int getMiddleStarveTime() {
		return STARVE_TIME[1];
	}
	
	public static int getPredatorStarveTime() {
		return STARVE_TIME[2];
	}
	
	public static int getPreyBreedTime() {
		return BREED_TIME[0];
	}
	
	public static int getMiddleBreedTime() {
		return BREED_TIME[1];
	}
	
	public static int getPredatorBreedTime() {
		return BREED_TIME[2];
	}
}
