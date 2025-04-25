package de.relluem94.rellulib.utils;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.net.SocketImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class NetworkUtilsTest {

    @Test
    void testGetURL_ValidURL() {
        String validUrl = "https://example.com";
        URL result = invokeGetURL(validUrl);
        assertNotNull(result);
        assertEquals(validUrl, result.toString());
    }

    @Test
    void testGetURL_MalformedURL() {
        String invalidUrl = "invalid-url";
        URL result = invokeGetURL(invalidUrl);
        assertNull(result);
    }

    private URL invokeGetURL(String http) {
        try (MockedStatic<LogUtils> logUtils = mockStatic(LogUtils.class)) {
            return (URL) NetworkUtils.class.getDeclaredMethod("getURL", String.class)
                    .invoke(null, http);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testNotExists_NullURL() {
        assertTrue(NetworkUtils.notExists(null));
    }

    @Test
    void testNotExists_ValidURL() throws IOException {
        URL url = mock(URL.class);
        InputStream inputStream = mock(InputStream.class);
        when(url.openStream()).thenReturn(inputStream);
        assertFalse(NetworkUtils.notExists(url));
        verify(inputStream).close();
    }

    @Test
    void testNotExists_IOException() throws IOException {
        URL url = mock(URL.class);
        when(url.openStream()).thenThrow(new IOException("Connection failed"));
        try (MockedStatic<LogUtils> logUtils = mockStatic(LogUtils.class)) {
            assertTrue(NetworkUtils.notExists(url));
            logUtils.verify(() -> LogUtils.error(anyString()));
        }
    }

    @Test
    void testDownloadImage_InvalidURL() {
        String invalidUrl = "invalid-url";
        try (MockedStatic<LogUtils> logUtils = mockStatic(LogUtils.class)) {
            assertNull(NetworkUtils.downloadImage(invalidUrl));
            logUtils.verify(() -> LogUtils.error(anyString()));
        }
    }

    @Test
    void testDownloadImage_URLNotExists() throws IOException {
        String http = "https://example.com/image.png";
        URL url = new URL(http);
        try (MockedStatic<NetworkUtils> networkUtils = mockStatic(NetworkUtils.class, CALLS_REAL_METHODS)) {
            networkUtils.when(() -> NetworkUtils.getURL(http)).thenReturn(url);
            networkUtils.when(() -> NetworkUtils.notExists(url)).thenReturn(true);
            assertNull(NetworkUtils.downloadImage(http));
        }
    }

    @Test
    void testDownloadImage_Success() throws IOException {
        String http = "https://example.com/image.png";
        URL url = new URL(http);
        BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        try (MockedStatic<NetworkUtils> networkUtils = mockStatic(NetworkUtils.class, CALLS_REAL_METHODS);
             MockedStatic<ImageIO> imageIO = mockStatic(ImageIO.class)) {
            networkUtils.when(() -> NetworkUtils.getURL(http)).thenReturn(url);
            networkUtils.when(() -> NetworkUtils.notExists(url)).thenReturn(false);
            imageIO.when(() -> ImageIO.read(url)).thenReturn(image);
            assertEquals(image, NetworkUtils.downloadImage(http));
        }
    }



    @Test
    void testDownloadImageToDisk_InvalidURL() {
        String http = "invalid-url";
        String path = "/path";
        String filename = "image.png";
        try (MockedStatic<LogUtils> logUtils = mockStatic(LogUtils.class)) {
            assertFalse(NetworkUtils.downloadImage(http, path, filename));
            logUtils.verify(() -> LogUtils.error(anyString()));
        }
    }

    @Test
    void testDownloadImageToDisk_URLNotExists() throws IOException {
        String http = "https://example.com/image.png";
        String path = "/path";
        String filename = "image.png";
        URL url = new URL(http);
        try (MockedStatic<NetworkUtils> networkUtils = mockStatic(NetworkUtils.class, CALLS_REAL_METHODS)) {
            networkUtils.when(() -> NetworkUtils.getURL(http)).thenReturn(url);
            networkUtils.when(() -> NetworkUtils.notExists(url)).thenReturn(true);
            assertFalse(NetworkUtils.downloadImage(http, path, filename));
        }
    }

    @Test
    void testDownloadImageToDisk_Success() throws IOException {
        String http = "https://example.com/image.png";
        String path = "/path";
        String filename = "image.png";
        URL url = new URL(http);
        BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        try (MockedStatic<NetworkUtils> networkUtils = mockStatic(NetworkUtils.class, CALLS_REAL_METHODS);
             MockedStatic<ImageIO> imageIO = mockStatic(ImageIO.class);
             MockedStatic<FileUtils> fileUtils = mockStatic(FileUtils.class)) {
            networkUtils.when(() -> NetworkUtils.getURL(http)).thenReturn(url);
            networkUtils.when(() -> NetworkUtils.notExists(url)).thenReturn(false);
            imageIO.when(() -> ImageIO.read(url)).thenReturn(image);
            assertTrue(NetworkUtils.downloadImage(http, path, filename));
            fileUtils.verify(() -> FileUtils.writeImage(any()));
        }
    }





}