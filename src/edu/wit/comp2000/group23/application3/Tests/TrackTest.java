package edu.wit.comp2000.group23.application3.Tests;

import edu.wit.comp2000.group23.application3.Direction;
import edu.wit.comp2000.group23.application3.GraphMap.IConnector;
import edu.wit.comp2000.group23.application3.GraphMap.Track;
import edu.wit.comp2000.group23.application3.Utilities.ILogger;
import edu.wit.comp2000.group23.application3.Utilities.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Stack;

import static org.junit.Assert.*;

/**
 * Created by beznosm on 10/27/2016.
 */
public class TrackTest {
    private static Logger logger;
    @BeforeClass
    public static void setupTest(){
        logger = new Logger();
    }
    @Test
    public void CircularTrack() throws Exception{
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
        assertArrayEquals(new boolean[]{true,true,true,true}, bools);
    }
    @Test(expected = StackOverflowError.class)
    public void RecursiveTrack() throws Exception{
        IConnector section1 = new Track<String>(logger);
        section1.setConnector(section1, Direction.Inbound);
        section1.setConnector(section1, Direction.Outbound);
    }

    @Test
    public void VerifyMultiDirectionalTrackPropagation(){
        IConnector section1 = new Track(logger);
        IConnector section2 = new Track(logger);
        IConnector section3 = new Track(logger, section1, section2);
        assertEquals(section1, section3.getConnector(Direction.Inbound));
        assertEquals(section2, section3.getConnector(Direction.Outbound));
    }
    @Test
    public void VerifyDirectionalTrackPropagation(){
        IConnector ic1 = new Track(logger);
        IConnector ic2 = new Track(logger, ic1, null);
        assertEquals(ic1, ic2.getConnector(Direction.Inbound));
        assertEquals(ic2, ic1.getConnector(Direction.Outbound));
    }

}