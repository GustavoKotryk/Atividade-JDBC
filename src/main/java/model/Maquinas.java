package model;

public class Maquinas {
	protected int id;
	protected String nome;
	protected String setor;
	protected StatusMaquinas status;

	public Maquinas (String nome, String setor, StatusMaquinas status){
		this.id = id;
		this.nome = nome;
		this.setor = setor;
		this.status = status;
	}


	public String getNome(){
		return nome;
	}
	public String getSetor(){
		return setor;
	}

	public void setNome(String nome){
		this.nome = nome;
	}
	public void setSetor(String setor){
		this.setor = setor;
	}

	public StatusMaquinas getStatus(){
		return this.status;
	}

	public void setStatus(StatusMaquinas status) {
		this.status = status;
	}

	public int getId() { return id;}

	public void setId(int id) { this.id = id;}
}
