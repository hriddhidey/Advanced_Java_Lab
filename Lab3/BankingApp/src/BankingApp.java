import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Scanner;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

/*
 * 1. Develop an application for a bank. The user will have the provision to register for the application. Once registered and approved by the admin,
 * the user should be able to log in using his or her credentials. Consider the scenario of not less than 10 users, and each one should be able to see
 * their balance. In case of any transaction made to any other account, show the current balance in the account after the transaction. The admin should
 * have the provision to block or freeze any account in case of any emergencies. The user should have a provision to see their transactions based on the
 * selected dates. 
 */

public class BankingApp {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		final String DB_URL = "jdbc:mysql://localhost/Banking";
		final String USER = "root";
		final String PASS = "student";
		String user,pass,name="",role="";
		int id=0;
		int balance = 0;
		char app = 0;
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement stmt_app_reject = null;
		PreparedStatement stmt_app_approve = null;
		PreparedStatement make_trans = null;
		PreparedStatement update_payee = null;
		PreparedStatement update_payer = null;
		PreparedStatement view_trans = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = (Statement) conn.createStatement();
			pstmt = (PreparedStatement) conn.prepareStatement("insert into users values(?,?,?,?,'F')");
			stmt_app_reject = (PreparedStatement) conn.prepareStatement("update users set approved='F' where id=?;");
			stmt_app_approve = (PreparedStatement) conn.prepareStatement("update users set approved='Y' where id=?;");
			make_trans = (PreparedStatement) conn.prepareStatement("insert into transaction values(?,?,?,?)");
			update_payee = (PreparedStatement) conn.prepareStatement("update users set balance=balance+? where id=?;");
			update_payer = (PreparedStatement) conn.prepareStatement("update users set balance=balance-? where id=?;");
			
			view_trans = (PreparedStatement) conn.prepareStatement("select * from transaction where tdate between ? and ? and uid=?;");
		
		}catch(Exception e){}
		

		int choice;
		System.out.println("Enter choice: \n1)Sign Up\n2)Sign In\n");
		choice = scanner.nextInt();

		try {
			if(choice==1){
				int t;
				System.out.println("ID: ");
				t = scanner.nextInt();
				String query = "select * from users";
				ResultSet rs = stmt.executeQuery(query);
				int flag = 0;
				while(rs.next()){
					if(t==(rs.getInt("id"))){
						flag=1;
						break;
					}
				}
				if(flag==1){
					System.out.println("ID already exists! Try signing in.");
					System.exit(0);
				}
				else {
					System.out.println("Name: ");
					name = scanner.next();
					System.out.println("Password: ");
					pass = scanner.next();
					System.out.println("ID: ");
					id = scanner.nextInt();
					System.out.println("Balance: ");
					int k = scanner.nextInt();
					pstmt.setString(1, name);
					pstmt.setString(2, pass);
					pstmt.setInt(3, id);
					pstmt.setInt(4, k);
					pstmt.execute();
					pstmt.close();
				}
				rs.close();
			}
			
			if(choice==2){
				System.out.println("Username: ");
				user = scanner.next();
				System.out.println("Password: ");
				pass = scanner.next();
				String query = "select * from users;";
				ResultSet rs = stmt.executeQuery(query);
				int flag = 0;
				while(rs.next()){
					name = rs.getString("name");
					String password = rs.getString("password");
					if(name.equals(user) && pass.equals(password)){
						flag=1;
						id = rs.getInt("id");
						balance = rs.getInt("balance");
						app = rs.getString("approved").charAt(0);
						break;
					}
				}
				if(flag==0){
					System.out.println("Invalid Login!");
					System.exit(0);
				}
				else {
					if(app=='F'){System.out.println("Your account is not approved.");System.exit(0);}
					else {
						System.out.println("User found!");
						System.out.println("Name: "+name);
						System.out.println("ID: "+id);
						System.out.println("Balance: "+balance);
						if(name.equals("Hriddhi")){
							int k;
							System.out.println("You can:\n1)Approve user.\n2)Block user.\n");
							k = scanner.nextInt();
							int uid;
							switch(k){
							case 1: 
								System.out.println("Enter user id: ");
								uid = scanner.nextInt();
								stmt_app_approve.setInt(1, uid);
								System.out.println("User approved!");
								stmt_app_approve.execute();
								break;
							case 2:
								System.out.println("Enter user id: ");
								uid = scanner.nextInt();
								stmt_app_reject.setInt(1, uid);
								System.out.println("User blocked!");
								stmt_app_reject.execute();
								break;
							}
						}
						else {
							System.out.println("You can:\n1)Make transaction.\n2)View transactions\n");
							int k;
							k = scanner.nextInt();
							switch(k){
							case 1: 
								System.out.println("Enter t_id: ");
								int tid = scanner.nextInt();
								System.out.println("Enter payee id: ");
								int pid = scanner.nextInt();
								
								make_trans.setInt(1, tid);
								make_trans.setInt(2, id);
								System.out.println("Enter amount: ");
								int amt = scanner.nextInt();	
								make_trans.setInt(3, amt);
								System.out.println("Enter date: ");
								String date = scanner.next();
								Date d = Date.valueOf(date);
								make_trans.setDate(4, d);
								make_trans.execute();
								update_payee.setInt(1,amt);
								update_payee.setInt(2, pid);
								update_payee.execute();
								update_payer.setInt(1, amt);
								update_payer.setInt(2, id);
								update_payer.execute();
								System.out.println("Trnasaction made!");
								break;
							case 2:
								System.out.println("Enter beginning date: ");
								String date_beg = scanner.next();
								Date db = Date.valueOf(date_beg);
								System.out.println("Enter ending date: ");
								String date_end = scanner.next();
								Date de = Date.valueOf(date_end);
								view_trans.setDate(1, db);
								view_trans.setDate(2, de);
								view_trans.setInt(3, id);
								ResultSet rss = view_trans.executeQuery();
								while(rss.next()){
									System.out.println("T_ID: "+ rss.getInt("tid")+", U_ID: "+rss.getInt("uid")+", amount: "+rss.getInt("amount")+", date: "+rss.getDate("tdate")+"\n");
								}
								break;
							}
						}
					}					
				}
				rs.close();
				stmt.close();
				update_payee.close();
				update_payer.close();
				view_trans.close();
				stmt_app_approve.close();
				stmt_app_reject.close();
				conn.close();
			}
		}catch(Exception e){System.out.println(e.toString());}
		System.out.println("\nThankyou for using this software!");	
	}
}
