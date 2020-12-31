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
import de.relluem94.rellulib.ID;
import de.relluem94.rellulib.color.Color3i;
import de.relluem94.rellulib.color.Color4f;
import de.relluem94.rellulib.events.EventExecutor;
import de.relluem94.rellulib.json.Json;
import de.relluem94.rellulib.stores.DoubleStore;
import de.relluem94.rellulib.stores.TrippleStore;
import de.relluem94.rellulib.textures.Texture;
import de.relluem94.rellulib.textures.TextureSize;
import de.relluem94.rellulib.threads.ThreadMaster;
import de.relluem94.rellulib.threads.ThreadWorker;
import de.relluem94.rellulib.utils.FileUtils;
import de.relluem94.rellulib.utils.InfoUtils;
import de.relluem94.rellulib.utils.LogUtils;
import de.relluem94.rellulib.utils.ReflectionUtils;
import de.relluem94.rellulib.utils.StringUtils;
import de.relluem94.rellulib.vector.Vector5f;
import de.relluem94.rellulib.windows.Frame;
import de.relluem94.rellulib.windows.SplashScreen;
import org.junit.Test;

public class Tests extends SplashScreen {

    //private static SplashScreen sc = new SplashScreen();
    private static EventExecutor ee = new EventExecutor();

    @Test
    public void symbolsTest(){
        LogUtils.test(StringUtils.replaceSymbols("[PICKAXE]"));
    }
    
    @Test
    public void logUtilsTest() {
        List<Object> list = new ArrayList<>();
        list.add(123);
        list.add("Hello");
        list.add(new Vector5f(1.0f, 0.4f, 5.3f, 0.123456789f, 0.0f));
        list.add(true);
        list.add(Color3i.RELLU_ORANGE);
        list.add(new TrippleStore("Das ist ein Test", 22, Color4f.GRAY));
        list.add(123);

        LogUtils.list(list);
    }

    @Test
    public void jsonTest() {
        FixedSizeList<DoubleStore> stores = new FixedSizeList<>(9);
        stores.set(0, new DoubleStore("Firstname", "Elon"));
        stores.set(1, new DoubleStore("Lastname", "Musk"));
        stores.set(2, new DoubleStore("Age", 42));
        stores.set(3, new DoubleStore("Married", false));
        stores.set(4, new DoubleStore("Size", 69.420F));
        String[] lang = {"Deutsch", "Englisch", "Java", "PHP", "MySQL"};
        Integer[] num = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] num2 = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        stores.set(5, new DoubleStore("Languages", lang));
        stores.set(6, new DoubleStore("Numbers", num));
        stores.set(7, new DoubleStore("Numbers2", num2));
        stores.set(8, new DoubleStore("Test", new DoubleStore("Test2", new DoubleStore("Test3", num))));
        try {
            FileUtils.writeDoubleStoreTextFile(new File(InfoUtils.USER_home() + "/RelluLib.txt"), stores);
            LogUtils.test(Json.toJson(FileUtils.readDoubleStoreTextFile(new File(InfoUtils.USER_home() + "/RelluLib.txt").getAbsolutePath(), Charset.defaultCharset())));
        } catch (IOException e) {
            LogUtils.error(e.getMessage());
        }
        LogUtils.log(Json.toJson(stores));
    }

    //@Test
    public void frameTest() {
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
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        f.setVisible(true);
    }

    @Test
    public void eventExecutorTest() {
        List<TestEvent> events = Arrays.asList(new TestEvent(), new TestEvent(), new TestEvent(), new TestEvent(), new TestEvent());

        for (TestEvent e : events) {
            ee.registerEvent(e);
        }

        ee.removeEvent(events.get(3).getID()); // Event 3 Wird entfernt
        ee.removeEvent(events.get(4)); // Event 5 wird entfernt

        ee.registerEvent(events.get(4)); // Event 5 wird neu registriert und ist jetzt Event 3 da in Slot 3 wieder Platz ist

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
        LogUtils.test("Es wurde" + m1 + ee.getEventsAmount() + " Event" + m2 + "ausgeführt. Es sind noch " + ee.getEventsFreeAmount() + " von " + ee.getEventsSizeAmount() + " Slots frei.");
    }

    @Test
    public void exit() {
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(5000);
                    ee.cleanUp();
                    System.exit(0);
                } catch (InterruptedException e) {
                    LogUtils.error(e.getMessage());
                }
            }
        };
        t.start();
    }

    @Test
    public void threadTest() {
        ThreadMaster tm = new ThreadMaster();
        tm.startTread(tm.addThread(new ThreadWorker() {
            @Override
            public void run() {
                sleep(2000);
                LogUtils.info("Test22");
            }
        }));
        tm.startTread(tm.addThread(new ThreadWorker() {
            @Override
            public void run() {
                sleep(3000);
                LogUtils.info("Test44");
            }
        }));
        tm.startTread(tm.addThread(new ThreadWorker() {
            @Override
            public void run() {
                sleep(4000);
                LogUtils.info("Test66");
            }
        }));

        tm.startTread( // USE ID FROM addThread Method
                tm.addThread( // USE CLASS ThreadWorker
                        new ThreadWorker() { // Override internal Method run
                    @Override
                    public void run() {
                        sleep(5000); // ThreadWorker extends ThreadSleeper Class for easy sleep handling 
                        LogUtils.info("Test88"); // Actual Code that is going to be executed 
                    }
                }
                )
        );
    }

    @Test
    public void reflectionUtils() {
        ID id_test = new ID();
        id_test.setID(27);

        ID id_test2 = new ID();
        id_test2.setID(8);

        LogUtils.info("ID 1: " + ReflectionUtils.getValue(ReflectionUtils.getMemberField(id_test, "id"), id_test));
        LogUtils.info("ID 2: " + ReflectionUtils.getValue(ReflectionUtils.getMemberField(id_test2, "id"), id_test2));
        ReflectionUtils.setValue(ReflectionUtils.getMemberField(id_test2, "id"), id_test2, 1994);
        LogUtils.info("ID 2: " + id_test2.getID());
    }
    
    @Test
    public void logUtilsLogLevelTest() {
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

}
