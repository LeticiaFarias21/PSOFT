package com.ufcg.psoft.commerce.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "entregadores")
public class Entregador {

    @Id
    @JsonProperty("id")
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

    @JsonProperty("codigoAcesso")
    private String codigoAcesso;

    @JsonProperty("disponilibidade")
    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean disponibilidade;

    @JsonProperty("statusAprovacao")
    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean statusAprovacao;

    @OneToMany(mappedBy = "entregador")
    private List<Associacao> associacoes = new ArrayList<>();
}