package model.DAO;

import java.sql.SQLException;
import java.util.List;

public interface UsuarioDAO {
    public void create(Object o) throws SQLException;
    public void update(Object o) throws SQLException;
    public void delete(Object o) throws SQLException;
    public List<Object> read() throws SQLException;
    public boolean validaLogin(String email, String senha) throws SQLException;

}
