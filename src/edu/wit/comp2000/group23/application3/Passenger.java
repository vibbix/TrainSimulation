package edu.wit.comp2000.group23.application3;

import edu.wit.comp2000.group23.application3.Utilities.Event;
import edu.wit.comp2000.group23.application3.Utilities.ILogger;

/**
 * Created by beznosm on 10/24/2016.
 *
 * STEPS OF THE PASSENGER:
 *
 * Leave home: Proceed to departure station
 *
 * Enter station Proceed to platform for desired direction of travel Move into
 * positive at the end of the line of passengers who arrived before you
 *
 * Direction: inbound or outbound?
 *
 * Wait for passengers to disembark, then embark
 *
 * ...
 *
 * If destination == destination, wait for doors to open
 * 	disembark
 *
 * Leave station
 *
 * HOW INBOUND | OUTBOUND WORKS:
 *
 * Each station is assigned a numeric value, we would use compareTo in this case
 * >1 means inbound, <1 means outbound, =0 means the passenger has arrived
 *
 * IMPLEMENT THIS IN STATION/PLATFORM CLASS
 */
public class Passenger implements iPassenger, ILogger {

    //private field variables
    private static String destination;
    private static String name;
    private static String currentStation;
    private static String currentPlatform;
    private static boolean onTrain;

    //constructors
    public Passenger() {

    }

    public Passenger(String destination, String name) {
        Passenger.destination = destination;
        Passenger.name = name;
        onTrain = false;
        currentStation = "non";
    }

    //passenger methods (core methods)
    /**
     * When the passenger enters the station
     */
    public void enterStation(String station) {
        currentStation = station;
    }

    /**
     * Inbound or outbound depending on the destination (use compareTo here?)
     */
    public void enterPlatform() {

    }

    @Override
    public void LogEvent(Event event) {

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
    public static String getDestination(){
        return destination;
    }

    public static String getName(){
        return name;
    }

    public static String getCurrentStation(){
        return currentStation;
    }

    public static String getCurrentPlatform(){
        return currentPlatform;
    }

    public static boolean onTrain(){
        return onTrain;
    }

    @Override
    public String toString(){
        return "Passenger info: " + "\nName: " + getName() + "\nDestination: " + getDestination() +
                "\nCurrent Station: " + getCurrentStation() + "\nPlatform: " + getCurrentPlatform() + "\nOn train: " + onTrain();
    }

    public static void main(String[] args) {
        Passenger passenger = new Passenger("Boston", "Tin");

        passenger.enterStation("Longwood");

        System.out.println(passenger);
    }
}
