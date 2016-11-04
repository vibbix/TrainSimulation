package edu.wit.comp2000.group23.application3.Exceptions;
/**
 * Comp2000 - Data Structures
 * Application 3 - Queues (TrainSim)
 * Group #23
 *
 * Team:
 * Andrew DeChristopher
 * Mark Beznos
 * Bryon Kucharski
 * Tin Wong
 * Jeffery Lindeland
 * Shakib Hassan
 */

/**
 * Exception thrown when a action requires the trains doors to be open
 */
public class TrainDoorsClosedException extends Exception {

    /**
     * Creates a new TrainDoorsClosedExcepetion
     */
    public TrainDoorsClosedException() {
        this("");
    }

    /**
     * /**
     * Creates a new TrainDoorsClosedException with message
     *
     * @param message Message to include
     */
    public TrainDoorsClosedException(String message) {
        super(message);
    }
}
