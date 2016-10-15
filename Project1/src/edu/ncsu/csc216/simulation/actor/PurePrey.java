package edu.ncsu.csc216.simulation.actor;

import java.awt.Color;

import edu.ncsu.csc216.simulation.environment.EcoGrid;
import edu.ncsu.csc216.simulation.environment.utils.Location;

public class PurePrey extends Animal {
	
	private int age;

	public PurePrey(char symbol) {
		super(symbol);
	}

	@Override
	public Color getColor() {
		return Configs.getPreyColor();
	}

	@Override
	public void act(Location position, EcoGrid positionFacts) {

	}

	@Override
	protected boolean pastBreedTime(int timeSinceLastBreed) {
		if (timeSinceLastBreed > Configs.getPreyBreedTime()) {
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
