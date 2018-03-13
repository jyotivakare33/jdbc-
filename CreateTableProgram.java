import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
public class CreateTableProgram {

	public static void main(String[] argv) throws Exception
	{
		Class.forName("org.postgresql.Driver");
		System.out.println("PostgreSQL JDBC Driver Registered!");

		Connection connection = DriverManager.getConnection(
					"jdbc:postgresql://127.0.0.1:5432/programs", "postgres",
					"postgres");
		if (connection != null) {
			System.out.println("Database is connected");
		} 
		else {
			System.out.println("Failed to make connection!");
		}
		Statement statement = connection.createStatement();
		statement.executeUpdate("create table programs(slno SERIAL PRIMARY KEY, names varchar(200), description text)");
	}	
}
