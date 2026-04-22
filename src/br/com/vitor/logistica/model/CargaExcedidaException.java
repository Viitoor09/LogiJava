package br.com.vitor.logistica.model;

public class CargaExcedidaException extends Exception {

    public CargaExcedidaException(double pesoCarga, double capacidade) {
        super(" Carga de " + pesoCarga + "kg excede a capacidade de " + capacidade + " kg!");
    }
}