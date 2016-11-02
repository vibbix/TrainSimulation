package edu.wit.comp2000.group23.application3.Exceptions;

/**
 * Created by dechristophera on 11/2/16.
 */
public class TrainDoorsClosedException extends Throwable {
    public TrainDoorsClosedException() {
        this(null);
    }

    public TrainDoorsClosedException(String message) {
        super(message);
    }
}
