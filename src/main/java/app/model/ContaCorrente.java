package main.java.app.model;

public class ContaCorrente extends Conta {
    private double chequeEspecial = 500.00;

    public ContaCorrente(int numero, String cpf, String titular, double saldo) {
        super(numero, cpf, titular, saldo);
    }

    public double getChequeEspecial() {
        return chequeEspecial;
    }

    @Override
    public void sacar(double valor) {
        double limite = getSaldo() + chequeEspecial;
        if (limite >= valor) {
            double saldoAtual = getSaldo();
            if (saldoAtual >= valor) {
                setSaldo(saldoAtual - valor);
            } else {
                double valorRestante = valor - saldoAtual;
                setSaldo(0);
                chequeEspecial -= valorRestante;
            }
        } else {
            System.out.println("Saldo insuficiente!");
        }
    }

    @Override
    public void depositar(double valor) {
        double taxaJuros = getTaxaJuros();
        double valorJuros = valor * taxaJuros;
        valor += valorJuros;
        setSaldo(getSaldo() + valor);
    }
}
