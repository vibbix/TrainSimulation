package edu.wit.comp2000.group23.application3;

import edu.wit.comp2000.group23.application3.GraphMap.IConnector;
import edu.wit.comp2000.group23.application3.Utilities.Event;
import edu.wit.comp2000.group23.application3.Utilities.ILogger;

/**
 * Created by beznosm on 10/24/2016.
 */
public class Train implements ILogger,IOccupant {
    @Override
    public void LogEvent(Event event) {

    }

    @Override
    public Direction getDirection() {
        return null;
    }

    @Override
    public void setDirection(Direction d) {

    }

    @Override
    public IConnector getConnector() {
        return null;
    }

    @Override
    public void setConnector(IConnector connector) {

    }
}
