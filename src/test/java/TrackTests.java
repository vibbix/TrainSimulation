import edu.wit.comp2000.group23.application3.Enums.Direction;
import edu.wit.comp2000.group23.application3.GraphMap.IConnector;
import edu.wit.comp2000.group23.application3.GraphMap.Track;
import edu.wit.comp2000.group23.application3.IOccupant;
import edu.wit.comp2000.group23.application3.Platform;
import edu.wit.comp2000.group23.application3.Utilities.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Created by beznosm on 10/27/2016.
 */
public class TrackTests {
    private static Logger logger;
    private static EncapsulatedLoggable el;

    @BeforeClass
    public static void setupTest() {
        el = new EncapsulatedLoggable();
        logger = el.getLogger();
    }

    @Test
    public void CircularTrack() throws Exception {
        IConnector section1 = new Track<String>(logger);
        IConnector section2 = new Track<String>(logger);
        IConnector section3 = new Track<String>(logger);
        String compare = "x";
        boolean[] bools = new boolean[4];
        section1.setConnector(section3, Direction.Outbound);
        section2.setConnector(section1, Direction.Outbound);
        section3.setConnector(section2, Direction.Outbound);
        section1.setOccupant(compare);
        bools[0] = section1.getOccupant().equals(compare);
        section1.moveConnector(Direction.Inbound);
        bools[1] = section2.getOccupant().equals(compare);
        section2.moveConnector(Direction.Inbound);
        bools[2] = section3.getOccupant().equals(compare);
        section3.moveConnector(Direction.Inbound);
        bools[3] = section1.getOccupant().equals(compare);
        assertArrayEquals(new boolean[]{true, true, true, true}, bools);
    }

    @Test(expected = StackOverflowError.class)
    public void RecursiveTrack() throws Exception {
        IConnector section1 = new Track<String>(logger);
        section1.setConnector(section1, Direction.Inbound);
        section1.setConnector(section1, Direction.Outbound);
    }

    @Test
    public void VerifyMultiDirectionalTrackPropagation() {
        IConnector section1 = new Track(logger);
        IConnector section2 = new Track(logger);
        IConnector section3 = new Track(logger, section1, section2, -1);
        assertEquals(section1, section3.getConnector(Direction.Inbound));
        assertEquals(section2, section3.getConnector(Direction.Outbound));
    }

    @Test
    public void VerifyDirectionalTrackPropagation() {
        IConnector ic1 = new Track(logger);
        IConnector ic2 = new Track(logger, ic1, null, -1);
        assertEquals(ic1, ic2.getConnector(Direction.Inbound));
        assertEquals(ic2, ic1.getConnector(Direction.Outbound));
    }

    @Test
    public void TestIOccupyCode() throws Exception {
        IConnector section1 = new Track<StringOccupant>(logger);
        IConnector section2 = new Track<StringOccupant>(logger);
        IConnector section3 = new Track<StringOccupant>(logger);
        StringOccupant so = new StringOccupant("x");
        boolean[] bools = new boolean[4];
        boolean[] correctconnector = new boolean[4];
        section1.setConnector(section3, Direction.Outbound);
        section2.setConnector(section1, Direction.Outbound);
        section3.setConnector(section2, Direction.Outbound);
        section1.setOccupant(so);
        so.setDirection(Direction.Inbound);
        bools[0] = section1.getOccupant().equals(so);
        correctconnector[0] = so.getConnector().equals(section1);
        section1.moveConnector();
        bools[1] = section2.getOccupant().equals(so);
        correctconnector[1] = so.getConnector().equals(section2);
        section2.moveConnector();
        bools[2] = section3.getOccupant().equals(so);
        correctconnector[2] = so.getConnector().equals(section3);
        section3.moveConnector();
        bools[3] = section1.getOccupant().equals(so);
        correctconnector[3] = so.getConnector().equals(section1);
        assertArrayEquals(new boolean[]{true, true, true, true}, bools); // tests if values match
        assertArrayEquals(new boolean[]{true, true, true, true}, correctconnector);
    }

    @Test
    public void IOccupantSetConnectorTest() {
        IConnector section1 = new Track<StringOccupant>(logger);
        StringOccupant so = new StringOccupant("x");
        section1.setOccupant(so);
        assertEquals(section1, so.getConnector());
    }

    @Test(expected = Exception.class)
    public void OccupiedTrackMoveTest() throws Exception {
        IConnector section1 = new Track<StringOccupant>(logger);
        IConnector section2 = new Track<StringOccupant>(logger);
        StringOccupant so = new StringOccupant("x");
        so.setDirection(Direction.Outbound);
        StringOccupant so2 = new StringOccupant("y");
        section1.setConnector(section2, Direction.Outbound);
        section1.setOccupant(so);
        section2.setOccupant(so2);
        section1.moveConnector();
    }

    @Test(expected = ClassCastException.class)
    public void MoveOccupantTest() throws Exception {
        Track section1 = new Track<String>(logger);
        Track section2 = new Track<String>(logger);
        section1.setConnector(section2, Direction.Outbound);
        String str = "x";
        String str2 = "y";
        section1.setOccupant(str);
        section2.setOccupant(str2);
        section1.moveConnector();
    }

    @Test
    public void tracktoStringTest() {
        IConnector section1 = new Track(logger, null, null, 0);
        IConnector section2 = new Track(logger, null, null, 1);
        IConnector section3 = new Track(logger, section1, section2, 2);
        String rtn = "Track: 2";
        rtn += "; logger: " + logger.hashCode();
        rtn += "; Inbound: (Track) 0";
        rtn += "; Outbound: (Track) 1";
        rtn += "; Occupant: " + null;
        assertEquals(rtn, section3.toString());
    }

    @Test
    public void platformtoStringTest() {
        IConnector section1 = new Platform(logger, null, null, 0);
        IConnector section2 = new Platform(logger, null, null, 1);
        IConnector section3 = new Track(logger, section1, section2, 2);
        String rtn = "Track: 2";
        rtn += "; logger: " + logger.hashCode();
        rtn += "; Inbound: (Platform) 0";
        rtn += "; Outbound: (Platform) 1";
        rtn += "; Occupant: " + null;
        assertEquals(rtn, section3.toString());
    }

    @Test
    public void getConnectors() {
        IConnector section1 = new Track(logger);
        IConnector section2 = new Track(logger);
        IConnector section3 = new Track(logger, section1, section2, -1);
        assertArrayEquals(new IConnector[]{section1, section2}, section3.getConnectors().toArray());
    }

    @Test
    public void emptyToStringTest() {
        IConnector section1 = new Track(logger);
        String rtn = "Track: -1";
        rtn += "; logger: " + logger.hashCode();
        rtn += "; Inbound: " + null;
        rtn += "; Outbound: " + null;
        rtn += "; Occupant: " + null;
        assertEquals(rtn, section1.toString());
    }


    @Test
    public void loggerTest() {
        IConnector section1 = new Track(logger);
        String str = el.toString();
    }

    protected class StringOccupant extends IOccupant {
        private String occupant = null;

        public StringOccupant(String occupy) {
            super();
            occupant = occupy;
        }

        public String getOccupant() {
            return occupant;
        }

        @Override
        public String toString() {
            return occupant;
        }
    }


}