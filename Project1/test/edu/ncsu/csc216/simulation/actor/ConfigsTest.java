/**
 * 
 */
package edu.ncsu.csc216.simulation.actor;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

/**
 * @author wspencer
 *
 */
public class ConfigsTest {

	@Test
	public void testSetToDefaults() {
		//create a Config variable, set it to default values
		Configs test = new Configs();
		test.setToDefaults();
		
		//test that default colors are set
		assertEquals(test.getPreyColor(), Color.green);
		assertEquals(test.getMiddleColor(), Color.orange);
		assertEquals(test.getPredatorColor(), Color.red);
		
		//test that default breed times are set
		assertEquals(test.getPreyBreedTime(), 1);
		assertEquals(test.getMiddleBreedTime(), 7);
		assertEquals(test.getPredatorBreedTime(), 15);
		
		//test that default food chain ranks are set
		assertEquals(test.getPreyFoodChainRank(), 0);
		assertEquals(test.getMiddleFoodChainRank(), 10);
		assertEquals(test.getPredatorFoodChainRank(), 20);
		
		//test that default starve times are set
		assertEquals(test.getPreyStarveTime(), 10);
		assertEquals(test.getMiddleStarveTime(), 6);
		assertEquals(test.getPredatorStarveTime(), 5);
	}

}
