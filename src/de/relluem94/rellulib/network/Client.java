package de.relluem94.rellulib.network;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {

    private final Socket socket = new Socket();
    private final int timeout = 60;

    public boolean connect(String host, int port) throws IOException {
        socket.connect(new InetSocketAddress(host, port), timeout);
        return socket.isConnected();
    }

    public void close() throws IOException {
        socket.close();
    }
}
