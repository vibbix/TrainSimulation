import edu.wit.comp2000.group23.application3.*;
import edu.wit.comp2000.group23.application3.Utilities.Logger;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by kucha on 10/30/2016.
 */
public class StationTests {

    @Test
    public void addArrivingPassengerTest() {
        ArrayList<Passenger> expected = new ArrayList<>();
        Station s = new Station(new Logger(), new TrainRoute(new Logger(), 0), 0, "MFA");
        Platform inboundTest = new Platform(null, null, null, 0);

        for (int i = 0; i < 3; i++) {
            Passenger pass = new Passenger(null, null, null, null, i);
            expected.add(pass);
            s.addArrivingPassenger(pass);
        }
        Assert.assertEquals(expected, s.getArrivedPassengers());
    }

    @Test
    public void getPlatformTest() {
        Station s = new Station(new Logger(), new TrainRoute(new Logger(), 0), 0, "MFA");
        Assert.assertEquals(s.getInbound(), s.getPlatform(Direction.Inbound));
        Assert.assertEquals(s.getOutbound(), s.getPlatform(Direction.Outbound));
    }

    @Test
    public void getPlatformsTest() {
        Station s = new Station(new Logger(), new TrainRoute(new Logger(), 0), 0, "MFA");
        ArrayList<Platform> expected = new ArrayList<>();
        expected.add(s.getInbound());
        expected.add(s.getOutbound());
        Assert.assertEquals(expected, s.getPlatforms());
    }

    @Test
    public void getRouteTest() {
        Station currentStation = new Station(new Logger(), new TrainRoute(new Logger(), 0), 0, "MFA");
        Station destinationStation = new Station(new Logger(), new TrainRoute(new Logger(), 0), 1, "Down Town Crossing");
        Platform testPlatform = new Platform(new Logger(), Direction.Inbound, currentStation, 0);
        Passenger p = new Passenger(new Logger(), destinationStation, testPlatform, currentStation, 0);

        Assert.assertEquals(currentStation.getInbound(), currentStation.getRoute(destinationStation));
    }

    @Test
    public void toStringTest() {
        Station s = new Station(new Logger(), new TrainRoute(new Logger(), 0), 0, "MFA");
        Station destinationStation = new Station(new Logger(), new TrainRoute(new Logger(), 0), 1, "Down Town Crossing");

        Platform testPlatform = new Platform(new Logger(), Direction.Inbound, s, 0);
        Passenger p1 = new Passenger(new Logger(), destinationStation, testPlatform, s, 0);
        Passenger p2 = new Passenger(new Logger(), destinationStation, testPlatform, s, 1);

        s.addArrivingPassenger(p1);
        s.addArrivingPassenger(p2);
        String expected = "Station info: \nStation Name: MFA\tStation ID: 0\nInbound Platform ID: 0" +
                "\tOutbound Platform ID: 1\nNumber of Arrived Passengers: 2";

        Assert.assertEquals(expected, s.toString());
    }

}