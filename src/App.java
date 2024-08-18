import java.sql.SQLException;

import dao.CarroDao;
import impl.CarroDaoImpl;
import model.Carro;

public class App {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		CarroDao carroDao = new CarroDaoImpl();
		Carro carro = carroDao.getCarro("JA32U2F");

		carro.setCor("Burro quando foge");
		carroDao.updateCarro(carro);
	}
}
