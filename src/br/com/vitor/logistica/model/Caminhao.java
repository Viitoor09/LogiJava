package br.com.vitor.logistica.model;

public class Caminhao extends Veiculo {
    private int quantidadeEixos;

    public Caminhao(String placa, String modelo, double capacidadeCarga, int quantidadeEixos) {
        super(placa, modelo, capacidadeCarga);
        this.quantidadeEixos = quantidadeEixos;
    }


    @Override
    public double calcularCustoViagem(double distancia) {
        return distancia * 5.0 + (quantidadeEixos * 10);
    }
}
