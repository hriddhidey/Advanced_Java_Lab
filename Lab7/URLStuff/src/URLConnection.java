import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;


public class URLConnection {
	public static void main(String[] args){
		try {
			URL url = new URL("http://hriddhidey.github.io/me");
			java.net.URLConnection conn = url.openConnection();
			InputStream reader = conn.getInputStream();
			int i;
			while((i=reader.read())!=-1){
				System.out.println((char)i);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
