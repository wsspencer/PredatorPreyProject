package edu.ncsu.csc216.simulation.actor;

import java.awt.Color;

import edu.ncsu.csc216.simulation.environment.EcoGrid;
import edu.ncsu.csc216.simulation.environment.utils.Location;

public class PredatorPrey extends Animal {
	
	private boolean bred = false;
	
	private boolean ate = false;
	
	private int age = 0;
	
	private int timeSinceLastBreed = 0;
	
	private int timeSinceLastAte = 0;

	public PredatorPrey(char symbol) { 
		super(symbol);
	}
 
	@Override
	public Color getColor() {
		return Configs.getMiddleColor();
	}

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
		if (!this.bred) {
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

	@Override
	protected boolean pastBreedTime(int timeSinceLastBreed) {
		if (timeSinceLastBreed >= Configs.getMiddleBreedTime()) {
			return true;
		}
		
		return false;
	}

	@Override
	protected Animal makeNewBaby() {
		return new PredatorPrey(this.getSymbol());
	}

	@Override
	protected int getFoodChainRank() {
		return Configs.getMiddleFoodChainRank();
	}

}
