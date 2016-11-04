import edu.wit.comp2000.group23.application3.Enums.Direction;
import edu.wit.comp2000.group23.application3.GraphMap.Track;
import edu.wit.comp2000.group23.application3.Passenger;
import edu.wit.comp2000.group23.application3.Station;
import edu.wit.comp2000.group23.application3.Train;
import edu.wit.comp2000.group23.application3.TrainRoute;
import edu.wit.comp2000.group23.application3.Utilities.Logger;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by beznosm on 11/2/2016.
 */
public class EndToEndTests {
    //full loop test:
    @Test
    public void fullLoopTest() {
        Logger l = new Logger();
        String[] stops = new String[]{"s1", "s2", "s3"};
        TrainRoute tr = new TrainRoute(l, 0);
        tr.createRoute(stops);
        Train t1 = new Train(Direction.Inbound, 100, 0, l);
        tr.getTrains().add(t1);
        Station s0 = tr.getStations().get(0);
        Station s1 = tr.getStations().get(1);
        Station s2 = tr.getStations().get(2);
        Passenger p1 = new Passenger(l, tr.getStations().get(2), tr.getStations().get(0), 0);
        Passenger p2 = new Passenger(l, tr.getStations().get(2), tr.getStations().get(0), 1);
        Passenger p3 = new Passenger(l, tr.getStations().get(2), tr.getStations().get(0), 2);
        s0.getPlatform(Direction.Inbound).setOccupant(t1);
        t1.setConnector(s0.getInbound());
        ArrayList<Passenger> ps = new ArrayList<>();
        ps.add(p1);
        ps.add(p2);
        ps.add(p3);
        for (Iterator<Passenger> itp = ps.iterator(); itp.hasNext(); ) {
            Passenger p = itp.next();
            p.Sync(itp);
        }
        Assert.assertEquals(s0.getPlatform(Direction.Inbound), p1.getCurrentPlatform());
        Assert.assertEquals(s0.getPlatform(Direction.Inbound), p2.getCurrentPlatform());
        Assert.assertEquals(s0.getPlatform(Direction.Inbound), p3.getCurrentPlatform());
        tr.Sync();
        //Assert.assertEquals(p4, s0.getArrivedPassengers().get(0));
        Assert.assertEquals(t1, p1.getTrain());
        Assert.assertEquals(t1, p2.getTrain());
        Assert.assertEquals(t1, p3.getTrain());
        tr.Sync();
        Assert.assertEquals(true, t1.getConnector() instanceof Track);
        Assert.assertEquals(null, p1.getCurrentStation());
        Assert.assertEquals(null, p2.getCurrentStation());
        Assert.assertEquals(null, p3.getCurrentStation());
        for (int i = 0; i < 9; i++) {
            tr.Sync();
        }
        Assert.assertEquals(3, s2.getArrivedPassengers().size());
    }
    //Passenger overflow

    //
}
