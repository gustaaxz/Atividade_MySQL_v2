package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SistemaDAO {
    public void cadastrarFornecedor(Fornecedor fornecedor) throws SQLException {
        String command = """
                INSERT INTO Fornecedor
                (id, nome, cnpj)
                VALUES
                (?, ?, ?)
                """;

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(command)){
            stmt.setInt(1, fornecedor.getId());
            stmt.setString(2, fornecedor.getNome());
            stmt.setString(3, fornecedor.getCnpj());
            stmt.executeUpdate();
        }
    }

    public Fornecedor buscarFornecedorPorId(int busca) throws SQLException {
        String query = """
                SELECT id, nome, cnpj
                FROM Fornecedor
                WHERE id = ?
                """;

        try(Connection conn = Conexao.conectar();
          PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, busca);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String cnpj = rs.getString("cnpj");

                Fornecedor fornecedor = new Fornecedor(id, nome, cnpj);

                return fornecedor;
            } else {
                throw new RuntimeException("ID do Fornecedor não encontrado!");
            }
        }
    }

    public List<Fornecedor> listarFornecedores() throws SQLException {
        String command = """
                SELECT id, nome, cnpj
                FROM Fornecedor;
                """;

        List<Fornecedor> fornecedores = new ArrayList<>();

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(command)){
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String cnpj = rs.getString("cnpj");

                fornecedores.add(new Fornecedor(id, nome, cnpj));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar todos os fornecedores!");
        } return fornecedores;
    }

    public Fornecedor atualizarFornecedor(Fornecedor fornecedor) throws SQLException {
        String command = """
            UPDATE Fornecedor
            SET nome = ?, cnpj = ?
            WHERE id = ?;
            """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(command)) {

            stmt.setString(1, fornecedor.getNome());
            stmt.setString(2, fornecedor.getCnpj());
            stmt.setInt(3, fornecedor.getId());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas == 0) {
                throw new RuntimeException("ID do Fornecedor não encontrado!");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar os dados do Fornecedor!");
            e.printStackTrace();
            throw e;
        }

        return fornecedor;
    }
}

