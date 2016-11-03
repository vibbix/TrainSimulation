import edu.wit.comp2000.group23.application3.*;
import edu.wit.comp2000.group23.application3.Enums.Direction;
import edu.wit.comp2000.group23.application3.Exceptions.TrainDoorsClosedException;
import edu.wit.comp2000.group23.application3.Exceptions.TrainPassengerOverflowException;
import edu.wit.comp2000.group23.application3.Utilities.Logger;
import junit.framework.Assert;
import org.junit.Test;

/**
 * JUnit test for Passenger
 *
 * @author wongt1
 */
public class PassengerTests {

    /**
     * test if passenger is on train
     *
     * @throws Exception
     */
    @SuppressWarnings("deprecation")
    @Test
    public void onTrainTest() throws Exception {
        Logger l = new Logger();
        Passenger p = new Passenger(l, null, null, null, -1);
        Train t = new Train(null, 0, 0, l);
        p.setTrain(t);
        Assert.assertEquals(true, p.getOnTrain());
    }

    /**
     * test for passenger disembarkTrain method
     * @throws TrainPassengerOverflowException
     * @throws TrainDoorsClosedException
     */
    @SuppressWarnings("deprecation")
    @Test
    public void disembarkTrainTest() throws TrainPassengerOverflowException, TrainDoorsClosedException{
    	Logger l = new Logger();
        Passenger p = new Passenger(l, null, null, null, -1);
        Train t = new Train(null, 2, 1, l);
        TrainRoute tr = new TrainRoute(l, 1);
        Station s = new Station(l, tr, 1);
        p.setStation(s);
        t.embarkPassenger(p);
        p.disembarkTrain();
        Assert.assertEquals(false, p.getOnTrain());
    }
    
    /**
     * test if the passenger's train is equal to the train
     *
     * @throws Exception
     */
    @SuppressWarnings("deprecation")
    @Test
    public void TrainTest() throws Exception {
        Logger l = new Logger();
        Passenger p = new Passenger(l, null, null, null, -1);
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
    public void PlatformTest() throws Exception {
        Logger l = new Logger();
        Passenger p = new Passenger(l, null, null, null, -1);
        TrainRoute tr = new TrainRoute(l, 1);
        Station s = new Station(l, tr, 1);
        Platform pf = new Platform(l, Direction.Inbound, s, 1);
        p.setPlatform(pf);
        Assert.assertEquals(pf, p.getPlatform());
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
        Assert.assertEquals(p.getCurrentStation(), s);
    }

    /**
     * test toString for Passenger class
     */
    @SuppressWarnings("deprecation")
    @Test
    public void toStringTest() throws Exception {
        Logger l = new Logger();
        Passenger p = new Passenger(l, null, null, null, -1);
        String string = "Passenger info: ";
        string += "\nPassenger ID: " + p.getID();
        string += "\nDestination: " + p.getDestination();
        string += "\nCurrent Station: " + p.getCurrentStation();
        string += "\nPlatform: " + p.getCurrentPlatform();
        string += "\nOn train: " + p.getOnTrain();
        Assert.assertEquals(string, p.toString());
    }
}