package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;
import model.Conexaodb;

public class ClienteDAO implements UsuarioDAO {
    private Connection conexao;

    public ClienteDAO() throws SQLException, ClassNotFoundException {
        this.conexao = Conexaodb.getConnection();
    }

    @Override
    public void create(Object o) throws SQLException {
        Cliente cliente = (Cliente) o;

        String sql = "INSERT INTO tb_usuario (nome, cpf_cnpj, tipo, email, senha) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setString(1, cliente.getNome());
        stmt.setString(2, cliente.getCpf());
        stmt.setString(3, "pf");
        stmt.setString(4, cliente.getEmail());
        stmt.setString(5, cliente.getSenha());

        stmt.execute();
        stmt.close();
    }

    @Override
    public void update(Object o) throws SQLException {
        Cliente cliente = (Cliente) o;

        String sql = "UPDATE tb_usuario SET nome = ?, cpf_cnpj = ?, email = ? WHERE senha = ?";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setString(1, cliente.getNome());
        stmt.setString(2, cliente.getCpf());
        stmt.setString(3, cliente.getEmail());
        stmt.setString(4, cliente.getSenha());

        stmt.execute();
        stmt.close();
    }

    @Override
    public void delete(Object o) throws SQLException {
        Cliente cliente = (Cliente) o;

        String sql = "DELETE FROM tb_usuario WHERE cpf_cnpj = ?";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setString(1, cliente.getCpf());

        stmt.execute();
        stmt.close();
    }

    @Override
    public List<Object> read() throws SQLException {

        List<Object> clientes = new ArrayList<>();

        String sql = "SELECT nome, cpf_cnpj, email FROM tb_usuario WHERE tipo = 'pf'";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            String nome = rs.getString("nome");
            String cpf_cnpj = rs.getString("cpf_cnpj");
            String email = rs.getString("email");
            Cliente cliente = new Cliente(nome, cpf_cnpj, email, "");
            clientes.add(cliente);
        }

        rs.close();
        stmt.close();

        return clientes;

    }

    public Cliente buscarCpf(String cpf) throws SQLException {
        String sql = "SELECT nome, cpf_cnpj, email, FROM tb_usuario WHERE cpf_cnpj = ?";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setString(1, cpf);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            String nome = rs.getString("nome");
            String cpf_cnpj = rs.getString("cpf_cnpj");
            String email = rs.getString("email");
            Cliente cliente = new Cliente(nome, cpf_cnpj, email, "");

            rs.close();
            stmt.close();

            return cliente;

        } else {
            rs.close();
            stmt.close();
            return null;
        }
        
    }
    
    @Override
    public boolean validaLogin(String email, String senha) throws SQLException {
        String sql = "SELECT email, senha FROM tb_usuario WHERE email = ? AND senha = ?";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setString(1, email);
        stmt.setString(2, senha);
        ResultSet rs = stmt.executeQuery();
        
        return false;
        
    }
}
