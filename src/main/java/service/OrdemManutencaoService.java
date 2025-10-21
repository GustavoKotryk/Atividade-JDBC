package service;

import dao.MaquinasDAO;
import dao.OrdemManutencaoDAO;
import model.OrdemManutencao;
import model.StatusMaquinas;

import java.sql.SQLException;
import java.time.LocalDate;

public class OrdemManutencaoService {
	private OrdemManutencaoDAO ordemDAO;
	private MaquinasDAO maquinasDAO;

	public OrdemManutencaoService(OrdemManutencaoDAO ordemDAO, MaquinasDAO maquinasDAO){
		this.ordemDAO = ordemDAO;
		this.maquinasDAO = maquinasDAO;
}

public void criarOrdem(int idTecnico, int id) throws SQLException{
		LocalDate dataSolicitacao = LocalDate.now();
		OrdemManutencao novaOrdem = new OrdemManutencao(idTecnico, dataSolicitacao, null);
		ordemDAO.criarOrdem(novaOrdem);

		maquinasDAO.atualizarStatus(StatusMaquinas.EM_MANUTENCAO);
}
}
