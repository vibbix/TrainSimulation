package edu.wit.comp2000.group23.application3.Utilities;

/**
 * Created by beznosm on 10/24/2016.
 */
public class Event {
    private String className;
    private int instanceID;
    private String eventText;

    public Event(String classname, int instanceid, String eventtext){
        className = classname;
        instanceID = instanceid;
        eventText = eventtext;
    }
    //region Accesors/Mutators
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getInstanceID() {
        return instanceID;
    }

    public void setInstanceID(int instanceID) {
        this.instanceID = instanceID;
    }

    public String getEventText() {
        return eventText;
    }

    public void setEventText(String eventText) {
        this.eventText = eventText;
    }
    //endregion
}
