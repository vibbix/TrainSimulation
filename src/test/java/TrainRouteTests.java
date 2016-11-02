import edu.wit.comp2000.group23.application3.Direction;
import edu.wit.comp2000.group23.application3.GraphMap.IConnector;
import edu.wit.comp2000.group23.application3.Platform;
import edu.wit.comp2000.group23.application3.TrainRoute;
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
    @Test
    public void getRouteTests(){

    }
}
