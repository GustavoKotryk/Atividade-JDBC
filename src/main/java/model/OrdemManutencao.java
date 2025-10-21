package model;

import java.time.LocalDate;

public class OrdemManutencao {
	private int id;
	private int idMaquina;
	private int idTecnico;
	private LocalDate dataSolicitacao;
	private StatusOrdem status;

	public OrdemManutencao(int idMaquina, LocalDate dataSolicitacao, StatusOrdem status) {
		this.idMaquina = idMaquina;
		this.idTecnico = idTecnico;
		this.dataSolicitacao = dataSolicitacao;
		this.status = status;
	}

	public int getId() { return id; }
	public void setId(int id) { this.id = id; }
	public int getIdMaquina() { return idMaquina; }
	public void setIdMaquina(int idMaquina) { this.idMaquina = idMaquina; }
	public int getIdTecnico() { return idTecnico; }
	public void setIdTecnico(int idTecnico) { this.idTecnico = idTecnico; }
	public LocalDate getDataSolicitacao() { return dataSolicitacao; }
	public void setDataSolicitacao(LocalDate dataSolicitacao) { this.dataSolicitacao = dataSolicitacao; }
	public StatusOrdem getStatus() { return status; }
	public void setStatus(StatusOrdem status) { this.status = status; }
}