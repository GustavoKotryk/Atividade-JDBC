package service;

import dao.TecnicosDAO;
import model.Tecnicos;

import java.sql.SQLException;

public class TecnicosService {
	private final TecnicosDAO tecnicosDAO;

	public TecnicosService(TecnicosDAO tecnicosDAO) {
		this.tecnicosDAO = tecnicosDAO;
	}

	public void criarTecnico(String nome, String especialidade) throws SQLException {
		if(nome.trim().isEmpty()){
			throw new IllegalArgumentException("O campo Nome é obrigatório.");
			}
		else if(especialidade.trim().isEmpty()){
			throw new IllegalArgumentException("O campo Especialidade é obrigatório.");
			}

		nome = nome.trim();
		especialidade = especialidade.trim();

		try{
			Tecnicos novoTecnico = new Tecnicos(nome, especialidade);
			TecnicosDAO.adicionarTecnico(novoTecnico);
			System.out.print("Técnico adicionado com sucesso!");
		} catch (SQLException e) {
			if (e.getMessage().contains("uk_tecnico_nome") ||
					e.getMessage().toLowerCase().contains("duplicate entry")) {
				throw new IllegalArgumentException("Já existe um técnico com o nome '" + nome + "'!");
			} else {
				throw e;
			}
		}
	}
}
