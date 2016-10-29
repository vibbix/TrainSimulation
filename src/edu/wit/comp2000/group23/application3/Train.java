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

    public void openDoors(){
        this.doorsOpen = true;
        currentPlatform.setTrainReadyToLeave(false);
        this.LogEvent("[TRAIN] " + this.id + " - opened doors.");
    }

    public void closeDoors(){
        this.doorsOpen = false;
        currentPlatform.setTrainReadyToLeave(true);
        this.LogEvent("[TRAIN] " + this.id + " - closed doors.");
    }

    public Station getCurrentStation() {
        return this.currentStation;
    }

    public void setCurrentStation(Station s) {
        this.currentStation = s;
    }

    public boolean embarkPassenger(Passenger p) {
        this.passengers.add(p);
        currentPassengers = passengers.size();
        this.LogEvent("[TRAIN] " + this.id + " - embark passenger: " + p.getPassengerID());

        if (passengers.size() == maxPassengers - 1) {
            this.closeDoors();
            this.LogEvent("[TRAIN] " + this.id + " - train full.");
        }

        return true;
    }

    public void disembarkPassenger(Passenger p) {
        this.passengers.remove(p);
        currentPassengers = passengers.size();
        this.LogEvent("[TRAIN] " + this.id + " - disembark passenger: " + p.getPassengerID());

    }

    public void changeDirection() {
        if (this.direction.equals(Direction.Inbound)) {
            setDirection(Direction.Outbound);
        } else {
            setDirection(Direction.Inbound);
        }
    }

    public void setDirection(Direction d) {
        super.setDirection(d);
        this.LogEvent("[TRAIN] " + this.id + " - changed direction to " + this.direction.name() + ".");
    }

    private void LogEvent(String event) {
        this.logger.logEvent(event);
    }

}
