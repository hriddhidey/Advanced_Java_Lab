import java.util.Scanner;


public class Employee {
	
	int id;
	String name;
	float salary;
	
	public Employee(int c,String a,float b) {
		id = c;
		name=a;
		salary=b;
	}
	
	public float salaryChange(float amount) {
		Scanner s = new Scanner(System.in);
		System.out.print("Inc or Dec? [I/D]");
		char ch = s.nextLine().charAt(0);
		switch(ch){
		case 'I':
		case 'i': salary+=amount;break;
		case 'D':
		case 'd': salary-=amount;break;
		default: System.exit(0);
		}
		return this.salary;
	}
	
	public void quit() {
		salary=0;
	}
	
	public float getInfo(){
		return this.salary;
	}
}
