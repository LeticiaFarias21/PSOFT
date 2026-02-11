package com.ufcg.psoft.commerce.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Associacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "estabelecimento_id")
    private Estabelecimento estabelecimento;

    @ManyToOne
    @JoinColumn(name = "entregador_id")
    private Entregador entregador;

    @JsonProperty("status")
    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean statusAssociacao;
}
