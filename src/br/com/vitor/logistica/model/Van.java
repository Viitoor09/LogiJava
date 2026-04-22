package br.com.vitor.logistica.model;

public class Van extends Veiculo{

    public Van(String placa, String modelo, double capacidadeCarga) {
        super(placa, modelo, capacidadeCarga);
    }

    @Override
    public double calcularCustoViagem(double distancia) {
        return distancia * 2.0;
    }
}
