package com.ufcg.psoft.commerce.dto.estabelecimento;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ufcg.psoft.commerce.model.Endereco;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EstabelecimentoResponseDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("nome")
    private String nome;

    @JsonProperty("codigoAcesso")
    @NotBlank(message = "O código de acesso não pode ser vazio")
    @Size(min = 6, message = "O código de acesso deve ter 6 dígitos")
    private String codigoAcesso;

    @JsonProperty("endereco")
    private Endereco endereco;

}
