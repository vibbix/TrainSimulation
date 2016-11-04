import edu.wit.comp2000.group23.application3.Exceptions.TrainDoorsClosedException;
import edu.wit.comp2000.group23.application3.Exceptions.TrainPassengerOverflowException;
import org.junit.Test;

/**
 * Created by beznosm on 11/3/2016.
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
