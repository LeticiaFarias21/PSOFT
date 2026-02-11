package com.ufcg.psoft.commerce.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Cliente")
public class Cliente {

    @JsonProperty("id")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(unique = true, nullable = false, name = "cliente_id")
    private Long id;

    @JsonProperty("nome")
    @Column(nullable = false, name = "cliente_nome")
    private String nome;

    @JsonProperty("endereco")
    @Column(name = "cliente_endereco")
    private String endereco;

    @JsonProperty("codigoAcesso")
    @Column(unique = true, nullable = false, name = "cliente_codigo_acesso")
    @Size(min = 6, max = 6, message = "O código de acesso deve ter 6 dígitos numéricos")
    private String codigoAcesso;
}
