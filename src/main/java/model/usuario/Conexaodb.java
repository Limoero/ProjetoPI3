package model.usuario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexaodb {

    private static final String URL = "jdbc:mysql://localhost:3306/meubanco";
    private static final String USUARIO = "root";
    private static final String SENHA = "";

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Erro ao conectar com o banco de dados", e);
        }
    }

    public static void fecharConexao(Connection conexao) {
        try {
            if (conexao != null) {
                conexao.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}