package br.com.vitor.logistica.model;

public class Caminhao extends Veiculo implements Seguravel{
    private int quantidadeEixos;

    @Override
    public String toCSV(double custo, double seguro) {
        return "CAMINHAO;" + getModelo() + ";" + getCapacidadeCarga() + ";" + seguro + ";" + (custo + seguro);
    }

    @Override
    public String toCSVBase() {
        return "CAMINHAO;" + getPlaca() + ";" + getModelo() + ";" + getCapacidadeCarga() + ";" + getEixos();
    }


    public Caminhao(String placa, String modelo, double capacidadeCarga, int quantidadeEixos) {
        super(placa, modelo, capacidadeCarga);
        this.quantidadeEixos = quantidadeEixos;
    }

    public int getEixos() {
        return quantidadeEixos;
    }


    @Override
    public double calcularCustoViagem(double distancia) {
        return distancia * 5.0 + (quantidadeEixos * 10);
    }

    @Override
    public double calcularValorSeguro() {
        return (this.getCapacidadeCarga() * 0.05) + 500;
    }

}
