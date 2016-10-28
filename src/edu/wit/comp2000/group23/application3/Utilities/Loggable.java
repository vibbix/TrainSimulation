package edu.wit.comp2000.group23.application3.Utilities;


/**
 * The logging interface implemented in each of the 5 main classes.
 */
public abstract class Loggable {
    private Logger logger;
    private String classname;

    /**
     * Creates a new logger with the default classname
     * @param logger Logger to push events to
     */
    public Loggable(Logger logger){
        this.logger = logger;
        this.classname = this.getClass().getSimpleName();
    }

    /**
     * Creates a new logger with a specified classname
     * @param logger Logger to push events to
     * @param name Name of the class
     */
    public Loggable(Logger logger, String name){
        this.logger = logger;
        this.classname = name;
    }
    /**
     * Logs a new event
     * @param event Event to log
     */
    public void logEvent(String event){
        logger.AddEvent(new Event(classname, this.hashCode(), event));
    }

    /**
     * Gets the logger
     * @return The logger
     */
    public Logger getLogger(){
        return logger;
    }
}
