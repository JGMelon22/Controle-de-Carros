package model;

public class Carro {
	
	private String marca;
	private String placa;
	private String cor;
	private String horaEntrada;
	private String horaSaida;
	
	// Constructors
	public Carro() {
		super();
	}
	
	public Carro(String marca) {
		super();
		this.marca = marca;
	}
	
	public Carro(String marca, String placa) {
        super();
        this.marca = marca;
        this.placa = placa;
    }

    public Carro(String marca, String placa, String cor) {
        super();
        this.marca = marca;
        this.placa = placa;
        this.cor = cor;
    }

    public Carro(String marca, String placa, String cor, String horaEntrada) {
        super();
        this.marca = marca;
        this.placa = placa;
        this.cor = cor;
        this.horaEntrada = horaEntrada;
    }

    public Carro(String marca, String placa, String cor, String horaEntrada, String horaSaida) {
        super();
        this.marca = marca;
        this.placa = placa;
        this.cor = cor;
        this.horaEntrada = horaEntrada;
        this.horaSaida = horaSaida;
    }

	// Getters & Setters
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getCor() {
		return cor;
	}
	public void setCor(String cor) {
		this.cor = cor;
	}
	public String getHoraEntrada() {
		return horaEntrada;
	}
	public void setHoraEntrada(String horaEntrada) {
		this.horaEntrada = horaEntrada;
	}
	public String getHoraSaida() {
		return horaSaida;
	}
	public void setHoraSaida(String horaSaida) {
		this.horaSaida = horaSaida;
	}
	
	
}
