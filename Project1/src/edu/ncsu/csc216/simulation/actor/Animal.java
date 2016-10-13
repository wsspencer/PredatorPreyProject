package edu.ncsu.csc216.simulation.actor;

import java.awt.Color;
import java.util.Random;

import edu.ncsu.csc216.simulation.environment.EcoGrid;
import edu.ncsu.csc216.simulation.environment.utils.Location;

public abstract class Animal {
	private int timeSinceLastMeal = 0;
	private int timeSinceLastBreed = 0;
	private boolean canActThisStep;
	private char symbol;
	private boolean alive;
	private static int seed;
	private static Random randomGenerator;
	
	public Animal(char symbol) {
		this.symbol = symbol;
	}
	
	public static void setRandomSeed(int maxBound) {
		
	}
	
	public char getSymbol() {
		return this.symbol;
	}
	
	public boolean isAlive() {
		return this.alive;
	}
	
	public void enable() {
		
	}
	
	public void disable() {
		
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
	
	protected boolean breed(Location position, EcoGrid positionFacts) {
		if (positionFacts.getItemAt(position).symbol == this.symbol) {
			if (pastBreedTime(getTimeSinceLastBreed())) {
				timeSinceLastBreed = 0;
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
		}
		if (direction == 1) {
			//go north
		}
		if (direction == 2) {
			//go east
		}
		if (direction == 3) {
			//go south
		}
	}
	
	protected boolean eat(Location position, EcoGrid positionFacts) {
		return false;
	}
	
	public abstract Color getColor();
	
	public abstract void act(Location position, EcoGrid positionFacts);
	
	protected abstract boolean pastBreedTime(int timeSinceLastBreed);
	
	protected abstract Animal makeNewBaby() ;
	
	protected abstract int getFoodChainRank();


}












