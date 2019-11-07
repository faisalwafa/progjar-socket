package socket;

import java.net.*;
import java.io.*;
import java.util.*;

public class InfoClient {

    private final int INFO_PORT = 50000;
    private final String TargetHost = "localhost";
    private final String QUIT = "QUIT";

    public InfoClient() {
        try {
            BufferedReader inFromUser
                    = new BufferedReader(new InputStreamReader(System.in));
            Socket clientSocket = new Socket(TargetHost, INFO_PORT);
            DataOutputStream outToServer
                    = new DataOutputStream(
                            clientSocket.getOutputStream());
            BufferedReader inFromServer
                    = new BufferedReader(
                            new InputStreamReader(
                                    clientSocket.getInputStream()));
            System.out.println(inFromServer.readLine());
            System.out.println(inFromServer.readLine());
            System.out.println(inFromServer.readLine());
            System.out.println("");
            boolean isQuit = false;
            while (!isQuit) {
                System.out.print("Perintah Anda : ");
                String cmd = inFromUser.readLine();
                cmd = cmd.toUpperCase();
                if (cmd.equals(QUIT)) {
                    isQuit = true;
                }
                outToServer.writeBytes(cmd + "\n");
                String result = inFromServer.readLine();
                System.out.println("Dari Server: " + result);
            }
            outToServer.close();
            inFromServer.close();
            clientSocket.close();
        } catch (IOException ioe) {
            System.out.println("Error:" + ioe);
        } catch (Exception e) {
            System.out.println("Error:" + e);
        }
    }

    public static void main(String[] args) {
        new InfoClient();
    }
}
