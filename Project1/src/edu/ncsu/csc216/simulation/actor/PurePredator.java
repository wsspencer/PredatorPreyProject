 package edu.ncsu.csc216.simulation.actor;

import java.awt.Color;

import edu.ncsu.csc216.simulation.environment.EcoGrid;
import edu.ncsu.csc216.simulation.environment.utils.Location;
 
/**
 * Class for representation of an animal on the top of the food chain
 * @author Scott Spencer
 *
 */
public class PurePredator extends Animal {
	
	/**
	 * A boolean instance variable for whether our animal has bred in a turn
	 */
	private boolean bred = false;
	
	/** 
	 * A boolean instance variable for whether or not our animal has eaten in a turn
	 */
	private boolean ate = false;
	
	/**
	 * An integer instance variable for the time since our animal last bred
	 */
	private int timeSinceLastBreed = 0;
	
	/**
	 * An integer instance variable for the time since our animal last ate
	 */
	private int timeSinceLastAte = 0;
	
	/**
	 * An integer instance variable for the age of our animal
	 */
	private int age = 0;

	/**
	 * A constructor method for an animal at the top of the food chain
	 * @param symbol char representing our animal's symbol
	 */
	public PurePredator(char symbol) {
		super(symbol);
	}  
	
	/**
	 * A getter method for returning this animal's color
	 */
	@Override
	public Color getColor() {
		return Configs.getPredatorColor();
	}

	/**
	 * A voided method for how an animal acts
	 */
	@Override 
	public void act(Location position, EcoGrid positionFacts) {
		bred = false;
		ate = false;
		
		//if the animal is a baby, it is disabled)
		if (this.age == 0) {
			this.disable();
		}
		
		//checks that the animal can act
		//animal attempts to eat 
		if (this.canAct() && this.isAlive() && this.getFoodChainRank() > 
				positionFacts.getItemAt(position).getFoodChainRank()) {
			this.eat(position, positionFacts);
			this.disable();
			ate = true;
		}
		
		//checks that the animal can act 
		// Checks that the animal has not bred in the passed two turns, attempts
		//to breed if not.
		if (this.pastBreedTime(timeSinceLastBreed) && this.canAct() && this.isAlive()
				&& this.getSymbol() == positionFacts.getItemAt(position).getSymbol()) {
			this.breed(position, positionFacts);
			this.disable();
			bred = true;
		} 
			
		// Checks if the animal has bred in the passed two turns.  If it has, attempts
		// to move.
		if (this.isAlive() && this.canAct() && !this.bred && !this.ate) {
			this.move(position, positionFacts);
			this.disable();
		}		
			
		//Checks if the animal is going to die of starvation, kills it if so.
		if (this.timeSinceLastAte >= Configs.getPredatorStarveTime()) {
			this.die();
		}
		
		//Checks if the animal has bred this turn, increments time since last
		//breed if not
		if (!this.bred) {
			this.incrementTimeSinceLastBreed();
		}
		
		//Checks if the animal has eaten this turn, increments time since last
		//the animal last ate
		if (!this.ate) {
			this.incrementTimeSinceLastMeal();
		}
		
		this.disable();		
		this.age++;
	}

	/**
	 * A boolean method for whether it is past this animal's breed time
	 */
	@Override
	protected boolean pastBreedTime(int timeSinceLastBreed) {
		if (timeSinceLastBreed >= Configs.getPredatorBreedTime()) {
			return true;
		}
		return false;
	}

	/**
	 * An animal method for whether or not a new baby should be made
	 */
	@Override
	protected Animal makeNewBaby() {
		return new PurePredator(this.getSymbol());
	}

	/**
	 * An integer getter method for retrieving an  animal's food chain rank
	 */
	@Override
	protected int getFoodChainRank() {
		return Configs.getPredatorFoodChainRank();
	}

}
