package edu.wit.comp2000.group23.application3;

import edu.wit.comp2000.group23.application3.Utilities.Event;
import edu.wit.comp2000.group23.application3.Utilities.ILogger;

/**
 * Created by beznosm on 10/24/2016.
 */
public class Platform implements ILogger {
    private Direction direction;
    private Station station;

    public Platform(Direction direction, Station station){

    }

    @Override
    public void LogEvent(Event event) {

    }

    //region accessors/mutators
    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }
    //endregion
}
