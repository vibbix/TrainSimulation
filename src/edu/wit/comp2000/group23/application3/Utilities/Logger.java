package edu.wit.comp2000.group23.application3.Utilities;


import java.io.PrintStream;

/**
 * Created by beznosm on 10/24/2016.
 */
public class Logger {
    private long currentTick;
    private ArrayQueue<Event> eventQueue;
    private PrintStream ps;

    /**
     * Creates a new logger instance using System.out as the PrintStream;
     */
    public Logger() {
        this(System.out);
    }

    /**
     * Creates a new logger instance using the specified PrintStream.
     *
     * @param stream PrintStream to flush the log to.
     */
    public Logger(PrintStream stream) {
        currentTick = 0;
        eventQueue = new ArrayQueue<>();
        ps = stream;
    }

    public void Sync() {
        currentTick++;
    }

    public void AddEvent(Event event) {
        ps.println("[" + currentTick + "]" + event.toString());
    }


}
