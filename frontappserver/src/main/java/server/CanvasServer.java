package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CanvasServer {

    private static final int SERVER_PORT = 8124;

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(20);
        try {
            ServerSocket listener = new ServerSocket(SERVER_PORT);
            System.out.println("The Canvas server is running and listening on port : " + SERVER_PORT);

            while (true) {
                pool.execute(new Canvas(listener.accept()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
