import edu.wit.comp2000.group23.application3.Exceptions.TrainDoorsClosedException;
import edu.wit.comp2000.group23.application3.Exceptions.TrainPassengerOverflowException;
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
public class ExceptionTests {

    @Test(expected = TrainDoorsClosedException.class)
    public void ThrowTrainDoorsClosed() throws TrainDoorsClosedException {
        throw new TrainDoorsClosedException();
    }

    @Test(expected = TrainDoorsClosedException.class)
    public void ThrowTrainDoorsClosedWithMessage() throws TrainDoorsClosedException {
        throw new TrainDoorsClosedException("message");
    }

    @Test(expected = TrainPassengerOverflowException.class)
    public void ThrowTrainPassengerOverflow() throws TrainPassengerOverflowException {
        throw new TrainPassengerOverflowException();
    }

    @Test(expected = TrainPassengerOverflowException.class)
    public void ThrowTrainPassengerOverflowWithMessage() throws TrainPassengerOverflowException {
        throw new TrainPassengerOverflowException("message");
    }
}
