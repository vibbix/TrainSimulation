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
     *
     * @param occupant Occupant to set to
     */
    void setOccupant(T occupant);

    /**
     * Gets the occupant of the connector
     */
    T getOccupant();

    /**
     * Gets the neighboring connectors for the current connector
     */
    List<IConnector> getConnectors();

    /**
     * Moves the occupant in a set direction
     *
     * @param direction Direction occupant goes in
     * @throws Exception thrown if the occupant cannot be moved in that direction
     */
    default void moveConnector(Direction direction) throws Exception {
        if (direction == Direction.Inbound) {
            if (getConnector(Direction.Inbound).getOccupant() != null) {
                throw new Exception("Multiple objects cannot be on the same IConnector");
            }
            getConnector(Direction.Inbound).setOccupant(getOccupant());
            if (getOccupant() instanceof IOccupant) {
                ((IOccupant) getOccupant()).setConnector(getConnector(Direction.Inbound));

            }
            this.setOccupant(null);
        }
        if (getConnector(Direction.Outbound).getOccupant() != null) {
            throw new Exception("Multiple objects cannot be on the same IConnector");
        }
        getConnector(Direction.Outbound).setOccupant(getOccupant());
        if (getOccupant() instanceof IOccupant) {
            ((IOccupant) getOccupant()).setConnector(getConnector(Direction.Outbound));
        }
        this.setOccupant(null);
    }

    /**
     * @param <T> An object that implements IOccupant
     */
    default <T extends IOccupant> void moveConnector() throws Exception {
        moveConnector(((IOccupant) getOccupant()).getDirection());
    }

    /**
     * @param connector Connector to assign to graph
     * @param direction Direction connector is going
     */
    void setConnector(IConnector connector, Direction direction);

    /**
     * @param direction
     * @return The IConnector in that set direction
     */
    IConnector getConnector(Direction direction);
}

