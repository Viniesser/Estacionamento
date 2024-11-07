package fag;

import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Estacionamento estacionamento = new Estacionamento(10);  

        while (true) {
            System.out.println("\nSistema de Estacionamento");
            System.out.println("1. Registrar Entrada");
            System.out.println("2. Registrar Saída");
            System.out.println("3. Relatório de Vagas Ocupadas");
            System.out.println("4. Relatório de Histórico");
            System.out.println("5. Mostrar Vagas");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    
                    System.out.print("Placa do veículo: ");
                    String placa = scanner.nextLine();
                    System.out.print("Modelo do veículo: ");
                    String modelo = scanner.nextLine();
                    System.out.print("Tamanho do veículo (Pequeno, Médio, Grande): ");
                    String tamanho = scanner.nextLine();
                    estacionamento.registrarEntrada(placa, modelo, tamanho);
                    break;

                case 2:
                   
                    System.out.print("Placa do veículo para saída: ");
                    placa = scanner.nextLine();
                    estacionamento.registrarSaida(placa);
                    break;

                case 3:
                   
                    estacionamento.relatorioVagasOcupadas();
                    break;

                case 4:
               
                    estacionamento.relatorioHistorico();
                    break;

                case 5:
                    
                    estacionamento.exibirVagas();
                    break;

                case 6:
                   
                    System.out.println("Saindo do sistema...");
                    scanner.close();
                    return;  

                default:
                    System.out.println("Opção inválida! Por favor, escolha uma opção válida.");
                    break;
            }
        }
    }
}
