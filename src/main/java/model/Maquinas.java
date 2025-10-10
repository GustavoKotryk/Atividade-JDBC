package model;

public class Maquinas {
	protected String nome;
	protected String setor;
	protected StatusMaquinas status;

	public Maquinas (String nome, String setor, StatusMaquinas status){
		this.nome = nome;
		this.setor = setor;
		this.status = status;
	}


	public String getNome(){
		return nome = nome;
	}
	public String getSetor(){
		return setor = setor;
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
}
