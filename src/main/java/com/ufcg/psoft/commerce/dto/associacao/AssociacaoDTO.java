package com.ufcg.psoft.commerce.dto.associacao;

import com.ufcg.psoft.commerce.model.Entregador;
import com.ufcg.psoft.commerce.model.Estabelecimento;
import jakarta.persistence.*;

public class AssociacaoDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "estabelecimento_id")
    private Estabelecimento estabelecimento;

    @ManyToOne
    @JoinColumn(name = "entregador_id")
    private Entregador entregador;

    private boolean statusAssociacao;
}
