package echo;

import java.util.*;
import java.io.*;
import java.net.*;

public class Server {

    protected ServerSocket mySocket;
    protected int myPort;
    public static boolean DEBUG = true;
    protected Class<?> handlerType;

    public Server(int port, String handlerTypeName) {
        try {
            myPort = port;
            mySocket = new ServerSocket(myPort);
            this.handlerType = Class.forName(handlerTypeName);
        } catch(Exception e) {
            System.err.println(e.getMessage());
            System.exit(1);
        } // catch
    }


    public void listen() {
        System.out.println("Server listening at port : " +myPort);
        while(true) {
            // accept a connection
            try{
                Socket socket = mySocket.accept();
                // make handler
                RequestHandler handler = makeHandler(socket);
                // start handler in its own thread
                Thread thread = new Thread(handler);
                thread.start();
            } catch (IOException exception){
                System.err.println(exception.getMessage());
                System.exit(1);
            }

        } // while
    }

    public RequestHandler makeHandler(Socket s) {
        // handler = handlerType.getDeclaredConstructor().newInstance()
        // set handler's socket to s
        // return handler
        return new RequestHandler(s);
    }



    public static void main(String[] args) {
        int port = 5555;
        String service = "echo.RequestHandler";
        if (1 <= args.length) {
            service = args[0];
        }
        if (2 <= args.length) {
            port = Integer.parseInt(args[1]);
        }
        Server server = new Server(port, service);
        server.listen();
    }
}