import edu.wit.comp2000.group23.application3.Enums.Direction;
import edu.wit.comp2000.group23.application3.GraphMap.IConnector;
import edu.wit.comp2000.group23.application3.GraphMap.Track;
import edu.wit.comp2000.group23.application3.Train;
import edu.wit.comp2000.group23.application3.Utilities.Logger;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by beznosm on 11/2/2016.
 */
public class IConnectorTests {
    @Test
    public void moveConnectorInbound() throws Exception {
        Logger logger = new Logger();
        IConnector t1 = new Track(logger);
        IConnector t2 = new Track(logger);
        IConnector t3 = new Track(logger);
        t2.setConnector(t1, Direction.Outbound);
        t3.setConnector(t3, Direction.Outbound);
        Train train1 = new Train(Direction.Inbound, 5, 0, logger);
        t1.setOccupant(train1);
        t1.moveConnector();
        Assert.assertEquals(t2, train1.getConnector());
    }

    @Test
    public void moveConnectorOutbound() throws Exception {
        Logger logger = new Logger();
        IConnector t1 = new Track(logger);
        IConnector t2 = new Track(logger);
        IConnector t3 = new Track(logger);
        t2.setConnector(t1, Direction.Outbound);
        t3.setConnector(t3, Direction.Outbound);
        Train train1 = new Train(Direction.Outbound, 5, 0, logger);
        t2.setOccupant(train1);
        t2.moveConnector();
        Assert.assertEquals(t1, train1.getConnector());
    }

    @Test(expected = Exception.class)
    public void TrainOverrunOutboundTest() throws Exception {
        Logger logger = new Logger();
        IConnector t1 = new Track(logger);
        IConnector t2 = new Track(logger);
        IConnector t3 = new Track(logger);
        t2.setConnector(t1, Direction.Outbound);
        t3.setConnector(t3, Direction.Outbound);
        Train train1 = new Train(Direction.Outbound, 5, 0, logger);
        Train train2 = new Train(Direction.Outbound, 5, 1, logger);
        t2.setOccupant(train1);
        t1.setOccupant(train2);
        t2.moveConnector();
    }

    @Test(expected = Exception.class)
    public void TrainOverrunInboundTest() throws Exception {
        Logger logger = new Logger();
        IConnector t1 = new Track(logger);
        IConnector t2 = new Track(logger);
        IConnector t3 = new Track(logger);
        t1.setConnector(t2, Direction.Inbound);
        t2.setConnector(t3, Direction.Inbound);
        Train train1 = new Train(Direction.Inbound, 5, 0, logger);
        Train train2 = new Train(Direction.Inbound, 5, 1, logger);
        t1.setOccupant(train1);
        t2.setOccupant(train2);
        t1.moveConnector();
    }
}
