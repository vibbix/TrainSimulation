package edu.wit.comp2000.group23.application3;

import edu.wit.comp2000.group23.application3.Utilities.Loggable;
import edu.wit.comp2000.group23.application3.Utilities.Logger;

/**
 * Created by beznosm on 10/24/2016.
 */
public class Passenger extends Loggable {

	// private field variables
	private Station destination;
	private Station currentStation;
	private Platform currentPlatform;
	private Train currentTrain;
	private boolean onTrain;
	private int passengerID;
	private Loggable logger;

	// constructors
	/**
	 * Default constructor.
	 * 
	 * @param logger l
	 *            
	 */
	public Passenger(Logger l) {
		this(l, null, null, null, -1);
	}

	/**
	 * Constructor, takes in logger, destination, currentPlatform,
	 * currentStation, pID
	 * 
	 * @param l
	 * @param destination
	 * @param currentPlatform
	 * @param currentStation
	 * @param pID
	 */
	public Passenger(Logger l, Station destination, Platform currentPlatform, Station currentStation, int pID) {
		super(l, pID);
		this.onTrain = false;
		this.destination = destination;
		this.currentPlatform = currentPlatform;
		this.currentStation = currentStation;
		this.passengerID = pID;
		this.currentTrain = null;
	}

	// passenger methods (core methods)

	/**
	 * Sync method, updates everytime passenger is at a new station (on train)
	 */
	public void Sync() {
		// at a station, not in queue
		if (this.currentPlatform == null && this.currentTrain == null) {
			this.setPlatform(this.currentStation.getRoute(this.destination));
			this.currentPlatform.enqueuePassenger(this);
			return;
		}
		if (this.currentStation.equals(this.destination)) {
			if (this.onTrain) {
				this.disembarkTrain();
			}
		}

	}

	/**
	 * Disembarks the train and add itself to the list of arrived passengers
	 */
	public void disembarkTrain() {
		this.setTrain(null);
		this.currentPlatform.getStation().addArrivingPassenger(this);
		
		this.logEvent("Passenger " + this.getPassengerID() + " disembark.");
	}

	// accessor methods

	/**
	 * accessor method, gets the station of the passenger
	 * 
	 * @return
	 */
	public Station getStation() {
		return this.currentStation;
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

	/**
	 * accessor method, gets the id of the train
	 * @return
	 */
	public int getTrain(){
		return this.currentTrain.getID();
	}
	
	/**
	 * accessor method, sets passenger id
	 * 
	 * @return int
	 */
	public int getPassengerID() {
		return this.passengerID;
	}

	// mutator method

	/**
	 * mutator method, sets passenger id
	 * 
	 * @param int
	 *            passengerID
	 */
	public void setPassengerID(int passengerID) {
		this.passengerID = passengerID;
	}

	/**
	 * mutator method, sets the train the passenger is currently on
	 * 
	 * @param Train
	 *            t
	 */
	public void setTrain(Train t) {
		this.currentTrain = t;
		if (t.equals(null)) {
			this.onTrain = false;
			this.LogEvent("Passenger " + this.getPassengerID() + " is off the train.");
		} else {
			this.onTrain = true;
			this.LogEvent("Passenger " + this.getPassengerID() + " is on the train.");
		}
	}

	/**
	 * mutator method, sets the station the passenger is currently on
	 * 
	 * @param Station
	 *            s
	 */
	public void setStation(Station s) {
		this.currentStation = s;
	}

	/**
	 * mutator method, sets the platform the passenger is currently on
	 * 
	 * @param Platform
	 * 
	 */
	public void setPlatform(Platform p) {
		this.currentPlatform = p;
	}

	/**
	 * toString method
	 * 
	 * @return String
	 */
	@Override
	public String toString() {
		return "Passenger info: " + "\nPassenger ID: " + this.getPassengerID() + "\nDestination: "
				+ this.getDestination() + "\nCurrent Station: " + this.getCurrentStation() + "\nPlatform: "
				+ this.getCurrentPlatform() + "\nOn train: " + this.getOnTrain();
	}
	
	/**
	 * Log events for Passenger class
	 */
	public void LogEvent(String event){
		this.logger.logEvent(event);
	}
}