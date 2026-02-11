package com.ufcg.psoft.commerce.dto.entregador;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ufcg.psoft.commerce.model.Entregador;
import com.ufcg.psoft.commerce.model.TipoVeiculo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EntregadorGetResponseDTO {

    @JsonProperty("id")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @JsonProperty("nome")
    private String nome;

    @JsonProperty("placaVeiculo")
    private String placaVeiculo;

    @JsonProperty("tipoVeiculo")
    @Enumerated(EnumType.STRING)
    private TipoVeiculo tipoVeiculo;

    @JsonProperty("corVeiculo")
    private String corVeiculo;

    public EntregadorGetResponseDTO(Entregador entregador){
        this.id = entregador.getId();
        this.nome = entregador.getNome();
        this.placaVeiculo = entregador.getPlacaVeiculo();
        this.tipoVeiculo = entregador.getTipoVeiculo();
        this.corVeiculo = entregador.getCorVeiculo();
    }
}
