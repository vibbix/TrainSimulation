package edu.wit.comp2000.group23.application3.Tests;

import org.junit.Test;
/**
 * JUnit test for Passenger
 * @author wongt1
 *
 */
public class PassengerTest {
	
	
	/**
	 * test if the passenger's train is equal to the train 
	 */
	@Test
	public void embarkTrain() throws Exception {
		Passenger p = new Passenger();
		p.setTrain(t);
		Assert.assertEquals(p.getTrain, t);
	}
	
	/**
	 * test toString for Passenger class
	 */
	@Test
	public void toStringTest() throws Exception{
		Passenger p = new Passenger();
		String string = "Passenger info: ";
		string += "\nDestination: " + getDestination();
		string += "\nCurrent Station: " + getCurrentStation();
		string += "\nPlatform: " + getCurrentPlatform();
		string += "\nOn train: " + onTrain();
		Assert.assertEquals(string, p.toString());
	}

}
