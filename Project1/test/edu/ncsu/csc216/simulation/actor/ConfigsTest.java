/**
 * 
 */
package edu.ncsu.csc216.simulation.actor;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

/**
 * A test class for testing the Configs java class
 * @author Scott Spencer
 *
 */
public class ConfigsTest {

	/**
	 * A test method for setting the configs to their default values
	 */
	@SuppressWarnings("static-access")
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

	/**
	 * Test method for testing the method meant to set custom configurations
	 */
	@SuppressWarnings("static-access")
	@Test 
	public void testCustomConfig() {
		//create a Config variable, set it to custom values
		Configs custom = new Configs();
		Color[] c = {Color.black, Color.cyan, Color.green};
		int[] s = {5, 3, 1};
		int[] b = {2, 4, 7};
		
		//set config variable to custom values for breed/starve time and colors
		custom.initConfigs(c, s, b);
		
		//test that custom colors are set
		assertEquals(custom.getPreyColor(), Color.black);
		assertEquals(custom.getMiddleColor(), Color.cyan);
		assertEquals(custom.getPredatorColor(), Color.green);
		
		//test that custom breed times are set
		assertEquals(custom.getPreyBreedTime(), 2);
		assertEquals(custom.getMiddleBreedTime(), 4);
		assertEquals(custom.getPredatorBreedTime(), 7);
		
		//test that default food chain ranks are set (despite initiating with custom other values)
		assertEquals(custom.getPreyFoodChainRank(), 0);
		assertEquals(custom.getMiddleFoodChainRank(), 10);
		assertEquals(custom.getPredatorFoodChainRank(), 20);
		
		//test that custom starve times are set
		assertEquals(custom.getPreyStarveTime(), 5);
		assertEquals(custom.getMiddleStarveTime(), 3);
		assertEquals(custom.getPredatorStarveTime(), 1);
	}
}
