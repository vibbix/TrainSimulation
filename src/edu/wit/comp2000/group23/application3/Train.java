package edu.wit.comp2000.group23.application3;

import edu.wit.comp2000.group23.application3.Utilities.Event;
import edu.wit.comp2000.group23.application3.Utilities.Loggable;
import edu.wit.comp2000.group23.application3.Utilities.Logger;

/**
 * Created by beznosm on 10/24/2016.
 */
//Java doesn't have multiple inheritance so you can't extend both IOccupant & Loggable
public class Train extends IOccupant {
    private Loggable logger;

    public Train(Logger l){
        logger = new Loggable(l) {};
    }
    private void LogEvent(String event){
        logger.logEvent(event);
    }
    public boolean canMove(){
        return true;
    }
}
