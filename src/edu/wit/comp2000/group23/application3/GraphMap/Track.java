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
     * @param l Logger for track to use
     */
    public Track(Logger l){
        this(l, null, null);
    }

    /**
     * Creates the track with preset connectors
     * @param l The logger to set.
     * @param inbound The inbound connector
     * @param outbound The outbound connector
     */
    public Track(Logger l, IConnector<? extends Object> inbound, IConnector<? extends Object> outbound){
        occupant = null;
        if(inbound != null)
            setConnector(inbound, Direction.Inbound);
        if(outbound != null)
            setConnector(outbound, Direction.Outbound);
        logger = l;
    }
    @Override
    public void setOccupant(T occupant) {
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
    public void moveConnector(Direction direction) throws Exception{
        if(direction == Direction.Inbound){
            if(inbound.getOccupant() != null){
                throw new Exception("Multiple trains cannot be on the same track or platform");
            }

            inbound.setOccupant(occupant);
            if(occupant instanceof IOccupant){
                ((IOccupant) occupant).setConnector(inbound);
            }
            this.setOccupant(null);
        }
        if(outbound.getOccupant() != null){
            throw new Exception("Multiple trains cannot be on the same track or platform");
        }
        outbound.setOccupant(occupant);
        if(occupant instanceof IOccupant){
            ((IOccupant) occupant).setConnector(outbound);
        }
        this.setOccupant(null);

    }

    @Override
    public <T extends IOccupant> void moveConnector() throws Exception{
        try{
            moveConnector(((IOccupant) occupant).getDirection());

        }
        catch(Exception ex){
            throw ex;
        }
    }
    @Override
    public void LogEvent(Event event) {
        logger.AddEvent(event);
    }
    //region Accessors/Mutators
    @Override
    public void setConnector(IConnector connector, Direction direction) {
        if(direction == Direction.Inbound) {
            if(inbound != null)
                inbound.setConnector(null, Direction.Outbound);
            inbound = connector;
            if(inbound.getConnector(Direction.Outbound) != this)
                inbound.setConnector(this, Direction.Outbound);
        }else {
            if(outbound != null)
                outbound.setConnector(null, Direction.Inbound);
            outbound = connector;
            if(outbound.getConnector(Direction.Inbound) != this)
                outbound.setConnector(this, Direction.Inbound);
        }
    }

    @Override
    public IConnector getConnector(Direction direction) {
        if(direction == Direction.Inbound)
            return inbound;
        else
            return outbound;
    }
    //endregion
}
