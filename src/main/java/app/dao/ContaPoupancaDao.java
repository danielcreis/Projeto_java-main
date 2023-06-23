package main.java.app.dao;

import main.java.app.model.ContaPoupanca;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContaPoupancaDao {
    private Connection conexao;

    public ContaPoupancaDao(Connection conexao) {
        this.conexao = conexao;
    }

    public void criarContaPoupanca(ContaPoupanca contaPoupanca) throws SQLException {
        String sql = "INSERT INTO contas_poupancas (numero, cpf, titular, saldo, taxa_juros) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, contaPoupanca.getNumero());
            stmt.setString(2, contaPoupanca.getCpf());
            stmt.setString(3, contaPoupanca.getTitular());
            stmt.setDouble(4, contaPoupanca.getSaldo());
            stmt.setDouble(5, contaPoupanca.getTaxaJuros());

            stmt.executeUpdate();
        }
    }

    public ContaPoupanca buscarContaPoupancaPorNumero(int numero) throws SQLException {
        String sql = "SELECT * FROM contas_poupancas WHERE numero = ?";
        ContaPoupanca contaPoupanca = null;

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, numero);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int numeroConta = rs.getInt("numero");
                    String cpf = rs.getString("cpf");
                    String titular = rs.getString("titular");
                    double saldo = rs.getDouble("saldo");
                    double taxaJuros = rs.getDouble("taxa_juros");

                    contaPoupanca = new ContaPoupanca(numeroConta, cpf, titular, saldo, taxaJuros);
                }
            }
        }

        return contaPoupanca;
    }
}
