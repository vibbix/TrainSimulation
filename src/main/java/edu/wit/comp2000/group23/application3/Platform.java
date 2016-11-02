package edu.wit.comp2000.group23.application3;

import edu.wit.comp2000.group23.application3.GraphMap.IConnector;
import edu.wit.comp2000.group23.application3.GraphMap.Track;
import edu.wit.comp2000.group23.application3.Utilities.Loggable;
import edu.wit.comp2000.group23.application3.Utilities.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by beznosm on 10/24/2016.
 */
public class Platform extends Loggable implements IConnector<Train> {
    private Train occupant;
    private Direction platformDirection;
    private Station station;
    private int platformID;
    private IConnector inbound;
    private IConnector outbound;
    private LinkedBlockingDeque<Passenger> boardingPassengers;
    private boolean trainReady = false;

    public Platform(Logger logger, Direction direction, Station station, int pID) {
        super(logger, pID);
        this.platformDirection = direction;
        this.station = station;
        this.platformID = pID;
        this.boardingPassengers = new LinkedBlockingDeque<>();
    }

    //region accessors/mutators
    public Direction getPlatformDirection() {
        return platformDirection;
    }

    public void setPlatformDirection(Direction direction) {
        this.platformDirection = direction;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    @Override
    public Train getOccupant() {
        return occupant;
    }

    @Override
    public void setOccupant(Train occupant) {
        this.occupant = occupant;
        if (occupant != null && occupant.getConnector() != this)
            occupant.setConnector(this);
    }

    @Override
    public List<IConnector> getConnectors() {

        ArrayList<IConnector> al = new ArrayList<>();
        al.add(this.inbound);
        al.add(this.outbound);
        return al;
    }

    @Override
    public void setConnector(IConnector connector, Direction direction) {
        if (direction == Direction.Inbound) {
            inbound = connector;
        } else {
            outbound = connector;
        }

    }

    @Override
    public IConnector getConnector(Direction direction) {
        if (direction == Direction.Inbound)
            return inbound;
        return outbound;
    }

    @Override
    public void moveConnector() throws Exception {
        moveConnector(occupant.getDirection());
    }

    public void enqueuePassenger(Passenger p) {
        if (!boardingPassengers.contains(p))
            boardingPassengers.push(p);
    }

    public boolean canDequeuePassenger() {
        return boardingPassengers.size() > 0;
    }

    public Passenger dequeuePassenger() {
        return this.boardingPassengers.pop();
    }

    public boolean isTrainReadyToLeave() {
        return trainReady;
    }

    public void setTrainReadyToLeave(boolean ready) {
        this.trainReady = ready;
    }

    public int getPlatformID() {
        return platformID;
    }

    //endregion
    @Override
    public String toString() {
        String rtn = "Platform: " + platformID;
        rtn += "; logger: " + super.getLogger().hashCode();
        rtn += "; Inbound: ";

        if (this.inbound == null) {
            rtn += "null";
        } else if (this.inbound instanceof Platform) {
            rtn += "(Platform)" + ((Platform) inbound).getPlatformID();
        } else if (this.inbound instanceof Track) {
            rtn += "(Track)" + ((Track) this.inbound).getTrackID();
        }
        rtn += "; Outbound: ";
        if (this.outbound == null) {
            rtn += "null";
        } else if (this.outbound instanceof Platform) {
            rtn += "(Platform)" + ((Platform) outbound).getPlatformID();
        } else if (this.outbound instanceof Track) {
            rtn += "(Track)" + ((Track) this.outbound).getTrackID();
        }
        rtn += "; Occupant: " + (occupant != null ? occupant.toString() : "null");
        return rtn;
    }
}
