package edu.wit.comp2000.group23.application3.Utilities;


/**
 * The logging interface implemented in each of the 5 main classes.
 */
public abstract class Loggable {
    private Logger logger;
    private String classname;
    private int classID;

    /**
     * Creates a new logger with the default classname
     *
     * @param logger Logger to push events to
     */
    public Loggable(Logger logger, int cID) {
        this.logger = logger;
        this.classname = this.getClass().getSimpleName();
        this.classID = cID;
    }

    /**
     * Creates a new logger with the default classname
     *
     * @param o      object that logger gets name of in logs
     * @param logger Logger to push events to
     */
    public Loggable(Object o, Logger logger, int cID) {
        this.logger = logger;
        this.classname = o.getClass().getSimpleName();
        this.classID = cID;
    }

    /**
     * Logs a new event
     *
     * @param event Event to log
     */
    public void logEvent(String event) {
        logger.AddEvent(new Event(classname, this.classID, event));
    }

    /**
     * Gets the logger
     *
     * @return The logger
     */
    public Logger getLogger() {
        return logger;
    }
}
