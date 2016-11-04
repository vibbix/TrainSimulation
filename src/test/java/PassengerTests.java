import edu.wit.comp2000.group23.application3.Enums.Direction;
import edu.wit.comp2000.group23.application3.Exceptions.TrainDoorsClosedException;
import edu.wit.comp2000.group23.application3.Exceptions.TrainPassengerOverflowException;
import edu.wit.comp2000.group23.application3.*;
import edu.wit.comp2000.group23.application3.Utilities.Logger;
import junit.framework.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * JUnit test for Passenger
 *
 * @author wongt1
 */
public class PassengerTests {

    /**
     * test default constructor for passenger (with only logger as param)
     */
    @SuppressWarnings("deprecation")
    @Test
    public void PassengerDefaultConstructorTest() throws Exception {
        Logger l = new Logger();
        Passenger p = new Passenger(l);

        Assert.assertEquals(l, p.getLogger());
    }

    /**
     * test for Passenger constructor with param:
     *
     * @param Logger   logger
     * @param Station  destination
     * @param Platform currentPlatform
     * @param Statoin  currentStation
     * @param int      pID
     */
    @SuppressWarnings("deprecation")
    @Test
    public void PassengerConstructorTest() throws Exception {
        Logger l = new Logger();
        TrainRoute tr = new TrainRoute(l, 1);
        Station s = new Station(l, tr, 1);
        Station destination = new Station(l, tr, 1);
        Passenger p = new Passenger(l, s, destination, -1);

        Assert.assertEquals(-1, p.getID());
    }

    /**
     * test if passenger is on train
     *
     * @throws Exception
     */
    @SuppressWarnings("deprecation")
    @Test
    public void onTrainTest() throws Exception {
        Logger l = new Logger();
        Passenger p = new Passenger(l, null, null, -1);
        Train t = new Train(null, 0, 0, l);
        p.setTrain(t);
        Assert.assertEquals(true, p.getOnTrain());
    }

    /**
     * test for passenger disembarkTrain method
     *
     * @throws TrainPassengerOverflowException
     * @throws TrainDoorsClosedException
     */
    @SuppressWarnings("deprecation")
    @Test
    public void disembarkTrainTest() throws TrainPassengerOverflowException, TrainDoorsClosedException {
        Logger l = new Logger();
        Passenger p = new Passenger(l, null, null, -1);
        Train t = new Train(null, 2, 1, l);
        TrainRoute tr = new TrainRoute(l, 1);
        Station s = new Station(l, tr, 1);
        p.setStation(s);
        t.embarkPassenger(p);
        ArrayList<Passenger> pass = new ArrayList<>();
        pass.add(p);
        for (Iterator<Passenger> itp = pass.iterator(); itp.hasNext(); ) {
            Passenger p1 = itp.next();
            p1.disembarkTrain(itp);
        }

        Assert.assertEquals(false, p.getOnTrain());
    }

    /**
     * disembark train with iterator parameter
     *
     * @throws Exception
     */
    @SuppressWarnings("deprecation")
    @Test
    public void disembarkTrainTestWithIterator() throws Exception {
        ArrayList<Passenger> list = new ArrayList<>();
        Logger l = new Logger();
        Passenger p = new Passenger(l, null, null, -1);
        list.add(p);
        Iterator<Passenger> itp = list.iterator();
        Train t = new Train(null, 2, 1, l);
        TrainRoute tr = new TrainRoute(l, 1);
        Station s = new Station(l, tr, 1);
        p.setStation(s);
        t.embarkPassenger(itp.next());
        p.disembarkTrain(itp);
        Assert.assertEquals(false, p.getOnTrain());

    }
    @Test
    public void TrainDoorsClosedWhileDisembarkingTest()throws Exception{
        ArrayList<Passenger> list = new ArrayList<>();
        Logger l = new Logger();
        Passenger p = new Passenger(l, null, null, -1);
        list.add(p);
        Iterator<Passenger> itp = list.iterator();
        Train t = new Train(null, 2, 1, l);
        TrainRoute tr = new TrainRoute(l, 1);
        Station s = new Station(l, tr, 1);
        p.setStation(s);
        t.setConnector(s.getPlatform(Direction.Outbound));
        t.embarkPassenger(itp.next());
        t.closeDoors();
        p.disembarkTrain(itp);
        Assert.assertEquals(true, p.getOnTrain());
    }
    @Test(expected = TrainDoorsClosedException.class)
    public void TrainDoorsClosedWhileEmbarkingTest()throws Exception{
        ArrayList<Passenger> list = new ArrayList<>();
        Logger l = new Logger();
        Passenger p = new Passenger(l, null, null, -1);
        list.add(p);
        Iterator<Passenger> itp = list.iterator();
        Train t = new Train(null, 2, 1, l);
        TrainRoute tr = new TrainRoute(l, 1);
        Station s = new Station(l, tr, 1);
        p.setStation(s);
        t.setConnector(s.getPlatform(Direction.Outbound));
        t.closeDoors();
        t.embarkPassenger(itp.next());
        Assert.assertEquals(false, p.getOnTrain());
    }

    @SuppressWarnings("deprecation")
    @Test
    public void PassengerArrivalTest() throws Exception {
        Logger l = new Logger();
        TrainRoute tr = new TrainRoute(l, 1);
        Station s = new Station(l, tr, 1, "Station");
        Station d = new Station(l, tr, 1, "Station");
        Passenger p = new Passenger(l, s, d, -1);
        Train t = new Train(null, 2, 1, l);
        p.setStation(s);
        t.embarkPassenger(p);
        if (p.getCurrentStation() == p.getDestination()) {
            if (p.getOnTrain()) {
                ArrayList<Passenger> pass = new ArrayList<>();
                pass.add(p);
                for (Iterator<Passenger> itp = pass.iterator(); itp.hasNext(); )
                    itp.next().disembarkTrain(itp);
            }
        }
        Assert.assertEquals(false, p.getOnTrain());
    }

    @Test
    public void PassengerStationDestinationTest() throws Exception {
        Logger l = new Logger();
        Passenger p = new Passenger(l, null, null, -1);
        Train t = new Train(null, 2, 1, l);
        TrainRoute tr = new TrainRoute(l, 1);
        Station s = new Station(l, tr, 1);
        p.setStation(s);
        t.embarkPassenger(p);
        ArrayList<Passenger> pass = new ArrayList<>();
        pass.add(p);
        for (Iterator<Passenger> itp = pass.iterator(); itp.hasNext(); ) {
            itp.next().disembarkTrain(itp);
        }
        Assert.assertEquals(false, p.getOnTrain());
    }

    /**
     * test if the passenger's train is equal to the train
     *
     * @throws Exception
     */
    @SuppressWarnings("deprecation")
    @Test
    public void setTrainTest() throws Exception {
        Logger l = new Logger();
        Passenger p = new Passenger(l, null, null, -1);
        Train t = new Train(null, 0, 0, l);
        p.setTrain(t);
        Assert.assertEquals(t, p.getTrain());

    }

    /**
     * test for passenger's platform
     *
     * @throws Exception
     */
    @SuppressWarnings("deprecation")
    @Test
    public void setPlatformTest() throws Exception {
        Logger l = new Logger();
        Passenger p = new Passenger(l, null, null, -1);
        TrainRoute tr = new TrainRoute(l, 1);
        Station s = new Station(l, tr, 1);
        Platform pf = new Platform(l, Direction.Inbound, s, 1);
        p.setPlatform(pf);
        Assert.assertEquals(pf, p.getPlatform());
    }

    /**
     * tests if the passenger is in the station (using station id) uncomment
     * s.getId()
     */
    @SuppressWarnings("deprecation")
    @Test
    public void setStationTest() throws Exception {
        Logger l = new Logger();
        Passenger p = new Passenger(l, null, null, -1);
        TrainRoute tr = new TrainRoute(l, 1);
        Station s = new Station(l, tr, 1);
        p.setStation(s);
        Assert.assertEquals(p.getCurrentStation(), s);
    }

    /**
     * test toString for Passenger class
     */
    @SuppressWarnings("deprecation")
    @Test
    public void toStringTest() throws Exception {
        Logger l = new Logger();
        Passenger p = new Passenger(l, null, null, -1);
        String string = "Passenger info: ";
        string += "\nPassenger ID: " + p.getID();
        string += "\nDestination: " + p.getDestination();
        string += "\nCurrent Station: " + p.getCurrentStation();
        string += "\nPlatform: " + p.getCurrentPlatform();
        string += "\nOn train: " + p.getOnTrain();
        Assert.assertEquals(string, p.toString());
    }

    /**
     * test for TrainDoorsClosedException, when the passenger tries to leave the
     * train but the doors is closed...
     */
    @Test
    public void TrainDoorsClosedExceptionTest() throws Exception {
        Logger l = new Logger();
        Passenger p = new Passenger(l, null, null, -1);
        Train t = new Train(null, 2, 1, l);
        TrainRoute tr = new TrainRoute(l, 1);
        Station s = new Station(l, tr, 1);
        Platform pf = new Platform(l, Direction.Inbound, s, 1);
        pf.setOccupant(t);
        p.setTrain(t);
        t.closeDoors();
        try {
            t.disembarkPassenger(p);
        } catch (TrainDoorsClosedException ex) {

        }
    }
}