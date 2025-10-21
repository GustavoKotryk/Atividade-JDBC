package dao;

import database.Conexao;
import model.Tecnicos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class TecnicosDAO {

	public void adicionarTecnico(Tecnicos tecnicos)throws SQLException {
		String sql = "INSERT INTO Tecnico (nome, especialidade) VALUES (?,?)";

		try (Connection conn = Conexao.getConnection();
		     PreparedStatement smt = conn.prepareStatement(sql)){

			smt.setString(1, tecnicos.getNome());
			smt.setString(2, tecnicos.getEspecialidade());

			smt.executeUpdate();
			System.out.print("Técnico adicionado com sucesso!");

			}
	}

	public List<Tecnicos> listarTecnicos() throws SQLException {
		List<Tecnicos> tecnicos = new java.util.ArrayList<>();
		String sql = "SELECT * FROM Tecnico";

		try (Connection conn = Conexao.getConnection();
		     PreparedStatement smt = conn.prepareStatement(sql);
		     java.sql.ResultSet rs = smt.executeQuery()) {

			while (rs.next()) {
				Tecnicos novoTecnico = new Tecnicos(
						rs.getString("nome"),
						rs.getString("especialidade")
				);
				tecnicos.add(novoTecnico);
			}
		}
		return tecnicos;
	}
}
