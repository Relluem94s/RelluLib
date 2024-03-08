package de.relluem94.rellulib.network;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {

    private final Socket socket = new Socket();

    public boolean connect(String host, int port) throws IOException {
        int timeout = 60;
        socket.connect(new InetSocketAddress(host, port), timeout);
        return socket.isConnected();
    }

    public void close() throws IOException {
        socket.close();
    }
}
