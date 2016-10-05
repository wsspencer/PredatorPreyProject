package edu.ncsu.csc216.simulation.actor;

import java.awt.Color;

public class Configs {
	private static final int[] DEFAULT_FOOD_CHAIN_RANK = {};
	private static final Color[] DEFAULT_COLORS = {};
	private static Color[] PLAYER_COLORS = {};
	private static final int[] DEFAULT_STARVE_TIME = {};
	private static int[] STARVE_TIME = {};
	private static final int[] DEFAULT_BREED_TIME = {};
	private static int[] BREED_TIME = {};
	
	public Configs() {
		
	}
	
	public static void initConfigs(Color[] playerColors, int[] starveTime, int[] breedTime) {
		if (playerColors != null && starveTime != null && breedTime != null) {
			PLAYER_COLORS = playerColors;
			STARVE_TIME = starveTime;
			BREED_TIME = breedTime;
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
		return PLAYER_COLORS[getPreyFoodChainRank()];
	}
	
	public static Color getMiddleColor() {
		return PLAYER_COLORS[getMiddleFoodChainRank()];
	}
	
	public static Color getPredatorColor() {
		return PLAYER_COLORS[getPredatorFoodChainRank()];
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
		return STARVE_TIME[getPreyFoodChainRank()];
	}
	
	public static int getMiddleStarveTime() {
		return STARVE_TIME[getMiddleFoodChainRank()];
	}
	
	public static int getPredatorStarveTime() {
		return STARVE_TIME[getPredatorFoodChainRank()];
	}
	
	public static int getPreyBreedTime() {
		return BREED_TIME[getPreyFoodChainRank()];
	}
	
	public static int getMiddleBreedTime() {
		return BREED_TIME[getMiddleFoodChainRank()];
	}
	
	public static int getPredatorBreedTime() {
		return BREED_TIME[getPredatorFoodChainRank()];
	}
}
