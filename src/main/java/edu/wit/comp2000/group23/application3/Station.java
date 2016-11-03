package edu.wit.comp2000.group23.application3;

import edu.wit.comp2000.group23.application3.Enums.Direction;
import edu.wit.comp2000.group23.application3.Exceptions.TrainDoorsClosedException;
import edu.wit.comp2000.group23.application3.Exceptions.TrainPassengerOverflowException;
import edu.wit.comp2000.group23.application3.Utilities.Loggable;
import edu.wit.comp2000.group23.application3.Utilities.Logger;

import java.util.ArrayList;

/**
 * Created by beznosm on 10/24/2016.
 */
public class Station extends Loggable {

    private Platform inbound;
    private Platform outbound;
    private ArrayList<Passenger> arrivedPassengers;
    private TrainRoute route;
    private String name;
    private Loggable logger;
    private int stationID;

    /**
     * Creates a new station
     *
     * @param l Logger to pass in
     * @param tr Train route to set to the station
     * @param sID ID of station
     *
     */
    public Station(Logger l, TrainRoute tr, int sID) {
        super(l, sID);
        this.route = tr;
        this.stationID = sID;
        this.arrivedPassengers = new ArrayList<>();
        inbound = new Platform(l, Direction.Inbound, this, sID * 10);
        outbound = new Platform(l, Direction.Outbound, this, (sID * 10) + 1);
    }

    /**
     * Creates a new station
     *
     * @param l Logger to pass in
     * @param tr Train route to set to the station
     * @param sID ID of station
     * @param name Name of the station
     *
     */
    public Station(Logger l, TrainRoute tr, int sID, String name) {
        super(l, sID);
        this.route = tr;
        this.arrivedPassengers = new ArrayList<>();
        this.name = name;
        this.stationID = sID;
        inbound = new Platform(l, Direction.Inbound, this, sID * 10);
        outbound = new Platform(l, Direction.Outbound, this, (sID * 10) + 1);
    }

    //getters/setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return stationID;
    }

    public void setStationID(int stationID) {
        this.stationID = stationID;
    }

    public TrainRoute getRoute() {
        return route;
    }

    public void setRoute(TrainRoute route) {
        this.route = route;
    }

    public Platform getInbound() {
        return inbound;
    }

    public void setInbound(Platform inbound) {
        this.inbound = inbound;
    }

    public Platform getOutbound() {
        return outbound;
    }

    public void setOutbound(Platform outbound) {
        this.outbound = outbound;
    }

    public ArrayList<Passenger> getArrivedPassengers() {
        return arrivedPassengers;
    }

    /**
     * gets the platform for a passenger to enqueue
     *
     * @param d direction of the passenger
     *
     * @return Platform to enqueue
     */
    public Platform getPlatform(Direction d) {
        if (d == Direction.Outbound)
            return outbound;
        return inbound;
    }
    /**
     * Fill train from inbound and outbound platform
     */
    public void Sync() {
        this.getPlatforms().stream().filter(p -> p.getOccupant() != null).forEach(p -> {
            Train t = p.getOccupant();
            if(!t.getDoorState())
                t.openDoors();
            while (p.canDequeuePassenger() && !p.isTrainReadyToLeave()) {
                if(t.getCurrentPassengers() == t.getMaxPassengers())
                    break;
                Passenger passenger = p.dequeuePassenger();
                super.logEvent("Dequeueing Passenger #" + passenger.getID() + " onto train #" + t.getID());
                try{
                    t.embarkPassenger(passenger);
                }catch (TrainDoorsClosedException e) {
                    this.logEvent("FATAL ERROR - TrainDoorsClosedException");
                    System.exit(0);
                } catch (TrainPassengerOverflowException ex) {
                    this.logEvent("FATAL ERROR - TrainPassengerOverflowException");
                    System.exit(0);
                }
            }
            t.closeDoors(); //closed in Train.sync()
        });
    }

    /**
     * Returns platform for passenger to enter (inbound or outbound) based on destination station.
     * @param destination End Station of the Passenger
     */
    public Platform getRoute(Station destination) {
        if(route.getRoute(this, destination) == Direction.Inbound)
            return inbound;
        return outbound;
    }

    /**
     * Returns ArrayList of both inbound and outbound platforms
     */
    public ArrayList<Platform> getPlatforms() {
        ArrayList<Platform> pt = new ArrayList<>();
        pt.add(inbound);
        pt.add(outbound);
        return pt;
    }

    /**
     * Adds a Passenger to the a list of passengers getting off a train
     *
     * @param p The Passenger getting off the train.
     */
    public void addArrivingPassenger(Passenger p) {
        this.arrivedPassengers.add(p);
        super.logEvent("Passenger #"+ p.getID() +" Arrived at Destination Station");
    }

    @Override
    public String toString() {
        return "Station info: " + "\nStation Name: " + getName() +
                "\tStation ID: " + getID() + "\nInbound Platform ID: " + getInbound().getPlatformID() +
                "\tOutbound Platform ID: " + getOutbound().getPlatformID() +
                "\nNumber of Arrived Passengers: " + getArrivedPassengers().size();
    }
}
