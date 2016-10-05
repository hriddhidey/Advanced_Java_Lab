import java.io.*;
import java.net.*;
import java.util.Scanner;


public class Server extends Thread {
    static Scanner s = new Scanner(System.in);
    private ServerSocket serverSocket;
    
    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(100000);
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("Waiting for client on port " + serverSocket.getLocalPort() + "...");
                Socket server = serverSocket.accept();
                System.out.println("Just connected to " + server.getRemoteSocketAddress());
                String msg;
                do {
                    InputStream inFromServer = server.getInputStream();
                    DataInputStream in = new DataInputStream(inFromServer);
                    System.out.println("Client: " + in.readUTF());

                    System.out.print("Server: ");
                    msg = s.nextLine();
                    OutputStream outToServer = server.getOutputStream();
                    DataOutputStream out = new DataOutputStream(outToServer);
                    out.writeUTF(msg);
                    if(msg.equals("stop")){server.close();System.exit(0);}
                } while (!msg.equals("exit"));
            } catch (Exception e) {break;}
        }
    }

    public static void main(String[] args) {
        int port = 6789;
        try {
            Thread t = new Server(port);
            t.start();
        } catch (IOException e) {
        }
    }
}