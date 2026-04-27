package br.com.vitor.logistica.model;

public class Van extends Veiculo{

    @Override
    public String toCSV(double custo, double seguro) {
        return "VAN;" + getModelo() + ";" + getCapacidadeCarga() + ";" + seguro + ";" + custo;
    }

    @Override
    public String toCSVBase() {
        return "VAN;" + getPlaca() + ";" + getModelo() + ";" + getCapacidadeCarga();
    }

    public Van(String placa, String modelo, double capacidadeCarga) {
        super(placa, modelo, capacidadeCarga);
    }

    @Override
    public double calcularCustoViagem(double distancia) {
        return distancia * 2.0;
    }
}
