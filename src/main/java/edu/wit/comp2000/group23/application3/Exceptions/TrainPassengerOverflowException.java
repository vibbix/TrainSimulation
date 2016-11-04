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
 *
 * Thrown when the train has a overflow of passengers.
 */

public class TrainPassengerOverflowException extends Exception {
    public TrainPassengerOverflowException() {
        this(null);
    }

    public TrainPassengerOverflowException(String message) {
        super(message);
    }
}
