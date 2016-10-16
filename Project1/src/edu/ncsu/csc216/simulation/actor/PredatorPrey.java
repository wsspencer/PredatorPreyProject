package edu.ncsu.csc216.simulation.actor;

import java.awt.Color;

import edu.ncsu.csc216.simulation.environment.EcoGrid;
import edu.ncsu.csc216.simulation.environment.utils.Location;

public class PredatorPrey extends Animal {

	public PredatorPrey(char symbol) {
		super(symbol);
	}
 
	@Override
	public Color getColor() {
		return Configs.getMiddleColor();
	}

	@Override
	public void act(Location position, EcoGrid positionFacts) {
		//TODO  
		
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
