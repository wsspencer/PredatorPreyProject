package edu.ncsu.csc216.simulation.actor;

import java.awt.Color;

import edu.ncsu.csc216.simulation.environment.EcoGrid;
import edu.ncsu.csc216.simulation.environment.utils.Location;

public class PurePrey extends Animal {
	
	private int age = 0;
	
	private int ageAtLastBreed = 0;
	
	private int timeSinceLastBreed = 0;

	public PurePrey(char symbol) {
		super(symbol);
	}

	@Override
	public Color getColor() {
		return Configs.getPreyColor();
	}
 
	@Override
	public void act(Location position, EcoGrid positionFacts) {
		//checks that the animal can act
		// Checks that the animal has not bred in the passed two turns, attempts
		//to breed if not.
		if (this.pastBreedTime(timeSinceLastBreed)) {
			this.breed(position, positionFacts);
			this.disable();
		}
			
		// Checks if the animal has bred in the passed two turns.  If it has, attempts
		// to move.
		else if (this.isAlive() && this.canAct()) {
			this.move(position, positionFacts);
			this.incrementTimeSinceLastBreed();
			this.disable();
		}		
			
		//Checks if the animal is going to die of old age, kills it if so.
		if (this.age >= Configs.getPreyStarveTime() && this.canAct()) {
			this.die();
		}
		
		this.disable();
		age++;
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
