import java.net.*;
import java.util.Scanner;

public class Client {

    static Scanner sc = new Scanner(System.in);
    static byte[] data = new byte[1024];
    static String sentence;

    public static void main(String args[]) throws Exception {
        try {
        	DatagramSocket clientSocket = new DatagramSocket();
            do {
                InetAddress IPAddress = InetAddress.getByName("localhost");
                System.out.print("Client: ");
                sentence = sc.nextLine();
                data = sentence.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(data, data.length, IPAddress, 1234);
                clientSocket.send(sendPacket);
                
                DatagramPacket receivePacket = new DatagramPacket(data, data.length);
                clientSocket.receive(receivePacket);
                sentence = new String(receivePacket.getData());
                System.out.println("Server: " + sentence);
            } while (!"exit".equals(sentence));
            clientSocket.close();
        }catch(Exception e){e.printStackTrace();}
    }
}