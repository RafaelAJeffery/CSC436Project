package Library.SQLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnection {

	static Connection sqlConnection = null;
	
	private static final String HOST = "localhost";
	private static final int PORT = 1001;
	private static final String SCHEME = "http";
	
	/*
	 * Establish connection to SQL
	 * args: [Database Name, Username, Password]
	 */
	public static void makeConnection(String[] args) {
		String connectionUrl = String.format("jdbc:sqlserver://%s:%d;databaseName=%s;user=%s;password=%s", HOST, PORT, args[0], args[1], args[2]);
		
		try {
			sqlConnection = DriverManager.getConnection(connectionUrl);
		}
		catch (SQLException e) {
			sqlConnection = null;
		}
	}
}
