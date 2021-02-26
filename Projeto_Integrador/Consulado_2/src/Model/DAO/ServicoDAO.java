package Model.DAO;

import Connection.DatabaseMySQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Model.Bean.Servico;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ServicoDAO {

    private final DatabaseMySQL db = new DatabaseMySQL();

    public boolean inserir(Servico servico) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("insert into tb_servico (nome, email, funcionario, setor)")
                .append("VALUES(?,?,?,?)");
        Connection conn = db.getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql.toString());
            stmt.setString(1, servico.getNome());
            stmt.setString(2, servico.getEmail());
            stmt.setString(3, servico.getFuncionario());
            stmt.setString(4, servico.getSetor());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            conn.close();
        }
    }

    public List<Servico> pesquisarPorSetor(String setor) throws SQLException {
        String sql = "SELECT * FROM tb_servico WHERE setor = ?";
        Servico retornoServico = new Servico();
        Connection conn = db.getConnection();
        List<Servico> lista = new ArrayList<Servico>();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, setor);
            ResultSet resultado = stmt.executeQuery();

            while (resultado.next()) {
                retornoServico = new Servico();
                retornoServico.setSolicitacao(resultado.getInt("solicitacao"));
                retornoServico.setNome(resultado.getString("nome"));
                retornoServico.setEmail(resultado.getString("email"));
                retornoServico.setFuncionario(resultado.getString("funcionario"));
                retornoServico.setSetor(resultado.getString("setor"));
                retornoServico.setAtivo(resultado.getInt("ativo"));
                lista.add(retornoServico);
            }

            return lista;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            conn.close();
        }
        return lista;
    }

    public Servico pesquisarPorSolicitacao(int solicitacao) throws SQLException {
        String sql = "SELECT * FROM tb_servico WHERE solicitacao = ?";
        Servico retornoServico = new Servico();
        Connection conn = db.getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, solicitacao);
            ResultSet resultado = stmt.executeQuery();

            if (resultado.next()) {
                retornoServico = new Servico();
                retornoServico.setSolicitacao(resultado.getInt("solicitacao"));
                retornoServico.setNome(resultado.getString("nome"));
                retornoServico.setEmail(resultado.getString("email"));
                retornoServico.setFuncionario(resultado.getString("funcionario"));
                retornoServico.setSetor(resultado.getString("setor"));
                retornoServico.setAtivo(resultado.getInt("ativo"));
            }

            return retornoServico;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            conn.close();
        }
        return retornoServico;
    }

    public int selecionarSolicitacao() throws SQLException {
        String sql = "SELECT solicitacao FROM tb_servico ORDER BY solicitacao DESC LIMIT 1";
        int a = 0;
        Connection conn = db.getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();

            if (resultado.next()) {

                a = resultado.getInt("solicitacao");
            }

            return a;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            conn.close();
        }
        return a;
    }
}
