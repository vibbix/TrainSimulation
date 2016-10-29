package edu.wit.comp2000.group23.application3;

import edu.wit.comp2000.group23.application3.Utilities.Loggable;
import edu.wit.comp2000.group23.application3.Utilities.Logger;

import java.util.ArrayList;

/**
 * Implemented by dechristophera on 10/27/2016.
 */

public class Train extends IOccupant {

    private Direction direction;
    private int speed;

    private int maxPassengers;
    private int currentPassengers = 0;

    private boolean doorsOpen = true;

    private int id;

    private Station currentStation;
    private Platform currentPlatform;

    private ArrayList<Passenger> passengers;

    private Loggable logger;

    /**
     * @param d - enum direction (inbound or outbound)
     * @param s - int speed constant
     */
    public Train(Direction d, int s, int max, int id, Logger l) {
        this.direction = d;
        this.speed = s;

        this.maxPassengers = max;

        this.id = id;

        this.passengers = new ArrayList<>();

        this.logger = new Loggable(l, id) {
        };
    }

    public void Sync() {

        for(Passenger p : this.getPassengers()) {
            p.setCurrentStation(this.currentStation);
            p.setCurrentPlatform(this.currentPlatform);
            p.Sync();
        }
    }

    public Direction getDirection() {
        return this.direction;
    }

    public int getSpeed() {
        return this.speed;
    }

    public int getMaxPassengers() {
        return this.maxPassengers;
    }

    public int getCurrentPassengers() {
        return this.currentPassengers;
    }

    public int getId() {
        return this.id;
    }

    public ArrayList<Passenger> getPassengers() {
        return this.passengers;
    }

    public void openDoors(){
        this.doorsOpen = true;
        this.currentPlatform.setTrainReadyToLeave(false);
        this.LogEvent("Opened doors");
    }

    public void closeDoors(){
        this.doorsOpen = false;
        this.currentPlatform.setTrainReadyToLeave(true);
        this.LogEvent("Closed doors");
    }

    public Station getCurrentStation() {
        return this.currentStation;
    }

    public void setCurrentStation(Station s) {
        this.currentStation = s;
    }

    public boolean embarkPassenger(Passenger p) {
        if(!this.doorsOpen){return false;}

        this.passengers.add(p);
        p.setTrain(this);
        currentPassengers = passengers.size();
        this.LogEvent("Embark passenger: " + p.getPassengerID());

        if (this.passengers.size() == this.maxPassengers) {
            this.closeDoors();
            this.LogEvent("TRAIN FULL");
            return false;
        }

        return true;
    }

    public void disembarkPassenger(Passenger p) {
        this.passengers.remove(p);
        p.setTrain(null);
        this.currentPassengers = passengers.size();
        this.LogEvent("Disembark passenger: " + p.getPassengerID());
    }

    public void setDirection(Direction d) {
        super.setDirection(d);
        this.LogEvent("Changed direction to " + this.direction.name() + ".");
    }

    private void LogEvent(String event) {
        this.logger.logEvent(event);
    }

}
