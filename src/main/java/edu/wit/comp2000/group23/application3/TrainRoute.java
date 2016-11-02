package edu.wit.comp2000.group23.application3;

import edu.wit.comp2000.group23.application3.GraphMap.IConnector;
import edu.wit.comp2000.group23.application3.GraphMap.Track;
import edu.wit.comp2000.group23.application3.Utilities.Loggable;
import edu.wit.comp2000.group23.application3.Utilities.Logger;

import java.util.ArrayList;

/**
 * Created by beznosm on 10/24/2016.
 */
public class TrainRoute extends Loggable {
    private long currentTick;
    private ArrayList<Train> trains;
    private ArrayList<Station> stations;
    private ArrayList<Track> tracks;
    private int trackCount;
    private boolean trackIntialized;

    /**
     * Creates the train route
     *
     * @param logger Logger to pass in
     * @param trID   The trainroute ID
     */
    public TrainRoute(Logger logger, int trID) {
        super(logger, trID);
        this.currentTick = 0;
        this.trains = new ArrayList<>();
        this.stations = new ArrayList<>();
        this.tracks = new ArrayList<>();
        this.trackCount = 0;
        this.trackIntialized = false;

    }

    /**
     * Creates a simple oval shaped route, as long as the station has at least 3 stops.
     *
     * @param stationNames Array of stations names to create
     */
    public void createRoute(String[] stationNames) {
        logEvent("Creating route");
        if (stationNames.length < 3) {
            throw new IllegalArgumentException("Requires at least 3 stops");
        }
        for (int i = 0; i < stationNames.length; i++) {
            logEvent("Creating station " + stationNames[i]);
            this.stations.add(new Station(super.getLogger(), this, i, stationNames[i]));
        }
        for (int i = 1; i <= (this.stations.size() - 2); i++) {
            //relative inbound for inbound
            Station up = stations.get(i + 1);
            Station c = stations.get(i);
            Station down = stations.get(i - 1);
            //add sanity check here so no train tracks are overridden
            if (c.getPlatform(Direction.Inbound).getConnector(Direction.Outbound) == null) {
                logEvent("Connecting station " + down.getName() + " inbound to " + c.getName() + " inbound");
                createInBetweenTrack(down.getPlatform(Direction.Inbound), c.getPlatform(Direction.Inbound), 4);
            }
            if (c.getPlatform(Direction.Inbound).getConnector(Direction.Inbound) == null) {
                logEvent("Connecting station " + c.getName() + " inbound to " + up.getName() + " inbound");
                createInBetweenTrack(c.getPlatform(Direction.Inbound), up.getPlatform(Direction.Inbound), 4);
            }
            if (c.getPlatform(Direction.Outbound).getConnector(Direction.Inbound) == null) {
                logEvent("Connecting station " + up.getName() + " outbound to " + c.getName() + " outbound");
                createInBetweenTrack(up.getPlatform(Direction.Outbound), c.getPlatform(Direction.Outbound), 4);
            }
            if (c.getPlatform(Direction.Outbound).getConnector(Direction.Inbound) == null) {
                logEvent("Connecting station " + c.getName() + " outbound to " + down.getName() + " outbound");
                createInBetweenTrack(c.getPlatform(Direction.Outbound), down.getPlatform(Direction.Outbound), 4);
            }
        }
        //set first station
        Station first = stations.get(0);
        Station last = stations.get(stations.size() - 1);
        Platform fpo = first.getPlatform(Direction.Inbound);
        Platform fpi = first.getPlatform(Direction.Outbound);
        logEvent("Connecting platform inbound to outbound on station " + first.getName());
        createInBetweenTrack(fpi, fpo, 1);
        Platform lpo = last.getPlatform(Direction.Inbound);
        Platform lpi = last.getPlatform(Direction.Outbound);
        logEvent("Connecting platform outbound to inbound on station " + last.getName());
        createInBetweenTrack(lpo, lpi, 1);
        this.trackIntialized = true;
    }

    /**
     * Creates 2 trains for a route in the middle of the section.
     */
    public void createTrains() {
        if(!this.trackIntialized){
            throw new SecurityException("The route has not been intialized");
        }
        //Generate trains
        Train t1 = new Train(Direction.Inbound, 100, 0, super.getLogger());
        Train t2 = new Train(Direction.Inbound, 100, 1, super.getLogger());
        trains.add(t1);
        trains.add(t2);
        int half = stations.size() / 2;
        Platform in = stations.get(half).getPlatform(Direction.Inbound);
        in.setOccupant(t1);
        Platform out = stations.get(half).getPlatform(Direction.Outbound);//.setOccupant(t2);
        out.setOccupant(t2);
    }

    private void createInBetweenTrack(Platform s, Platform e, int tickDistance) {
        IConnector cnode = s;
        for (int i = 0; i < tickDistance; i++) {
            Track t = new Track(super.getLogger(), null, cnode, this.trackCount);
            this.trackCount++;
            this.tracks.add(t);
            cnode = t;
        }
        cnode.setConnector(e, Direction.Inbound);
    }

    /**
     * Syncs the Trainroute simulation
     */
    public void Sync() {
        if(!this.trackIntialized){
            throw new SecurityException("The route has not been intialized");
        }
        for (Train t : this.trains) {
            try {
                boolean hasmoved = false;
                if (t.getConnector() instanceof Platform) {
                    if (((Platform) t.getConnector()).isTrainReadyToLeave()) {
                        IConnector c = t.getConnector();
                        c.moveConnector();
                        hasmoved = true;
                    }
                }
                if (t.getConnector() instanceof Track && !hasmoved){
                    t.getConnector().moveConnector();
                    hasmoved = true;
                }
                if(hasmoved){
                    IConnector c = t.getConnector();
                    logEvent("Moved Train #"+t.getID() + " to " +
                            (c instanceof Platform ? "(Platform)"+((Platform) c).getPlatformID() :
                                    "(Track)" + ((Track)c).getTrackID()));
                }
            } catch (Exception ex) {
                logEvent("Could not move train. Passengers vaporized.");
            }
        }
        this.trains.forEach(Train::Sync);
        this.stations.forEach(Station::Sync);
        this.getLogger().Sync();
        currentTick++;
    }

    /**
     * Retrieves the optimal direction for the passenger
     *
     * @param start The arrival station
     * @param end   The deperature station
     * @return The optimal direction the passenger should go to arrive at their destination
     */
    public Direction getRoute(Station start, Station end) {
        if(!this.trackIntialized){
            throw new SecurityException("The route has not been intialized");
        }
        int s = this.stations.indexOf(start);
        int e = this.stations.indexOf(end);
        if (e < s) {
            return Direction.Outbound;
        }
        return Direction.Inbound;
    }

    /**
     * Gets the stations index
     *
     * @param s The station to get the index
     * @return Gets the stations index in the Stations list
     */
    public int getStationIndex(Station s) {
        return stations.indexOf(s);
    }

    /**
     * Gets the TrainRoutes list of stations
     *
     * @return The list of stations visible to the train route
     */
    public ArrayList<Station> getStations() {
        return this.stations;
    }

    /**
     * Gets the trains visible to the train route, but not the simulation. Phantom trains can be preset on IConnectors
     * Occupant, and can throw an exception if they try to occupy the tile in which 'ghost' tile resides.
     *
     * @return A list of trains visible to the simulation.
     */
    public ArrayList<Train> getTrains() {
        return this.trains;
    }
}
