package edu.wit.comp2000.group23.application3;

import edu.wit.comp2000.group23.application3.GraphMap.IConnector;
import edu.wit.comp2000.group23.application3.Utilities.Event;
import edu.wit.comp2000.group23.application3.Utilities.ILogger;
import edu.wit.comp2000.group23.application3.Utilities.Logger;

import java.util.List;

/**
 * Created by beznosm on 10/24/2016.
 */
public class Platform implements ILogger, IConnector<Train> {
    private Train occupant;
    private Direction platformDirection;
    private Station station;
    private IConnector inbound;
    private IConnector outbound;
    private Logger logger;

    public Platform(Logger logger, Direction direction, Station station) {
        this.platformDirection = direction;
        this.station = station;
    }

    @Override
    public void LogEvent(Event event) {

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
