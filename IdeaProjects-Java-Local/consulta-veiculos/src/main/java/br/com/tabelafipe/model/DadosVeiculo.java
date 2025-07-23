package br.com.tabelafipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosVeiculo(@JsonAlias("Modelo") String descricao, @JsonAlias("AnoModelo") Integer ano,
                           @JsonAlias("Valor") String valor, @JsonAlias("Combustivel") String combustivel) {
}
