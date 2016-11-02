package edu.wit.comp2000.group23.application3;

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

    public Station(Logger l, TrainRoute tr, int sID){
        super(l, sID);
        this.route = tr;
        this.stationID = sID;
        this.arrivedPassengers = new ArrayList<>();
        inbound = new Platform(l, Direction.Inbound, this, sID*10);
        outbound = new Platform(l, Direction.Outbound, this, (sID*10)+1);
    }

    public Station(Logger l, TrainRoute tr, int sID, String name){
        super(l, sID);
        this.route = tr;
        this.arrivedPassengers = new ArrayList<>();
        this.name = name;
        this.stationID = sID;
        inbound = new Platform(l, Direction.Inbound, this, sID*10);
        outbound = new Platform(l, Direction.Outbound, this, (sID*10)+1);
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

    public Platform getPlatform(Direction d)
    {
        if (d == Direction.Outbound)
            return outbound;
        return inbound;
    }

    /**
     * Fill train from inbound and outbound platform
     * */
    public void Sync() {
        for (Platform p : this.getPlatforms()) {
            if (p.getOccupant() != null) {
                Train t = p.getOccupant();
                t.openDoors();
                while (p.canDequeuePassenger() && !p.isTrainReadyToLeave()) {
                    if (!t.embarkPassenger(p.dequeuePassenger()))
                        break;
                }
            }
        }
    }

    /**
    *   Returns platform for passenger to enter (inbound or outbound) based on destination station.
     *
    *   @param destination End Station of the Passenger
    */

    public Platform getRoute(Station destination){
        return inbound;
    }
    /**
    *  Returns ArrayList of both inbound and outbound platforms
    */
    public ArrayList<Platform> getPlatforms(){
        ArrayList<Platform> pt = new ArrayList<>();
        pt.add(inbound);
        pt.add(outbound);
        return pt;
    }

    /**
     * Adds a Passenger to the a list of passengers getting off a train
     *
     * @param p  The Passenger getting off the train.
     *
     */
    public void addArrivingPassenger(Passenger p){
        this.arrivedPassengers.add(p);
        super.logEvent("Passenger Arrived at Destination Station");
    }

    @Override
    public String toString(){
        return "Station info: " + "\nStation Name: " + getName() +
                "\tStation ID: " + getID() + "\nInbound Platform ID: "+ getInbound().getPlatformID() +
                "\tOutbound Platform ID: " + getOutbound().getPlatformID()+
                "\nNumber of Arrived Passengers: " + getArrivedPassengers().size() ;
    }
}
