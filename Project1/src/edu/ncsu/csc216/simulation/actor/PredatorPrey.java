package edu.ncsu.csc216.simulation.actor;

import java.awt.Color;

import edu.ncsu.csc216.simulation.environment.EcoGrid;
import edu.ncsu.csc216.simulation.environment.utils.Location;

public class PredatorPrey extends Animal {

	public PredatorPrey(char symbol) {
		super(symbol);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void act(Location position, EcoGrid positionFacts) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean pastBreedTime(int timeSinceLastBreed) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected Animal makeNewBaby() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected int getFoodChainRank() {
		// TODO Auto-generated method stub
		return 0;
	}

}
