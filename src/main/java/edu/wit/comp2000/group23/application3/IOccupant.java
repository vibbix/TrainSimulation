package edu.wit.comp2000.group23.application3;

import edu.wit.comp2000.group23.application3.GraphMap.IConnector;
import edu.wit.comp2000.group23.application3.GraphMap.Track;

/**
 * Interface for classes that have direction
 */
public abstract class IOccupant {
    private Direction direction;
    private IConnector connector;

    public IOccupant() {
        direction = Direction.Inbound;
        connector = null;
    }

    /**
     * Gets an objects direction
     *
     * @return The objects direction
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * Sets an objects direction
     *
     * @param d Direction to set object in
     */
    public void setDirection(Direction d) {
        direction = d;
    }

    /**
     * Gets the current connector that the occupant is on
     *
     * @return The connector the occupant is on
     */
    public IConnector getConnector() {
        return connector;
    }

    /**
     * Sets the connector
     *
     * @param connector Connector to set to
     */
    public void setConnector(IConnector connector) {
        this.connector = connector;
        if (connector.getOccupant() != this)
            connector.setOccupant(this);
    }

    @Override
    public String toString() {
        String toStr = "";
        toStr = "Direction: " + direction.toString();
        if (connector == null) {
            toStr += "; Connector: null";
        } else if (connector instanceof Track) {
            toStr += "; Connector: (Track) " + ((Track) connector).getTrackID();
        } else if (connector instanceof Platform) {
            toStr += "; Connector: (Platform) " + ((Platform) connector).getPlatformID();
        } else {
            try {
                toStr += "; Connector: " + connector.toString();
            } catch (Exception ex) {
                toStr += "; Connector: " + ex.toString();
            }
        }
        return toStr;
    }
}
