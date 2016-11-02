package edu.wit.comp2000.group23.application3.Tests;

import edu.wit.comp2000.group23.application3.Utilities.Loggable;
import edu.wit.comp2000.group23.application3.Utilities.Logger;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by beznosm on 10/28/2016.
 */
class EncapsulatedLoggable extends Loggable {
    private ByteArrayOutputStream baos = new ByteArrayOutputStream();

    //private PrintStream ps = new PrintStream(baos);
    public EncapsulatedLoggable() {
        this(new ByteArrayOutputStream());
    }

    private EncapsulatedLoggable(ByteArrayOutputStream baos) {
        super(new Logger(new PrintStream(baos)), -1);
        this.baos = baos;
    }

    @Override
    public String toString() {
        return new String(baos.toByteArray(), StandardCharsets.UTF_8).trim();
    }
}

public class LoggableTests {
    @Test
    public void NamePropagationtest() {
        EncapsulatedLoggable el = new EncapsulatedLoggable();
        el.logEvent("Hello world");
        assertEquals("[0][EncapsulatedLoggable\t\t][-1" + "\t][Hello world]", el.toString());
    }
}
