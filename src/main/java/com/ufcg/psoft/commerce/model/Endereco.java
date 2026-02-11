package com.ufcg.psoft.commerce.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "enderecos")
public class Endereco {

    @JsonProperty("id")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(unique = true, nullable = false, name = "endereco_id")
    private Long id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "endereco_logradouro", nullable = false)
    private Logradouro logradouro;

    @JsonProperty("numero")
    @Column(nullable = false, name = "endereco_numero")
    private Integer numero;

    @JsonProperty("endereco_complemento")
    @Column(nullable = false, name = "endereco_complemento")
    private String complemento;
}
