package edu.ncsu.csc216.simulation.actor;

import java.awt.Color;

import edu.ncsu.csc216.simulation.environment.EcoGrid;
import edu.ncsu.csc216.simulation.environment.utils.Location;

/**
 * This is a class outlining the behaviors of an animal at the bottom of the food chain
 * @author Scott Spencer
 *
 */
public class PurePrey extends Animal {
	
	/**
	 * An instance variable for this animal's age
	 */
	private int age = 0;
	
	/**
	 * An instance variable for the time since our animal last bred
	 */
	private int timeSinceLastBreed = 0;
	
	/**
	 * An instance variable for whether our animal's breeding was a success
	 */
	private boolean breedSuccess = false;

	/**
	 * A constructor method for an animal at the bottom of the food chain
	 * @param symbol the char representation of an animal
	 */
	public PurePrey(char symbol) { 
		super(symbol);
	}

	/**
	 * A Color getter method for the color of an animal at the bottom of the food chain
	 */
	@Override
	public Color getColor() {
		return Configs.getPreyColor();
	}
 
	/**
	 * A voided method for outlining our animal's actions
	 */
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
		if (this.pastBreedTime(timeSinceLastBreed) && this.canAct() && 
				this.breed(position, positionFacts) == true) {
			this.disable(); 
			this.breedSuccess = true;
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

	/**
	 * A boolean method for whether it is past our animal's breed time.
	 */
	@Override
	protected boolean pastBreedTime(int timeSinceLastBreed) {
		if (timeSinceLastBreed >= Configs.getPreyBreedTime()) {
			return true;
		}
		return false;
	}

	/**
	 * An animal method for making a new baby of an animal with ours' type
	 */
	@Override
	protected Animal makeNewBaby() {
		return new PurePrey(this.getSymbol());
	}

	/**
	 * An integer getter method for retrieving an animal at the bottom of the food chain's rank
	 */
	@Override
	protected int getFoodChainRank() {
		return Configs.getPreyFoodChainRank();
	}

}
