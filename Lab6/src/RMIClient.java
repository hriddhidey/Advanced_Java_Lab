import java.rmi.*;  
import java.util.Scanner;

public class RMIClient{  
	public static void main(String args[]){  
		try{  
			Adder stub=(Adder)Naming.lookup("rmi://localhost:1234/calc");  
			System.out.println("What would you like to do?\n1) Add\n2) Subtract"
					+ "\n3) Multiply\n4) Divide\n5)String Compare\n6) String reverse\n");
			Scanner sc = new Scanner(System.in);
			int ch = sc.nextInt();
			double x,y,ans;
			String a,b,sans;
			switch(ch){
			case 1: System.out.println("Enter two integers: ");
					x=sc.nextDouble();
					y=sc.nextDouble();
					ans = stub.add(x, y);
					System.out.println("Answer :"+ans);
				break;
			case 2:System.out.println("Enter two integers: ");
				x=sc.nextDouble();
				y=sc.nextDouble();
				ans = stub.subtract(x, y);
				System.out.println("Answer :"+ans);
				break;
			case 3:System.out.println("Enter two integers: ");
				x=sc.nextDouble();
				y=sc.nextDouble();
				ans = stub.multiply(x, y);
				System.out.println("Answer :"+ans);
				break;
			case 4:System.out.println("Enter two integers: ");
				x=sc.nextDouble();
				y=sc.nextDouble();
				ans = stub.divide(x, y);
				System.out.println("Answer :"+ans);
				break;
			case 5:System.out.println("Enter two strings: ");
				a = sc.next();
				b = sc.next();
				ans=stub.compare(a, b);
				if(ans==1)sans=a+" is greater.";
				else if(ans==0)sans="They are equal.";
				else sans=b+" is equal.";
				System.out.println("Answer :"+sans);
				break;
			case 6:System.out.println("Enter a strings: ");
				a = sc.next();
				sans = stub.reverse(a);
				System.out.println("Answer :"+sans);
				break;
			}
			sc.close();
		}catch(Exception e){}  
	}  
}  