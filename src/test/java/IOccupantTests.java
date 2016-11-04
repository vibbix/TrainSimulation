import edu.wit.comp2000.group23.application3.Enums.Direction;
import edu.wit.comp2000.group23.application3.GraphMap.IConnector;
import edu.wit.comp2000.group23.application3.GraphMap.Track;
import edu.wit.comp2000.group23.application3.IOccupant;
import edu.wit.comp2000.group23.application3.Platform;
import edu.wit.comp2000.group23.application3.Utilities.Logger;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by beznosm on 10/28/2016.
 */
public class IOccupantTests {
    private static Logger logger;

    @BeforeClass
    public static void setupTests() {
        logger = new Logger();
    }

    @Test
    public void nullConnectorToStringTest() {
        IOccupant ic = new IOccupant() {
        };
        ic.setDirection(Direction.Outbound);
        String toStr = "Direction: " + ic.getDirection().toString();
        toStr += "; Connector: null";
        Assert.assertEquals(toStr, ic.toString());
    }

    @Test
    public void TrackToStringTest() {
        IConnector s1 = new Track(logger);
        IOccupant ic = new IOccupant() {
        };
        ic.setConnector(s1);
        ic.setDirection(Direction.Outbound);
        String toStr = "Direction: " + ic.getDirection().toString();
        toStr += "; Connector: (Track) -1";
        Assert.assertEquals(toStr, ic.toString());
    }

    @Test
    public void PlatformToStringTest() {
        IConnector p1 = new Platform(logger, Direction.Inbound, null, 0);
        IOccupant ic = new IOccupant() {
        };
        ic.setConnector(p1);
        ic.setDirection(Direction.Outbound);
        String toStr = "Direction: " + ic.getDirection().toString();
        toStr += "; Connector: (Platform) 0";
        Assert.assertEquals(toStr, ic.toString());
    }
}
