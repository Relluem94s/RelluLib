package de.relluem94.rellulib.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;

import javax.imageio.ImageIO;

import de.relluem94.rellulib.Image;

public class NetworkUtils {
	
	/**
	 * 
	 * @param http String with Hyperlink
	 * @return URL Resource
	 */
	private static URL getURL(String http){
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
	        input = null;
	        result = true;
	    } 
	    catch (IOException ex) {
	    	result = false;
	    }   
	    return result;
	} 
	
	/** 
	 * 
	 * @param http String with Hyperlink
	 * @return BufferedImage from Hyperlink
	 */
	public static BufferedImage downloadImage(String http){
		URL url = getURL(http);
		if(exists(url)){
			try {
				return ImageIO.read(url);
			} catch (IOException e) {
				LogUtils.error(e.getMessage());
				return null;
			}
		}
		else{
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
	public static boolean downloadImage(String http, String path, String filename){
		URL url = getURL(http);
		if(exists(url)){
			try{
				FileUtils.writeImage(new Image(ImageIO.read(url), new File(path + "/" + filename)));
				url = null;
				return true;
			}
			catch(Exception e){
				LogUtils.error(e.getMessage());
				url = null;
				return false;
			}
			
			
		}
		else{
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
	public static boolean checkPort(String host, int port, int timeout){
		try {
        	Socket socket = new Socket();	        	 
            socket.connect(new InetSocketAddress(host, port), timeout);
            socket.close();
            return true;
        } 
        catch (Exception e) {
        	return false;
        }
	}
	
	/**
	 * 
	 * @param host
	 * @return IP Address as an String
	 */
	public static String getIP(String host){
		InetSocketAddress in = new InetSocketAddress(host, 0);
		if(in.isUnresolved()){
			return null;
		}
		else{
			return in.getAddress().getHostAddress();
			
		}
	}
}
