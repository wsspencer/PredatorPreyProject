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
	private int timeSinceLastMeal = 0;
	private int timeSinceLastBreed = 0; 
	private boolean canActThisStep = false;
	private char symbol;
	private boolean alive = true;
	private static int seed; 
	private static Random randomGenerator = new Random();
	
	public Animal(char symbol) {
		this.symbol = symbol; 
	}
	
	public static void setRandomSeed(int maxBound) {
		setSeed(randomGenerator.nextInt(maxBound));
	}
	
	public char getSymbol() {
		return symbol;
	}
	
	public boolean isAlive() {
		return alive;
	}
	
	public void enable() {
		canActThisStep = true;
	}
	
	public void disable() {
		canActThisStep = false;
	}
	
	protected void die() {
		this.disable();
		this.alive = false;
	}
	
	protected boolean canAct() {
		return canActThisStep;
	}
	
	protected int getTimeSinceLastBreed() {
		return timeSinceLastBreed;
	}
	
	protected int getTimeSinceLastMeal() {
		return timeSinceLastMeal;
	}
	
	protected void incrementTimeSinceLastMeal() {
		timeSinceLastMeal++;
	}
	
	protected void incrementTimeSinceLastBreed() {
		timeSinceLastBreed++;
	} 
	
	/**
	 * 
	 * @param position the position 
	 * @param positionFacts the ecosystem grid
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

	protected void move(Location position, EcoGrid positionFacts) {
		int direction = randomGenerator.nextInt(4); //Limits the results to 0, 1, 2, and 3
		
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
	
	protected boolean eat(Location position, EcoGrid positionFacts) {
		if (this.canActThisStep) {
			if (positionFacts.getItemAt(position).getFoodChainRank() < this.getFoodChainRank()) {
				this.timeSinceLastMeal = 0;
				positionFacts.remove(position);
				this.move(position, positionFacts);
				return true;
			}
		}
		incrementTimeSinceLastMeal();
		return false;
	}
	
	public abstract Color getColor();
	
	public abstract void act(Location position, EcoGrid positionFacts);
	
	protected abstract boolean pastBreedTime(int timeSinceLastBreed);
	
	protected abstract Animal makeNewBaby() ;
	
	protected abstract int getFoodChainRank();

	public static int getSeed() {
		return seed;
	}

	public static void setSeed(int seed) {
		Animal.seed = seed;
	}


}












