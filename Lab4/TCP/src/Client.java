import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Client {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            System.out.println("Connecting to localhost on port 6789");
            Socket client = new Socket("localhost", 6789);
            System.out.println("Just connected to " + client.getRemoteSocketAddress());
            String msg;
            do {
                System.out.print("Client: ");
                msg = sc.nextLine();
                OutputStream outToServer = client.getOutputStream();
                DataOutputStream out = new DataOutputStream(outToServer);
                out.writeUTF(msg); 
                if(msg.equals("stop")){client.close();System.exit(0);}
                InputStream inFromServer = client.getInputStream();
                DataInputStream in = new DataInputStream(inFromServer);
                System.out.println("Server: " + in.readUTF());
            } while (!msg.equals("stop"));
        } catch (Exception e) {}
    }
}