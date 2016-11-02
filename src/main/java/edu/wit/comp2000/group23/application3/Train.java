package edu.wit.comp2000.group23.application3;

import com.sun.javaws.exceptions.InvalidArgumentException;
import edu.wit.comp2000.group23.application3.Exceptions.TrainPassengerOverflowException;
import edu.wit.comp2000.group23.application3.GraphMap.IConnector;
import edu.wit.comp2000.group23.application3.GraphMap.Track;
import edu.wit.comp2000.group23.application3.Utilities.Loggable;
import edu.wit.comp2000.group23.application3.Utilities.Logger;

import java.util.ArrayList;

/**
 * Implemented by dechristophera on 10/27/2016.
 */

public class Train extends IOccupant {

    private Direction direction;

    private int maxPassengers;
    private int currentPassengers = 0;

    private boolean doorsOpen = true;

    private int id;

    private Station currentStation;
    private Platform currentPlatform;

    private ArrayList<Passenger> passengers;

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

    public void Sync() {
        for (Passenger p : this.getPassengers()) {
            p.setStation(this.currentStation);
            p.setPlatform(this.currentPlatform);
            p.Sync();
        }
    }

    public Direction getDirection() {
        return super.getDirection();
    }

    public void setDirection(Direction d) {
        super.setDirection(d);
        this.LogEvent("Changed direction to " + this.direction.name() + ".");
    }

    public int getMaxPassengers() {
        return this.maxPassengers;
    }

    public int getCurrentPassengers() {
        return this.currentPassengers;
    }

    public int getID() {
        return this.id;
    }

    public ArrayList<Passenger> getPassengers() {
        return this.passengers;
    }

    public boolean getDoorState() {
        return this.doorsOpen;
    }

    public void openDoors() {
        this.doorsOpen = true;
        this.currentPlatform.setTrainReadyToLeave(false);
        this.LogEvent("Opened doors");
    }

    public void closeDoors() {
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

        if (!this.doorsOpen) {
            //throw new IllegalArgumentException("Cannot embark passenger while doors are closed.");
            return false;
        }
        //if(this.passengers.size() == this.maxPassengers){
        //    throw new TrainPassengerOverflowException();
        //}
        this.passengers.add(p);
        p.setTrain(this);
        this.currentPassengers = this.passengers.size();
        this.LogEvent("Embark passenger: " + p.getID());
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
        this.LogEvent("Disembark passenger: " + p.getID());
    }

    public void setDirectionNoLog(Direction d) {
        super.setDirection(d);
    }

    public void LogEvent(String event) {
        this.logger.logEvent(event);
    }

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

    public String toString() {
        String str = "TRAIN " + this.getID();
        str += " - " + this.getDirection().name();
        str +=        " [" + this.getCurrentPassengers() + "/" + this.getMaxPassengers() + "] " ;
        str +=        "[DOORS" + ((this.getDoorState()) ? "OPEN" : "CLOSED") + "] " ;
        if(this.getConnector() instanceof Platform){
            str +=        "{Platform: " + ((Platform) this.getConnector()).getPlatformID() + "} " ;
        }else if(this.getConnector() instanceof Track){
            str +=        "{Track: " + ((Track) this.getConnector()).getTrackID()+"}" ;
        }
        if(this.currentStation != null){
            str +=        "{Station: " + this.getCurrentStation().getID() + "}";
        }
        return str;
    }
}
