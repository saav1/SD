package Controller;
import MyHTTPServer.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


class Controller {
    public static void main(String args[]) {
        try {
            init();
        } catch (Exception e) {
            System.err.println("Controller's config file not found");
        }
    }

    /**
     * Does the same that the MyHTTPServer's init, but with minor changes in order to work like a Controller
     * @param settings the readen settings.
     * @see Parking.MyHTTPServer.MyHTTPServer
     */
    private static void init() {
        try {
            SocketUtils.displayIPAdresses(); //Shows all available IP's that can connect to the server
            ServerSocket serverSocket = new ServerSocket(MyHTTPSettings.CONTROLLER_PORT);
            System.out.println("SOCKET ABIERTO EN EL PUERTO: " + serverSocket.getLocalPort());

            //noinspection InfiniteLoopStatement
            while (true) { //Server is stopped with ^C
                Socket requestSocket = serverSocket.accept();
                System.out.println("Serving to " + requestSocket.getRemoteSocketAddress());
                if (Thread.activeCount() <= MyHTTPSettings.MAX_SERVER_CONNECTIONS) {
                    Thread t = new ControllerThread(requestSocket);
                    t.start();
                } else {
                    System.err.println("Connection with " + requestSocket.getRemoteSocketAddress() + " closed due to connection limit");
                    requestSocket.close();
                }
            }
        } catch (IOException e) {
            System.err.println("ERROR PUERTO SOCKET");
        }
    }
}
