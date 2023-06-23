package main.java.app.model;

public class Conta {
    private int numero;
    private String cpf;
    private String titular;
    private double saldo;
    private double taxaJuros;

    public Conta(int numero, String cpf, String titular, double saldo) {
        this.numero = numero;
        this.cpf = cpf;
        this.titular = titular;
        this.saldo = saldo;
        this.taxaJuros = 0.005; 
    }

    public int getNumero() {
        return numero;
    }

    public String getCpf() {
        return cpf;
    }

    public String getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public void depositar(double valor) {
        saldo += valor;
    }

    public void sacar(double valor) {
        if (valor <= saldo) {
            saldo -= valor;
        } else if (valor <= saldo + 500) {
            saldo = 0;
            System.out.println("Saldo insuficiente. Usando cheque especial de R$500.");
        } else {
            System.out.println("Saldo insuficiente!");
        }
    }

    public void aumentarTaxaJuros(double aumento) {
        taxaJuros += aumento;
    }

    public double calcularJuros() {
        return saldo * taxaJuros;
    }
}
