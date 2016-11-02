package edu.wit.comp2000.group23.application3.Tests;

import edu.wit.comp2000.group23.application3.Platform;
import edu.wit.comp2000.group23.application3.Station;
import edu.wit.comp2000.group23.application3.Direction;
import edu.wit.comp2000.group23.application3.TrainRoute;
import edu.wit.comp2000.group23.application3.Utilities.Logger;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Assert;
import org.junit.Test;
/**
 * Created by lindelandj on 10/31/2016.
 */
public class PlatformTest {

    @Test
    public void CreatePlatformTest(){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        TrainRoute tr = new TrainRoute(new Logger(), 0);
        Platform p = new Platform(new Logger(ps), Direction.Inbound, new Station(new Logger(), tr, 0) , 1);
        Assert.assertEquals(p.getPlatformDirection(), Direction.Inbound);

    }

    @Test
    public void TrainReadyTest(){
        TrainRoute tr = new TrainRoute(new Logger(), 0);
        Platform p = new Platform(new Logger(), Direction.Inbound, new Station(new Logger(), tr, 0), 1);
        p.setTrainReadyToLeave(false);
        Assert.assertEquals(p.isTrainReadyToLeave(), false);
        p.setTrainReadyToLeave(true);
        Assert.assertEquals(p.isTrainReadyToLeave(), true);
    }

    @Test
    public void setStationTest(){
        TrainRoute tr = new TrainRoute(new Logger(), 0);
        Station firstStation = new Station(new Logger(), tr, 0);
        Platform p = new Platform(new Logger(), Direction.Inbound, firstStation, 1);
        Station secondStation = new Station(new Logger(), tr, 1);
        Assert.assertEquals(p.getStation(), firstStation);
        p.setStation(secondStation);
        Assert.assertEquals(p.getStation(), secondStation);
    }

    @Test
    public void setPlatformDirectionTest(){
        TrainRoute tr = new TrainRoute(new Logger(), 0);
        Platform p = new Platform(new Logger(), Direction.Inbound, new Station(new Logger(), tr, 0), 1);
        Assert.assertEquals(p.getPlatformDirection(), Direction.Inbound);
        p.setPlatformDirection(Direction.Outbound);
        Assert.assertEquals(p.getPlatformDirection(), Direction.Outbound);
    }

    

}
