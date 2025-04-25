package de.relluem94.rellulib.utils;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;

import javax.imageio.ImageIO;

import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;

import de.relluem94.rellulib.Image;

public class NetworkUtils {

    public NetworkUtils() {
        throw new IllegalStateException("Utility class");
    }

    /**
     *
     * @param http String with Hyperlink
     * @return URL Resource
     */
    protected static URL getURL(String http) {
        try {
            return new URL(http);
        } catch (MalformedURLException e) {
            LogUtils.error(e.getMessage());
            return null;
        }
    }

    /**
     *
     * @param url URL
     * @return boolean true if HTTP OK
     */
    public static boolean notExists(@Nullable URL url) {
        if(url == null){
            return true;
        }

        try {
            InputStream input = url.openStream();
            input.close();
            return false;
        } catch (IOException ex) {
            LogUtils.error(ex.getMessage());
            return true;
        }
    }

    /**
     *
     * @param http String with Hyperlink
     * @return BufferedImage from Hyperlink
     */
    public static @Nullable BufferedImage downloadImage(String http) {
        URL url = getURL(http);
        if(notExists(url)) {
            return null;
        }

        try {
            return ImageIO.read(url);
        } catch (IOException e) {
            LogUtils.error(e.getMessage());
            return null;
        }
    }

    /**
     *
     * @param http String with Hyperlink
     * @param path String with Path to Location on the Disk
     * @param filename Name to save the Image
     * @return boolean true if saved to Disk
     */
    public static boolean downloadImage(String http, String path, String filename) {
        URL url = getURL(http);
        if(notExists(url)) {
            return false;
        }

        try {
            FileUtils.writeImage(new Image(ImageIO.read(url), new File(path + "/" + filename)));
            return true;
        } catch (IOException e) {
            LogUtils.error(e.getMessage());
            return false;
        }
    }

    /**
     *
     * @param host String
     * @param port to check
     * @param timeout in ms
     * @return boolean true if port is open
     */
    public static boolean checkPort(String host, int port, int timeout) {
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(host, port), timeout);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     *
     * @param host String
     * @return IP Address as a String
     */
    public static String getIP(String host) {
        InetSocketAddress in = new InetSocketAddress(host, 0);
        if (in.isUnresolved()) {
            return null;
        } else {
            return in.getAddress().getHostAddress();

        }
    }

    public static JSONObject getJSON(String getUrl){
        StringBuilder jsonString = new StringBuilder();
        try {
            URL url = getURL(getUrl);

            if(url == null){
                return null;
            }

            URLConnection uc = url.openConnection();
            try (BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()))) {
                String inputLine;
                while ((inputLine = in.readLine()) != null){
                    jsonString.append(inputLine).append(" ");
                }
            }
        } catch (IOException e) {
            LogUtils.error(e.getMessage());
        }

        if(jsonString.isEmpty()){
            jsonString.append("{}");
        }

        return new JSONObject(jsonString.toString());
    }
}
