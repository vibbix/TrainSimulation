package edu.wit.comp2000.group23.application3.Tests;

import edu.wit.comp2000.group23.application3.Direction;
import edu.wit.comp2000.group23.application3.GraphMap.IConnector;
import edu.wit.comp2000.group23.application3.GraphMap.Track;
import edu.wit.comp2000.group23.application3.IOccupant;
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
        toStr += "; couldn't get back connector";
        Assert.assertEquals(toStr, ic.toString());
    }

    @Test
    public void ConnectorToStringTest() {
        IConnector s1 = new Track(logger);
        IOccupant ic = new IOccupant() {
        };
        ic.setConnector(s1);
        ic.setDirection(Direction.Outbound);
        String toStr = "Direction: " + ic.getDirection().toString();
        toStr += "; Connector: " + s1.toString();
        Assert.assertEquals(toStr, ic.toString());
    }
}
