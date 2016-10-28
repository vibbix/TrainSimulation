package edu.wit.comp2000.group23.application3;

import edu.wit.comp2000.group23.application3.GraphMap.IConnector;

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
    }

    public String toString() {
        String toStr = "";
        try {
            toStr = "Direction: " + direction.toString();
            toStr += "; Connector: " + connector.toString();
        } catch (NullPointerException npe) {
            toStr += "; couldn't get back connector";
        }
        return toStr;
    }
}
