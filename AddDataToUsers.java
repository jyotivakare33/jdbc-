import java.sql.*;
import java.util.*;
import java.lang.*;
import org.postgresql.util.PSQLException;
class AddDataToUsers
{
	public static void main(String[] args) throws SQLException, ClassNotFoundException
	{
		try
		{
			Class.forName("org.postgresql.Driver");
			Connection connectionDatabase = DriverManager.getConnection("jdbc:postgresql://localhost/" , "postgres" , "suresh");
			Statement statementDatabase = connectionDatabase.createStatement();
			ResultSet resultSetDatabase = statementDatabase.executeQuery("select datname from pg_database;");
			while(resultSetDatabase.next())
			{
				System.out.println(resultSetDatabase.getString(1));
			}
			System.out.println("Enter the database name which you want to modify");
			Scanner inputDatabase = new Scanner(System.in);
			String databaseName = inputDatabase.next();
			Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost/" + databaseName , "postgres" , "suresh");
			Statement statement = connection.createStatement();
			ResultSet resultSet;
			String[] types = {"TABLE"};
			String column = "" , insertValue = "" , createTableSyntax = "";
			resultSet = connection.getMetaData().getTables(null, null, "%", types);
			if(resultSet.next())
			{
				resultSet = connection.getMetaData().getTables(null, null, "%", types);
				System.out.println("List of tables present in the " + databaseName + " are ");
				while(resultSet.next())
				{
					System.out.println(resultSet.getString(3));
				}
				System.out.println("choose the table name you want to modify.");
				Scanner inputTableName = new Scanner(System.in);
				String tablename = inputTableName.next();
				while(true)
				{
					resultSet = statement.executeQuery("select *from " + tablename);
					System.out.println("Enter any number to continue or enter 0 to exit.");
					Scanner input = new Scanner(System.in);
					int number = input.nextInt();
					if(number != 0)
					{
						for(int index = 1 ; index <= resultSet.getMetaData().getColumnCount(); index ++)
						{
							System.out.println("column name: " + resultSet.getMetaData().getColumnName(index));
							System.out.println("Enter the value you want to insert of " + resultSet.getMetaData().getColumnTypeName(index) + " data type.");
							Scanner inputValue = new Scanner(System.in);
							String value = inputValue.nextLine();
							column +=  resultSet.getMetaData().getColumnName(index) + ", ";
							insertValue += value + ", ";
						}
						System.out.println("Insert into " + tablename + " (" + column.replaceAll(", $", "") + ") " + "Values (" + insertValue.replaceAll(", $", "") + ");" );
						statement.executeUpdate("Insert into " + tablename + " (" + column.replaceAll(", $", "") + ") " + "Values (" + insertValue.replaceAll(", $", "") + ");" );
						System.out.println("data inserted into table.");
						column = "";
						insertValue = "";
					}else
					{
						break;
					}
				}
			}else
			{
				System.out.println("No tables present in this data base.");
			}
		}catch(PSQLException ex)
		{
			System.out.println("Invalid input, PSQL exception raised.");
			ex.printStackTrace();
		}
	}
}
