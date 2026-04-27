package br.com.vitor.logistica.service;

import br.com.vitor.logistica.model.*;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ArquivoService {
    public void salvarFrotaCompleta(ArrayList<Veiculo> frota) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("minha_frota_cadastrada.csv"))) {
            writer.println("TIPO;PLACA;MODELO;CAPACIDADE;EIXOS");
            for (Veiculo v : frota) {
                writer.println(v.toCSVBase());
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar frota: " + e.getMessage());
        }
    }

    public void exportarParaExcel(ArrayList<Veiculo> frota, PedidoFrete pedidoFrete) {
        // Criei o arquivo com o nome da rota para ficar organizado
        String nomeArquivo = "Relatorio_" + pedidoFrete.getDestino().replace(" ", "_") + ".csv";

        try (PrintWriter writer = new PrintWriter(new FileWriter(nomeArquivo))) {
            writer.println("sep=;");

            // 1. Criei o Cabeçalho (as colunas do Excel)
            writer.println("TIPO;MODELO;CAPACIDADE;SEGURO;CUSTO_TOTAL");

            // 2. Varri a frota para escrever os dados
            for (Veiculo v : frota) {
                double seguro = 0;
                if (v instanceof Seguravel s) {
                    seguro = s.calcularValorSeguro();
                }
                double custoViagem = v.calcularCustoViagem(pedidoFrete.getDistancia());

                // Escrevi a linha do veículo no arquivo
                writer.println(v.toCSV(custoViagem, seguro));
            }

            System.out.println("✅ Relatório '" + nomeArquivo + "' gerado com sucesso!");

        } catch (IOException e) {
            System.err.println("❌ Erro ao gerar o arquivo: " + e.getMessage());
        }
    }
}