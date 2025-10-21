package model;

public class Tecnicos {
	protected int id;
	protected String nome;
	protected String especialidade;

	public Tecnicos(String nome, String especialidade){
		if(nome == null || nome.trim().isEmpty()){
			throw new IllegalArgumentException("A opção nome é obrigatória!");
		}
		this.id = id;
		this.nome = nome;
		this.especialidade = especialidade;
	}

	public String getNome(){
		return nome;
	}
	public String getEspecialidade(){
		return especialidade;
	}

	public void setNome(String nome){
		this.nome = nome;
	}
	public void setEspecialidade(String especialidade){
		this.especialidade = especialidade;
	}

	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
}
