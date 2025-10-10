package dao;

import database.Conexao;
import model.Tecnicos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TecnicosDAO {

	public static void adicionarTecnico(Tecnicos tecnicos)throws SQLException {
		String sql = "INSERT INTO Tecnico (nome, especialidade) VALUES (?,?)";

		try (Connection conn = Conexao.getConnection();
		     PreparedStatement smt = conn.prepareStatement(sql)){

			smt.setString(1, tecnicos.getNome());
			smt.setString(2, tecnicos.getEspecialidade());

			smt.executeUpdate();
			System.out.print("Técnico adicionado com sucesso!");

			}
	}
}
