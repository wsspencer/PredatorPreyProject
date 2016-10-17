package edu.ncsu.csc216.simulation.actor;

import java.awt.Color;
import java.util.Random;

import edu.ncsu.csc216.simulation.environment.EcoGrid;
import edu.ncsu.csc216.simulation.environment.utils.Location;

/**
 * This is the parent class of the different classes of animals which represents an abstract class of their shi
 * 
 * @author Scott Spencer
 * 
 */
public abstract class Animal {  
	/**
	 * This is an integer instance variable representing the time since our animal ate last
	 */
	private int timeSinceLastMeal = 0;
	/**
	 * This is an integer instance variable representing the time since our animal last reproduced
	 */
	private int timeSinceLastBreed = 0;  
	/**
	 * This is a boolean instance variable representing whether or not our animal can act during this step.
	 */
	private boolean canActThisStep = false;
	/**
	 * This is a boolean instance variable representing the symbol of our animal
	 */
	private char symbol;
	/** 
	 * This is a boolean instance variable representing whether or not our animal is alive
	 */
	private boolean alive = true;
	/**
	 * This is an integer instance variable representing whether or random seed.
	 */
	private static int seed; 
	/**
	 * This is a random instance variable representing our random object for randomizing our seed.
	 */
	private static Random randomGenerator = new Random();
	
	/**
	 * This is our animal's constructor method
	 * @param symbol the symbol our animal will use on the grid.
	 */
	public Animal(char symbol) { 
		this.symbol = symbol; 
	}
	
	/**
	 * This is a method for setting our animals random seed used for randomizing direction.
	 * @param maxBound the integer we want to be the maximum bound of the random generator.
	 */
	public static void setRandomSeed(int maxBound) {
		//update the random
		randomGenerator = new Random();
		setSeed(randomGenerator.nextInt(maxBound));
	}
	
	/**
	 * This is a getter method to return the instance variabl of our animal's symbol
	 * @return char the symbol of our animal
	 */
	public char getSymbol() {
		return symbol;
	}
	
	/**
	 * This is a boolean method for returning the instance variable of whether or not our animal is alive
	 * @return boolean the status of our animals life.
	 */
	public boolean isAlive() {
		return alive;
	}
	
	/**
	 * This is a void method for enabling (making sure our animal can act) our animal
	 */
	public void enable() {
		canActThisStep = true;
	}
	
	/**
	 * This is a voided method for making sure our animal is disabled (can't act on a step).
	 */
	public void disable() {
		canActThisStep = false;
	}
	
	/**
	 * This is a voided method for "killing off" our animal
	 */
	protected void die() {
		this.disable();
		this.alive = false;
	}
	
	/**
	 * This is a voided method for returning whether or not our animal can act on this step.
	 * @return boolean of whether or not our animal can act
	 */
	protected boolean canAct() {
		return canActThisStep;
	}
	
	/**
	 * This is an integer method for getting the timeSinceLastBreed instance variable which returns
	 * the amount of steps since our animal last bred.
	 * @return int the time since our animal last reproduced.
	 */
	protected int getTimeSinceLastBreed() {
		return timeSinceLastBreed;
	}
	
	/**
	 * This is an integer method for getting the time since our animal last ate
	 * @return int the instance variable storing the amount of steps since our animal last ate.
	 */
	protected int getTimeSinceLastMeal() {
		return timeSinceLastMeal;
	} 
	
	/**
	 * This is a voided method for incrementing the instance variable holding amount of steps since 
	 * our animal last ate.
	 */
	protected void incrementTimeSinceLastMeal() {
		timeSinceLastMeal++;
	}
	
	/**
	 * This is a voided methodfor incrementing the instance variable holding amount of steps since
	 * our animal last ate.
	 */
	protected void incrementTimeSinceLastBreed() { 
		timeSinceLastBreed++;
	} 
	
	/**
	 * This is a boolean method meant to test our animals breeding and return whether or not it was a success
	 * @param position the position holding our animal's prospective mate.
	 * @param positionFacts the ecosystem grid.
	 * @return boolean of whether or not the animal will breed
	 */
	protected boolean breed(Location position, EcoGrid positionFacts) {
		if (positionFacts.getItemAt(position).getSymbol() == this.symbol) {
			if (pastBreedTime(getTimeSinceLastBreed())) {
				this.timeSinceLastBreed = 0;
				positionFacts.add(makeNewBaby(), positionFacts.findFirstEmptyNeighbor(position, 0));
				return true;
			}
			incrementTimeSinceLastBreed();
			return false;
		}
		else {
			incrementTimeSinceLastBreed();
			return false;
		}
	}

	/**
	 * This is the voided method that moves our animal in one step increments across our grid
	 * @param position the prospective new position of our animal
	 * @param positionFacts the grid our animal is located in
	 */
	protected void move(Location position, EcoGrid positionFacts) {
		int direction = getSeed();
		
		if (direction == 0) {
			//go west
			positionFacts.remove(position);
			position = positionFacts.dueWest(position);
			if (positionFacts.isEmpty(position)) {
				positionFacts.add(this, position);
			}
		}
		if (direction == 1) {
			//go north
			positionFacts.remove(position);
			position = positionFacts.dueNorth(position);
			if (positionFacts.isEmpty(position)) {
				positionFacts.add(this, position);
			}
		}
		if (direction == 2) {
			//go east
			positionFacts.remove(position);
			position = positionFacts.dueEast(position);
			if (positionFacts.isEmpty(position)) {
				positionFacts.add(this, position);				
			}
		}
		if (direction == 3) {
			//go south
			positionFacts.remove(position);
			position = positionFacts.dueSouth(position);
			if (positionFacts.isEmpty(position)) {
				positionFacts.add(this, position);	
			}
		}
	}
	
	/**
	 * This is the boolean method meant to test and return whether or not our animal will eat.
	 * @param position the location of the animal our animal may eat
	 * @param positionFacts the grid the two animals are located on.
	 * @return boolean the method meant to return whether or not our animal eats.
	 */
	protected boolean eat(Location position, EcoGrid positionFacts) {
		if (this.canActThisStep && positionFacts.getItemAt(position).getFoodChainRank() < this.getFoodChainRank()) {
			this.timeSinceLastMeal = 0;
			positionFacts.remove(position);
			this.move(position, positionFacts);
			return true;
		}
		incrementTimeSinceLastMeal();
		return false;
	}
	
	/**
	 * Abstracted method to ensure our animal has a getter method for their color.
	 * @return Color the color of our animal
	 */
	public abstract Color getColor();
	
	/**
	 * Abstracted method to ensure our animal has a method for their class-specific actions
	 * @param position Location that our animal will be acting on
	 * @param positionFacts our grid storing the animals
	 */
	public abstract void act(Location position, EcoGrid positionFacts);
	
	/**
	 * Abstracted method for ensuring our animal has a class-specific method for finding whether it is 
	 * "past" our animals breed time.
	 * @param timeSinceLastBreed integer for the time since our animal last bred.
	 * @return boolean representing whether or not it is "past" our animals breed time
	 */
	protected abstract boolean pastBreedTime(int timeSinceLastBreed);
	
	/**
	 * Abstracted method for ensuring our animal has a class-specific method for our animal's new babies
	 * @return Animal our animal's offspring
	 */
	protected abstract Animal makeNewBaby() ;
	
	/**
	 * Abstracted method for returning the integer of our animal's food chain rank.
	 * @return int an integer of our animal's food chain rank.
	 */
	protected abstract int getFoodChainRank();

	/**
	 * Static method for returning our randomized seed.
	 * @return int an integer of our random seed
	 */
	public static int getSeed() {
		return seed;
	}

	/**
	 * Voided method meant for setting the random seed we will use.
	 * @param seed an int representation of our random seed.
	 */
	public static void setSeed(int seed) {
		Animal.seed = seed;
	}


}












