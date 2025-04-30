import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import de.relluem94.rellulib.exceptions.EventException;
import de.relluem94.rellulib.vector.Vector5;
import org.json.JSONObject;

import de.relluem94.rellulib.ID;
import de.relluem94.rellulib.color.Color3i;
import de.relluem94.rellulib.color.Color4f;
import de.relluem94.rellulib.events.EventExecutor;
import de.relluem94.rellulib.stores.TrippleStore;
import de.relluem94.rellulib.threads.ThreadMaster;
import de.relluem94.rellulib.threads.ThreadWorker;
import de.relluem94.rellulib.utils.LogUtils;
import de.relluem94.rellulib.utils.NetworkUtils;
import de.relluem94.rellulib.utils.ReflectionUtils;
import de.relluem94.rellulib.utils.StringUtils;
import de.relluem94.rellulib.utils.TypeUtils;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnitTests {

    private static  EventExecutor eventExecutor;

    @BeforeAll
    static void setUpExecutor() {
        eventExecutor = new EventExecutor();
    }

    @AfterEach
    void cleanUp() {
        eventExecutor.cleanUp();
    }

    @Test
    @DisplayName("Replace symbols in string")
    void testReplaceSymbols() {
        String input = "[PICKAXE]";
        String expected = "⛏";
        String result = StringUtils.replaceSymbols(input);
        assertEquals(expected, result, "Symbols should be replaced correctly");
    }

    @Test
    @DisplayName("Log various object types")
    void testLogUtilsList() {
        List<Object> list = new ArrayList<>();
        list.add(123);
        list.add("Hello");
        list.add(new Vector5<>(1.0f, 0.4f, 5.3f, 0.123456789f, 0.0f));
        list.add(true);
        list.add(Color3i.RELLU_ORANGE);
        list.add(new TrippleStore<>("Das ist ein Test", 22, Color4f.GRAY));
        list.add(123);

        assertDoesNotThrow(() -> LogUtils.list(list), "Logging list should not throw an exception");
    }


    @Test
    public void symbolsTest() {
        
        LogUtils.test(StringUtils.replaceSymbols("[PICKAXE]"));
        
    }

    @Test
    public void logUtilsTest() {
        
        List<Object> list = new ArrayList<>();
        list.add(123);
        list.add("Hello");
        list.add(new Vector5<>(1.0f, 0.4f, 5.3f, 0.123456789f, 0.0f));
        list.add(true);
        list.add(Color3i.RELLU_ORANGE);
        list.add(new TrippleStore<>("Das ist ein Test", 22, Color4f.GRAY));
        list.add(123);

        LogUtils.list(list);
    }


    @Test
    public void eventExecutorTest() {
        
        List<TestEvent> events = Arrays.asList(new TestEvent(), new TestEvent(), new TestEvent(), new TestEvent(), new TestEvent());

        events.forEach(eventExecutor::registerEvent);

        eventExecutor.removeEvent(events.get(3).getID()); // Event 3 Wird entfernt
        eventExecutor.removeEvent(events.get(4)); // Event 5 wird entfernt

        eventExecutor.registerEvent(events.get(4)); // Event 5 wird neu registriert und ist jetzt Event 3 da in Slot 3 wieder Platz ist

        eventExecutor.executeEvents(); // Führt alle registrierten Events aus

        String m1;
        String m2;

        if (eventExecutor.getEventsAmount() > 1) {
            m1 = "n ";
            m2 = "s ";
        } else {
            m1 = " ";
            m2 = " ";
        }
        LogUtils.test("Es wurde" + m1 + eventExecutor.getEventsAmount() + " Event" + m2 + "ausgeführt. Es sind noch " + eventExecutor.getEventsFreeAmount() + " von " + eventExecutor.getEventsSizeAmount() + " Slots frei.");
        
    }

    @Test
    public void exit() {
        
        Thread t = new Thread(() -> {
            try {
                Thread.sleep(50);
                LogUtils.debug("Rellu", true);
                eventExecutor.cleanUp();
                System.exit(0);
            } catch (InterruptedException e) {
                LogUtils.error(e.getMessage());
            }
        });
        t.start();
        
    }

    @Test
    public void threadTest() {
        ThreadMaster tm = new ThreadMaster();
        boolean t1 = tm.startTread(tm.addThread(new ThreadWorker() {
            @Override
            public void run() {
                sleep(2);
                LogUtils.info("Test22");
            }
        }));
        boolean t2 = tm.startTread(tm.addThread(new ThreadWorker() {
            @Override
            public void run() {
                sleep(3);
                LogUtils.info("Test44");
            }
        }));
        boolean t3 = tm.startTread(tm.addThread(new ThreadWorker() {
            @Override
            public void run() {
                sleep(4);
                LogUtils.info("Test66");
            }
        }));

        boolean t4 = tm.startTread( // USE ID FROM addThread Method
                tm.addThread( // USE CLASS ThreadWorker
                        new ThreadWorker() { // Override internal Method run
                    @Override
                    public void run() {
                        sleep(5); // ThreadWorker extends ThreadSleeper Class for easy sleep handling 
                        LogUtils.info("Test88"); // Actual Code that is going to be executed 
                    }
                }
                )
        );

        Assertions.assertTrue(t1);
        Assertions.assertTrue(t2);
        Assertions.assertTrue(t3);
        Assertions.assertTrue(t4);
    }

    @Test
    public void reflectionUtils() {
        
        ID id_test = new ID();
        id_test.setID(27);

        ID id_test2 = new ID();
        id_test2.setID(8);

        LogUtils.info("ID 1: " + ReflectionUtils.getValue(Objects.requireNonNull(ReflectionUtils.getMemberField(id_test, "id")), id_test));
        LogUtils.info("ID 2: " + ReflectionUtils.getValue(Objects.requireNonNull(ReflectionUtils.getMemberField(id_test2, "id")), id_test2));
        ReflectionUtils.setValue(Objects.requireNonNull(ReflectionUtils.getMemberField(id_test2, "id")), id_test2, 1994);
        LogUtils.info("ID 2: " + id_test2.getID());
        
    }

    @Test
    public void logUtilsLogLevelTest() {
        
        LogUtils.error("Test66");
        LogUtils.setLog2File(true);

        LogUtils.error("Test22");
        LogUtils.setError(false);
        LogUtils.error("Test44");
        LogUtils.setError(true);
        LogUtils.error("Test66");

        LogUtils.info("Test22");
        LogUtils.setInfo(false);
        LogUtils.info("Test44");
        LogUtils.setInfo(true);
        LogUtils.info("Test66");

        LogUtils.warning("Test22");
        LogUtils.setWarning(false);
        LogUtils.warning("Test44");
        LogUtils.setWarning(true);
        LogUtils.warning("Test66");

        LogUtils.log("Test22");
        LogUtils.setOther(false);
        LogUtils.log("Test44");
        LogUtils.setOther(true);
        LogUtils.log("Test66");
        
    }
    
    @Test
    public void pingTest(){
        
        LogUtils.info("website port 443 is " + (NetworkUtils.checkPort("https://www.relluem94.de", 443, 90) ? "open" : "closed")  );
        
    }

    @Test
    public void typeTest(){
        JSONObject  js = new JSONObject();
        LogUtils.info(js);
        LogUtils.info("Int2Hex " + TypeUtils.convertInt2Hex(243));
        LogUtils.info("Hex2Int " + TypeUtils.convertHex2Int(0xF3));
        
    }

    @Test
    void testEventExecutorSize(){
        int eventSize = 50;
        EventExecutor lEventExecutor = new EventExecutor(eventSize);
        Assertions.assertEquals(eventSize, lEventExecutor.getEventsSizeAmount());
    }

    @Test
    void testEventExecutorEventCanceled() throws EventException {
        TestEvent testEvent = new TestEvent();
        eventExecutor.registerEvent(testEvent);

        int preExecute = eventExecutor.getEventsAmount();

        testEvent.setCanceled(true);
        int executed = eventExecutor.executeEvents();

        int postExecute = eventExecutor.getEventsAmount();

        Assertions.assertNotEquals(preExecute, executed);
        Assertions.assertNotEquals(postExecute, executed);
        Assertions.assertEquals(preExecute, postExecute);
        Assertions.assertEquals(1, preExecute);
        Assertions.assertEquals(1, postExecute);

        Assertions.assertTrue(testEvent.isCanceled());

        ID idOfEvent = testEvent.getID();

        Assertions.assertNotNull(idOfEvent);
        eventExecutor.getEvent(idOfEvent).setCanceled(false);

        Assertions.assertFalse(eventExecutor.getEvent(testEvent).isCanceled());

        executed = eventExecutor.executeEvents();
        Assertions.assertEquals(executed, preExecute);



    }
}