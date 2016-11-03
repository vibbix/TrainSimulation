import edu.wit.comp2000.group23.application3.Enums.Direction;
import edu.wit.comp2000.group23.application3.GraphMap.IConnector;
import edu.wit.comp2000.group23.application3.GraphMap.Track;
import edu.wit.comp2000.group23.application3.Train;
import edu.wit.comp2000.group23.application3.Utilities.Logger;
import org.junit.Test;

/**
 * Created by beznosm on 11/2/2016.
 */
public class IConnectorTests {
    @Test
    public void moveConnectorInbound() throws Exception{
        Logger logger = new Logger();
        IConnector t1 = new Track(logger);
        IConnector t2 = new Track(logger);
        IConnector t3 = new Track(logger);
        t2.setConnector(t1, Direction.Outbound);
        t3.setConnector(t3, Direction.Outbound);
        Train train1 = new Train(Direction.Outbound, 5, 0, logger);
        t2.setOccupant(train1);
        t2.moveConnector();

    }
}
