package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;

public class Canvas implements Runnable {

    private Socket socket;
    private Scanner in;
    private PrintWriter out;

    public Canvas(Socket socket) {
        this.socket = socket;
    }

//    public void run() {
//        if (this.socket == null) return;
//
//        try {
//            DataInputStream in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
//            PrintStream out = new PrintStream(
//                    new BufferedOutputStream(socket.getOutputStream(), 1024), false);
//            FrontLogoProtocol flp = new FrontLogoProtocol();
//            String inputLine, outputLine;
//
//            outputLine = flp.processInput(null);
//            out.println(outputLine);
//            out.flush();
//
//            while ((inputLine = in.readLine()) != null) {
//                if (inputLine.toLowerCase().equals("quit")) {
//                    break;
//                }
//                outputLine = flp.processInput(inputLine);
//                out.println(outputLine);
//                out.flush();
//            }
//            out.close();
//            in.close();
//        }catch(IOException e){
//            e.printStackTrace();
//        } finally {
//            try {
//                socket.close();
//            } catch (IOException e) {
//                System.out.println("Closed :" + socket + "\tMessage :" + e.getMessage());
//                e.printStackTrace();
//            }
//        }
//  }

    public void run() {

        try {
            in = new Scanner(socket.getInputStream());
            //in = new Scanner(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), false);

            out.write("hello\n");
            out.flush();

            FrontLogoProtocol flp = new FrontLogoProtocol();
            String inputLine, outputLine;
            StringBuilder sb = new StringBuilder();


            while ((inputLine = in.nextLine()) != null) {
                if (inputLine.equals("quit")) {
                    sb.setLength(sb.length() - 1);
                    out.println(sb.toString());
                    out.flush();
                    //sb = new StringBuilder();
                    break;
                }
                outputLine = flp.processInput(inputLine);
                if (outputLine != null) {
                    sb.append(outputLine);
                    sb.append("\r\n");
                }
            }
            out.close();
            in.close();
        } catch (Exception ex) {
            System.out.println("Error: " + socket + "\tMessage :" + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println("Closed :" + socket + "\tMessage :" + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
