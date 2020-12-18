package de.relluem94.rellulib.test;

import de.relluem94.rellulib.events.EventExecutor;
import de.relluem94.rellulib.utils.LogUtils;

public class TestClass3 {

    private static EventExecutor ee = new EventExecutor();

    public static void main(String[] args) {
        eventExecutorTest();
        exit();
    }

    public static void eventExecutorTest() {
        TestEvent testevent_0 = new TestEvent() {
            @Override
            public void update() {
                System.out.println("aa");
            }
        };

        TestEvent testevent_1 = new TestEvent() {
            @Override
            public void update() {
                System.out.println("aa");
            }
        };

        ee.registerEvent(testevent_0); //First Event
        ee.registerEvent(testevent_1); //Second Event

        ee.executeEvents(); // F�hrt alle Registrierten Events aus

        String m1 = "";
        String m2 = "";

        if (ee.getEventsAmount() > 1) {
            m1 = "n ";
            m2 = "s ";
        } else {
            m1 = " ";
            m2 = " ";
        }
        LogUtils.log("Es wurde" + m1 + ee.getEventsAmount() + " Event" + m2 + "ausgef�hrt. Es sind noch " + ee.getEventsFreeAmount() + " von "
                + ee.getEventsSizeAmount() + " Slots frei.");
    }

    public static void exit() {
        Thread t = new Thread() {
            public void run() {
                try {
                    sleep(5000);
                    ee.cleanUp();
                    System.exit(0);
                } catch (Exception e) {
                    LogUtils.error(e.toString());
                }
            }
        };
        t.start();
    }
}
