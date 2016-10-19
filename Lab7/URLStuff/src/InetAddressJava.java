import java.net.*;
public class InetAddressJava {

	public static void main (String[] args){
		try {
			InetAddress IP  = InetAddress.getByName("hriddhidey.github.io");
			System.out.println(IP.getHostName()+"\n"+IP.getHostAddress());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
