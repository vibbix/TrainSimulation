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
    public Station(Logger l, TrainRoute tr, int sID){
        super(l, sID);
        this.route = tr;
        inbound = new Platform(l, Direction.Inbound, this, sID*10);
        outbound = new Platform(l, Direction.Outbound, this, (sID*10)+1);
    }
    public void Sync(){
        for(Platform p : this.getPlatforms()){
            if(p.getOccupant() != null){
                Train t = p.getOccupant();
                t.openDoors();
                while(p.canDequeuePassenger() && !p.isTrainReadyToLeave()){
                    if(!t.embarkPassenger(p.dequeuePassenger()))
                        break;
                }
            }
        }
    }

    public Platform getPlatform(Direction d)
    {
        if (d == Direction.Outbound)
            return outbound;
        return inbound;
    }

    public Platform getRoute(Station destination){
        return null;
    }

    public ArrayList<Platform> getPlatforms(){
        ArrayList<Platform> pt = new ArrayList<>();
        pt.add(inbound);
        pt.add(outbound);
        return pt;
    }

    public void addArrivingPassenger(Passenger p){
        arrivedPassengers.add(p);
    }


}
