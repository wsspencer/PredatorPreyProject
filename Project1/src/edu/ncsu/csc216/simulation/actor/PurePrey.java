package edu.ncsu.csc216.simulation.actor;

import java.awt.Color;

import edu.ncsu.csc216.simulation.environment.EcoGrid;
import edu.ncsu.csc216.simulation.environment.utils.Location;

public class PurePrey extends Animal {
	
	private int age = 0;
	
	private int timeSinceLastBreed = 0;
	
	private boolean breedSuccess = false;

	public PurePrey(char symbol) { 
		super(symbol);
	}

	@Override
	public Color getColor() {
		return Configs.getPreyColor();
	}
 
	@Override 
	public void act(Location position, EcoGrid positionFacts) {
		breedSuccess = false; 
		
		//checks that the animal is a baby
		if (this.age == 0) {
			this.disable();
		}
		
		//checks that the animal can act 
		// Checks that the animal has not bred in the passed two turns, attempts
		//to breed if not.
		if (this.pastBreedTime(timeSinceLastBreed) && this.canAct()) {
			if (this.breed(position, positionFacts) == true) {
				this.disable(); 
				this.breedSuccess = true;
			}
			this.disable();
			
		}
			
		// Checks if the animal has bred in the passed two turns.  If it has, attempts
		// to move.
		else if (this.isAlive() && this.canAct()) {
			this.move(position, positionFacts);
			this.disable();
		}		
		
		//Checks if the animal has bred this turn, increments time since last
		//breed if not
		if (!this.breedSuccess) {
			this.incrementTimeSinceLastBreed();
		}	
			
		//increment age before checking if it should die
		age++;
		//Checks if the animal is going to die of old age, kills it if so.
		if (this.age >= Configs.getPreyStarveTime()) {
			this.die();
		}
		
		this.disable();
	}

	@Override
	protected boolean pastBreedTime(int timeSinceLastBreed) {
		if (timeSinceLastBreed >= Configs.getPreyBreedTime()) {
			return true;
		}
		return false;
	}

	@Override
	protected Animal makeNewBaby() {
		return new PurePrey(this.getSymbol());
	}

	@Override
	protected int getFoodChainRank() {
		return Configs.getPreyFoodChainRank();
	}

}
