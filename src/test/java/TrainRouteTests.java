import edu.wit.comp2000.group23.application3.*;
import edu.wit.comp2000.group23.application3.Enums.Direction;
import edu.wit.comp2000.group23.application3.GraphMap.IConnector;
import edu.wit.comp2000.group23.application3.Utilities.Logger;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by beznosm on 10/31/2016.
 */
public class TrainRouteTests {
    @Test
    public void verifyPlatformConnect() {
        String[] stops = new String[]{"s1", "s2", "s3"};
        EncapsulatedLoggable el = new EncapsulatedLoggable();
        TrainRoute tr = new TrainRoute(el.getLogger(), 0);
        boolean[] expected = new boolean[6];
        Arrays.fill(expected, true);
        boolean[] actual = new boolean[6];
        tr.createRoute(stops);
        Platform s1i = tr.getStations().get(0).getPlatform(Direction.Inbound);
        Platform s1o = tr.getStations().get(0).getPlatform(Direction.Outbound);
        Platform s2i = tr.getStations().get(1).getPlatform(Direction.Inbound);
        Platform s2o = tr.getStations().get(1).getPlatform(Direction.Outbound);
        Platform s3i = tr.getStations().get(2).getPlatform(Direction.Inbound);
        Platform s3o = tr.getStations().get(2).getPlatform(Direction.Outbound);
        Platform[] pfs = new Platform[]{s2i, s3i, s3o, s2o, s1o, s1i};
        IConnector cnode = s1i;
        //s1->s2
        //s2->s3
        //s3[o]->sr[i]
        //s2<-s3
        //s1<-s3
        //s1[i]<-s1[o]
        for (int i = 0; i < pfs.length; i++) {
            while (true) {
                cnode = cnode.getConnector(Direction.Inbound);
                if (cnode instanceof Platform)
                    break;
            }
            actual[i] = (pfs[i] == cnode);
        }
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void testCreateRouteLogging() {
        String[] stops = new String[]{"s1", "s2", "s3"};
        EncapsulatedLoggable el = new EncapsulatedLoggable();
        TrainRoute tr = new TrainRoute(el.getLogger(), 0);
        tr.createRoute(stops);
        String toStr = "[0][TrainRoute\t\t][0\t][Creating route]\n" +
                "[0][TrainRoute\t\t][0\t][Creating station s1]\n" +
                "[0][TrainRoute\t\t][0\t][Creating station s2]\n" +
                "[0][TrainRoute\t\t][0\t][Creating station s3]\n" +
                "[0][TrainRoute\t\t][0\t][Connecting station s1 inbound to s2 inbound]\n" +
                "[0][TrainRoute\t\t][0\t][Connecting station s2 inbound to s3 inbound]\n" +
                "[0][TrainRoute\t\t][0\t][Connecting station s3 outbound to s2 outbound]\n" +
                "[0][TrainRoute\t\t][0\t][Connecting station s2 outbound to s1 outbound]\n" +
                "[0][TrainRoute\t\t][0\t][Connecting platform inbound to outbound on station s1]\n" +
                "[0][TrainRoute\t\t][0\t][Connecting platform outbound to inbound on station s3]";

        Assert.assertEquals(toStr, el.toString().replace("\r\n", "\n"));
    }
    @Test(expected = IllegalArgumentException.class)
    public void invalidStationListTest(){
        String[] stops = new String[]{"s1"};
        TrainRoute tr = new TrainRoute(new Logger(), 0);
        tr.createRoute(stops);
    }

    @Test(expected = SecurityException.class)
    public void getRouteBeforeRouteTest(){
        String[] stops = new String[]{"s1", "s2", "s3"};
        TrainRoute tr = new TrainRoute(new Logger(), 0);
        tr.getStations().add(new Station(new Logger(),tr, 0));
        tr.getRoute(tr.getStations().get(0), tr.getStations().get(0));
    }
    @Test(expected = SecurityException.class)
    public void syncBeforeRouteTest(){
        String[] stops = new String[]{"s1", "s2", "s3"};
        EncapsulatedLoggable el = new EncapsulatedLoggable();
        TrainRoute tr = new TrainRoute(el.getLogger(), 0);
        tr.Sync();
    }
    @Test
    public void getStationIndexTest(){
        String[] stops = new String[]{"s1", "s2", "s3"};
        TrainRoute tr = new TrainRoute(new Logger(), 0);
        tr.createRoute(stops);
        Assert.assertEquals(0, tr.getStationIndex(tr.getStations().get(0)));
    }

    @Test
    public void getRouteInboundTests(){
        String[] stops = new String[]{"s1", "s2", "s3"};
        EncapsulatedLoggable el = new EncapsulatedLoggable();
        TrainRoute tr = new TrainRoute(el.getLogger(), 0);
        tr.createRoute(stops);
        Assert.assertEquals(Direction.Inbound, tr.getRoute(tr.getStations().get(0), tr.getStations().get(2)));
    }
    @Test
    public void getRouteOutboundTests(){
        String[] stops = new String[]{"s1", "s2", "s3"};
        EncapsulatedLoggable el = new EncapsulatedLoggable();
        TrainRoute tr = new TrainRoute(el.getLogger(), 0);
        tr.createRoute(stops);
        Assert.assertEquals(Direction.Outbound, tr.getRoute(tr.getStations().get(2), tr.getStations().get(0)));
    }

    @Test
    public void testTrainMovementSync(){
        String[] stops = new String[]{"s1", "s2", "s3"};
        EncapsulatedLoggable el = new EncapsulatedLoggable();
        TrainRoute route = new TrainRoute(el.getLogger(), 0);
        route.createRoute(stops);
        //t1@s2ite(stops);
        //t2@s2o
        Train t1 = new Train(Direction.Inbound, 100, 0, el.getLogger());
        Train t2 = new Train(Direction.Inbound, 100, 1, el.getLogger());

        route.getTrains().add(t1);
        route.getTrains().add(t2);
        int half = route.getStations().size() / 2;
        Platform in = route.getStations().get(half).getPlatform(Direction.Inbound);
        in.setOccupant(t1);
        Platform out = route.getStations().get(half).getPlatform(Direction.Outbound);//.setOccupant(t2);
        out.setOccupant(t2);
        t1.closeDoors();
        t2.closeDoors();
        Assert.assertEquals(route.getStations().get(1).getPlatform(Direction.Inbound),
                route.getTrains().get(0).getConnector());
        Assert.assertEquals(route.getStations().get(1).getPlatform(Direction.Outbound),
                route.getTrains().get(1).getConnector());
        route.Sync();
        Assert.assertEquals(
                route.getStations().get(1).getPlatform(Direction.Inbound).getConnector(Direction.Inbound),
                route.getTrains().get(0).getConnector());
        Assert.assertEquals(
                route.getStations().get(1).getPlatform(Direction.Outbound).getConnector(Direction.Inbound),
                route.getTrains().get(1).getConnector());
    }
    @Test
    public void testTrainMovementDoorsOpenSync(){
        String[] stops = new String[]{"s1", "s2", "s3"};
        EncapsulatedLoggable el = new EncapsulatedLoggable();
        TrainSimulation ts = new TrainSimulation(el.getLogger(), stops, false, 0);
        //t1@s2i
        //t2@s2o
        ts.createTrains();
        ts.getRoute().getTrains().get(0).openDoors();
        ts.getRoute().getTrains().get(1).openDoors();
        Assert.assertEquals(ts.getRoute().getStations().get(1).getPlatform(Direction.Inbound),
                ts.getRoute().getTrains().get(0).getConnector());
        Assert.assertEquals(ts.getRoute().getStations().get(1).getPlatform(Direction.Outbound),
                ts.getRoute().getTrains().get(1).getConnector());
        ts.getRoute().Sync();
        Assert.assertEquals(ts.getRoute().getStations().get(1).getPlatform(Direction.Inbound),
                ts.getRoute().getTrains().get(0).getConnector());
        Assert.assertEquals(ts.getRoute().getStations().get(1).getPlatform(Direction.Outbound),
                ts.getRoute().getTrains().get(1).getConnector());
    }
}
