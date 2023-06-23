package main.java.app.dao;

import main.java.app.model.ContaCorrente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContaCorrenteDao {
    private Connection conexao;

    public ContaCorrenteDao(Connection conexao) {
        this.conexao = conexao;
    }

    public void criarContaCorrente(ContaCorrente contaCorrente) throws SQLException {
        String sql = "INSERT INTO contas_correntes (numero, cpf, titular, saldo, cheque_especial) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, contaCorrente.getNumero());
            stmt.setString(2, contaCorrente.getCpf());
            stmt.setString(3, contaCorrente.getTitular());
            stmt.setDouble(4, contaCorrente.getSaldo());
            stmt.setDouble(5, contaCorrente.getChequeEspecial());

            stmt.executeUpdate();
        }
    }

    public ContaCorrente buscarContaCorrentePorNumero(int numero) throws SQLException {
        String sql = "SELECT * FROM contas_correntes WHERE numero = ?";
        ContaCorrente contaCorrente = null;

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, numero);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int numeroConta = rs.getInt("numero");
                    String cpf = rs.getString("cpf");
                    String titular = rs.getString("titular");
                    double saldo = rs.getDouble("saldo");
                    double chequeEspecial = rs.getDouble("cheque_especial");

                    contaCorrente = new ContaCorrente(numeroConta, cpf, titular, saldo);
                    // Não é necessário chamar o setChequeEspecial(), pois o valor já está definido no construtor da classe
                }
            }
        }

        return contaCorrente;
    }
}
