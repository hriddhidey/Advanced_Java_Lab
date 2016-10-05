import java.sql.*;
import java.util.Scanner;

public class Login {
	public static void main(String[] args) {
		
		/*
		 * Initial setup
		 */		
		Scanner scanner = new Scanner(System.in);
		final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		final String DB_URL = "jdbc:mysql://localhost/student";
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
			
			pstmt = conn.prepareStatement("insert into student values(?,?,?,'Student')");
		}catch(Exception e){}
		
		
		/*
		 * Choice 
		 */
		int choice;
		System.out.println("Enter choice: \n1)Sign Up\n2)Sign In\n");
		choice = scanner.nextInt();
		
		
		/*
		 * Registration
		 */
		try {
			if(choice==1){
				System.out.println("Username: ");
				name = scanner.next();
				String query = "select * from student";
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
				
				String query = "select * from student";
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
					System.out.println("Age: "+age);
					System.out.println("Role: "+role);
					if(role.equals("Admin")){
						System.out.println("You can:\n1)View Table.\n2)Enter record.\n");
					}
					else if(role.equals("Student")){
						System.out.println("You can:\n1)View Table.\n");
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
		String temp = scanner.next();
		System.out.println("Thankyou for using this software!");		
	}
}
