package com.ufcg.psoft.commerce.dto.entregador;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ufcg.psoft.commerce.model.TipoDoVeiculoValidator;
import com.ufcg.psoft.commerce.model.TipoVeiculo;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EntregadorPostPutRequestDTO {

    @JsonProperty("nome")
    @NotBlank(message = "Nome obrigatorio")
    private String nome;

    @JsonProperty("placaVeiculo")
    @NotBlank(message = "A placa do veiculo e obrigatoria")
    private String placaVeiculo;

    @JsonProperty("tipoVeiculo")
    @TipoDoVeiculoValidator(regexp = "MOTO|CARRO")
    @Enumerated(EnumType.STRING)
    private TipoVeiculo tipoVeiculo;

    @JsonProperty("corVeiculo")
    @NotBlank(message = "A cor do veiculo e obrigatoria")
    private String corVeiculo;

    @JsonProperty("codigoAcesso")
    @NotBlank(message = "O codigo de acesso e obrigatorio")
    @Size(min = 6, message = "Codigo de acesso deve ter tamanho minimo de 6 digitos")
    private String codigoAcesso;
}