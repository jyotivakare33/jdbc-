import java.sql.*;
import java.util.*;
import java.lang.*;
import org.postgresql.util.PSQLException;
class CreateTable ProgramHistories
{
	public static void main(String[] args) throws SQLException, ClassNotFoundException
	{
		Class.forName("org.postgresql.Driver");
		Connection connectionDatabase = DriverManager.getConnection("jdbc:postgresql://localhost/" , "postgres" , "postgres");
		Statement statementDatabase = connectionDatabase.createStatement();
		ResultSet resultSetDatabase = statementDatabase.executeQuery("select datname from pg_database;");
		while(resultSetDatabase.next())
		{
			System.out.println(resultSetDatabase.getString(1));
		}
		System.out.println("Enter the database name which you want to modify");
		Scanner inputDatabase = new Scanner(System.in);
		String databaseName = inputDatabase.next();
		Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost/" + databaseName , "postgres" , "postgres");
		Statement statement = connection.createStatement();
		ResultSet resultSet;
		String[] types = {"TABLE"};
		String column = "" , insertValue = "" , createTableSyntax = "";
		try
		{
			System.out.println("Enter the table name you want to create.");
			Scanner inputCreate = new Scanner(System.in);
			String createTable = inputCreate.nextLine();
			System.out.println("enter number of columns you want to create");
			Scanner nunmberOfColumns = new Scanner(System.in);
			int columnCount = nunmberOfColumns.nextInt();
			for(int index1 = 1 ; index1 <= columnCount; index1 ++)
			{
				System.out.println("Enter the column name.");
				Scanner inputColumn = new Scanner(System.in);
				String columnName = inputColumn.nextLine();
				System.out.println("Enter the data type.");
				Scanner inputType = new Scanner(System.in);
				String dataType = inputType.nextLine();
				createTableSyntax +=  columnName + " " + dataType + ", ";
			}
			statement.execute( "create table " + createTable + "(" + createTableSyntax.replaceAll(", $", "") + ");");
			System.out.println(createTable + " table created.");
		}catch(PSQLException e)
		{
			System.out.println("Table name you entered is already existed.");
		}
	}
}
