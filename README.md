# 🚚 Sistema de Gestão Logística e Cotação de Fretes

Este projeto é um simulador de operações logísticas desenvolvido em **Java**. O sistema gerencia uma frota fixa de veículos (Vans e Caminhões), processa pedidos de frete de clientes e gera relatórios de cotação automáticos em formato Excel/CSV, aplicando regras de negócio para viabilidade de carga e cálculos de seguro.

## 🚀 Funcionalidades

- **Gestão de Frota:** Cadastro e persistência de veículos com diferentes capacidades e custos operacionais.
- **Processamento de Pedidos:** Entrada de dados de clientes, destinos e peso de carga.
- **Motor de Regras de Negócio:** - Validação de capacidade de carga por veículo.
    - Cálculo dinâmico de custo de viagem (Km + Eixos para caminhões).
    - Cálculo de seguro obrigatório para veículos seguráveis.
- **Relatórios Automatizados:** - Geração de CSV para cotações individuais por destino.
    - Persistência da frota atualizada em arquivo local.
- **Interface Console UX:** Menu interativo com formatação visual e alinhamento de dados para melhor legibilidade.

## 🛠️ Tecnologias Utilizadas

- **Linguagem:** Java 17+
- **Paradigma:** Programação Orientada a Objetos (POO)
- **Conceitos Aplicados:** - Herança e Classes Abstratas
    - Interfaces (`Seguravel`)
    - Polimorfismo
    - Tratamento de Exceções Personalizadas (`CargaExcedidaException`)
    - Manipulação de Arquivos (Java I/O)

## 📁 Estrutura do Projeto

O projeto segue uma arquitetura organizada em pacotes:
- `br.com.vitor.logistica.model`: Entidades (Veiculo, Van, Caminhao, PedidoFrete).
- `br.com.vitor.logistica.service`: Lógica de negócio e manipulação de arquivos.
- `br.com.vitor.logistica.main`: Classe principal com a interface de usuário.

## 📊 Exemplo de Saída (Console)

```text
============================================================
   📦 PLANEJAMENTO DE CARGA: SÃO PAULO
   🏢 CLIENTE: GERTEC
   ⚖️ CARGA: 5000.0kg | 🛣️ DISTÂNCIA: 200.0km
============================================================

  > [OPÇÃO] Scania R450      (VTR-2020 ) | VALOR: R$ 2.840,00
  x [INDISPONÍVEL] Fiat Fiorino: Carga excedida.