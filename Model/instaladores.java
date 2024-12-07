package Model;

public class instaladores {
	private int id;
	private String nome;
	private String empresa;
	private String telefone;
	
	public int getId() {
		return id;
	}

	public boolean setId(int id) {
		if(id > 0) {			
			this.id = id;
			return true;
		}else {
			return false;			
		}
	}

	public String getNome() {
		return nome;
	}
	
	public boolean setNome(String nome) {
		if(nome.length() >= 3 && nome.length() <= 40 ) {
			this.nome = nome;
			return true;
		}
		else {		
			return false;
		}
	}
	
	public String getEmpresa() {
		return empresa;
	}
	
	public boolean setEmpresa(String empresa) {
		if(empresa.length() >= 3 && empresa.length() <= 40 ) {
			this.empresa = empresa;
			return true;
		}
		else {
			return false;
		}
	}
	
	public String getTelefone() {
		return telefone;
	}
	
	public boolean setTelefone(String telefone) {
		if(telefone.length() >= 10 && telefone.length() <= 11 ) {
			this.telefone = telefone;
			return true;
		}
		else {
			return false;
		}	
	}	
}
