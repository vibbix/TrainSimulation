package edu.wit.comp2000.group23.application3;

import edu.wit.comp2000.group23.application3.Utilities.Event;
import edu.wit.comp2000.group23.application3.Utilities.ILogger;

/**
 * Created by beznosm on 10/24/2016.
 */
public class TrainRoute implements ITrainRoute, ILogger {
    @Override
    public void LogEvent(Event event) {

    }

    @Override
    public void Sync() {

    }

    //@Override
    public Platform getNextPlatform(Platform pt) {
        return null;
    }

    //@Override
    public int tickDistancetoNextPlatform(Platform pt) {
        return 0;
    }

    public Direction getRoute(Station start, Station end) {
        return Direction.Inbound;
    }
}
