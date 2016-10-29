package edu.wit.comp2000.group23.application3;

import edu.wit.comp2000.group23.application3.Utilities.Event;
<<<<<<< HEAD
import edu.wit.comp2000.group23.application3.Utilities.Loggable;
import edu.wit.comp2000.group23.application3.Utilities.Logger;
=======
import edu.wit.comp2000.group23.application3.Utilities.ILogger;
import edu.wit.comp2000.group23.application3.Utilities.Logger;

import java.util.ArrayList;
>>>>>>> 9954c5e81420af60d3bfcc215f8edeca7923b51b

/**
 * Implemented by dechristophera on 10/27/2016.
 */
<<<<<<< HEAD
//Java doesn't have multiple inheritance so you can't extend both IOccupant & Loggable
public class Train extends IOccupant {
    private Loggable logger;

    public Train(Logger l){
        logger = new Loggable(l) {};
    }
    private void LogEvent(String event){
        logger.logEvent(event);
    }
    public boolean canMove(){
        return true;
=======
public class Train implements ILogger {

    private Direction direction;
    private int speed;

    private int maxPassengers;
    private int currentPassengers = 0;

    private int id;

    private Station currentStation;

    private ArrayList<Passenger> passengers;

    private Logger logger;

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

        this.logger = l;
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

    public Station getCurrentStation() {
        return this.currentStation;
    }

    public void setCurrentStation(Station s) {
        this.currentStation = s;
    }

    public void embarkPassenger(Passenger p) {
        this.passengers.add(p);
        currentPassengers = passengers.size();
        LogEvent(new Event("Train", this.id, "[TRAIN] " + this.id + " embark passenger: " + p.getId()));
    }

    public void disembarkPassenger(Passenger p) {
        this.passengers.remove(p);
        currentPassengers = passengers.size();
        LogEvent(new Event("Train", this.id, "[TRAIN] " + this.id + " disembark passenger: " + p.getId()));

    }

    public void changeDirection() {
        if (this.direction.equals(Direction.Inbound)) {
            setDirection(Direction.Outbound);
        } else {
            setDirection(Direction.Inbound);
        }
    }

    private void setDirection(Direction d) {
        this.direction = d;
        LogEvent(new Event("Train", this.id, "[TRAIN] " + this.id + " changed direction to " + this.direction.name() + "."));
    }

    @Override
    public void LogEvent(Event event) {
        this.logger.AddEvent(event);
>>>>>>> 9954c5e81420af60d3bfcc215f8edeca7923b51b
    }
}
