package edu.wit.comp2000.group23.application3;

import edu.wit.comp2000.group23.application3.Utilities.Loggable;
import edu.wit.comp2000.group23.application3.Utilities.Logger;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;

/**
 * Created by beznosm on 10/24/2016.
 */
public class TrainRoute extends Loggable {
    private long currentTick;
    private ArrayList<Train> trains;
    private ArrayList<Station> stations;
    //private
    public TrainRoute(Logger logger, int trID) {
        super(logger, trID);
        this.currentTick = 0;
        this.trains = new ArrayList<>();
        this.stations = new ArrayList<>();
    }

    //@Override
    public void Sync() {
        for (Train t : this.trains) {
            try {
                if (t.getConnector() instanceof Platform) {
                    if (((Platform) t.getConnector()).isTrainReadyToLeave()) {
                        t.getConnector().moveConnector();
                    }
                }
            } catch (Exception ex) {
                logEvent("Could not move train. Passengers liquified.");
            }
        }
        for(Station s : this.stations){
            s.Sync();
        }
    }
    public Direction getRoute(Station start, Station end) {
        throw new NotImplementedException();
    }
}
