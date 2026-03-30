package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
                return null;
            }
        }
    }

    public void listarFornecedores() throws SQLException {
        ArrayList<Fornecedor> fornecedores = new ArrayList<>();
        String command = """
                SELECT id, nome, cnpj
                FROM Fornecedor;
                """;

    }
}

