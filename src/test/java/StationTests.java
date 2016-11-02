import edu.wit.comp2000.group23.application3.Passenger;
import edu.wit.comp2000.group23.application3.Platform;
import edu.wit.comp2000.group23.application3.Station;
import edu.wit.comp2000.group23.application3.TrainRoute;
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
}
