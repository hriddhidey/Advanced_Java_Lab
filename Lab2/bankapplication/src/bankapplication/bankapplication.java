package bankapplication;
import java.sql.*;
import java.util.Scanner;

class SavingsAccount
{
	private double balance;

	public SavingsAccount()
	{
		balance = 0;
	}

	public SavingsAccount(double initialBalance)
	{
		balance = initialBalance;
	}

	public void deposit(double amount)
	{
		balance = balance + amount;
	}

	public void withdraw(double amount)
	{
		balance = balance - amount;
	}

	public void accounttrnsfr(double amount)
	{
		balance = balance - amount;
	}

	public double getBalance()
	{
		return balance;
	}

}

public class bankapplication {

	private static Scanner scanner;
	private static String jDBC_DRIVER;
	private static String temp;
	
	public static void main(String[] args) {
		scanner = new Scanner(System.in);
		setjDBC_DRIVER("com.mysql.jdbc.Driver");
		final String DB_URL = "jdbc:mysql://localhost/firstdb";
		final String USER = "root";
		final String PASS = "student";
	
		String user,pass,name="",role="";
		int age=0;
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
			
			pstmt = conn.prepareStatement("insert into company values(?,?,?,?,?'company')");
		}catch(Exception e){}
		
		
		/*
		 * Choice 
		 */
		int choice;
		System.out.println("Enter choice: \n1)Sign Up\n2)Sign In");
		choice = scanner.nextInt();
		
		/*
		 * Registration
		 */
		try {
			if(choice==1){
				System.out.println("Username: ");
				name = scanner.next();
				String query = "select * from company";
				ResultSet rs = stmt.executeQuery(query);
				int flag = 0;
				String temp;
				while(rs.next()){
					temp = rs.getString("name");
					if(name.equals(temp)){
						flag=1;
						break;
					}
				}
				if(flag==1){
					System.out.println("User already exists! Try signing in.");
					System.exit(0);
				}
				else {
					System.out.println("Password: ");
					pass = scanner.next();
					System.out.println("Age: ");
					age = scanner.nextInt();
					pstmt.setString(1, name);
					pstmt.setString(2, pass);
					pstmt.setInt(3, age);
					pstmt.execute();
					pstmt.close();
				}
				rs.close();
			}
		}catch(Exception e){}
		

		/*
		 * Login
		 */
		try {
			if(choice==2){
				System.out.println("Username: ");
				user = scanner.next();
				System.out.println("Password: ");
				//pass = scanner.next();
				String passw = new String(System.console().readPassword());
				//pass = PasswordField.readPassword("Enter password: ");
				
				String query = "select * from company";
				ResultSet rs = stmt.executeQuery(query);
				int flag = 0;
				while(rs.next()){
					name = rs.getString("name");
					String password = rs.getString("password");
					if(name.equals(user) && passw.equals(password)){
						flag=1;
						age = rs.getInt("age");
						role = rs.getString("role");
						break;
					}
				}
				if(flag==0){
					System.out.println("Invalid Login!");
					System.exit(0);
				}
				else {
					System.out.println("User found!");
					System.out.println("Name: "+name);
					System.out.println(": "+age);
					System.out.println("Role: "+role);
					if(role.equals("Admin")){
						SavingsAccount kpsSavings = new SavingsAccount(1000);
						int ch;
						System.out.println("You can:\n1)View Table.\n2)Enter record.\n");
						ch= scanner.nextInt();
						switch(ch)
						{
						case 1:System.out.println(kpsSavings.getBalance());
						break;
						case 2:break;
						}
					}
				
					else if(role.equals("cust")){
						SavingsAccount kpsSavings = new SavingsAccount(1000);
						int c;
						System.out.println("You can:\n1)View Table.\n2)freeze account\n3)account transfer");
						c= scanner.nextInt();
						switch(c)
						{
							case 1:System.out.println(kpsSavings.getBalance());
									break;
							case 2:break;
							case 3:kpsSavings.accounttrnsfr(200);
									break;
						}
	
					}					
				}
				rs.close();
			}
			stmt.close();
			conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		temp = scanner.next();
		
		// TODO Auto-generated method stub
		
		
		
		System.out.println("Expected: 1265.0");
		//1000-250=750 => 750+400=1150 => 1150+1150*0.10=1265.0

	}

	public static String getjDBC_DRIVER() {
		return jDBC_DRIVER;
	}

	public static void setjDBC_DRIVER(String jDBC_DRIVER) {
		bankapplication.jDBC_DRIVER = jDBC_DRIVER;
	}

}
