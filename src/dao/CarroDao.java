package dao;

import java.util.List;

import model.Carro;

public interface CarroDao {
	public List<Carro> getAllCarros();
    public Carro getCarro(int id);
    public void updateCarro(Carro carro);
    public void deleteCarro(Carro carro);
}
