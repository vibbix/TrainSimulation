package edu.wit.comp2000.group23.application3;

import edu.wit.comp2000.group23.application3.Enums.Direction;
import edu.wit.comp2000.group23.application3.Utilities.Loggable;
import edu.wit.comp2000.group23.application3.Utilities.Logger;

import java.util.Random;
import java.util.Scanner;

/**
 * The train simulator, including random passenger generation. and console output
 */
public class TrainSimulation extends Loggable {
    private TrainRoute route;
    private int passengerID = 0;
    private Logger logger;
    private Random rand;

    /**
     * Creates a new train simulator
     *
     * @param logger  Logger to use
     * @param stops   List of stop names
     * @param useRand Use random passenger generation
     * @param tsID    trainsimulation ID
     */
    public TrainSimulation(Logger logger, String[] stops, boolean useRand, int tsID) {
        super(logger, tsID);
        this.logger = logger;
        this.rand = new Random();
        this.route = new TrainRoute(logger, 0);
        this.route.createRoute(stops);
        this.createTrains();
        this.generatePassengers();
    }

    /*
    event simulation order:
    -sync each class
    -Flush logger queue
     */
    public static void main(String[] args) {
        Logger logger = new Logger();
        TrainSimulation ts = new TrainSimulation(logger, new String[]{"s1", "s2", "s3", "s4", "s5"}, true, 0);
        Scanner s = new Scanner(System.in);
        ts.generatePassengers();
        while (true) {
            ts.getRoute().Sync();
            ts.logEvent(ts.getSimulationProgress());
            if (ts.isSimulationDone()) {
                ts.logEvent("Simulation over. All passengers have been vaporized.");
                return;
            }
        }
    }

    /**
     * Creates 2 trains for a route in the middle of the section.
     */
    private void createTrains() {
        //Generate trains
        Train t1 = new Train(Direction.Inbound, 100, 0, super.getLogger());
        Train t2 = new Train(Direction.Inbound, 100, 1, super.getLogger());
        this.route.getTrains().add(t1);
        this.route.getTrains().add(t2);
        int half = this.route.getStations().size() / 2;
        Platform in = this.route.getStations().get(half).getPlatform(Direction.Inbound);
        in.setOccupant(t1);
        Platform out = this.route.getStations().get(half).getPlatform(Direction.Outbound);//.setOccupant(t2);
        out.setOccupant(t2);
    }

    /**
     * Generates the passengers, randomly distributed among each station
     */
    private void generatePassengers() {
        for (Station s : route.getStations()) {
            int cap = 30 + rand.nextInt(70);
            for (int i = 0; i < cap; i++) {
                Station dest = route.getStations().get(rand.nextInt(route.getStations().size()));
                new Passenger(logger, dest, s, passengerID);
                passengerID++;
            }
        }
    }

    /**
     * Returns true if the simulation is over
     *
     * @return True if the simulation is over
     */
    public boolean isSimulationDone() {
        int count = 0;
        for (Station s : this.route.getStations()) {
            count += s.getArrivedPassengers().size();
        }
        return count >= passengerID;
    }

    private String getSimulationProgress() {
        int count = 0;
        for (Station s : this.route.getStations()) {
            count += s.getArrivedPassengers().size();
        }
        return count + "/" + passengerID;
    }

    /**
     * Gets the simulations route
     *
     * @return The Simulations route
     */
    public TrainRoute getRoute() {
        return this.route;
    }

}