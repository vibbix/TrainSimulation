package edu.wit.comp2000.group23.application3;

import edu.wit.comp2000.group23.application3.Utilities.Loggable;
import edu.wit.comp2000.group23.application3.Utilities.Logger;

import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

/**
 * The train simulator, including random passenger generation. and console output
 */
public class TrainSimulation extends Loggable {
    private final double ticksPerSecond = 1.0;
    private TrainRoute route;
    private Timer simtimer;
    private TimerTask simtask;
    private Timer passengertimer;
    private TimerTask passengertask;
    private int passengerID = 0;
    private Logger logger;
    private Random rand;

    /**
     * Creates a new train simulator
     * @param logger Logger to use
     * @param stops List of stop names
     * @param useRand Use random passenger generation
     * @param tsID trainsimulation ID
     */
    public TrainSimulation(Logger logger, String[] stops, boolean useRand, int tsID) {
        super(logger, tsID);
        this.logger = logger;
        rand = new Random();
        this.simtimer = new Timer("sim loop");
        this.passengertimer = new Timer("passenger loop");
        this.simtask = new TimerTask() {
            public void run() {
                Sync();
            }
        };
        this.passengertask = new TimerTask() {
            @Override
            public void run() {
                if(useRand) {
                    generateRandomPassengers(5);
                }
                else {
                    generatePassengers();
                }
            }
        };
        this.route = new TrainRoute(logger, 0);
        this.route.createRoute(stops);
        this.route.createTrains();
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
            ts.simtask.run();
            ts.passengertask.run();
            try {
                Thread.sleep(1000);
            } catch (Exception ex) {
            }
        }
    }

    /**
     * Starts the simulation
     */
    public void startSimulation() {
        simtimer.schedule(simtask, (long) (ticksPerSecond * 1000));
        passengertimer.schedule(passengertask, 2500);
    }

    private void generateRandomPassengers(int num) {
        for (int i = 0; i < 5; i++) {
            Station dest = route.getStations().get(rand.nextInt(route.getStations().size()));
            Station arrival = route.getStations().get(rand.nextInt(route.getStations().size()));
            Passenger p = new Passenger(logger, dest, null, arrival, passengerID);
            p.Sync();
            passengerID++;
        }
    }

    private void generatePassengers() {
        for (Station s : route.getStations())
            for (int i = 0; i < 2; i++) {
                Station dest = route.getStations().get(rand.nextInt(route.getStations().size()));
                Passenger pass = new Passenger(logger, dest, null, s, passengerID);
                pass.Sync();
            }
    }

    /**
     * Stops the simulation
     */
    public void stopSimulation() {
        simtimer.cancel();
        passengertask.cancel();
    }

    private void Sync() {
        route.Sync();
    }

}
