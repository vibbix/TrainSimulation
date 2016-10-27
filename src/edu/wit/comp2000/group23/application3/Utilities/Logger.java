package edu.wit.comp2000.group23.application3.Utilities;


/**
 * Created by beznosm on 10/24/2016.
 */
public class Logger {
    private long currentTick;
    ArrayQueue<Event> eventQueue;
    public Logger(){
        currentTick = 0;
        eventQueue = new ArrayQueue<>();
    }

    void FlushQueue(){

    }

}
