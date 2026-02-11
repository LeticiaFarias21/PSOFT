package com.ufcg.psoft.commerce.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;


import java.util.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Estabelecimento")
public class Estabelecimento {

    @JsonProperty("id")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(unique = true, nullable = false, name = "estabelecimento_id")
    private Long id;

    @JsonProperty("nome")
    @Column(name = "estabelecimento_nome")
    private String nome;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "estabelecimento_endereco")
    private Endereco endereco;

    @JsonProperty("codigoAcesso")
    @Column(unique = true, nullable = false, name = "estabelecimento_codigo_acesso")
    @Size(min = 6, max = 6, message = "O código de acesso deve ter 6 dígitos")
    private String codigoAcesso;

    @OneToMany(mappedBy = "estabelecimento")
    private List<Associacao> associacoes = new ArrayList<>();

    @JsonProperty("sabores")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "sabores")
    private Set<Sabor> sabores;

    @JsonProperty("entregadores")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    @JoinColumn(name = "entregadores")
    private List<Entregador> entregadoresDisponiveis = new LinkedList<>();

}

