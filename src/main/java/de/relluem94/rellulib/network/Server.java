package de.relluem94.rellulib.network;

import de.relluem94.rellulib.utils.LogUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private final ServerSocket serverSocket;
    private Socket socket;
    BufferedReader bufferedReader;
    private volatile boolean isRunning = true;

    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    public void start() throws IOException {
        while (isRunning && !serverSocket.isClosed()) {
            try {
                socket = serverSocket.accept();
                bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                bufferedReader.close();

                if (socket != null && !socket.isClosed()) {
                    socket.close();
                }
            }
            catch (IOException e) {
                if (!isRunning || serverSocket.isClosed()) {
                    break;
                }
                throw e;
            }

        }
    }

    public void stop() throws IOException {
        isRunning = false;
        try {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }

            if (!serverSocket.isClosed()) {
                serverSocket.close();
            }
        } catch (IOException e) {
            LogUtils.error("Error closing server socket: " + e.getMessage());
        }
    }


    public int getPort() {
        return serverSocket.getLocalPort();
    }
}
