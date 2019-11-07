package socket;

import java.net.*;
import java.io.*;

public class serverport {

    public static void main(String[] args) {
        ServerSocket theServer;
        for (int i = 1024; i <= 65535; i++) {
            try {
                theServer = new ServerSocket(i);
                theServer.close();
            } catch (IOException e) {
                System.out.println("There is a server on port " + i + ".");
            }
        }
    }
}
