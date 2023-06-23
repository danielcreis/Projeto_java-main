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
    double limite = getSaldo() + getChequeEspecial();
    
    if (valor <= limite) {
        if (valor <= getSaldo()) {
            setSaldo(getSaldo() - valor);
        } else {
            double valorRestante = valor - getSaldo();
            setSaldo(0);
            setChequeEspecial(getChequeEspecial() - valorRestante);
        }
        
        System.out.println("Saque realizado com sucesso!");
        System.out.println("Novo saldo da conta: R$" + getSaldo());
    } else {
        System.out.println("Saldo insuficiente! Limite disponível: R$" + limite);
    }
}

    private void setChequeEspecial(double d) {
}

    @Override
    public void depositar(double valor) {
        double taxaJuros = getTaxaJuros();
        double valorJuros = valor * taxaJuros;
        valor += valorJuros;
        setSaldo(getSaldo() + valor);
    }
}
