import java.sql.SQLException;
import java.util.List;

import impl.CarroDaoImpl;
import model.Carro;

public class App {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		/*
		 * try { Connection connection =
		 * DriverManager.getConnection("jdbc:ucanaccess://Parkinglot.mdb");
		 * System.out.println(connection); } catch (SQLException e) {
		 * e.printStackTrace(); }
		 */

		CarroDaoImpl carroDao = new CarroDaoImpl();
        List<Carro> carros = carroDao.getAllCarros();
        for (Carro carro : carros) {
        	StringBuilder stringBuilder = new StringBuilder();
        	stringBuilder.append(carro.getId());
        	stringBuilder.append(" - ");
        	stringBuilder.append(carro.getMarca());
        	stringBuilder.append(" - ");
        	stringBuilder.append(carro.getPlaca());
        	stringBuilder.append(" - ");
        	stringBuilder.append(carro.getHoraEntrada());
        	stringBuilder.append(" - ");
        	stringBuilder.append(carro.getHoraSaida());

        	System.out.println(stringBuilder);
        }

	}
}
