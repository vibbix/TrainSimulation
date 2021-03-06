import edu.wit.comp2000.group23.application3.Enums.Direction;
import edu.wit.comp2000.group23.application3.GraphMap.IConnector;
import edu.wit.comp2000.group23.application3.GraphMap.Track;
import edu.wit.comp2000.group23.application3.*;
import edu.wit.comp2000.group23.application3.Utilities.Logger;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

/**
 * Comp2000 - Data Structures
 * Application 3 - Queues (TrainSim)
 * Group #23
 *
 * Team:
 * Andrew DeChristopher
 * Mark Beznos
 * Bryon Kucharski
 * Tin Wong
 * Jeffery Lindeland
 * Shakib Hassan
 */
public class PlatformTests {

    @Test
    public void CreatePlatformTest() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        TrainRoute tr = new TrainRoute(new Logger(), 0);
        Platform p = new Platform(new Logger(ps), Direction.Inbound, new Station(new Logger(), tr, 0), 1);
        Assert.assertEquals(p.getPlatformDirection(), Direction.Inbound);

    }

    @Test
    public void TrainReadyTest() {
        TrainRoute tr = new TrainRoute(new Logger(), 0);
        Platform p = new Platform(new Logger(), Direction.Inbound, new Station(new Logger(), tr, 0), 1);
        p.setTrainReadyToLeave(false);
        Assert.assertEquals(p.isTrainReadyToLeave(), false);
        p.setTrainReadyToLeave(true);
        Assert.assertEquals(p.isTrainReadyToLeave(), true);
    }

    @Test
    public void setStationTest() {
        TrainRoute tr = new TrainRoute(new Logger(), 0);
        Station firstStation = new Station(new Logger(), tr, 0);
        Platform p = new Platform(new Logger(), Direction.Inbound, firstStation, 1);
        Station secondStation = new Station(new Logger(), tr, 1);
        Assert.assertEquals(p.getStation(), firstStation);
        p.setStation(secondStation);
        Assert.assertEquals(p.getStation(), secondStation);
    }

    @Test
    public void setPlatformDirectionTest() {
        TrainRoute tr = new TrainRoute(new Logger(), 0);
        Platform p = new Platform(new Logger(), Direction.Inbound, new Station(new Logger(), tr, 0), 1);
        Assert.assertEquals(p.getPlatformDirection(), Direction.Inbound);
        p.setPlatformDirection(Direction.Outbound);
        Assert.assertEquals(p.getPlatformDirection(), Direction.Outbound);
    }

    @Test
    public void passengerPlatformTest() {
        TrainRoute tr = new TrainRoute(new Logger(), 0);
        Station firstStation = new Station(new Logger(), tr, 0);
        Station secondStation = new Station(new Logger(), tr, 1);
        Platform p = new Platform(new Logger(), Direction.Inbound, firstStation, 1);
        Passenger enteringPassenger = new Passenger(new Logger(), secondStation, firstStation, 1);
        Assert.assertEquals(p.canDequeuePassenger(), false);
        p.enqueuePassenger(enteringPassenger);
        Assert.assertEquals(p.canDequeuePassenger(), true);
        Passenger exitingPassenger = p.dequeuePassenger();
        Assert.assertEquals(exitingPassenger, enteringPassenger);
    }

    @Test
    public void setOccupantTest() {
        Train t = new Train(Direction.Inbound, 100, 0, new Logger());
        TrainRoute tr = new TrainRoute(new Logger(), 0);
        Station s = new Station(new Logger(), tr, 0);
        Platform p = new Platform(new Logger(), Direction.Inbound, s, 0);
        Assert.assertEquals(p.getOccupant(), null);
        p.setOccupant(t);
        Assert.assertEquals(p.getOccupant(), t);
    }

    @Test
    public void setConnectorTest() throws Exception {
        Train t1 = new Train(Direction.Inbound, 100, 0, new Logger());
        Train t2 = new Train(Direction.Inbound, 100, 0, new Logger());
        TrainRoute tr = new TrainRoute(new Logger(), 0);
        Station firstStation = new Station(new Logger(), tr, 0);
        Station secondStation = new Station(new Logger(), tr, 1);
        Station thirdStation = new Station(new Logger(), tr, 2);
        Platform p1in = new Platform(new Logger(), Direction.Inbound, firstStation, 0);
        //Platform p1out = new Platform(new Logger(), Direction.Outbound, firstStation, 50);
        Platform p2in = new Platform(new Logger(), Direction.Inbound, secondStation, 1);
        //Platform p2out = new Platform(new Logger(), Direction.Outbound, secondStation, 51);
        Platform p3in = new Platform(new Logger(), Direction.Inbound, thirdStation, 2);
        //Platform p3out = new Platform(new Logger(), Direction.Outbound, thirdStation, 52);
        p1in.setConnector(p2in, Direction.Inbound);
        p2in.setConnector(p3in, Direction.Inbound);
        p2in.setConnector(p1in, Direction.Outbound);
        p3in.setConnector(p2in, Direction.Outbound);
        //p3out.setConnector(p2out, Direction.Outbound);
        //p2out.setConnector(p1out, Direction.Outbound);
        Assert.assertEquals(p2in, p1in.getConnector(Direction.Inbound));
        ArrayList<IConnector> al = new ArrayList<>();
        al.add(p3in);
        al.add(p1in);
        Assert.assertEquals(al, p2in.getConnectors());
        p1in.setOccupant(t1);
        p1in.moveConnector();
        Assert.assertEquals(t1, p2in.getOccupant());
    }

    @Test
    public void getPlatformIDTest() {
        TrainRoute tr = new TrainRoute(new Logger(), 0);
        Station s = new Station(new Logger(), tr, 0);
        Platform p = new Platform(new Logger(), Direction.Inbound, s, 99);
        Assert.assertEquals(99, p.getPlatformID());
    }

    @Test
    public void platformConnectionToStringTest() {
        Logger l = new Logger();
        Train t1 = new Train(Direction.Outbound, 100, 0, l);
        Platform p1in = new Platform(l, Direction.Inbound, null, 0);
        //Platform p1out = new Platform(new Logger(), Direction.Outbound, firstStation, 50);
        Platform p2in = new Platform(l, Direction.Inbound, null, 1);
        //Platform p2out = new Platform(new Logger(), Direction.Outbound, secondStation, 51);
        Platform p3in = new Platform(l, Direction.Inbound, null, 2);
        p2in.setConnector(p3in, Direction.Inbound);
        p2in.setConnector(p1in, Direction.Outbound);
        p2in.setOccupant(t1);
        Assert.assertEquals("Platform: 1; logger: " + l.hashCode() + "; Inbound: (Platform)2; Outbound: (Platform)0; Occupant: TRAIN 0 - Outbound [0/100] [DOORSOPEN] {Platform: 1} ", p2in.toString());
    }

    @Test
    public void trackConnectionToStringTest() {
        Logger l = new Logger();
        Train t1 = new Train(Direction.Outbound, 100, 0, l);
        Platform p2in = new Platform(l, Direction.Inbound, null, 0);
        Track track1 = new Track(l);
        Track track2 = new Track(l);
        p2in.setConnector(track1, Direction.Inbound);
        p2in.setConnector(track2, Direction.Outbound);
        Assert.assertEquals("Platform: 0; logger: " + l.hashCode() + "; Inbound: (Track)-1; Outbound: (Track)-1; Occupant: null", p2in.toString());
    }

}
