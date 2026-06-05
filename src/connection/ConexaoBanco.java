package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBanco {

    public static Connection conectar() {
        try {
            String url = "jdbc:mysql://localhost:3306/mini_rpg";
            String usuario = "root";
            String senha = "1234";

            return DriverManager.getConnection(url, usuario, senha);

        } catch (SQLException e) {
            System.out.println("Erro ao conectar com o banco!");
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        Connection conn = conectar();

        if (conn != null) {
            System.out.println("Conectado com sucesso!");
        } else {
            System.out.println("Falha na conexão.");
        }
    }
}