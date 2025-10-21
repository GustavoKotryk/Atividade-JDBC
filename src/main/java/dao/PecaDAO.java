package dao;

import database.Conexao;
import model.Peca;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PecaDAO {

	public void adicionarPeca(Peca peca) throws SQLException {
		String sql = "INSERT INTO Peca (nome, estoque) VALUES (?,?)";

		try (Connection conn = Conexao.getConnection();
		PreparedStatement smt = conn.prepareStatement(sql)){

			smt.setString(1, peca.getNome());
			smt.setDouble(2, peca.getEstoque());

			smt.executeUpdate();
			System.out.println("Peca adicionada!");
		}
	}
}
