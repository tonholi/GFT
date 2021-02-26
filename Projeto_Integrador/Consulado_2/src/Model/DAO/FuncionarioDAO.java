package Model.DAO;

import Connection.DatabaseMySQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Model.Bean.Funcionario;

public class FuncionarioDAO {

    private final DatabaseMySQL db = new DatabaseMySQL();

    public List<Funcionario> listarFuncionario() throws SQLException {
        String sql = "SELECT * FROM tb_funcionario";
        Funcionario funcionario = new Funcionario();
        List<Funcionario> lista = new ArrayList<Funcionario>();
        Connection conn = db.getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                funcionario = new Funcionario();
                funcionario.setId(resultado.getInt("id"));
                funcionario.setNome(resultado.getString("nome"));
                funcionario.setSetor(resultado.getString("setor"));
                lista.add(funcionario);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            conn.close();
        }
        return lista;
    }

    public Funcionario pesquisar(Integer id) throws SQLException {
        String sql = "SELECT * FROM tb_funcionario WHERE id = ?";
        Funcionario retornoFuncionario = new Funcionario();
        Connection conn = db.getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet resultado = stmt.executeQuery();

            if (resultado.next()) {
                retornoFuncionario = new Funcionario();
                retornoFuncionario.setId(resultado.getInt("id"));
                retornoFuncionario.setNome(resultado.getString("nome"));
                retornoFuncionario.setSetor(resultado.getString("setor"));
            }

            return retornoFuncionario;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            conn.close();
        }
        return retornoFuncionario;
    }

}
