package cadastrobd.model;

import cadastrobd.model.util.ConectorBD;
import cadastrobd.model.util.SequenceManager;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PessoaJuridicaDAO {

    public PessoaJuridica getPessoa(int id) {
        String sql = "SELECT * FROM Pessoa INNER JOIN PessoaJuridica ON Pessoa.id = PessoaJuridica.id WHERE Pessoa.id = ?";
        PessoaJuridica pessoa = null;

        try (Connection conn = ConectorBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                pessoa = new PessoaJuridica(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("logradouro"),
                    rs.getString("cidade"),
                    rs.getString("estado"),
                    rs.getString("telefone"),
                    rs.getString("email"),
                    rs.getString("cnpj")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pessoa;
    }

    public List<PessoaJuridica> getPessoas() {
        List<PessoaJuridica> pessoas = new ArrayList<>();
        String sql = "SELECT * FROM Pessoa INNER JOIN PessoaJuridica ON Pessoa.id = PessoaJuridica.id";

        try (Connection conn = ConectorBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                PessoaJuridica pessoa = new PessoaJuridica(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("logradouro"),
                    rs.getString("cidade"),
                    rs.getString("estado"),
                    rs.getString("telefone"),
                    rs.getString("email"),
                    rs.getString("cnpj")
                );
                pessoas.add(pessoa);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pessoas;
    }

    public void incluir(PessoaJuridica pessoa) {
        String sqlPessoa = "INSERT INTO Pessoa (id, nome, logradouro, cidade, estado, telefone, email) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String sqlPessoaJuridica = "INSERT INTO PessoaJuridica (id, cnpj) VALUES (?, ?)";

        int id = SequenceManager.getValue("PessoaSeq");
        pessoa.id = id;

        try (Connection conn = ConectorBD.getConnection();
             PreparedStatement stmtPessoa = conn.prepareStatement(sqlPessoa);
             PreparedStatement stmtPessoaJuridica = conn.prepareStatement(sqlPessoaJuridica)) {

            stmtPessoa.setInt(1, pessoa.id);
            stmtPessoa.setString(2, pessoa.nome);
            stmtPessoa.setString(3, pessoa.logradouro);
            stmtPessoa.setString(4, pessoa.cidade);
            stmtPessoa.setString(5, pessoa.estado);
            stmtPessoa.setString(6, pessoa.telefone);
            stmtPessoa.setString(7, pessoa.email);
            stmtPessoa.executeUpdate();

            stmtPessoaJuridica.setInt(1, pessoa.id);
            stmtPessoaJuridica.setString(2, pessoa.cnpj);
            stmtPessoaJuridica.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void alterar(PessoaJuridica pessoa) {
        String sqlPessoa = "UPDATE Pessoa SET nome = ?, logradouro = ?, cidade = ?, estado = ?, telefone = ?, email = ? WHERE id = ?";
        String sqlPessoaJuridica = "UPDATE PessoaJuridica SET cnpj = ? WHERE id = ?";

        try (Connection conn = ConectorBD.getConnection();
             PreparedStatement stmtPessoa = conn.prepareStatement(sqlPessoa);
             PreparedStatement stmtPessoaJuridica = conn.prepareStatement(sqlPessoaJuridica)) {

            stmtPessoa.setString(1, pessoa.nome);
            stmtPessoa.setString(2, pessoa.logradouro);
            stmtPessoa.setString(3, pessoa.cidade);
            stmtPessoa.setString(4, pessoa.estado);
            stmtPessoa.setString(5, pessoa.telefone);
            stmtPessoa.setString(6, pessoa.email);
            stmtPessoa.setInt(7, pessoa.id);
            stmtPessoa.executeUpdate();

            stmtPessoaJuridica.setString(1, pessoa.cnpj);
            stmtPessoaJuridica.setInt(2, pessoa.id);
            stmtPessoaJuridica.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(int id) {
        String sqlPessoaJuridica = "DELETE FROM PessoaJuridica WHERE id = ?";
        String sqlPessoa = "DELETE FROM Pessoa WHERE id = ?";

        try (Connection conn = ConectorBD.getConnection();
             PreparedStatement stmtPessoaJuridica = conn.prepareStatement(sqlPessoaJuridica);
             PreparedStatement stmtPessoa = conn.prepareStatement(sqlPessoa)) {

            stmtPessoaJuridica.setInt(1, id);
            stmtPessoaJuridica.executeUpdate();

            stmtPessoa.setInt(1, id);
            stmtPessoa.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
