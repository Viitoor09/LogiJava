package br.com.vitor.logistica.main;

import br.com.vitor.logistica.model.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static void main(String[] args) {
        ArrayList<Veiculo> frota = new ArrayList<>();
        Scanner leitor = new Scanner(System.in);
        int opcao = 0;

        while (opcao != 3) {
            System.out.println("\n---SISTEMA DE LOGÍSTICA---");
            System.out.println("1. Cadastrar Van");
            System.out.println("2. Cadastrar Caminhão");
            System.out.println("3. Finalizar e Validar Rotas");
            System.out.println("Escolha uma opção: ");
            opcao = leitor.nextInt();
            leitor.nextLine();

            switch (opcao){
                case 1:
                    System.out.println("---Cadastrando Van---");
                    System.out.println("Digite a placa: ");
                    String placa = leitor.nextLine();

                    System.out.println("Digite o modelo: ");
                    String modelo = leitor.nextLine();

                    System.out.println("Capacidade de carga (kg): ");
                    double cap = leitor.nextDouble();

                    //Aqui onde cria o objeto com os dados que o usuario digitou:
                    frota.add(new Van(placa, modelo, cap));

                    System.out.println("Van adicionada com sucesso!");
                    break;

                case 2:
                    System.out.println("---Cadastrando Caminhão---");
                    System.out.println("Digite a placa: ");
                    String placaC = leitor.nextLine();

                    System.out.println("Digite o modelo: ");
                    String modeloC = leitor.nextLine();

                    System.out.println("Capacidade de carga (kg): ");
                    double capC = leitor.nextDouble();

                    System.out.println("Quantos Eixos: ");
                    int eixo = leitor.nextInt();

                    frota.add(new Caminhao(placaC, modeloC, capC, eixo));
                    break;
                case 3:
                    System.out.println("Saindo do cadastro...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }

        //frota.add(new Van("ABC-1234", "Sprinter", 1500.0));
        //frota.add(new Caminhao("XYZ-9999", "Volvo FH" , 20000.0, 6));
        //frota.add(new Van("KID-0000", "Fiorino", 600.0));

        Rota r1 = new Rota("Ribeirão Pires", 100.0, 1000.0);
        LogisticaService servico = new LogisticaService();

        double custoTotal = 0;
        for (Veiculo v : frota) {
            try {
                servico.validarViagem(v, r1);
                custoTotal += v.calcularCustoViagem(r1.getDistancia());
            } catch (CargaExcedidaException e) {
                System.out.println("ALERTA para o veículo " + v.getModelo() + " Com a placa: " + v.getPlaca() + e.getMessage());
            }
        }
        System.out.printf("Custo Total da Operação: R$ %.2f%n", custoTotal);
    }

}
