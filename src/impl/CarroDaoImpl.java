package impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import connection.ConnectionFactory;
import dao.CarroDao;
import model.Carro;

public class CarroDaoImpl implements CarroDao {

	private Connection conn;

	public CarroDaoImpl() throws ClassNotFoundException, SQLException {
		conn = ConnectionFactory.getConnection();
	}

	@Override
	public List<Carro> getAllCarros() {
		List<Carro> carros = new ArrayList<>();
		try {
			Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM carros");
			while (resultSet.next()) {
				Carro carro = new Carro();
				carro.setId(resultSet.getInt("Id"));
				carro.setMarca(resultSet.getString("Marca"));
				carro.setPlaca(resultSet.getString("Placa"));
				carro.setCor(resultSet.getString("Cor"));
				carro.setHoraEntrada(resultSet.getInt("HoraEntrada"));
				carro.setHoraSaida(resultSet.getInt("HoraSaida"));
				carros.add(carro);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return carros;
	}

	@Override
	public Carro getCarro(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateCarro(Carro carro) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteCarro(Carro carro) {
		// TODO Auto-generated method stub

	}

}
