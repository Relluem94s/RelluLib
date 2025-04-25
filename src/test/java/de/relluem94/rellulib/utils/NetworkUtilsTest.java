package de.relluem94.rellulib.utils;

import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class NetworkUtilsTest {

    @Test
    void checkForUtilityClass(){
        Assertions.assertThrows(IllegalStateException.class, NetworkUtils::new);
    }

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
        try (MockedStatic<LogUtils> ignored = mockStatic(LogUtils.class)) {
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

    @Test
    void testGetIP_ResolvedHost() {
        String ip = NetworkUtils.getIP("google.com");
        assertNotNull(ip);
        assertFalse(ip.isEmpty());
    }

    @Test
    void testGetIP_UnresolvedHost() {
        String ip = NetworkUtils.getIP("invalid.invalidDomain.test");
        assertNull(ip);
    }

    @Test
    void testGetJSON_ValidJSON() throws Exception {
        String expectedJson = "{\"key\":\"value\"}";

        InputStream inputStream = new ByteArrayInputStream(expectedJson.getBytes());
        URLConnection mockConnection = mock(URLConnection.class);
        when(mockConnection.getInputStream()).thenReturn(inputStream);

        URL mockUrl = mock(URL.class);
        when(mockUrl.openConnection()).thenReturn(mockConnection);

        try (MockedStatic<NetworkUtils> networkUtils = mockStatic(NetworkUtils.class, CALLS_REAL_METHODS)) {
            networkUtils.when(() -> NetworkUtils.getURL("https://example.com/data.json")).thenReturn(mockUrl);

            JSONObject json = NetworkUtils.getJSON("https://example.com/data.json");
            assertNotNull(json);
            assertEquals("value", json.getString("key"));
        }
    }


    @Test
    void testGetJSON_IOException() throws Exception {
        URL url = mock(URL.class);
        when(url.openConnection()).thenThrow(new IOException("fail"));

        try (MockedStatic<NetworkUtils> utils = mockStatic(NetworkUtils.class, CALLS_REAL_METHODS);
             MockedStatic<LogUtils> logUtils = mockStatic(LogUtils.class)) {

            utils.when(() -> NetworkUtils.getURL("https://example.com/data.json")).thenReturn(url);

            JSONObject result = NetworkUtils.getJSON("https://example.com/data.json");
            assertNotNull(result);
            assertTrue(result.isEmpty());
            logUtils.verify(() -> LogUtils.error(anyString()));
        }
    }

    @Test
    void testGetJSON_NULL() {
        try (MockedStatic<NetworkUtils> utils = mockStatic(NetworkUtils.class, CALLS_REAL_METHODS);
             MockedStatic<LogUtils> logUtils = mockStatic(LogUtils.class)) {

            utils.when(() -> NetworkUtils.getURL("https://example.com/data.json")).thenReturn(null);

            JSONObject result = NetworkUtils.getJSON("https://example.com/data.json");
            assertNull(result);
            logUtils.verify(() -> LogUtils.error(anyString()), Mockito.never());
        }
    }

    @Test
    void testCheckPort_IOException() {
        assertFalse(NetworkUtils.checkPort("256.256.256.256", 80, 100));
    }

    @Test
    void testDownloadImage_IOException() throws IOException {
        String http = "https://example.com/image.png";
        URL url = new URL(http);

        try (MockedStatic<NetworkUtils> networkUtils = mockStatic(NetworkUtils.class, CALLS_REAL_METHODS);
             MockedStatic<ImageIO> imageIO = mockStatic(ImageIO.class);
             MockedStatic<LogUtils> logUtils = mockStatic(LogUtils.class)) {

            networkUtils.when(() -> NetworkUtils.getURL(http)).thenReturn(url);
            networkUtils.when(() -> NetworkUtils.notExists(url)).thenReturn(false);
            imageIO.when(() -> ImageIO.read(url)).thenThrow(new IOException("fail"));

            assertNull(NetworkUtils.downloadImage(http));
            logUtils.verify(() -> LogUtils.error(anyString()), Mockito.times(2));
        }
    }

    @Test
    void testDownloadImageToDisk_IOException() throws IOException {
        String http = "https://example.com/image.png";
        String path = "/path";
        String filename = "file.png";
        URL url = new URL(http);

        try (MockedStatic<NetworkUtils> networkUtils = mockStatic(NetworkUtils.class, CALLS_REAL_METHODS);
             MockedStatic<ImageIO> imageIO = mockStatic(ImageIO.class);
             MockedStatic<LogUtils> logUtils = mockStatic(LogUtils.class)) {

            networkUtils.when(() -> NetworkUtils.getURL(http)).thenReturn(url);
            networkUtils.when(() -> NetworkUtils.notExists(url)).thenReturn(false);
            imageIO.when(() -> ImageIO.read(url)).thenThrow(new IOException("fail"));

            assertFalse(NetworkUtils.downloadImage(http, path, filename));
            logUtils.verify(() -> LogUtils.error(anyString()), Mockito.times(2));
        }
    }
}