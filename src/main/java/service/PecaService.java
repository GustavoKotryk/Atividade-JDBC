package service;

import dao.PecaDAO;
import model.Peca;

import java.sql.SQLException;

public class PecaService {
	private final PecaDAO pecaDAO;

	public PecaService(PecaDAO pecaDAO) {
		this.pecaDAO = pecaDAO;
	}

	public void criarPeca(String nome, double estoque) throws SQLException{
		if (nome.trim().isEmpty()){
			throw new IllegalArgumentException("O campo nome é um campo obrigatório");
		}

		nome = nome.trim();

		try{
			Peca novaPeca = new Peca(nome, estoque);
			pecaDAO.adicionarPeca(novaPeca);
			System.out.println("Peca adicionada com sucesso!");
		} catch (SQLException e){
			if(e.getMessage().contains("uk_peca_nome") ||
			e.getMessage().toLowerCase().contains("duplicate entry")){
	throw new IllegalArgumentException("Já existe uma peca com o nome '" + nome + "'!");
			} else{
	throw e;
			}
		}
	}
}
