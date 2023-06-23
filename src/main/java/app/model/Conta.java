package main.java.app.model;

public abstract class Conta {
    private int numero;
    private String cpf;
    private String titular;
    private double saldo;

    public Conta(int numero, String cpf, String titular, double saldo) {
        this.numero = numero;
        this.cpf = cpf;
        this.titular = titular;
        this.saldo = saldo;
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

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public abstract void sacar(double valor);

    public abstract void depositar(double valor);

    public double getChequeEspecial() {
        return 0; // Implemente o método nas subclasses
    }

    public double getTaxaJuros() {
        return 0; // Implemente o método nas subclasses
    }
}
