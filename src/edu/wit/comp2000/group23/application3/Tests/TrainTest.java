package edu.wit.comp2000.group23.application3.Tests;

import edu.wit.comp2000.group23.application3.Direction;
import edu.wit.comp2000.group23.application3.Train;
import edu.wit.comp2000.group23.application3.Utilities.Logger;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Created by beznosm on 10/29/2016.
 */
public class TrainTest {
    @Test
    public void CreateTrainTest(){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        Train t = new Train(Direction.Inbound, 100, -1, new Logger(ps));
        Assert.assertEquals(100, t.getMaxPassengers());
    }
}