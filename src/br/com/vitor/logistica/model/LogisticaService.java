package br.com.vitor.logistica.model;

import java.util.ArrayList;

public class LogisticaService {
    public void validarViagem(Veiculo v, PedidoFrete r) throws CargaExcedidaException {

        if (r.getPesoCarga() > v.getCapacidadeCarga()){
            throw new CargaExcedidaException(r.getPesoCarga(), v.getCapacidadeCarga());
        }
            double custoFinal = v.calcularCustoViagem(r.getDistancia());
        System.out.println("[ " + v.getModelo() + ", " + v.getPlaca() + " ] ✅ VIAGEM AUTORIZADA! Custo: R$ " + custoFinal);

    }

    public ArrayList<Veiculo> filtrarVeiculoAptos(ArrayList<Veiculo> frota, PedidoFrete pedidoFrete) {
        ArrayList<Veiculo> aptos = new ArrayList<>();
        for (Veiculo v : frota) {
            if (v.getCapacidadeCarga() >= pedidoFrete.getPesoCarga()) {
                aptos.add(v);
            }
        }
        return aptos;
    }


}
