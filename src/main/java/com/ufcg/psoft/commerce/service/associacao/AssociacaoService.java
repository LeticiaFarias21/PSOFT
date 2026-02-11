package com.ufcg.psoft.commerce.service.associacao;

import com.ufcg.psoft.commerce.model.Associacao;

public interface AssociacaoService {
    public Associacao associarEntregadorEstabelecimento(Long entregadorId, Long estabelecimentoId, String codigoAcessoEntregador);

    public Associacao aceitarAssociacao(Long id, String codigoDeAcessoEstabelecimento);

    public void recusarAssociacao(Long id, String codigoDeAcessoEstabelecimento);


    public Associacao buscarAssociacao(Long entregadorId, Long estabelecimentoId, String codigoAcessoEstabelecimento);
}
