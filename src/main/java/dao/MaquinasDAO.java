package dao;

import database.Conexao;
import model.Maquinas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MaquinasDAO {

	public static void adicionarMaquinas(Maquinas maquinas) throws SQLException{
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
}
