package br.com.vitor.logistica.model;

public class LogisticaService {
    public void validarViagem(Veiculo v, Rota r) throws CargaExcedidaException {

        if (r.getPesoCaga() > v.getCapacidadeCarga()){
            throw new CargaExcedidaException(r.getPesoCaga(), v.getCapacidadeCarga());
        }
            double custoFinal = v.calcularCustoViagem(r.getDistancia());
        System.out.println("✅ VIAGEM AUTORIZADA! Custo: R$ " + custoFinal);

        }


}
