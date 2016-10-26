package edu.wit.comp2000.group23.application3.Utilities;


/**
 * The logging interface implemented in each of the 5 main classes.
 */
public interface ILogger {
    Logger logger = null;

    /**
     * Log's an event spawned by the
     * @param event Event to log
     */
    void LogEvent(Event event);
}
