package lib;
import java.sql.*;
public class DBProgram {
	public Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://localhost/programs" ,"postgres", "postgres");
			System.out.println("connected");
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return conn;
	}
} 
