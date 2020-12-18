package de.relluem94.rellulib.test;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import de.relluem94.rellulib.FixedSizeList;
import de.relluem94.rellulib.color.Color3i;
import de.relluem94.rellulib.color.Color4f;
import de.relluem94.rellulib.events.EventExecutor;
import de.relluem94.rellulib.json.Json;
import de.relluem94.rellulib.stores.DoubleStore;
import de.relluem94.rellulib.stores.TrippleStore;
import de.relluem94.rellulib.textures.Texture;
import de.relluem94.rellulib.textures.TextureSize;
import de.relluem94.rellulib.utils.FileUtils;
import de.relluem94.rellulib.utils.LogUtils;
import de.relluem94.rellulib.vector.Vector5f;
import de.relluem94.rellulib.windows.Frame;
import de.relluem94.rellulib.windows.SplashScreen;

public class TestClass extends SplashScreen {

    //private static SplashScreen sc = new SplashScreen();
    private static EventExecutor ee = new EventExecutor();

    public static void main(String[] args) {
        logUtilsTest();
        eventExecutorTest();
        frameTest();
        jsonTest();

        exit();
    }

    public static void logUtilsTest() {
        List<Object> list = new ArrayList<Object>();
        list.add(123);
        list.add("Hello");
        list.add(new Vector5f(1.0f, 0.4f, 5.3f, 0.123456789f, 0.0f));
        list.add(true);
        list.add(Color3i.RELLU_ORANGE);
        list.add(new TrippleStore("Das ist ein Test", 22, Color4f.GRAY));
        list.add(123);

        LogUtils.list(list);
    }

    public static void jsonTest() {
        FixedSizeList<DoubleStore> stores = new FixedSizeList<DoubleStore>(9);
        stores.set(0, new DoubleStore("Firstname", "Johannes"));
        stores.set(1, new DoubleStore("Lastname", "M�ller"));
        stores.set(2, new DoubleStore("Age", 22));
        stores.set(3, new DoubleStore("Married", false));
        stores.set(4, new DoubleStore("Size", 1.94F));
        String[] lang = {"Deutsch", "Englisch", "Java", "PHP", "MySQL"};
        Integer[] num = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] num2 = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        stores.set(5, new DoubleStore("Languages", lang));
        stores.set(6, new DoubleStore("Numbers", num));
        stores.set(7, new DoubleStore("Numbers2", num2));
        stores.set(8, new DoubleStore("Test", new DoubleStore("Test2", new DoubleStore("Test3", num))));
        try {
            FileUtils.writeDoubleStoreTextFile(new File("C:/Users/Rellu/RelluEngine2/test.txt"), stores);
            LogUtils.log(Json.toJson(FileUtils.readDoubleStoreTextFile(new File("C:/Users/Rellu/RelluEngine2/test.txt").getAbsolutePath(), Charset.defaultCharset())));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        LogUtils.log(Json.toJson(stores));
    }

    public static void frameTest() {
        Frame f = new Frame("TestClass", new Dimension(500, 500), new Dimension(500, 500), new Dimension(500, 500), new Dimension(500, 500), false, false, true);

        f.addToPanel(new JLabel("abc"));
        f.addToPanel(new JLabel("123"));
        f.addToPanel(new JLabel("def"));
        f.addToPanel(new JLabel("456"));
        f.addToPanel(new JLabel("ghi"));

        f.addToPanel(new JLabel(new ImageIcon(Texture.generateChessImage(Color3i.RELLU_GRAY, Color3i.RELLU_BLUE, TextureSize.SMALL))));
        f.addToPanel(new JLabel(new ImageIcon(Texture.generateChessImage(Color3i.RELLU_GRAY, Color3i.RELLU_GREEN, TextureSize.NORMAL))));
        f.addToPanel(new JLabel(new ImageIcon(Texture.generateChessImage(Color3i.RELLU_GRAY, Color3i.RELLU_RED, TextureSize.DOUBLE))));
        f.addToPanel(new JLabel(new ImageIcon(Texture.generateChessImage(Color3i.RELLU_GRAY, Color3i.RELLU_ORANGE, TextureSize.TRIPPLE))));

        f.addToPanel(new JButton("Test Button"));

        f.setLayout(new BorderLayout());
        f.add(f.getPanel(), BorderLayout.CENTER);

        f.pack();

        JButton b = new JButton("a");
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        f.setVisible(true);
    }

    public static void eventExecutorTest() {
        List<TestEvent> events = Arrays.asList(new TestEvent(), new TestEvent(), new TestEvent(), new TestEvent(), new TestEvent());

        for (TestEvent e : events) {
            ee.registerEvent(e);
        }

        ee.removeEvent(events.get(3).getID()); // Event 3 Wird entfernt
        ee.removeEvent(events.get(5)); // Event 5 wird entfernt

        ee.registerEvent(events.get(5)); // Event 5 wird neu registriert und ist jetzt Event 3 da in Slot 3 wieder Platz ist

        ee.executeEvents(); // Führt alle Registrierten Events aus

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
