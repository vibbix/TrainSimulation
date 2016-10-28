package edu.wit.comp2000.group23.application3;

import edu.wit.comp2000.group23.application3.Utilities.Loggable;
import edu.wit.comp2000.group23.application3.Utilities.Logger;

/**
 * Created by beznosm on 10/24/2016.
 * <p>
 * STEPS OF THE PASSENGER:
 * <p>
 * Leave home: Proceed to departure station
 * <p>
 * Enter station Proceed to platform for desired direction of travel Move into
 * positive at the end of the line of passengers who arrived before you
 * <p>
 * Direction: inbound or outbound?
 * <p>
 * Wait for passengers to disembark, then embark
 * <p>
 * ...
 * <p>
 * If destination == destination, wait for doors to open
 * disembark
 * <p>
 * Leave station
 * <p>
 * HOW INBOUND | OUTBOUND WORKS:
 * <p>
 * Each station is assigned a numeric value, we would use compareTo in this case
 * >1 means inbound, <1 means outbound, =0 means the passenger has arrived
 * <p>
 * IMPLEMENT THIS IN STATION/PLATFORM CLASS
 */
public class Passenger extends Loggable {

    //private field variables
    private Station destination;
    private Station currentStation;
    private Platform currentPlatform;
    private boolean onTrain;

    //constructors
    public Passenger(Logger l){
        this(l, null, null, null);
    }

    public Passenger(Logger l, Station destination, Platform currentPlatform, Station currentStation) {
        super(l);
        onTrain = false;
        this.destination = destination;
        this.currentPlatform = currentPlatform;
        this.currentStation = currentStation;
    }

    //passenger methods (core methods)

    /**
     * When the passenger enters the station
     */
    public void enterStation(Station station) {
        currentStation = station;
    }

    /**
     * Inbound or outbound depending on the destination (use compareTo here?)
     */
    public void enterPlatform() {

    }

    //waitForPassengers and enterTrain both use queues

    /**
     * If train arrivals, wait for Passengers
     */
    public void waitForPassengers() {

    }

    /**
     * Enter train when passengers leave the train
     */
    public void enterTrain() {
        onTrain = true;
    }

    /**
     * Disembark.
     */
    public void leaveTrain() {

    }

    /**
     * Leave station.
     */
    public void leaveStation() {

    }

    //accessor methods
    public Station getDestination() {
        return destination;
    }


    public Station getCurrentStation() {
        return currentStation;
    }

    public Platform getCurrentPlatform() {
        return currentPlatform;
    }

    public boolean onTrain() {
        return onTrain;
    }

    @Override
    public String toString() {
        return "Passenger info: "+ "\nDestination: " + getDestination() +
                "\nCurrent Station: " + getCurrentStation() + "\nPlatform: " + getCurrentPlatform() + "\nOn train: " + onTrain();
    }

}
