package de.relluem94.rellulib.windows;

import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import de.relluem94.rellulib.color.Color4i;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class SplashScreenTest {

    @Mock
    private BufferedImage logo;

    @Mock
    private Image scaledImage;

    @Spy
    private SplashScreen splashScreen;

    private Color4i color;

    @BeforeEach
    void setUp() {
        color = new Color4i(255, 0, 0, 255);
        when(logo.getScaledInstance(375, 75, Image.SCALE_SMOOTH)).thenReturn(scaledImage);
        // Initialize SplashScreen with mocks
        splashScreen = spy(new SplashScreen(logo, "Test Title", "Test Text", color));
    }

    //@Test
    void testConstructor() {
        if (GraphicsEnvironment.isHeadless()) {
            return; // Skip GUI tests in headless environment
        }
        JFrame frame = splashScreen.getFrame();
        JProgressBar progressBar = splashScreen.getProgressBar();

        assertEquals("Test Title", frame.getTitle());
        assertEquals(425, frame.getWidth());
        assertEquals(155, frame.getHeight());
        assertEquals(JFrame.DISPOSE_ON_CLOSE, frame.getDefaultCloseOperation());
        assertEquals(logo, frame.getIconImage());
        assertTrue(frame.isUndecorated());
        assertEquals(new Color(255, 0, 0, 255), frame.getContentPane().getBackground());
        assertNull(frame.getLayout());

        assertEquals(0, progressBar.getMinimum());
        assertEquals(100, progressBar.getMaximum());
        assertTrue(progressBar.isStringPainted());
        assertEquals(Color.GRAY, progressBar.getForeground());
        assertEquals(Color.LIGHT_GRAY, progressBar.getBackground());
        assertEquals(15, progressBar.getX());
        assertEquals(125, progressBar.getY());
        assertEquals(393, progressBar.getWidth());
        assertEquals(20, progressBar.getHeight());
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
        if (GraphicsEnvironment.isHeadless()) {
            return; // Skip GUI tests in headless environment
        }
        splashScreen.setVisible(true);
        assertTrue(splashScreen.getFrame().isVisible());
        splashScreen.setVisible(false);
        assertFalse(splashScreen.getFrame().isVisible());
    }

    @Test
    void testStartWithProgressBarAndNoDispose() throws InterruptedException {
        if (GraphicsEnvironment.isHeadless()) {
            return; // Skip GUI tests in headless environment
        }
        splashScreen.showProgressBar(true);
        splashScreen.setDisposeAfterLoading(false);
        splashScreen.setVisible(true);

        splashScreen.start();
        Thread.sleep(1000); // Wait for some progress
        assertTrue(splashScreen.getProgressBar().getValue() > 0);

        Thread.sleep(9000); // Wait for thread completion
        assertTrue(splashScreen.getFrame().isDisplayable());
    }

    @Test
    void testStartWithDisposeAfterLoading() throws InterruptedException {
        if (GraphicsEnvironment.isHeadless()) {
            return; // Skip GUI tests in headless environment
        }
        splashScreen.showProgressBar(false);
        splashScreen.setDisposeAfterLoading(true);
        splashScreen.setVisible(true);

        splashScreen.start();
        Thread.sleep(10000); // Wait for thread completion
        assertFalse(splashScreen.getFrame().isDisplayable());
    }

    //@Test
    void testStartWithInterruptedException() throws InterruptedException {
        if (GraphicsEnvironment.isHeadless()) {
            return; // Skip GUI tests in headless environment
        }
        // Spy the thread to simulate interruption
        Thread mockThread = mock(Thread.class);
        doThrow(new InterruptedException("Test interruption")).when(mockThread);
        Thread.sleep(anyLong());

        // Use reflection to replace the thread in start
        splashScreen.showProgressBar(true);
        splashScreen.setDisposeAfterLoading(false);
        splashScreen.start();

        // Since we can't easily replace the Thread, this test is limited
        // Consider refactoring SplashScreen to inject Thread or Runnable
    }
}