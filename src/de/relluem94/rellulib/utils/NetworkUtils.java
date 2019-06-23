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
	private static URL getURL(String http){
		try {
			return new URL(http);
		} catch (MalformedURLException e) {
			LogUtils.error(e.getMessage());
			return null;
		}
	}
	
	public static boolean exists(URL url) {
	    boolean result = false;
	    try {
			InputStream input = url.openStream();
	        input.close();
	        input = null;
	        result = true;
	    } catch (IOException ex) {}   
	    return result;
	} 
	
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
