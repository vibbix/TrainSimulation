package edu.wit.comp2000.group23.application3.GraphMap;

import edu.wit.comp2000.group23.application3.Direction;
import edu.wit.comp2000.group23.application3.IOccupant;
import edu.wit.comp2000.group23.application3.Utilities.Event;
import edu.wit.comp2000.group23.application3.Utilities.ILogger;
import edu.wit.comp2000.group23.application3.Utilities.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * The track class is analogous to edges in graph theory. The Trains
 */
public class Track<T> implements IConnector<T>, ILogger {
    private T occupant;
    private IConnector inbound;
    private IConnector outbound;
    private Logger logger;


    /**
     * Creates the track with preset
     *
     * @param logger Logger for track to use
     */
    public Track(Logger logger) {
        this(logger, null, null);
    }

    /**
     * Creates the track with preset connectors
     *
     * @param logger   The logger to set.
     * @param inbound  The inbound connector
     * @param outbound The outbound connector
     */
    public Track(Logger logger, IConnector<? extends Object> inbound, IConnector<? extends Object> outbound) {
        occupant = null;
        if (inbound != null)
            setConnector(inbound, Direction.Inbound);
        if (outbound != null)
            setConnector(outbound, Direction.Outbound);
        this.logger = logger;

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

    @Override
    public void LogEvent(Event event) {
        logger.AddEvent(event);
    }

    //region Accessors/Mutators
    @Override
    public void setConnector(IConnector connector, Direction direction) {
        if (direction == Direction.Inbound) {
            if (inbound != null)
                inbound.setConnector(null, Direction.Outbound);
            inbound = connector;
            if (inbound.getConnector(Direction.Outbound) != this)
                inbound.setConnector(this, Direction.Outbound);
        } else {
            if (outbound != null)
                outbound.setConnector(null, Direction.Inbound);
            outbound = connector;
            if (outbound.getConnector(Direction.Inbound) != this)
                outbound.setConnector(this, Direction.Inbound);
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
        String rtn = "Track: " + this.hashCode();
        rtn += "; logger: " + logger.hashCode();
        rtn += "; Inbound: " + (inbound != null ? inbound.hashCode() : "null");
        rtn += "; Outbound: " + (outbound != null ? outbound.hashCode() : "null");
        rtn += "; Occupant: " + (occupant != null ? occupant.toString() : "null");
        return rtn;
    }

}
