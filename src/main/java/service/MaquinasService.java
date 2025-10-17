package service;

import dao.MaquinasDAO;
import model.Maquinas;
import model.StatusMaquinas;
import java.sql.SQLException;

public class MaquinasService {
	private final MaquinasDAO maquinasDAO;

	public MaquinasService(MaquinasDAO maquinasDAO) {
		this.maquinasDAO = maquinasDAO;
	}

	public void criarMaquina(String nome, String setor, StatusMaquinas status) throws SQLException {
		if (nome.trim().isEmpty()) {
			throw new IllegalArgumentException("O campo Nome da máquina é obrigatório.");
		}
		else if (setor.trim().isEmpty()) {
			throw new IllegalArgumentException("O campo setor é obrigatório.");
		}
		else if (status == null) {
			throw new IllegalArgumentException("O campo Status é obrigatório.");
		}

		nome = nome.trim();
		setor = setor.trim();

		try {
			Maquinas novaMaquina = new Maquinas(nome, setor, status);
			maquinasDAO.adicionarMaquinas(novaMaquina);
			System.out.println("Máquina cadastrada com sucesso!");

		} catch (SQLException e) {
			if (e.getMessage().contains("uk_maquina_nome_setor") ||
					e.getMessage().toLowerCase().contains("duplicate entry")) {
				throw new IllegalArgumentException("Já existe uma máquina '" + nome + "' no setor '" + setor + "'!");
			} else {
				throw e;
			}
		}
	}
}