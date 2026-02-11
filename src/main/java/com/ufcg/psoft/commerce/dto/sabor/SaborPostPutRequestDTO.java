package com.ufcg.psoft.commerce.dto.sabor;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaborPostPutRequestDTO {

    @JsonProperty("nome")
    @NotBlank(message = "O nome não pode ser vazio")
    private String nome;

    @JsonProperty("tipo")
    @NotBlank(message = "O tipo não pode ser vazio")
    private String tipo;

    @JsonProperty("precoM")
    @Column(nullable = false, name = "precoM")
    private Double precoM;

    @JsonProperty("precoG")
    @Column(nullable = false, name = "precoM")
    private Double precoG;

//    @JsonProperty("tamanho")
//    @NotBlank(message = "O tamanho não pode ser vazio")
//    private String tamanho;

    @JsonProperty("disponivel")
    @NotBlank(message = "Disponibilidade obrigatoria" )
    @Column(nullable = false, name = "disponivel")
    private Boolean disponivel;
}
