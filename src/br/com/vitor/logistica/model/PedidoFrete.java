package br.com.vitor.logistica.model;

public class PedidoFrete {
    private String nomeEmpresa;
    private String destino;
    private double distancia;
    private double pesoCarga;

    public PedidoFrete(String destino, String nomeEmpresa, double distancia, double pesoCarga) {
        this.destino = destino;
        this.distancia = distancia;
        this.pesoCarga = pesoCarga;
        this.nomeEmpresa = nomeEmpresa;
    }

    // Getters para podermos usar esses dados na Service depois
    public String getNomeEmpresa() { return nomeEmpresa; }

    public String getDestino() {
        return destino;
    }

    public double getPesoCarga() {
        return pesoCarga;
    }

    public double getDistancia() { return distancia; }

}