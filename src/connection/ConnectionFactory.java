package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	private static Connection conn;

	private ConnectionFactory() {
	}

	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		if (conn == null || conn.isClosed()) {
			try {
				Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
				conn = DriverManager.getConnection("jdbc:ucanaccess://Parkinglot.mdb");
			} catch (SQLException e) {
				throw new SQLException("Failed to connect to the database", e);
			}
		}
		return conn;
	}
}
