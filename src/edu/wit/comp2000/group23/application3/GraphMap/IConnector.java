package edu.wit.comp2000.group23.application3.GraphMap;

import edu.wit.comp2000.group23.application3.Direction;
import edu.wit.comp2000.group23.application3.IOccupant;

import java.util.List;

/**
 * The connection interface for vertices and edges.
 */
public interface IConnector<T> {

    /**
     * Sets the occupant of the connector
     * @param occupant Occupant to set to
     */
    void setOccupant(T occupant);
    /**
     * Gets the occupant of the connector
     * */
    T getOccupant();
    /**
     * Gets the neighboring connectors for the current connector
     * */
    List<IConnector> getConnectors();

    /**
     * Moves the occupant in a set direction
     * @param direction Direction occupant goes in
     * @exception Exception thrown if the occupant cannot be moved in that direction
     */
    void moveConnector(Direction direction) throws Exception;

    /**
     * @param <T> An object that implements IOccupant
     */
    <T extends IOccupant> void moveConnector() throws Exception;
    /**
     * @param connector Connector to assign to graph
     * @param direction Direction connector is going
     */
    void setConnector(IConnector connector, Direction direction);


    /**
     * @param direction
     * @return
     */
    IConnector getConnector(Direction direction);
}

