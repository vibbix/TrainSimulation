package edu.wit.comp2000.group23.application3;

import edu.wit.comp2000.group23.application3.Utilities.Loggable;
import edu.wit.comp2000.group23.application3.Utilities.Logger;
import java.util.Timer;
import java.util.TimerTask;
/**
 * Created by beznosm on 10/24/2016.
 */
public class TrainSimulation extends Loggable {
    private final double ticksPerSecond = 1.0;
    private Timer timer;
    private TimerTask task;
    private TrainRoute route;
    public TrainSimulation(Logger logger, String configURI, int tsID){
        super(logger, tsID);
        timer = new Timer("loop");
        task = new TimerTask(){
            public void run(){
                Sync();
            }
        };

    }
    public void startSimulation(){
        timer.schedule(task, (long)(ticksPerSecond*1000));
    }
    public void stopSimulation(){
        timer.cancel();
    }
    private void Sync(){
        route.Sync();
    }
    /*
    event simulation order:
    -sync each class
    -Flush logger queue
     */

}
