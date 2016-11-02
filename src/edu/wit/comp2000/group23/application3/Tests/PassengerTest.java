package edu.wit.comp2000.group23.application3.Tests;

import org.junit.Test;

import edu.wit.comp2000.group23.application3.Direction;
import edu.wit.comp2000.group23.application3.Passenger;
import edu.wit.comp2000.group23.application3.Platform;
import edu.wit.comp2000.group23.application3.Station;
import edu.wit.comp2000.group23.application3.Train;
import edu.wit.comp2000.group23.application3.TrainRoute;
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
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void onTrainTest() throws Exception {
		Logger l = new Logger();
		Passenger p = new Passenger(l, null, null, null, -1);
		Train t = new Train(null, 0, 0, l);
		p.setTrain(t);
		Assert.assertEquals(p.getOnTrain(), true);
	}
	
	/**
	 * test if the passenger's train is equal to the train
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void TrainTest() throws Exception {
		Logger l = new Logger();
		Passenger p = new Passenger(l, null, null, null, -1);
		Train t = new Train(null, 0, 0, l);
		p.setTrain(t);
		Assert.assertEquals(p.getTrain(), t.getId());
	}
	
	/**
	 * test for passenger's platform
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void PlatformTest() throws Exception {
		Logger l = new Logger();
		Passenger p = new Passenger(l, null, null, null, -1);
		TrainRoute tr = new TrainRoute(l, 1);
		Station s = new Station(l, tr, 1);
		Platform pf = new Platform(l, Direction.Inbound, s, 1);
		p.setPlatform(pf);
		Assert.assertEquals(p.getPlatform(), pf);
	}
	/**
	 * tests if the passenger is in the station (using station id)
	 * uncomment s.getId()
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void StationTest() throws Exception {
		Logger l = new Logger();
		Passenger p = new Passenger(l, null, null, null, -1);
		TrainRoute tr = new TrainRoute(l, 1);
		Station s = new Station(l, tr, 1);
		p.setStation(s);
		//Assert.assertEquals(p.getStation(), s.getId());
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
		string += "\nPassenger ID: " + p.getPassengerID();
		string += "\nDestination: " + p.getDestination();
		string += "\nCurrent Station: " + p.getCurrentStation();
		string += "\nPlatform: " + p.getCurrentPlatform();
		string += "\nOn train: " + p.getOnTrain();
		Assert.assertEquals(string, p.toString());
	}
}