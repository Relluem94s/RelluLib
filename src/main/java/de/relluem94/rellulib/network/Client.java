package de.relluem94.rellulib.network;

import de.relluem94.rellulib.utils.LogUtils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {

    private final Socket socket;

    public Client(){
        socket = new Socket();
    }

    public boolean connect(String host, int port) throws IOException {
        int timeout = 60;
        if (socket.isClosed()) {
            LogUtils.error("Client socket is already closed before connect");
            throw new IllegalStateException("Socket is closed");
        }
        LogUtils.info("Client attempting to connect to " + host + ":" + port);
        socket.connect(new InetSocketAddress(host, port), timeout);
        LogUtils.info("Client connection status: " + socket.isConnected());
        return socket.isConnected();
    }

    public void close() throws IOException {
        if (!socket.isClosed()) {
            LogUtils.info("Closing client socket");
            socket.close();
        }
    }
}
