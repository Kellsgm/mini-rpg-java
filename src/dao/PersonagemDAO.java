package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import connection.ConexaoBanco;
import model.Personagem;
import model.PersonagemFactory;

public class PersonagemDAO {


	public boolean salvar(Personagem personagem) {
		String sql = "INSERT INTO personagens "
				+ "(nome, vida, vida_maxima, dano_minimo, dano_maximo, forca, defesa, moedas) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			Connection conn = ConexaoBanco.conectar();
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			stmt.setString(1, personagem.getNome());
			stmt.setInt(2, personagem.getVida());
			stmt.setInt(3, personagem.getVidaMaxima());
			stmt.setInt(4, personagem.getDanoMinimo());
			stmt.setInt(5, personagem.getDanoMaximo());
			stmt.setInt(6, personagem.getForca());
			stmt.setInt(7, personagem.getDefesa());
			stmt.setInt(8, personagem.getMoedas());

			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();

			if (rs.next()) {
				int idGerado = rs.getInt(1);
				personagem.setId(idGerado);
			}

			stmt.close();
			conn.close();
			return true;
		} catch (SQLException e) {
			System.out.println("Erro ao salvar personagem!");

			e.printStackTrace();
			return false;
		}
	}

	public boolean atualizar(Personagem personagem) {
		String sql = "UPDATE personagens SET " + "nome = ?, vida = ?, vida_maxima = ?, dano_minimo = ?, "
				+ "dano_maximo = ?, forca = ?, defesa = ?, moedas = ? " + "WHERE id = ?";

		try {
			Connection conn = ConexaoBanco.conectar();
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, personagem.getNome());
			stmt.setInt(2, personagem.getVida());
			stmt.setInt(3, personagem.getVidaMaxima());
			stmt.setInt(4, personagem.getDanoMinimo());
			stmt.setInt(5, personagem.getDanoMaximo());
			stmt.setInt(6, personagem.getForca());
			stmt.setInt(7, personagem.getDefesa());
			stmt.setInt(8, personagem.getMoedas());
			stmt.setInt(9, personagem.getId());

			stmt.executeUpdate();

			stmt.close();
			conn.close();

			return true;

		} catch (SQLException e) {
			System.out.println("Erro ao atualizar personagem!");
			e.printStackTrace();
			return false;
		}

	}
}