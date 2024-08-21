package dao;

import java.util.List;

import model.Carro;

public interface CarroDao {
	public List<Carro> getAllCarros();

	public Carro getCarro(String placa);

	public void createCarro(Carro carro);

	public void updateCarro(Carro carro);

	public void deleteCarro(Carro carro);
}
