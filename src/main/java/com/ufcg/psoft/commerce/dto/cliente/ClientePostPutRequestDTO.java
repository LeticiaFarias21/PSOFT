package com.ufcg.psoft.commerce.dto.cliente;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientePostPutRequestDTO {

        @JsonProperty("nome")
        @NotBlank(message = "Nome obrigatorio")
        private String nome;

        @JsonProperty("endereco")
        @NotBlank(message = "Endereco obrigatorio")
        private String endereco;

        @JsonProperty("codigoAcesso")
        @NotBlank(message = "O código de acesso não pode ser vazio")
        private String codigoAcesso;
}
