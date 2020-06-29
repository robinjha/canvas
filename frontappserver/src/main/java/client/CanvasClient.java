package client;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class CanvasClient {
    public static void main(String[] args) throws IOException {
//        if (args.length != 1) {
//            System.err.println("Pass the server IP as the sole command line argument");
//            return;
//        }
        //Socket socket = new Socket(args[0], 59090);
        Socket socket = new Socket("127.0.0.1", 8124);
        Scanner in = new Scanner(socket.getInputStream());
        System.out.println("Server response: " + in.nextLine());
    }
}