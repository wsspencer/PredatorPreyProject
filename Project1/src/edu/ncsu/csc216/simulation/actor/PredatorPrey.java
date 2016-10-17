package edu.ncsu.csc216.simulation.actor;

import java.awt.Color;

import edu.ncsu.csc216.simulation.environment.EcoGrid;
import edu.ncsu.csc216.simulation.environment.utils.Location;

/**
 * A class to outline the specific behaviors of animals in the middle of the food chain
 * @author Scott Spencer 
 *
 */
public class PredatorPrey extends Animal {
	 
	/**
	 * A boolean instance variable for whether the animal has bred in a step.
	 */
	private boolean bred = false;
	
	/**
	 * A boolean instance variable for whether the animal has eaten in a step.
	 */
	private boolean ate = false;
	
	/**
	 * An integer instance variable for our animal's age.
	 */
	private int age = 0;
	
	/**
	 * An integer instance variable for the time since our animal last bred.
	 */
	private int timeSinceLastBreed = 0;
	
	/**
	 * An integer instance variable for the time since our animal last ate.
	 */
	private int timeSinceLastAte = 0;

	/**
	 * Constructor method for an animal in the middle of the food chain
	 * @param symbol char of our animal's symbol
	 */
	public PredatorPrey(char symbol) { 
		super(symbol);
	}
 
	/**
	 * Getter method for a middle animal's color on the grid.
	 */
	@Override
	public Color getColor() {
		return Configs.getMiddleColor();
	}

	/**
	 * Voided method for our animal's actions
	 */
	@Override
	public void act(Location position, EcoGrid positionFacts) {
		bred = false;
		ate = false;
		
		//checks if this is a baby and disables it if so
		if (this.age == 0) {
			this.disable();
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
		
		//checks that the animal can act
		//animal attempts to eat
		if (this.canAct() && this.isAlive() && this.getFoodChainRank() > 
				positionFacts.getItemAt(position).getFoodChainRank()) {
			this.eat(position, positionFacts);
			this.disable();
			ate = true;
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
		if (!this.bred && age != 0) {
			this.incrementTimeSinceLastBreed();
		}
		
		//Checks if the animal has eaten this turn, increments time since last
		//the animal last ate
		if (!this.ate) {
			this.incrementTimeSinceLastMeal();
		}
		
		this.disable();		
		age++;
	}

	/**
	 * Boolean method for checking whether or not it is "past" our animal's breed time.
	 */
	@Override
	protected boolean pastBreedTime(int timeSinceLastBreed) {
		if (timeSinceLastBreed >= Configs.getMiddleBreedTime()) {
			return true;
		}
		
		return false;
	}

	/**
	 * Animal method for making a new baby 
	 */
	@Override
	protected Animal makeNewBaby() {
		return new PredatorPrey(this.getSymbol());
	}

	/**
	 * Integer method for getting our animal's food chain rank
	 */
	@Override
	protected int getFoodChainRank() {
		return Configs.getMiddleFoodChainRank();
	}

}
