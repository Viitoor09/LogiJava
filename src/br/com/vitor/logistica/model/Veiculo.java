package br.com.vitor.logistica.model;

public abstract class Veiculo {
    private String placa;
    private String modelo;
    private double capacidadeCarga;

    public Veiculo (String placa,String modelo, double capacidadeCarga) {
        this.placa= placa;
        this.modelo = modelo;
        this.capacidadeCarga = capacidadeCarga;
    }

    // Método que as filhas SERÃO OBRIGATORIAS a implementar (Polimorfismo)
    public abstract double calcularCustoViagem(double distancia);

    public String getPlaca() { return placa; }
    public String getModelo() { return modelo; }
    public double getCapacidadeCarga() { return capacidadeCarga; }

    public abstract String toCSV(double custo, double seguro);
    public abstract String toCSVBase();
}
