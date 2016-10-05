import java.lang.*;
import java.io.*;
import java.net.*;

class Client {
   public static void main(String args[]) {
      try {
         Socket skt = new Socket("localhost", Integer.parseInt(args[0]));
         BufferedReader in = new BufferedReader(new InputStreamReader(skt.getInputStream()));        
         System.out.print("Received message: ");
         String line;
         while ((line = in.readLine())!=null) {
        	 System.out.println(line +"\n");
         }
    	          
         BufferedWriter out = new BufferedWriter(new OutputStreamWriter(skt.getOutputStream()));
         String msg = "Test was successfull!";
         System.out.print("Sending message: " + msg + "\n");
         out.write(msg);
         
         skt.close();
      }
      catch(Exception e) {
    	  System.out.println(e.toString());
      }
   }
}