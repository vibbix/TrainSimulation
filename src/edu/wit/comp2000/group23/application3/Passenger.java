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
    private Train currentTrain;
    private boolean onTrain;
    private int passengerID;

    //constructors
    public Passenger(Logger l) {
        this(l, null, null, null, -1);
    }

    public Passenger(Logger l, Station destination, Platform currentPlatform, Station currentStation, int pID) {
        super(l, pID);
        this.onTrain = false;
        this.destination = destination;
        this.currentPlatform = currentPlatform;
        this.currentStation = currentStation;
        this.passengerID = pID;
        this.currentTrain = null;
    }

    //passenger methods (core methods)
    public void Sync() {
        //at a station, not in queue
        if(this.currentPlatform == null && this.currentTrain == null){
            this.setPlatform(this.currentStation.getRoute(this.destination));
            this.currentPlatform.enqueuePassenger(this);
            return;
        }
        if (this.currentStation == this.destination) {
            if (this.onTrain) {
                this.disembarkTrain();
            }
        }

    }

    public Station getStation(){
        return this.currentStation;
    }

    public void setStation(Station s) {
        this.currentStation = s;
    }

    public Platform getPlatform(){
        return this.currentPlatform;
    }

    public void setPlatform(Platform p) {
        this.currentPlatform = p;
    }

    public Train getTrain() {
        return this.currentTrain;
    }

    public void setTrain(Train t) {
        this.currentTrain = t;
        if(t == null){
            this.onTrain = false;
        }else {
            this.onTrain = true;
        }
    }


    /**
     * Disembarks the train and add itself to the list of arrived passengers
     */
    public void disembarkTrain() {
        this.onTrain = false;
        this.currentTrain = null;
        this.currentPlatform.getStation().addArrivingPassenger(this);
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

    public int getPassengerID() {
        return passengerID;
    }

    public void setPassengerID(int passengerID) {
        this.passengerID = passengerID;
    }

    @Override
    public String toString() {
        return "Passenger info: " + "\nDestination: " + getDestination() +
                "\nCurrent Station: " + getCurrentStation() + "\nPlatform: " + getCurrentPlatform() + "\nOn train: " + onTrain();
    }


}
