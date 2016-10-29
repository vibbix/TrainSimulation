package edu.wit.comp2000.group23.application3;

import edu.wit.comp2000.group23.application3.Utilities.Loggable;
import edu.wit.comp2000.group23.application3.Utilities.Logger;

import java.util.ArrayList;

/**
 * Created by beznosm on 10/24/2016.
 */
public class TrainRoute extends Loggable implements ITrainRoute {
    private long currentTick;
    private ArrayList<Train> trains;
    private ArrayList<Station> stations;
    public TrainRoute(Logger logger){
        super(logger);
        currentTick = 0;
    }

    @Override
    public void Sync() {

    }


    public Direction getRoute(Station start, Station end) {
        return Direction.Inbound;
    }
}
