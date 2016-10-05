import java.lang.*;
import java.io.*;
import java.net.*;

class Server {
   public static void main(String args[]) {
      String data = "Testing this configuration!";
      int port = Integer.parseInt(args[0]);
      try {
         ServerSocket srvr = new ServerSocket(port);
         System.out.print("Server is listening!\n");
         Socket skt = srvr.accept();
         System.out.print("Server has connected!\n");
         //PrintWriter out = new PrintWriter(skt.getOutputStream(), true);
         DataOutputStream out = new DataOutputStream(skt.getOutputStream());
         System.out.print("Sending message: " + data + "\n");
         out.writeBytes(data);

         BufferedReader in = new BufferedReader(new InputStreamReader(skt.getInputStream()));
         String line;
         System.out.print("Received message: ");
         while((line=in.readLine())!=null){
        	 System.out.println(line +"\n");
         }
         out.close();
         skt.close();
         srvr.close();
      }
      catch(Exception e) {
         //System.out.print("Oops! It didn't work!\n");
         System.out.println(e.toString());
      }
      
   }
}