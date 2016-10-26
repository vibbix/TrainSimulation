package edu.wit.comp2000.group23.application3.Tests;

import edu.wit.comp2000.group23.application3.Utilities.Event;
import edu.wit.comp2000.group23.application3.Utilities.Logger;
import org.junit.Test;
import org.junit.Assert;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Created by beznosm on 10/26/2016.
 */
public class LoggerTest {
    @Test
    public void FlushQueueTest() throws Exception {
        //system.out capturer.
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        //catches system.out
        Logger lg = new Logger(ps);
        lg.AddEvent(new Event("classname", 1, "message"));
        Assert.assertEquals("", new String(baos.toByteArray(), StandardCharsets.UTF_8));
        lg.FlushQueue();
        Assert.assertEquals("[0][classname\t\t][1\t][message]", new String(baos.toByteArray(), StandardCharsets.UTF_8).trim());
    }

    @Test
    public void TickCountTest() throws Exception {
        //system.out capturer
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        //catches system.out
        Logger lg = new Logger(ps);
        lg.AddEvent(new Event("classname", 1, "message"));
        lg.FlushQueue();
        lg.AddEvent(new Event("classname", 2, "message"));
        lg.FlushQueue();
        Assert.assertEquals("[0][classname\t\t][1\t][message]\n[1][classname\t\t][2\t][message]",
                baos.toString().trim().replace("\r", ""));

    }
    @Test
    public void ValidateDefaultLogger() throws Exception{
        java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(out));
        Logger logger = new Logger();
        logger.AddEvent(new Event("classname", 1, "message"));
        logger.FlushQueue();
        Assert.assertEquals("[0][classname\t\t][1\t][message]", out.toString().trim());
    }

}