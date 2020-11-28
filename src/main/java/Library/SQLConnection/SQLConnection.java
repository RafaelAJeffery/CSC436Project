package Library.SQLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SQLConnection {

	@SuppressWarnings("exports")
	public static Connection sqlConnection = null; 
	
	private static final String HOST = "localhost";
	private static final int PORT = 1433;
	
	private static final String USER = "SA";
	private static final String PASSWORD = "Library_is_c00l";
		
	
	/*
	 * Establish connection to the SQL server "library"
	 */
	public static void makeConnection() {
		String connectionUrl = String.format("jdbc:sqlserver://%s:%d", HOST, PORT);
		
		try {
			sqlConnection = DriverManager.getConnection(connectionUrl, USER, PASSWORD);
			if (sqlConnection == null) {
				logInfo("Connection not made");
			} else {
				DatabaseMetaData metadata = (DatabaseMetaData) sqlConnection.getMetaData();
				
				logInfo(String.format("Connection established:\nDriver name: %s\nDriver version: %s\nProduct name: %s\nProduct version: %s", 
						metadata.getDriverName(),
						metadata.getDriverVersion(),
						metadata.getDatabaseProductName(),
						metadata.getDatabaseProductVersion()));
				
				// enters the scope of the library database (tests connection)
				Statement stmt = sqlConnection.createStatement();
				stmt.execute("USE library");
				// stmt.execute("GO");
			}
		}
		catch (SQLException e) {
			sqlConnection = null;
			logError(e.getMessage());
		}
	}
	
	/*
	 * Execute statement
	 * @param statement {String} SQL statement
	 */
	public static void execute(String statement) {
		try {
			Statement stmt = sqlConnection.createStatement();
			stmt.execute(statement);
		} catch (SQLException ex) {
			logError(ex.getMessage());
		}
	}
	
	/*
	 * Execute query
	 * @param query {String} SQL query
	 * @return set of results
	 */
	public static ResultSet executeQuery(String query) {
		ResultSet results = null;
		logInfo("executeQuery: " + query);
		try {
			Statement stmt = sqlConnection.createStatement();
			results = stmt.executeQuery(query);
		} catch (SQLException ex) {
			logError(ex.getMessage());
			return null;
		}
		
		return results;
	}

	public static void closeConnection() {
		try {
			if (sqlConnection != null) {
				sqlConnection.close();
				logInfo("Connection closed");
			}
		}
		catch (SQLException e) {
			sqlConnection = null;
		}
	}
	
	private static void logInfo(String msg) {
		Logger.getLogger(SQLConnection.class.getName()).log(Level.INFO, msg);
	}
	
	private static void logError(String msg) {
		Logger.getLogger(SQLConnection.class.getName()).log(Level.SEVERE, msg);
	}
}
