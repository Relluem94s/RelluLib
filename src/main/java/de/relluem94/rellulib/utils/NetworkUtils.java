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

import org.json.JSONObject;

import de.relluem94.rellulib.Image;

public class NetworkUtils {

    /**
     *
     * @param http String with Hyperlink
     * @return URL Resource
     */
    private static URL getURL(String http) {
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
    public static boolean exists(URL url) {
        boolean result;
        try {
            InputStream input = url.openStream();
            input.close();
            result = true;
        } catch (IOException ex) {
            result = false;
        }
        return result;
    }

    /**
     *
     * @param http String with Hyperlink
     * @return BufferedImage from Hyperlink
     */
    public static BufferedImage downloadImage(String http) {
        URL url = getURL(http);
        if (exists(url)) {
            try {
                return ImageIO.read(url);
            } catch (IOException e) {
                LogUtils.error(e.getMessage());
                return null;
            }
        } else {
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
        if (exists(url)) {
            try {
                FileUtils.writeImage(new Image(ImageIO.read(url), new File(path + "/" + filename)));
                return true;
            } catch (IOException e) {
                LogUtils.error(e.getMessage());
                return false;
            }

        } else {
            return false;
        }
    }

    /**
     *
     * @param host
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
     * @param host
     * @return IP Address as an String
     */
    public static String getIP(String host) {
        InetSocketAddress in = new InetSocketAddress(host, 0);
        if (in.isUnresolved()) {
            return null;
        } else {
            return in.getAddress().getHostAddress();

        }
    }

    public static JSONObject getJSON(String geturl){
        String jsonString = "";
        try {
            URL url = new URL(geturl);
            URLConnection uc = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
            
            String inputLine;
            while ((inputLine = in.readLine()) != null){
                jsonString += inputLine + " ";
            }
                
            in.close();

        } catch (IOException e) {
            LogUtils.error(e.getMessage());
        }
        if(jsonString.length() == 0){
            jsonString = "{}";
        }
        return new JSONObject(jsonString);
    }
}
