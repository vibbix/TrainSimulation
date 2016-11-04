import edu.wit.comp2000.group23.application3.TrainSimulation;
import edu.wit.comp2000.group23.application3.Utilities.Logger;
import org.junit.Assert;
import org.junit.Test;

/**
 * Comp2000 - Data Structures
 * Application 3 - Queues (TrainSim)
 * Group #23
 *
 * Team:
 * Andrew DeChristopher
 * Mark Beznos
 * Bryon Kucharski
 * Tin Wong
 * Jeffery Lindeland
 * Shakib Hassan
 */
public class TrainSimulatorTests {
    @Test
    public void createTrainTest() {
        Logger logger = new Logger();
        String[] stops = new String[]{"s1", "s2", "s3"};
        TrainSimulation ts = new TrainSimulation(logger, stops, false, 0);
        Assert.assertEquals(2, ts.getRoute().getTrains().size());
    }
}
