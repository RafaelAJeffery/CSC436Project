package Library.SQLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

public class SQLConnection {

	public static Connection sqlConnection = null;
	
	private static final String HOST = "localhost";
	private static final int PORT = 1433;
	
	private static final String USER = "SA";
	private static final String PASSWORD = "Library_is_c00l";
	
	private static final boolean debug = true;	// True: debug print statements, False: None
	
	// private static final String DBNAME = "SQLDB";
	
	/*
	 * Establish connection to SQL
	 * args: [Database Name, Username, Password]
	 */
	public static void makeConnection(String[] args) {
		String connectionUrl = String.format("jdbc:sqlserver://%s:%d", HOST, PORT);
		
		try {
			sqlConnection = DriverManager.getConnection(connectionUrl, USER, PASSWORD);
			if (sqlConnection == null) {
				if (debug) System.out.println("Connection not made");
			} else {
				if (debug) {
					System.out.println("Connection made:");
					DatabaseMetaData metadata = (DatabaseMetaData) sqlConnection.getMetaData();
					System.out.println("Driver name: " + metadata.getDriverName());
	                System.out.println("Driver version: " + metadata.getDriverVersion());
	                System.out.println("Product name: " + metadata.getDatabaseProductName());
	                System.out.println("Product version: " + metadata.getDatabaseProductVersion());
				}
			}
		}
		catch (SQLException e) {
			sqlConnection = null;
			if (debug) System.err.format("SQL Status: %s\n%s\n", e.getSQLState(), e.getMessage());
		}
	}

	public static void closeConnection() {
		try {
			if (sqlConnection != null)
				sqlConnection.close();
		}
		catch (SQLException e) {
			sqlConnection = null;
		}
	}
}
