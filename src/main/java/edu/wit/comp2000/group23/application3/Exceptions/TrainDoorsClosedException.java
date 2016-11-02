package edu.wit.comp2000.group23.application3.Exceptions;

import edu.wit.comp2000.group23.application3.Train;

/**
 * Exception thrown when a action requires the trains doors to be open
 */
public class TrainDoorsClosedException extends Exception {

    /**
     * Creates a new TrainDoorsClosedExcepetion
     */
    public TrainDoorsClosedException(){this("");}

    /**
     * /**
     * Creates a new TrainDoorsClosedException with message
     * @param message Message to include
     */
    public TrainDoorsClosedException(String message){
        super(message);
    }
}
