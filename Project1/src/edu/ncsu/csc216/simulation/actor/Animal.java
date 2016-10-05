package edu.ncsu.csc216.simulation.actor;

import java.awt.Color;
import java.util.Random;

public abstract class Animal {
	private int timeSinceLastMeal;
	private int timeSinceLastBreed;
	private boolean canActThisStep;
	private char symbol;
	private boolean alive;
	private static int seed;
	private static Random randomGenerator;
	
	public Animal(char symbol) {
	
	}
	
	public static void setRandomSeed(int maxBound) {
		
	}
	
	public char getSymbol() {
		
	}
	
	public boolean isAlive() {
		
	}
	
	public void enable() {
		
	}
	
	public void disable() {
		
	}
	
	protected void die() {
		
	}
	
	protected boolean canAct() {
		
	}
	
	protected int getTimeSinceLastBreed() {
		
	}
	
	protected int getTimeSinceLastMeal() {
		
	}
	
	protected void incrementTimeSinceLastMeal() {
		
	}
	
	protected void incrementTimeSinceLastBreed() {
		
	}
	
	protected boolean breed(Location position, EcoGrid positionFacts) {
		
	}
	
	protected void move(Lovation position, EcoGrid positionFacts) {
		
	}
	
	protected boolean eat(Location position, EcoGrid positionFacts) {
		
	}
	
	public abstract Color getColor();
	
	public abstract void act(Location position, EcoGrid positionFacts);
	
	protected abstract boolean pastBreedTime(int timeSinceLastBreed);
	
	protected abstract Animal makeNewBaby() ;
	
	protected abstract int getFoodChainRank(); 
}












