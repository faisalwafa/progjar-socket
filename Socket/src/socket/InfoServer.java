package socket;

import java.io.*;
import java.net.*;
import java.util.*;

public class InfoServer {

    private final int INFO_PORT = 50000;
    private String datafromClient;

    public InfoServer() {
        BufferedReader inFromClient;
        DataOutputStream outToClient;
        Socket serverSocket;
        try {
            ServerSocket infoServer
                    = new ServerSocket(INFO_PORT);
            System.out.println("Server telah siap...");
            while (true) {
                serverSocket = infoServer.accept();
                System.out.println("Ada client "
                        + "yang terkoneksi!");
                inFromClient
                        = new BufferedReader(
                                new InputStreamReader(
                                        serverSocket.getInputStream()));
                outToClient
                        = new DataOutputStream(
                                serverSocket.getOutputStream());
                outToClient.writeBytes("InfoServer versi 0.1\n"
                        + "hanya untuk testing..\n"
                        + "Silahkan berikan perintah TIME | NET | QUIT | LOCALHOST\n");
                boolean isQUIT = false;
                while (!isQUIT) {
                    datafromClient = inFromClient.readLine();
                    if (datafromClient.startsWith("TIME")) {
                        outToClient.writeBytes(new Date().toString() + "\n"
                        );
                    } else if (datafromClient.startsWith("NET")) {
                        outToClient.writeBytes(
                                InetAddress.getByName("google.com").toString()
                                + "\n");
                    } else if (datafromClient.startsWith("QUIT")) {
                        isQUIT = true;
                    } else if (datafromClient.startsWith("LOCALHOST")) {
                        outToClient.writeBytes(InetAddress.getLocalHost().toString()+"\n");
                    }
                }
                outToClient.close();
                inFromClient.close();
                serverSocket.close();
                System.out.println("Koneksi client tertutup..");
            }
        } catch (IOException ioe) {
            System.out.print("error: " + ioe);
        } catch (Exception e) {
            System.out.print("error: " + e);
        }
    }

    public static void main(String[] args) {
        new InfoServer();
    }
}
