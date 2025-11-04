import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * The type Time data.
 */
public class TimeData extends Timer {

    /**
     * Instantiates a new Time data.
     *
     * @param delay    the delay
     * @param listener the listener
     */
    public TimeData(int delay, ActionListener listener) {
        super(delay, listener);
    }

    /**
     * Start time string.
     *
     * @param timeStorage the time storage
     * @return the string
     */
    public String startTime(String timeStorage) {
        timeStorage = new Date().toString();
        return timeStorage;
    }
}
