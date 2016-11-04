package edu.wit.comp2000.group23.application3;

import edu.wit.comp2000.group23.application3.Enums.Direction;
import edu.wit.comp2000.group23.application3.Exceptions.TrainDoorsClosedException;
import edu.wit.comp2000.group23.application3.Exceptions.TrainPassengerOverflowException;
import edu.wit.comp2000.group23.application3.GraphMap.IConnector;
import edu.wit.comp2000.group23.application3.GraphMap.Track;
import edu.wit.comp2000.group23.application3.Utilities.Loggable;
import edu.wit.comp2000.group23.application3.Utilities.Logger;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Implemented by dechristophera on 10/27/2016.
 */

public class Train extends IOccupant {

    private int id;
    private Direction direction;

    private int maxPassengers;
    private int currentPassengers = 0;

    private ArrayList<Passenger> passengers;

    private boolean doorsOpen = true;

    private Station currentStation;
    private Platform currentPlatform;

    private Loggable logger;

    /**
     * @param d   - enum direction (inbound or outbound)
     * @param max - max capacity
     * @param id  - train id
     * @param l   - logger
     */
    public Train(Direction d, int max, int id, Logger l) {
        this.direction = d;

        this.maxPassengers = max;

        this.id = id;

        this.passengers = new ArrayList<>();
        this.logger = new Loggable(this, l, id) {
        };

        this.setDirectionNoLog(d);
    }

    /**
     * Propagates Sync to all passengers in the train
     */
    public void Sync() {
        if (this.currentPlatform != null)
            if (!this.getDoorState())
                this.openDoors();
        for (Iterator<Passenger> itp = this.passengers.iterator(); itp.hasNext(); ) {
            Passenger p = itp.next();
            p.setStation(this.currentStation);
            p.setPlatform(this.currentPlatform);
            p.Sync(itp);
        }
        if (this.currentPlatform != null) {
            this.closeDoors();
        }
    }

    /**
     * Returns current train direction (inbound, outbound)
     *
     * @return
     */
    public Direction getDirection() {
        return super.getDirection();
    }


    /**
     * Sets the direction of the train
     *
     * @param d Direction to set the train
     */
    public void setDirection(Direction d) {
        super.setDirection(d);
        this.LogEvent("Changed direction to " + this.direction.name() + ".");
    }

    /**
     * @return max passenger capacity
     */
    public int getMaxPassengers() {
        return this.maxPassengers;
    }


    /**
     * @return number of current passengers aboard train
     */
    public int getCurrentPassengers() {
        return this.currentPassengers;
    }


    /**
     * @return train id
     */
    public int getID() {
        return this.id;
    }


    /**
     * @return ArrayList of current passengers aboard train
     */
    public ArrayList<Passenger> getPassengers() {
        return this.passengers;
    }


    /**
     * @return whether or not the door is open
     */
    public boolean getDoorState() {
        return this.doorsOpen;
    }


    /**
     * Opens train doors and sets the train as not ready to leave
     */
    public void openDoors() {
        this.doorsOpen = true;
        this.currentPlatform.setTrainReadyToLeave(false);
        this.LogEvent("Opened doors");
    }

    /**
     * Closes train doors and sets train as ready to leave
     */
    public void closeDoors() {
        this.doorsOpen = false;
        this.currentPlatform.setTrainReadyToLeave(true);
        this.LogEvent("Closed doors");
    }


    /**
     * @return station train is currently at
     */
    public Station getCurrentStation() {
        return this.currentStation;
    }


    /**
     * Sets the station the train is currently at
     *
     * @param s station
     */
    public void setCurrentStation(Station s) {
        this.currentStation = s;
    }


    /**
     * Boards passenger on train
     *
     * @param p passenger
     * @return success of boarding
     */
    public boolean embarkPassenger(Passenger p) throws TrainPassengerOverflowException, TrainDoorsClosedException {

        if (!this.doorsOpen) {
            //throw new IllegalArgumentException("Cannot embark passenger while doors are closed.");
            throw new TrainDoorsClosedException(this.getID() + "");
        }
        //if(this.passengers.size() == this.maxPassengers){
        if (this.getCurrentPassengers() == this.getMaxPassengers()) {
            throw new TrainPassengerOverflowException(this.getID() + "");
        }

        this.passengers.add(p);
        p.setTrain(this);
        this.currentPassengers = this.getPassengers().size();
        this.LogEvent("Embark passenger: " + p.getID());
        if (this.passengers.size() == this.getMaxPassengers()) {
            this.closeDoors();

            this.LogEvent("TRAIN FULL");
        }

        return true;
    }

    /**
     * Passenger leaves train
     *
     * @param p passenger
     * @return success of un-boarding
     */
    public boolean disembarkPassenger(Passenger p) throws TrainDoorsClosedException {
        if (!this.doorsOpen) {
            throw new TrainDoorsClosedException(this.getID() + "");
        }
        this.passengers.remove(p);
        p.setTrain(null);
        this.currentPassengers = passengers.size();
        this.LogEvent("Disembark passenger: " + p.getID());

        return true;
    }

    /**
     * Passenger leaves train
     *
     * @param p passenger
     * @return success of un-boarding
     */
    public boolean disembarkPassenger(Passenger p, Iterator<Passenger> itp) throws TrainDoorsClosedException {
        if (!this.doorsOpen) {
            throw new TrainDoorsClosedException(this.getID() + "");
        }
        itp.remove();
        p.setTrain(null);
        this.currentPassengers = passengers.size();
        this.LogEvent("Disembark passenger: " + p.getID());

        return true;
    }


    /**
     * Sets direction for testing in a simple manner
     *
     * @param d direction
     */
    public void setDirectionNoLog(Direction d) {
        super.setDirection(d);
    }


    /**
     * Pushes event to event logger
     *
     * @param event
     */
    public void LogEvent(String event) {
        this.logger.logEvent(event);
    }


    /**
     * Returns current track or platform train resides on
     *
     * @param c track or platform
     */
    public void setConnector(IConnector c) {
        super.setConnector(c);
        if (c instanceof Platform) {
            this.currentPlatform = (Platform) c;
            this.setCurrentStation(((Platform) c).getStation());
        } else {
            this.currentPlatform = null;
            this.currentStation = null;
        }
    }


    /**
     * @return string representation of current train
     */
    public String toString() {
        String str = "TRAIN " + this.getID();
        str += " - " + this.getDirection().name();
        str += " [" + this.getCurrentPassengers() + "/" + this.getMaxPassengers() + "] ";
        str += "[DOORS" + ((this.getDoorState()) ? "OPEN" : "CLOSED") + "] ";
        if (this.getConnector() instanceof Platform) {
            str += "{Platform: " + ((Platform) this.getConnector()).getPlatformID() + "} ";
        } else if (this.getConnector() instanceof Track) {
            str += "{Track: " + ((Track) this.getConnector()).getTrackID() + "}";
        }
        if (this.currentStation != null) {
            str += "{Station: " + this.getCurrentStation().getID() + "}";
        }
        return str;
    }
}
