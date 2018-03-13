import java.sql.*;
import java.util.*;
class CreateTable
{
	public static void main(String[] args) {
		try {
				Connection conn = null;
				Scanner scan = new Scanner(System.in);
				Class.forName("org.postgresql.Driver");
				if(conn == null)
					conn = DriverManager.getConnection("jdbc:postgresql://localhost/" ,"postgres", "postgres");
				Statement statement = conn.createStatement();
				ResultSet resultSet = statement.executeQuery("select datname from pg_database");
				while(resultSet.next()) {
					System.out.println(resultSet.getString(1));
				}
				System.out.println("enter the database name in which you want to create table");
				String dbName = scan.next();
				System.out.println("enter the table name");
				String tableName = scan.next();
				conn = DriverManager.getConnection("jdbc:postgresql://localhost/"+dbName, "postgres", "postgres");
				Statement statement1 = conn.createStatement();
				ArrayList<String> list = new ArrayList();
				System.out.println("enter the number of columns");
				String str = ",";
				int number = scan.nextInt();
				for(int index = 0; index < number; index++) {
					System.out.println("enter column name");
					list.add(scan.next());
					System.out.println("enter datatype");
					list.add(scan.next());
				}
				String sql = "";
				for(int index = 0, innerIndex = 1; index < list.size() && innerIndex < list.size(); index = index + 2,innerIndex = innerIndex + 2) {
							if(innerIndex == (list.size() - 1)) {
								sql = sql + list.get(index) + " " + list.get(innerIndex);
							}
							if(innerIndex != (list.size() - 1))
							{
								sql = sql + list.get(index) + " " + list.get(innerIndex) + str; 
							}
				}
				System.out.println(sql);
				statement1.execute("CREATE TABLE "+ tableName +"("+sql + ")");
				System.out.println("table created");
		}
		catch(Exception e) {
			System.out.println("exception occurs");
		}
	}
}	
   
