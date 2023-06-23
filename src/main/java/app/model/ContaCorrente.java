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
                super.sacar(valor);
            } else {
                double valorRestante = valor - saldoAtual;
                super.sacar(saldoAtual);
                chequeEspecial -= valorRestante;
            }
        } else {
            System.out.println("Saldo insuficiente!");
        }
    }
}
