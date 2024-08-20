import java.sql.SQLException;
import java.util.List;

import dao.CarroDao;
import impl.CarroDaoImpl;
import model.Carro;

public class App {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		CarroDao carroDao = new CarroDaoImpl();

		// List All Vehicles
		List<Carro> carros = carroDao.getAllCarros();
		for (Carro carro : carros) {
			System.out.println("Id: " + carro.getId() + ", Marca: " + carro.getMarca() + ", Placa: " + carro.getPlaca()
					+ ", Cor: " + carro.getCor() + ", Hora Entrada: " + carro.getHoraEntrada() + ", Hora Saída: "
					+ carro.getHoraSaida());
		}

		// Find One Specific Vehicle
//		Carro carro = carroDao.getCarro("ABC1234");
//		System.out.println("Id: " + carro.getId() + ", Marca: " + carro.getMarca() + ", Placa: " + carro.getPlaca()
//				+ ", Cor: " + carro.getCor() + ", Hora Entrada: " + carro.getHoraEntrada() + ", Hora Saída: "
//				+ carro.getHoraSaida());

		// Update a Vehicle Color
//		Carro carro = carroDao.getCarro("ABC1234");
//		carro.setCor("Purple Rain");
//		carroDao.updateCarro(carro);
	}
}
