import edu.wit.comp2000.group23.application3.TrainSimulation;
import edu.wit.comp2000.group23.application3.Utilities.Logger;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by beznosm on 11/2/2016.
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
