package edu.wit.comp2000.group23.application3.GraphMap;

import edu.wit.comp2000.group23.application3.Direction;
import edu.wit.comp2000.group23.application3.IOccupant;
import edu.wit.comp2000.group23.application3.Platform;
import edu.wit.comp2000.group23.application3.Utilities.Loggable;
import edu.wit.comp2000.group23.application3.Utilities.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * The track class is analogous to edges in graph theory. The Trains
 */
public class Track<T> extends Loggable implements IConnector<T> {
    private T occupant;
    private IConnector inbound;
    private IConnector outbound;
    private int trackID;

    /**
     * Creates the track with preset
     *
     * @param logger Logger for track to use
     */
    public Track(Logger logger) {
        this(logger, null, null, -1);
    }

    /**
     * Creates the track with preset connectors
     *
     * @param logger   The logger to set.
     * @param inbound  The inbound connector
     * @param outbound The outbound connector
     */
    public Track(Logger logger, IConnector<?> inbound, IConnector<?> outbound, int tID) {
        super(logger, tID);
        this.occupant = null;
        this.trackID = tID;
        if (inbound != null)
            setConnector(inbound, Direction.Inbound);
        if (outbound != null)
            setConnector(outbound, Direction.Outbound);


    }

    @Override
    public void setOccupant(T occupant) {
        if (occupant instanceof IOccupant) {
            ((IOccupant) occupant).setConnector(this);
        }
        this.occupant = occupant;
    }

    @Override
    public T getOccupant() {
        return occupant;
    }

    @Override
    public List<IConnector> getConnectors() {
        ArrayList<IConnector> connectors = new ArrayList<>();
        connectors.add(inbound);
        connectors.add(outbound);
        return connectors;
    }


    //region Accessors/Mutators
    @Override
    public void setConnector(IConnector connector, Direction direction) {
        if (direction == Direction.Inbound) {
            if (this.inbound != null)
                this.inbound.setConnector(null, Direction.Outbound);
            this.inbound = connector;
            if (this.inbound.getConnector(Direction.Outbound) != this)
                this.inbound.setConnector(this, Direction.Outbound);
        } else {
            if (this.outbound != null)
                this.outbound.setConnector(null, Direction.Inbound);
            this.outbound = connector;
            if (this.outbound.getConnector(Direction.Inbound) != this)
                this.outbound.setConnector(this, Direction.Inbound);
        }
    }

    @Override
    public IConnector getConnector(Direction direction) {
        if (direction == Direction.Inbound)
            return inbound;
        else
            return outbound;
    }

    //endregion
    @Override
    public String toString() {
        String rtn = "Track: " + this.trackID;
        rtn += "; logger: " + super.getLogger().hashCode();
        rtn += "; Inbound: ";
        if (inbound == null){
            rtn += "null";
        } else if (inbound instanceof Platform){
            rtn += "(Platform)"+ ((Platform) inbound).getPlatformID();
        } else if (inbound instanceof Track){
            rtn += "(Track)" + ((Track) inbound).trackID;
        }
        rtn += "; Outbound: ";
        if (outbound == null){
            rtn += "null";
        } else if (outbound instanceof Platform){
            rtn += "(Platform)"+ ((Platform) outbound).getPlatformID();
        } else if (outbound instanceof Track){
            rtn += "(Track)" + ((Track) outbound).trackID;
        }
        rtn += "; Occupant: " + (occupant != null ? occupant.toString() : "null");
        return rtn;
    }

    public int getTrackID() {
        return trackID;
    }
}
