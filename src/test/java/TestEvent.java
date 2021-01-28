
import java.util.Random;

import de.relluem94.rellulib.events.Event;
import de.relluem94.rellulib.utils.LogUtils;

public class TestEvent extends Event {

    private Random r = new Random();

    @Override
    public void update() {
        LogUtils.test("Test Event with ID: " + getID().getID() + " Executed and generated following Integer: " + r.nextInt(345678));
    }
}
