package impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
	public Carro getCarro(String placa) {
		Carro carro = new Carro();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM Carros WHERE Placa = ?");
			preparedStatement.setString(1, placa);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				carro.setId(resultSet.getInt("Id"));
				carro.setMarca(resultSet.getString("Marca"));
				carro.setPlaca(resultSet.getString("Placa"));
				carro.setCor(resultSet.getString("Cor"));
				carro.setHoraEntrada(resultSet.getInt("HoraEntrada"));
				carro.setHoraSaida(resultSet.getInt("HoraSaida"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return carro;
	}

	@Override
	public void updateCarro(Carro carro) {
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(
					"UPDATE Carros SET Marca = ?, Cor = ?, HoraEntrada = ?, HoraSaida = ? WHERE Placa = ?");
			preparedStatement.setString(1, carro.getMarca());
			preparedStatement.setString(2, carro.getCor());
			preparedStatement.setInt(3, carro.getHoraEntrada());
			preparedStatement.setInt(4, carro.getHoraSaida());
			preparedStatement.setString(5, carro.getPlaca());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteCarro(Carro carro) {
		try {
			PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM Carros WHERE Placa = ?");
			preparedStatement.setString(1, carro.getPlaca());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void createCarro(Carro carro) {
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(
					"INSERT INTO Carros (Marca, Placa, Cor, HoraEntrada, HoraSaida) VALUES(?, ?, ?, ?, ?)");
			preparedStatement.setString(1, carro.getMarca());
			preparedStatement.setString(2, carro.getPlaca());
			preparedStatement.setString(3, carro.getCor());
			preparedStatement.setInt(4, carro.getHoraEntrada());
			preparedStatement.setInt(5, carro.getHoraSaida());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
