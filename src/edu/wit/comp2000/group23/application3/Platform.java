package edu.wit.comp2000.group23.application3;

import edu.wit.comp2000.group23.application3.GraphMap.IConnector;
import edu.wit.comp2000.group23.application3.Utilities.Loggable;
import edu.wit.comp2000.group23.application3.Utilities.Logger;

import java.util.List;

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

    public Platform(Logger logger, Direction direction, Station station, int pID) {
        super(logger, pID);
        this.platformDirection = direction;
        this.station = station;
        this.platformID = pID;
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
    public void setOccupant(Train occupant) {
        this.occupant = occupant;
    }

    @Override
    public Train getOccupant() {
        return occupant;
    }

    @Override
    public List<IConnector> getConnectors() {
        return null;
    }


    @Override
    public void setConnector(IConnector connector, Direction direction) {

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
    //endregion
}
