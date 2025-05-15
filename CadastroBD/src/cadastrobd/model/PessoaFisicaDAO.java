package cadastrobd.model;

import cadastrobd.model.util.ConectorBD;
import cadastrobd.model.util.SequenceManager;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PessoaFisicaDAO {

    public PessoaFisica getPessoa(int id) {
        String sql = "SELECT * FROM Pessoa INNER JOIN PessoaFisica ON Pessoa.id = PessoaFisica.id WHERE Pessoa.id = ?";
        PessoaFisica pessoa = null;

        try (Connection conn = ConectorBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                pessoa = new PessoaFisica(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("logradouro"),
                    rs.getString("cidade"),
                    rs.getString("estado"),
                    rs.getString("telefone"),
                    rs.getString("email"),
                    rs.getString("cpf")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pessoa;
    }

    public List<PessoaFisica> getPessoas() {
        List<PessoaFisica> pessoas = new ArrayList<>();
        String sql = "SELECT * FROM Pessoa INNER JOIN PessoaFisica ON Pessoa.id = PessoaFisica.id";

        try (Connection conn = ConectorBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                PessoaFisica pessoa = new PessoaFisica(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("logradouro"),
                    rs.getString("cidade"),
                    rs.getString("estado"),
                    rs.getString("telefone"),
                    rs.getString("email"),
                    rs.getString("cpf")
                );
                pessoas.add(pessoa);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pessoas;
    }

    public void incluir(PessoaFisica pessoa) {
        String sqlPessoa = "INSERT INTO Pessoa (id, nome, logradouro, cidade, estado, telefone, email) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String sqlPessoaFisica = "INSERT INTO PessoaFisica (id, cpf) VALUES (?, ?)";

        int id = SequenceManager.getValue("PessoaSeq");
        pessoa.id = id;

        try (Connection conn = ConectorBD.getConnection();
             PreparedStatement stmtPessoa = conn.prepareStatement(sqlPessoa);
             PreparedStatement stmtPessoaFisica = conn.prepareStatement(sqlPessoaFisica)) {

            stmtPessoa.setInt(1, pessoa.id);
            stmtPessoa.setString(2, pessoa.nome);
            stmtPessoa.setString(3, pessoa.logradouro);
            stmtPessoa.setString(4, pessoa.cidade);
            stmtPessoa.setString(5, pessoa.estado);
            stmtPessoa.setString(6, pessoa.telefone);
            stmtPessoa.setString(7, pessoa.email);
            stmtPessoa.executeUpdate();

            stmtPessoaFisica.setInt(1, pessoa.id);
            stmtPessoaFisica.setString(2, pessoa.cpf);
            stmtPessoaFisica.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void alterar(PessoaFisica pessoa) {
        String sqlPessoa = "UPDATE Pessoa SET nome = ?, logradouro = ?, cidade = ?, estado = ?, telefone = ?, email = ? WHERE id = ?";
        String sqlPessoaFisica = "UPDATE PessoaFisica SET cpf = ? WHERE id = ?";

        try (Connection conn = ConectorBD.getConnection();
             PreparedStatement stmtPessoa = conn.prepareStatement(sqlPessoa);
             PreparedStatement stmtPessoaFisica = conn.prepareStatement(sqlPessoaFisica)) {

            stmtPessoa.setString(1, pessoa.nome);
            stmtPessoa.setString(2, pessoa.logradouro);
            stmtPessoa.setString(3, pessoa.cidade);
            stmtPessoa.setString(4, pessoa.estado);
            stmtPessoa.setString(5, pessoa.telefone);
            stmtPessoa.setString(6, pessoa.email);
            stmtPessoa.setInt(7, pessoa.id);
            stmtPessoa.executeUpdate();

            stmtPessoaFisica.setString(1, pessoa.cpf);
            stmtPessoaFisica.setInt(2, pessoa.id);
            stmtPessoaFisica.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(int id) {
        String sqlPessoaFisica = "DELETE FROM PessoaFisica WHERE id = ?";
        String sqlPessoa = "DELETE FROM Pessoa WHERE id = ?";

        try (Connection conn = ConectorBD.getConnection();
             PreparedStatement stmtPessoaFisica = conn.prepareStatement(sqlPessoaFisica);
             PreparedStatement stmtPessoa = conn.prepareStatement(sqlPessoa)) {

            stmtPessoaFisica.setInt(1, id);
            stmtPessoaFisica.executeUpdate();

            stmtPessoa.setInt(1, id);
            stmtPessoa.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
