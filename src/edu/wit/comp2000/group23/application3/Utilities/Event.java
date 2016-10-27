package edu.wit.comp2000.group23.application3.Utilities;

/**
 * An immutable logged Event.
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
    //region Accesors
    public String getClassName() {
        return className;
    }

    public int getInstanceID() {
        return instanceID;
    }

    public String getEventText() {
        return eventText;
    }

    //endregion
    @Override
    public String toString(){
        return "["+getClassName()+"\t\t]"+"["+getInstanceID()+"\t]" + "[" + getEventText() + "]";
    }
}
