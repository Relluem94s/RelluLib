package de.relluem94.rellulib.windows;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.awt.image.BufferedImage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import de.relluem94.rellulib.color.Color4i;

public class SplashScreenTest {

    private SplashScreen splashScreen;

    @BeforeEach
    void setUp() {
        BufferedImage logo = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        Color4i color = new Color4i(255, 0, 0, 255);
        splashScreen = new SplashScreen(logo, "Test Title", "Test Text", color);
    }

    @AfterEach
    void tearDown() {
        if (splashScreen != null) {
            splashScreen.setVisible(false);
        }
    }

    @Test
    void testInit() {
        assertEquals("Test Title", splashScreen.getFrame().getTitle());
        assertEquals(425, splashScreen.getFrame().getWidth());
        assertEquals(155, splashScreen.getFrame().getHeight());
        assertTrue(splashScreen.getFrame().isUndecorated());
        assertEquals(new Color(255, 0, 0, 255), splashScreen.getFrame().getContentPane().getBackground());
    }

    @Test
    void testSetDisposeAfterLoading() {
        splashScreen.setDisposeAfterLoading(false);
        assertFalse(splashScreen.isDisposeAfterLoading());
    }

    @Test
    void testShowProgressBar() {
        splashScreen.showProgressBar(false);
        assertFalse(splashScreen.isShowProgressBar());
    }

    @Test
    void testSetVisible() {
        splashScreen.setVisible(true);
        assertTrue(splashScreen.getFrame().isVisible());
        splashScreen.setVisible(false);
        assertFalse(splashScreen.getFrame().isVisible());
    }

    @Test
    void testStart() throws InterruptedException {
        splashScreen.setVisible(true);
        splashScreen.start();
        Thread.sleep(10000); // Wait for thread to complete
        assertFalse(splashScreen.getFrame().isDisplayable());
    }

    @Test
    void testProgressBarUpdates() throws InterruptedException {
        splashScreen.setVisible(true);
        splashScreen.start();
        Thread.sleep(1000); // Wait for some progress
        assertTrue(splashScreen.getProgressBar().getValue() > 0);
    }
}