package edu.wit.comp2000.group23.application3.Tests;

import edu.wit.comp2000.group23.application3.*;
import edu.wit.comp2000.group23.application3.Utilities.Logger;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by kucha on 10/30/2016.
 */
public class StationTest {

    @Test
    public void addArrivingPassengerTest() {
        ArrayList<Passenger> expected = new ArrayList<>();
        Station s = new Station(null, null, 0);
        Platform inboundTest = new Platform(null, null, null, 0);

        for (int i = 0; i < 3; i++) {
            Passenger pass = new Passenger(null, null, null, null, i);
            expected.add(pass);
            s.addArrivingPassenger(pass);
        }
        Assert.assertEquals(expected, s.getArrivedPassengers());
    }
}
