package edu.wit.comp2000.group23.application3.Tests;

import org.junit.Test;

import edu.wit.comp2000.group23.application3.Passenger;
import edu.wit.comp2000.group23.application3.Train;
import edu.wit.comp2000.group23.application3.Utilities.Logger;
import junit.framework.Assert;
/**
 * JUnit test for Passenger
 * @author wongt1
 *
 */
public class PassengerTest {
	
	
	/**
	 * test if passenger is on train
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void embarkTrainTest() throws Exception {
		Logger l = new Logger();
		Passenger p = new Passenger(l, null, null, null, -1);
		Train t = new Train(null, 0, 0, l);
		p.setTrain(t);
		Assert.assertEquals(p.getOnTrain(), true);
	}
	
	/**
	 * test toString for Passenger class
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void toStringTest() throws Exception{
		Logger l = new Logger();
		Passenger p = new Passenger(l, null, null, null, -1);
		String string = "Passenger info: ";
		string += "\nDestination: " + p.getDestination();
		string += "\nCurrent Station: " + p.getCurrentStation();
		string += "\nPlatform: " + p.getCurrentPlatform();
		string += "\nOn train: " + p.getOnTrain();
		Assert.assertEquals(string, p.toString());
	}

}
