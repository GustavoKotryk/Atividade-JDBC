package dao;

import database.Conexao;
import model.OrdemManutencao;
import java.sql.*;

public class OrdemManutencaoDAO {
	public void criarOrdem(OrdemManutencao ordem) throws SQLException {
		String sql = "INSERT INTO OrdemManutencao (id_maquina, id_tecnico, data_solicitacao, status) VALUES (?, ?, ?, ?)";
		try (Connection conn = Conexao.getConnection();
		     PreparedStatement smt = conn.prepareStatement(sql)) {

			smt.setInt(1, ordem.getIdMaquina());
			smt.setInt(2, ordem.getIdTecnico());
			smt.setDate(3, java.sql.Date.valueOf(ordem.getDataSolicitacao()));
			smt.setString(4, ordem.getStatus().name());

			smt.executeUpdate();
		}
	}
}