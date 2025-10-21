package model;

public class Peca {
	protected String nome;
	protected double estoque;

	public Peca(String nome, double estoque){
		this.nome = nome;
		this.estoque = estoque;
	}

	public String getNome(){
		return nome;
	}
	public double getEstoque(){
		return estoque;
	}

	public void setNome(String nome){
		this.nome = nome;
	}
	public void setEstoque(double estoque){
		this.estoque = estoque;
	}
}
