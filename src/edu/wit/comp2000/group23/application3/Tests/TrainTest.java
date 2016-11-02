package edu.wit.comp2000.group23.application3.Tests;

import edu.wit.comp2000.group23.application3.*;
import edu.wit.comp2000.group23.application3.Utilities.Logger;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;


/**
 * Implemented by dechristophera on 10/29/2016.
 */
public class TrainTest {
    @Test
    public void CreateTrainTest() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        Train t = new Train(Direction.Inbound, 100, -1, new Logger(ps));
        Assert.assertNotEquals(null, t);
    }

    @Test
    public void TestGetDirection() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        Train t = new Train(Direction.Inbound, 100, -1, new Logger(ps));
        Assert.assertEquals(Direction.Inbound.toString(), t.getDirection().toString());
    }

    @Test
    public void TestGetMaxPassengers() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        Train t = new Train(Direction.Inbound, 100, -1, new Logger(ps));
        Assert.assertEquals(100, t.getMaxPassengers());
    }

    @Test
    public void TestGetCurrentPassengers() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        Train t = new Train(Direction.Inbound, 100, -1, new Logger(ps));

        TrainRoute tr = new TrainRoute(new Logger(ps), 0);
        Station s = new Station(new Logger(ps), tr, 0);
        Platform p = new Platform(new Logger(ps), Direction.Inbound, s, 0);

        Passenger p1 = new Passenger(new Logger(ps), s, p, s, 0);
        Passenger p2 = new Passenger(new Logger(ps), s, p, s, 1);

        t.embarkPassenger(p1);
        t.embarkPassenger(p2);

        Assert.assertEquals(2, t.getCurrentPassengers());
    }

    @Test
    public void TestGetID() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        Train t = new Train(Direction.Inbound, 100, -1, new Logger(ps));
        Assert.assertEquals(-1, t.getID());
    }

    @Test
    public void TestGetPassengersArrayList() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        Train t = new Train(Direction.Inbound, 100, -1, new Logger(ps));

        TrainRoute tr = new TrainRoute(new Logger(ps), 0);
        Station s = new Station(new Logger(ps), tr, 0);
        Platform p = new Platform(new Logger(ps), Direction.Inbound, s, 0);

        Passenger p1 = new Passenger(new Logger(ps), s, p, s, 0);
        Passenger p2 = new Passenger(new Logger(ps), s, p, s, 1);

        ArrayList<Passenger> pl = new ArrayList<>();
        pl.add(p1);
        pl.add(p2);

        t.embarkPassenger(p1);
        t.embarkPassenger(p2);

        Assert.assertEquals(pl, t.getPassengers());
    }

    @Test
    public void TestGetDoorState() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        Train t = new Train(Direction.Inbound, 100, -1, new Logger(ps));

        TrainRoute tr = new TrainRoute(new Logger(ps), 0);
        Station s = new Station(new Logger(ps), tr, 0);
        Platform p = new Platform(new Logger(ps), Direction.Inbound, s, 0);

        t.setConnector(p);

        t.openDoors();
        Assert.assertEquals(true, t.getDoorState());
        Assert.assertEquals(false, p.isTrainReadyToLeave());

        t.closeDoors();
        Assert.assertEquals(false, t.getDoorState());
        Assert.assertEquals(true, p.isTrainReadyToLeave());
    }

    @Test
    public void TestOpenDoors() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        Train t = new Train(Direction.Inbound, 100, -1, new Logger(ps));

        TrainRoute tr = new TrainRoute(new Logger(ps), 0);
        Station s = new Station(new Logger(ps), tr, 0);
        Platform p = new Platform(new Logger(ps), Direction.Inbound, s, 0);

        t.setConnector(p);

        t.openDoors();

        Assert.assertEquals(true, t.getDoorState());
        Assert.assertEquals(false, p.isTrainReadyToLeave());
    }

    @Test
    public void TestCloseDoors() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        Train t = new Train(Direction.Inbound, 100, -1, new Logger(ps));

        TrainRoute tr = new TrainRoute(new Logger(ps), 0);
        Station s = new Station(new Logger(ps), tr, 0);
        Platform p = new Platform(new Logger(ps), Direction.Inbound, s, 0);

        t.setConnector(p);

        t.closeDoors();

        Assert.assertEquals(false, t.getDoorState());
        Assert.assertEquals(true, p.isTrainReadyToLeave());
    }

    @Test
    public void TestGetCurrentStation() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        Train t = new Train(Direction.Inbound, 100, -1, new Logger(ps));

        TrainRoute tr = new TrainRoute(new Logger(ps), 0);
        Station s = new Station(new Logger(ps), tr, 0);

        Assert.assertEquals(null, t.getCurrentStation());

        t.setCurrentStation(s);

        Assert.assertEquals(s, t.getCurrentStation());
    }

    @Test
    public void TestSetCurrentStation() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        Train t = new Train(Direction.Inbound, 100, -1, new Logger(ps));

        TrainRoute tr = new TrainRoute(new Logger(ps), 0);
        Station s = new Station(new Logger(ps), tr, 0);

        t.setCurrentStation(s);

        Assert.assertEquals(s, t.getCurrentStation());
    }

    @Test
    public void TestEmbarkPassengerNormal() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        Train t = new Train(Direction.Inbound, 100, -1, new Logger(ps));

        TrainRoute tr = new TrainRoute(new Logger(ps), 0);
        Station s = new Station(new Logger(ps), tr, 0);
        Platform p = new Platform(new Logger(ps), Direction.Inbound, s, 0);

        Passenger p1 = new Passenger(new Logger(ps), s, p, s, 0);

        boolean success = t.embarkPassenger(p1);

        Assert.assertEquals(true, success);
    }

    /**
     * Sets max capacity of train to 1, adds a passenger, and then tests the result of the
     * next attempt at adding a passenger.
     * <p>
     * EXPECT false
     */
    @Test
    public void TestEmbarkPassengerMaxCap() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        Train t = new Train(Direction.Inbound, 1, -1, new Logger(ps));

        TrainRoute tr = new TrainRoute(new Logger(ps), 0);
        Station s = new Station(new Logger(ps), tr, 0);
        Platform p = new Platform(new Logger(ps), Direction.Inbound, s, 0);

        Passenger p1 = new Passenger(new Logger(ps), s, p, s, 0);

        t.setConnector(p);
        t.embarkPassenger(p1);

        boolean success = t.embarkPassenger(p1);

        Assert.assertEquals(false, success);
    }

    /**
     * Closes door before adding passenger. Should return false.
     * <p>
     * EXPECT false
     */
    @Test
    public void TestEmbarkPassengerDoorClosed() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        Train t = new Train(Direction.Inbound, 100, -1, new Logger(ps));

        TrainRoute tr = new TrainRoute(new Logger(ps), 0);
        Station s = new Station(new Logger(ps), tr, 0);
        Platform p = new Platform(new Logger(ps), Direction.Inbound, s, 0);

        Passenger p1 = new Passenger(new Logger(ps), s, p, s, 0);

        t.setConnector(p);

        t.closeDoors();

        t.embarkPassenger(p1);

        boolean success = t.embarkPassenger(p1);

        Assert.assertEquals(false, success);
    }

    @Test
    public void TestDisembarkPassenger() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        Train t = new Train(Direction.Inbound, 100, -1, new Logger(ps));

        TrainRoute tr = new TrainRoute(new Logger(ps), 0);
        Station s = new Station(new Logger(ps), tr, 0);
        Platform p = new Platform(new Logger(ps), Direction.Inbound, s, 0);

        Passenger p1 = new Passenger(new Logger(ps), s, p, s, 0);

        t.setConnector(p);

        t.embarkPassenger(p1);

        t.disembarkPassenger(p1);

        Assert.assertEquals(false, t.getPassengers().contains(p1));
    }

    @Test
    public void TestSetDirection() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        Train t = new Train(Direction.Inbound, 100, -1, new Logger(ps));

        t.setDirection(Direction.Outbound);

        Assert.assertEquals(Direction.Outbound, t.getDirection());
    }

    @Test
    public void TestSetCurrentPlatformTesting() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        Train t = new Train(Direction.Inbound, 100, -1, new Logger(ps));

        TrainRoute tr = new TrainRoute(new Logger(ps), 0);
        Station s = new Station(new Logger(ps), tr, 0);
        Platform p = new Platform(new Logger(ps), Direction.Inbound, s, 0);

        t.setConnector(p);

        Assert.assertEquals(p, t.getConnector());
    }

    @Test
    public void TestLogEvent() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Logger l = new Logger(new PrintStream(baos));
        Train t = new Train(Direction.Inbound, 100, -1, l);

        t.LogEvent("TESTING");

        Assert.assertEquals("[0][Train\t\t][-1\t][TESTING]", baos.toString().trim());
    }

}