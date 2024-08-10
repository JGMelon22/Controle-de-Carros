import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class App {
	public static void main(String[] args) {
		
		try {
			Connection connection = DriverManager.getConnection("jdbc:ucanaccess://Parkinglot.mdb");
			System.out.println(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
