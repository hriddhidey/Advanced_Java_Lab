import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Adder extends Remote{  
	public double add(double x,double y)throws RemoteException;  
	public double subtract(double x,double y)throws RemoteException;
	public double multiply(double x,double y)throws RemoteException;
	public double divide(double x,double y)throws RemoteException;
	public int compare(String a,String b)throws RemoteException;
	public String reverse(String a)throws RemoteException;
}  