package edu.wit.comp2000.group23.application3;

import edu.wit.comp2000.group23.application3.Exceptions.TrainDoorsClosedException;
import edu.wit.comp2000.group23.application3.Utilities.Loggable;
import edu.wit.comp2000.group23.application3.Utilities.Logger;

import java.util.Iterator;

/**
 * /**
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

    // private field variables
    private Station destination;
    private Station currentStation;
    private Platform currentPlatform;
    private Train currentTrain;
    private boolean onTrain;
    private int passengerID;

    // constructors

    /**
     * Default constructor.
     *
     * @param logger
     */
    public Passenger(Logger logger) {
        this(logger, null, null, -1);
    }

    /**
     * Constructor, takes in logger, destination, currentPlatform,
     * currentStation, pID
     *
     * @param logger         logger to use
     * @param destination    The station the passenger wants to go to
     * @param currentStation The station the passenger is currently at
     * @param pID            Passenger ID
     */
    public Passenger(Logger logger, Station destination, Station currentStation, int pID) {
        super(logger, pID);
        this.onTrain = false;
        this.destination = destination;
        this.currentStation = currentStation;
        this.passengerID = pID;
        this.currentTrain = null;
        super.logEvent("Created new passenger: id=" + this.getID() +
                ";Arrival=" + (this.currentStation != null ? currentStation.getName() : "null") +
                ";Destination=" + (this.destination != null ? destination.getName() : "null"));
        this.getOnPlatform();
    }

    // passenger methods (core methods)

    /**
     * Gets the passenger onto the platform
     */
    private void getOnPlatform() {
        // at a station, not in queue
        try {
            this.setPlatform(this.currentStation.getRoute(this.destination));
            this.currentPlatform.enqueuePassenger(this);
            super.logEvent("Enqueueing to platform #" + this.currentPlatform.getPlatformID());
        } catch (Exception ex) {
            super.logEvent("Passenger ran into door. Vaporized.");
        }
    }

    /**
     * Sync method, updates everytime passenger is at a new station (on train)
     *
     * @param itp The iterator for passenger, so the Passenger can be safely removed from the passenger list
     */
    public void Sync(Iterator<Passenger> itp) {
        // at a station, not in queue
        if (this.currentStation == this.destination) {
            if (this.onTrain) {
                this.disembarkTrain(itp);
            }

        }
    }

    /**
     * Disembarks the train and add itself to the list of arrived passengers
     */
    public void disembarkTrain(Iterator<Passenger> itp) {
        super.logEvent("Disembarking train #" + this.currentTrain.getID());
        try {
            this.currentTrain.disembarkPassenger(this, itp);
            //this.currentTrain.disembarkPassenger(this, itp)
        } catch (TrainDoorsClosedException tdce) {
            super.logEvent("Dumb passenger walks into door. Vaporized.");
        }
        this.currentStation.addArrivingPassenger(this);
    }

    /**
     * mutator method, sets the station the passenger is currently on
     *
     * @param station
     */
    public void setStation(Station station) {
        this.currentStation = station;
    }

    /**
     * accessor method, gets the platform of the passenger
     *
     * @return
     */
    public Platform getPlatform() {
        return this.currentPlatform;
    }

    /**
     * mutator method, sets the platform the passenger is currently on
     *
     * @param platform platform
     */
    public void setPlatform(Platform platform) {
        this.currentPlatform = platform;
        if (platform != null)
            this.currentStation = this.currentPlatform.getStation();
    }

    /**
     * accessor method, gets the destination of the passenger
     *
     * @return Station
     */
    public Station getDestination() {
        return this.destination;
    }

    /**
     * accessor method, gets the current station of the passenger
     *
     * @return Station
     */
    public Station getCurrentStation() {
        return this.currentStation;
    }

    /**
     * accessor method, gets if passenger is on current platform
     *
     * @return Platform
     */
    public Platform getCurrentPlatform() {
        return this.currentPlatform;
    }

    /**
     * accessor method, gets if passenger is on train
     *
     * @return boolean
     */
    public boolean getOnTrain() {
        return this.onTrain;
    }

    // mutator method

    /**
     * accessor method, sets passenger id
     *
     * @return int
     */
    public int getID() {
        return this.passengerID;
    }

    /**
     * If the passenger is on a train, it will return a pointer to said train
     *
     * @return The train the passenger is on
     */
    public Train getTrain() {
        return this.currentTrain;
    }

    /**
     * mutator method, sets the train the passenger is currently on
     *
     * @param train
     */
    public void setTrain(Train train) {
        this.currentTrain = train;
        if (train == null) {
            this.onTrain = false;
        } else {
            this.onTrain = true;
        }
    }

    /**
     * toString method
     *
     * @return String
     */
    @Override
    public String toString() {
        return "Passenger info: " + "\nPassenger ID: " + this.getID() +
                "\nDestination: " + this.getDestination() +
                "\nCurrent Station: " + this.getCurrentStation() +
                "\nPlatform: " + this.getCurrentPlatform() +
                "\nOn train: " + this.getOnTrain();
    }


}
