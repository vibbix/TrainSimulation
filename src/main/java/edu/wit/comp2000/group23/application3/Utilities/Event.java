package edu.wit.comp2000.group23.application3.Utilities;

/**
 * An immutable logged Event.
 */
public class Event {
    private String className;
    private int instanceID;
    private String eventText;

    public Event(String classname, int iID, String eText) {
        className = classname;
        instanceID = iID;
        eventText = eText;
    }

    //region Accessors
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
    public String toString() {
        if(this.getClassName().equals("Train") || this.getClassName().equals("Track") || this.getClassName().equals("Station")){
            return "[" + this.getClassName() + "\t\t\t]" + "[" + this.getInstanceID() + "\t]" + "[" + this.getEventText() + "]";
        }
        return "[" + this.getClassName() + "\t\t]" + "[" + this.getInstanceID() + "\t]" + "[" + this.getEventText() + "]";
    }
}
