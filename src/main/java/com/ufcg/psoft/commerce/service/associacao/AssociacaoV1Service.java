package com.ufcg.psoft.commerce.service.associacao;

import com.ufcg.psoft.commerce.exception.*;
import com.ufcg.psoft.commerce.model.Associacao;
import com.ufcg.psoft.commerce.model.Entregador;
import com.ufcg.psoft.commerce.model.Estabelecimento;
import com.ufcg.psoft.commerce.repository.AssociacaoRepository;
import com.ufcg.psoft.commerce.repository.EntregadorRepository;
import com.ufcg.psoft.commerce.repository.EstabelecimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssociacaoV1Service implements AssociacaoService{

    @Autowired
    private AssociacaoRepository associacaoRepository;

    @Autowired
    private EntregadorRepository entregadorRepository;

    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;

    @Override
    public Associacao associarEntregadorEstabelecimento(Long entregadorId, Long estabelecimentoId, String codigoAcessoEntregador) {
        Estabelecimento estabelecimento = estabelecimentoRepository.findById(estabelecimentoId).orElseThrow(EstabelecimentoNaoExisteException::new);
        Entregador entregador = entregadorRepository.findById(entregadorId).orElseThrow(EntregadorNaoExisteException::new);
        if (entregador.getCodigoAcesso().equals(codigoAcessoEntregador)){
            Associacao associacao = Associacao.builder()
                    .entregador(entregador)
                    .estabelecimento(estabelecimento)
                    .statusAssociacao(false)
                    .build();

            return associacaoRepository.save(associacao);
        }

        throw new EntregadorNaoAutorizadoException();
    }

    @Override
    public Associacao aceitarAssociacao(Long id, String codigoDeAcessoEstabelecimento) {
        Associacao associacao = associacaoRepository.findById(id).orElseThrow(AssociacaoNaoExisteException::new);
        Estabelecimento estabelecimento = associacao.getEstabelecimento();
        if (estabelecimento.getCodigoAcesso().equals(codigoDeAcessoEstabelecimento)){
            associacao.setStatusAssociacao(true);
            return associacaoRepository.save(associacao);
        }

        throw new EstabelecimentoNaoAutorizadoException();
    }

    @Override
    public void recusarAssociacao(Long id, String codigoDeAcessoEstabelecimento) {
        Associacao associacao = associacaoRepository.findById(id).orElseThrow(AssociacaoNaoExisteException::new);
        Estabelecimento estabelecimento = associacao.getEstabelecimento();
        if (estabelecimento.getCodigoAcesso().equals(codigoDeAcessoEstabelecimento)) {
            associacaoRepository.deleteById(id);
        } else {
            throw new EstabelecimentoNaoAutorizadoException();
        }

    }

    @Override
    public Associacao buscarAssociacao(Long entregadorId, Long estabelecimentoId, String codigoAcessoEstabelecimento) {
        Estabelecimento estabelecimento = estabelecimentoRepository.findById(estabelecimentoId).orElseThrow(EstabelecimentoNaoExisteException::new);

//        if (estabelecimento.getCodigoAcesso().equals(codigoAcessoEstabelecimento)){
//            return associacaoRepository.findByEntregadorIdAndEstabelecimentoId(entregadorId, estabelecimentoId);
//        }

        return null;
    }

}

