package com.ufcg.psoft.commerce.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "pizzas")
public class Sabor {
    @JsonProperty("id")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(unique = true, nullable = false, name = "id")
    private Long id;

    @JsonProperty("nome")
    @Column(nullable = false, name = "nome")
    private String nome;

    @JsonProperty("tipo")
    @Column(nullable = false, name = "tipo")
    private String tipo;

    @JsonProperty("precoM")
    @Column(nullable = false, name = "preco_m")
    private Double precoM;

    @JsonProperty("precoG")
    @Column(nullable = false, name = "preco_g")
    private Double precoG;

    @JsonProperty("disponivel")
    @Column(nullable = false, name = "disponivel")
    private Boolean disponivel;

    @JsonProperty
    @Builder.Default
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "clientes_interessados")
    private Set<Cliente> clientesInteressados = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sabor sabor)) return false;
        if (!this.getNome().equals(sabor.getNome())) return false;
        if (!this.getTipo().equals(sabor.getTipo())) return false;
        if (!this.getPrecoM().equals(sabor.getPrecoM())) return false;
        if (!this.getPrecoG().equals(sabor.getPrecoG())) return false;
        return this.getDisponivel().equals(sabor.getDisponivel());
    }
}
