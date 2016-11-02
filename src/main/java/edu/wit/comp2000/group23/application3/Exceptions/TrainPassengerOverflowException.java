package edu.wit.comp2000.group23.application3.Exceptions;

/**
 * Created by dechristophera on 10/29/16.
 */
public class TrainPassengerOverflowException extends Exception {
    public TrainPassengerOverflowException() {
        this(null);
    }

    public TrainPassengerOverflowException(String message) {
        super(message);
    }
}
