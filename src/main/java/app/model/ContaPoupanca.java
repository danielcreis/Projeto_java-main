package main.java.app.model;

public class ContaPoupanca extends Conta {
    private double taxaJuros = 0.005;

    public ContaPoupanca(int numero, String cpf, String titular, double saldo) {
        super(numero, cpf, titular, saldo);
    }

    public double getTaxaJuros() {
        return taxaJuros;
    }

    public void setTaxaJuros(double taxaJuros) {
        this.taxaJuros = taxaJuros;
    }

    @Override
    public void sacar(double valor) {
        if (getSaldo() >= valor) {
            setSaldo(getSaldo() - valor);
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
