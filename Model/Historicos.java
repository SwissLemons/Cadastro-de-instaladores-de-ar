package Model;

public class Historicos {
	private int id;
	private String marca;
	private String modelo;
	private float preco;
	private int quantidade;
	private String data;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMarca() {
		return marca;
	}
	public boolean setMarca(String marca) {
		if(marca.length() >= 3 && marca.length() <= 20 ) {
			this.marca = marca;
			return true;
		}
		else {		
			return false;
		}
	}
	public String getModelo() {
		return modelo;
	}
	public boolean setModelo(String modelo) {
		if(modelo.length() >= 3 && modelo.length() <= 20 ) {
			this.modelo = modelo;
			return true;
		}
		else {		
			return false;
		}
	}
	public float getPreco() {
		return preco;
	}
	public boolean setPreco(float preco) {
		if(preco >= 0) {
			this.preco = preco;
			return true;
		}
		else {		
			return false;
		}
	}
	public int getQuantidade() {
		return quantidade;
	}
	public boolean setQuantidade(int quantidade) {
		if(quantidade >= 0) {
			this.quantidade = quantidade;
			return true;
		}
		else {		
			return false;
		}
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}	
}
