package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connection.ConexaoBanco;
import model.Personagem;

public class PersonagemDAO {


	public boolean salvar(Personagem personagem) {
		String sql = "INSERT INTO personagens "
				+ "(nome, classe, vida, vida_maxima, dano_minimo, dano_maximo, forca, defesa, moedas) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			Connection conn = ConexaoBanco.conectar();
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			stmt.setString(1, personagem.getNome());
			stmt.setString(2, personagem.getClasse());
			stmt.setInt(3, personagem.getVida());
			stmt.setInt(4, personagem.getVidaMaxima());
			stmt.setInt(5, personagem.getDanoMinimo());
			stmt.setInt(6, personagem.getDanoMaximo());
			stmt.setInt(7, personagem.getForca());
			stmt.setInt(8, personagem.getDefesa());
			stmt.setInt(9, personagem.getMoedas());

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
		String sql = "UPDATE personagens SET " + "nome = ?, classe = ?, vida = ?, vida_maxima = ?, dano_minimo = ?, "
				+ "dano_maximo = ?, forca = ?, defesa = ?, moedas = ? " + "WHERE id = ?";

		try {
			Connection conn = ConexaoBanco.conectar();
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, personagem.getNome());
			stmt.setString(2, personagem.getClasse());
			stmt.setInt(3, personagem.getVida());
			stmt.setInt(4, personagem.getVidaMaxima());
			stmt.setInt(5, personagem.getDanoMinimo());
			stmt.setInt(6, personagem.getDanoMaximo());
			stmt.setInt(7, personagem.getForca());
			stmt.setInt(8, personagem.getDefesa());
			stmt.setInt(9, personagem.getMoedas());
			stmt.setInt(10, personagem.getId());

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
	public ArrayList<Personagem> listarPersonagens() {
	    ArrayList<Personagem> personagens = new ArrayList<>();

	    String sql = "SELECT * FROM personagens LIMIT 3";

	    try {
	        Connection conn = ConexaoBanco.conectar();
	        PreparedStatement stmt = conn.prepareStatement(sql);
	        ResultSet rs = stmt.executeQuery();

	        while (rs.next()) {
	            Personagem personagem = new Personagem(
	                rs.getString("nome"),
	                rs.getString("classe"),
	                rs.getInt("vida_maxima"),
	                rs.getInt("dano_minimo"),
	                rs.getInt("dano_maximo"),
	                rs.getInt("forca"),
	                rs.getInt("defesa"),
	                rs.getInt("moedas")
	            );

	            personagem.setId(rs.getInt("id"));
	            personagem.setVida(rs.getInt("vida"));

	            personagens.add(personagem);
	        }

	        rs.close();
	        stmt.close();
	        conn.close();

	    } catch (SQLException e) {
	        System.out.println("Erro ao listar personagens!");
	        e.printStackTrace();
	    }

	    return personagens;
	}
}