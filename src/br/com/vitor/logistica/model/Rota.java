package br.com.vitor.logistica.model;

public class Rota{
    private String destino;
    private double distancia;
    private double pesoCaga;

    public Rota(String destino, double distancia, double pesoCaga) {
        this.destino = destino;
        this.distancia = distancia;
        this.pesoCaga = pesoCaga;
    }

    // Getters para podermos usar esses dados na Service depois
    public String getDestino() {
        return destino;
    }

    public double getPesoCaga() {
        return pesoCaga;
    }

    public double getDistancia() {
        return distancia;
    }
}