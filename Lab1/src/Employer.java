import java.util.ArrayList;
import java.util.Scanner;


public class Employer {
	public static void main(String[] args) {
		int ch=0;
		int k;
		String a;
		float b;
		int c=0;
		Scanner s = new Scanner(System.in);
		Employee t;
		ArrayList<Employee> emp = new ArrayList<Employee>();
		do {
			System.out.println("\nWhat would you like to do?\n1) Add Employee\n2) Change Salary\n3) Check Salary\n4) Fire\n5) Exit\n");
			ch = s.nextInt();
			switch(ch){
			case 1:
				System.out.println("\nEnter Name: \n");
				a = s.next();
				System.out.println("\nEnter Salary: \n");
				b = s.nextFloat();
				t = new Employee(c,a, b);
				emp.add(t);
				System.out.println("Salary : " + emp.get(c).getInfo()+", id = "+c);
				c++;
				break;
				
			case 2:
				System.out.println("\nEnter ID: \n");
				k = s.nextInt();
				System.out.println("\nEnter amount: \n");
				b = s.nextFloat();
				System.out.println("Salary:"+emp.get(k).salaryChange(b)+"\n");
				break;
				
			case 3:
				System.out.println("\nEnter ID: \n");
				k = s.nextInt();
				System.out.println("Salary:"+emp.get(k).getInfo()+"\n");
				break;
				
			case 4:
				System.out.println("\nEnter ID: \n");
				k = s.nextInt();
				emp.get(k).quit();
				System.out.println("Salary:0\n");
				
			}
		}while(ch!=5);
	}
}
