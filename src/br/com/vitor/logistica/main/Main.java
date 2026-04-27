package br.com.vitor.logistica.main;
import br.com.vitor.logistica.model.*;
import br.com.vitor.logistica.service.ArquivoService;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static double lerDoubleObrigatorio(Scanner leitor, String campo){
        while (true) {
            System.out.println(campo + ": ");
            if (leitor.hasNextDouble()){
                double valor = leitor.nextDouble();
                leitor.nextLine();
                if (valor > 0) {
                    return valor;
                }else{
                    System.err.println("Erro: O valor deve ser maior que zero!");
                }
            }else{
                System.err.println("Erro: Digite apenas números (use vírgula para decimais)!");
                leitor.nextLine();
            }
        }
    }
    private static String lerTextoObrigatorio(Scanner leitor, String campo) {
        String texto = "";
        while (texto.isBlank()) {
            System.out.println("Digite o(a) " + campo + ": ");
            texto = leitor.nextLine().trim();
            if (texto.isBlank()) {
                System.err.println("ERRO: O campo " + campo + " é obrigatório!");
            }
        }
        return texto;
    }
    private static void inicializarMinhaFrota(ArrayList<Veiculo> frota) {
        frota.add(new Van("VTR-1010", "Mercedes Sprinter", 1500.0));
        frota.add(new Caminhao("VTR-2020", "Scania R450", 25000.0, 9));
        frota.add(new Van("VTR-3030", "Fiat Fiorino", 650.0));

        System.out.println(">>> [SISTEMA] Frota da companhia carregada!");
    }
    static void main(String[] args) {
        ArrayList<Veiculo> frota = new ArrayList<>();
        ArrayList<PedidoFrete> pedidoFretes = new ArrayList<>();
        inicializarMinhaFrota(frota);
        ArquivoService arquivoService = new ArquivoService();
        Scanner leitor = new Scanner(System.in);
        int opcao = 0;

        while (opcao != 4) {
            System.out.println("\n╔════════════════════════════════════════╗");
            System.out.println("║          SISTEMA DE LOGÍSTICA          ║");
            System.out.println("╠════════════════════════════════════════╣");
            System.out.println("║  1. Cadastrar Van                      ║");
            System.out.println("║  2. Cadastrar Caminhão                 ║");
            System.out.println("║  3. Novo Pedido de Frete               ║");
            System.out.println("║  4. Processar e Sair                   ║");
            System.out.println("╚════════════════════════════════════════╝");
            System.out.print(" Escolha uma opção: ");
            opcao = leitor.nextInt();
            leitor.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("---Cadastrando Van---");
                    String placa = lerTextoObrigatorio(leitor, "placa");

                    String modelo = lerTextoObrigatorio(leitor, "modelo");

                    double cap = lerDoubleObrigatorio(leitor, "Capacidade");

                    frota.add(new Van(placa, modelo, cap));
                    arquivoService.salvarFrotaCompleta(frota);
                    System.out.println("Van adicionada com sucesso!");
                    break;

                case 2:
                    System.out.println("---Cadastrando Caminhão---");
                    String placaC = lerTextoObrigatorio(leitor, "placa");

                    String modeloC = lerTextoObrigatorio(leitor, "modelo");

                    double capC = lerDoubleObrigatorio(leitor, "capacidade da carga (Kg): ");

                    System.out.println("Quantos Eixos: ");
                    int eixo = leitor.nextInt();
                    leitor.nextLine();

                    frota.add(new Caminhao(placaC, modeloC, capC, eixo));
                    arquivoService.salvarFrotaCompleta(frota);
                    System.out.println("Caminhão adicionado com sucesso!");
                    break;

                case 3:
                    System.out.println("--- Cadastrar Pedido de Frete ---");

                    String nomeEmpresa = lerTextoObrigatorio(leitor, "nome da empresa");

                    String destino = lerTextoObrigatorio(leitor, "destino");

                    double distancia = lerDoubleObrigatorio(leitor, "distância (km)");

                    double pesoCarga = lerDoubleObrigatorio(leitor, "peso da carga (kg)");
                    leitor.nextLine();

                    pedidoFretes.add(new PedidoFrete(destino, nomeEmpresa, distancia, pesoCarga));

                    System.out.println("Pedido de frete registrado para " + nomeEmpresa + "!");
                    break;

                case 4:
                    System.out.println("Saindo do cadastro...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }

        arquivoService.salvarFrotaCompleta(frota);
        LogisticaService servico = new LogisticaService();

        for (PedidoFrete r : pedidoFretes) {
            System.out.println("\n\n" + "=".repeat(50));
            System.out.println("   📦 PLANEJAMENTO DE CARGA: " + r.getDestino().toUpperCase());
            System.out.println("   🏢 CLIENTE: " + r.getNomeEmpresa());
            System.out.println("   ⚖️ CARGA: " + r.getPesoCarga() + "kg | 🛣️ DISTÂNCIA: " + r.getDistancia() + "km");
            System.out.println("=".repeat(50));

            // 1. Criamos uma lista temporária só para os veículos que aguentam essa rota
            ArrayList<Veiculo> veiculosAptos = new ArrayList<>();

            for (Veiculo v : frota) {
                try {
                    servico.validarViagem(v, r);
                    veiculosAptos.add(v);

                    double custoViagem = v.calcularCustoViagem(r.getDistancia());
                    double custoComSeguro = custoViagem;

                    if (v instanceof Seguravel s) {
                        custoComSeguro += s.calcularValorSeguro();
                    }

                    // Exibição mais limpa por veículo
                    System.out.printf("  > [OPÇÃO] %-15s (%-10s) | VALOR: R$ %,.2f%n\n",
                            v.getModelo(), v.getPlaca(), custoComSeguro);

                } catch (CargaExcedidaException e) {
                    // Opcional: mostrar em uma cor diferente ou apenas um aviso discreto
                    System.out.println("  x [INDISPONÍVEL] " + v.getModelo() + ": Carga excedida.");
                }
            }

            if (!veiculosAptos.isEmpty()) {
                arquivoService.exportarParaExcel(veiculosAptos, r);
                // Print de sucesso com uma linha em branco depois
                System.out.println("\n✅ Relatório '" + r.getDestino() + "' gerado com sucesso!");
            } else {
                System.err.println("\n⚠️ NENHUM VEÍCULO DA FROTA SUPORTA ESTA CARGA.");
            }
        }

        System.out.println("\n\n--- Processamento Finalizado ---");

    }
}
