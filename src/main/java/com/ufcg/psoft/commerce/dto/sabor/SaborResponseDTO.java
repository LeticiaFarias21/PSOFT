package com.ufcg.psoft.commerce.dto.sabor;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;

public class SaborResponseDTO {
    @JsonProperty("nome")
    private String nome;

    @JsonProperty("tipo")
    private String tipo;

    @JsonProperty("precoM")
    private Double precoM;

    @JsonProperty("precoG")
    private Double precoG;

    @JsonProperty("disponivel")
    private Boolean disponivel;
}
