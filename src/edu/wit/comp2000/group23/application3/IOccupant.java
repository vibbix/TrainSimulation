package edu.wit.comp2000.group23.application3;

import edu.wit.comp2000.group23.application3.GraphMap.IConnector;

/**
 * Interface for classes that have direction
 */
public interface IOccupant {

    /**
     * Gets an objects direction
     * @return The objects direction
     */
    Direction getDirection();
    /**
     * Sets an objects direction
     * @param d Direction to set object in
     */
    void setDirection(Direction d);

    /**
     * Gets the current connector that the occupant is on
     * @return The connector the occupant is on
     */
    IConnector getConnector();

    /**
     *
     * @param connector Connector to set to
     */
    void setConnector(IConnector connector);
}
