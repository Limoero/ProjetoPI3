package controller;

import java.sql.SQLException;
import java.util.List;
import usuario.Cliente;
import usuario.DAO.ClienteDAO;

public class ClienteController {

    private ClienteDAO clienteDAO;

    public ClienteController() throws SQLException, ClassNotFoundException {
        this.clienteDAO = new ClienteDAO();
    }

    public void cadastrarCliente(String nome, String cpf, String email, String senha) throws SQLException {
        Cliente cliente = new Cliente(nome, cpf, email, senha);

        List<Object> clientes = clienteDAO.read();
        for (Object o : clientes) {
            Cliente c = (Cliente) o;
            if (c.getCpf().equals(cpf)) {
                throw new RuntimeException("Já existe um cliente cadastrado com o CPF informado.");
            }
            if (c.getEmail().equals(email)) {
                throw new RuntimeException("Já existe um cliente cadastrado com o e-mail informado.");
            }
        }

        clienteDAO.create(cliente);
    }

    public void atualizarCliente(String nome, String cpf, String email, String senha) throws SQLException {
        Cliente cliente = new Cliente(nome, cpf, email, senha);
        clienteDAO.update(cliente);
    }

    public void excluirCliente(String cpf) throws SQLException {
        List<Object> clientes = clienteDAO.read();
        boolean found = false;
        for (Object o : clientes) {
            Cliente c = (Cliente) o;
            if (c.getCpf().equals(cpf)) {
                found = true;
                clienteDAO.delete(c);
                break;
            }
        }
        if (!found) {
            throw new RuntimeException("Não foi possível encontrar um cliente com o CPF informado.");
        }
    }

    public List<Object> listarClientes() throws SQLException {
        List<Object> clientes = clienteDAO.read();
        if (clientes.isEmpty()) {
            System.out.println("Não há clientes cadastrados.");
        } else {
            for (Object o : clientes) {
                Cliente c = (Cliente) o;
                System.out.println("Nome: " + c.getNome() + ", CPF: " + c.getCpf() + ", E-mail: " + c.getEmail());
            }
        }
        return clientes;
    }

    public Cliente buscar(String cpf) {

        Cliente cliente = null;
        try {
            cliente = clienteDAO.buscarCpf(cpf);
            if (cliente == null) {
                throw new SQLException("Cliente não encontrado");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar cliente: " + e.getMessage());
        }
        return cliente;
    }

    public void imprimirCliente(String cpf) {
        try {
            Cliente cliente = buscar(cpf);
            if (cliente != null) {
                System.out.println("Nome: " + cliente.getNome());
                System.out.println("CPF/CNPJ: " + cliente.getCpf());
                System.out.println("E-mail: " + cliente.getEmail());
            } else {
                System.out.println("Cliente não encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar cliente no banco de dados.");
            e.printStackTrace();
        }
    }
}
