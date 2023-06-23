package main.java.app;

import main.java.app.dao.ContaDao;
import main.java.app.model.Conta;
import main.java.app.model.ContaCorrente;
import main.java.app.model.ContaPoupanca;
import main.java.app.database.Banco;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            Connection conexao = criarConexao();
            ContaDao contaDao = new ContaDao(conexao);

            Scanner scanner = new Scanner(System.in);
            int opcao;

            do {
                exibirMenu();
                opcao = scanner.nextInt();

                switch (opcao) {
                    case 1:
                        criarConta(scanner, contaDao);
                        break;
                    case 2:
                        depositar(scanner, contaDao);
                        break;
                    case 3:
                        realizarSaque(scanner, contaDao);
                        break;
                    case 4:
                        consultarSaldo(scanner, contaDao);
                        break;
                    case 5:
                        System.out.println("Saindo do programa...");
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } while (opcao != 5);

            scanner.close();
            conexao.close();
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }

    private static Connection criarConexao() {
        try {
            return Banco.conectar();
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            return null;
        }
    }

    private static void exibirMenu() {
        System.out.println("-------- BANCO JAVA LI --------");
        System.out.println("1. Criar conta");
        System.out.println("2. Realizar depósito");
        System.out.println("3. Realizar saque");
        System.out.println("4. Consultar saldo");
        System.out.println("5. Sair do programa");
        System.out.println("-----------------------");
        System.out.print("Escolha uma opção: ");
    }

    private static void criarConta(Scanner scanner, ContaDao contaDao) {
        System.out.println("Digite o número da conta:");
        int numero = scanner.nextInt();

        System.out.println("Digite o CPF do titular:");
        String cpf = scanner.next();

        System.out.println("Digite o nome do titular:");
        String titular = scanner.next();

        System.out.println("Digite o saldo inicial:");
        double saldo = scanner.nextDouble();

        System.out.println("Digite o tipo de conta (1 - Poupança, 2 - Corrente):");
        int tipoConta = scanner.nextInt();

        Conta conta;
        if (tipoConta == 1) {
            conta = new ContaPoupanca(numero, cpf, titular, saldo);
        } else if (tipoConta == 2) {
            conta = new ContaCorrente(numero, cpf, titular, saldo);
        } else {
            System.out.println("Tipo de conta inválido.");
            return;
        }

        try {
            contaDao.criarConta(conta);
            System.out.println("Conta criada com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao criar conta: " + e.getMessage());
        }
    }

    private static void depositar(Scanner scanner, ContaDao contaDao) {
        System.out.println("Digite o número da conta:");
        int numero = scanner.nextInt();

        System.out.println("Digite o valor do depósito:");
        double valor = scanner.nextDouble();

        try {
            Conta conta = contaDao.buscarContaPorNumero(numero);

            if (conta != null) {
                conta.depositar(valor);
                contaDao.atualizarConta(conta);
                System.out.println("Depósito realizado com sucesso!");
                System.out.println("Novo saldo da conta: R$" + conta.getSaldo());
            } else {
                System.out.println("Conta não encontrada.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao realizar depósito: " + e.getMessage());
        }
    }

    private static void realizarSaque(Scanner scanner, ContaDao contaDao) {
        System.out.println("Digite o número da conta:");
        int numero = scanner.nextInt();

        System.out.println("Digite o valor do saque:");
        double valor = scanner.nextDouble();

        try {
            Conta conta = contaDao.buscarContaPorNumero(numero);

            if (conta != null) {
                if (conta.getSaldo() >= valor) {
                    conta.sacar(valor);
                    contaDao.atualizarConta(conta);
                    System.out.println("Saque realizado com sucesso!");
                    System.out.println("Novo saldo da conta: R$" + conta.getSaldo());
                } else {
                    System.out.println("Saldo insuficiente.");
                }
            } else {
                System.out.println("Conta não encontrada.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao realizar saque: " + e.getMessage());
        }
    }

    private static void consultarSaldo(Scanner scanner, ContaDao contaDao) {
        System.out.println("Digite o número da conta:");
        int numero = scanner.nextInt();

        try {
            double saldo = contaDao.consultarSaldo(numero);
            System.out.println("Saldo da conta: R$" + saldo);
        } catch (SQLException e) {
            System.out.println("Erro ao consultar saldo: " + e.getMessage());
        }
    }
}
