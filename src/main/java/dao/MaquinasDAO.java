package dao;

import database.Conexao;
import model.Maquinas;
import model.StatusMaquinas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MaquinasDAO {

	public void adicionarMaquinas(Maquinas maquinas) throws SQLException{
		String sql = "INSERT INTO Maquina (nome, setor, status) VALUES(?,?,?)";

		try (Connection conn = Conexao.getConnection();
		     PreparedStatement smt =conn.prepareStatement(sql)){

			smt.setString(1, maquinas.getNome());
			smt.setString(2, maquinas.getSetor());
			smt.setString(3, maquinas.getStatus().name());

			smt.executeUpdate();
			System.out.println("Máquina adicionada!");

		}
	}

	public List<Maquinas> listarMaquinasOperacionais() throws SQLException {
		List<Maquinas> maquinas = new ArrayList<>();
		String sql = "SELECT * FROM Maquina WHERE status = 'OPERACIONAL'";

		try (Connection conn = Conexao.getConnection();
		     PreparedStatement smt = conn.prepareStatement(sql);
		     ResultSet rs = smt.executeQuery()) {

			while (rs.next()) {
				Maquinas maquina = new Maquinas(
						rs.getString("nome"),
						rs.getString("setor"),
						StatusMaquinas.valueOf(rs.getString("status"))
				);
				maquinas.add(maquina);
			}
		}
		return maquinas;
	}

	public void atualizarStatus(int idMaquina, StatusMaquinas novoStatus) throws SQLException {
		String sql = "UPDATE Maquina SET status = ? WHERE id = ?";
		try (Connection conn = Conexao.getConnection();
		     PreparedStatement smt = conn.prepareStatement(sql)) {
			smt.setString(1, novoStatus.name());
			smt.setInt(2, idMaquina);
			smt.executeUpdate();
		}
	}
	}