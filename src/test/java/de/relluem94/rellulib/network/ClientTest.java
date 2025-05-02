package de.relluem94.rellulib.network;

import de.relluem94.rellulib.threads.ThreadMaster;
import de.relluem94.rellulib.threads.ThreadWorker;
import de.relluem94.rellulib.utils.LogUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClientTest {
    private Client client;
    private Server server;
    private Socket socket;
    private ThreadMaster tm;
    private int port;

    @BeforeEach
    void setUp() throws IOException {
        port = getFreePort();

        client = new Client();
        server = new Server(port);
        socket = spy(Socket.class);
        tm = new ThreadMaster();
    }

    @Test
    void testConnectSuccess() throws IOException, InterruptedException {
        CountDownLatch serverReady = new CountDownLatch(1);
        CountDownLatch clientDone = new CountDownLatch(1);
        AtomicReference<Throwable> serverError = new AtomicReference<>();

        ThreadWorker twServer = new ThreadWorker(1) {
            @Override
            public void run() {
                try {
                    LogUtils.info("Server thread started");
                    serverReady.countDown();
                    server.start();
                } catch (IOException e) {
                    LogUtils.error("Server thread failed: " + e.getMessage());
                    serverError.set(e);
                    serverReady.countDown();
                }
            }
        };

        ThreadWorker twClient = new ThreadWorker(1) {
            @Override
            public void run() {
                try {
                    LogUtils.info("Client thread waiting for server");
                    assertTrue(serverReady.await(5, TimeUnit.SECONDS));
                    LogUtils.info("Client thread attempting connection");
                    assertTrue(client.connect("localhost", port));
                    LogUtils.info("Client thread connected successfully");
                    assertEquals(port, server.getPort());
                    clientDone.countDown();
                } catch (IOException | InterruptedException e) {
                    LogUtils.error("Client thread failed: " + e.getMessage());
                    throw new RuntimeException(e);
                }
            }
        };

        tm.addThread(twServer);
        tm.addThread(twClient);

        LogUtils.info("Starting Server Thread");
        tm.startTread(0);

        assertTrue(serverReady.await(5, TimeUnit.SECONDS), "Server did not start in time");

        if (serverError.get() != null) {
            fail("Server thread failed with exception: " + serverError.get().getMessage());
        }

        LogUtils.info("Starting Client Thread");
        tm.startTread(1);

        assertTrue(clientDone.await(5, TimeUnit.SECONDS), "Client did not complete in time");

        client.close();
        server.stop();
    }

    private static int getFreePort() throws IOException {
        try (ServerSocket socket = new ServerSocket(0)) {
            return socket.getLocalPort();
        }
    }

    @Test
    void testConnectFailure() throws IOException {
        doThrow(new IOException("Connection refused")).when(socket).connect(any(InetSocketAddress.class), eq(60));

        assertThrows(IOException.class, () -> client.connect("invalid-host", 8080));
        client.close();
    }

    @Test
    void testSocketClosed() throws IOException {
        client.close();
        assertThrows(IllegalStateException.class, () -> client.connect("localhost", 8080));
        client.close();
    }
}