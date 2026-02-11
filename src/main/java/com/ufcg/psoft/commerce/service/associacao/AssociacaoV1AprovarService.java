package com.ufcg.psoft.commerce.service.associacao;

import com.ufcg.psoft.commerce.exception.AssociacaoNaoExisteException;
import com.ufcg.psoft.commerce.exception.CodigoDeAcessoInvalidoException;
import com.ufcg.psoft.commerce.exception.EntregadorNaoExisteException;
import com.ufcg.psoft.commerce.exception.EstabelecimentoNaoExisteException;
import com.ufcg.psoft.commerce.model.Associacao;
import com.ufcg.psoft.commerce.model.Estabelecimento;
import com.ufcg.psoft.commerce.repository.AssociacaoRepository;
import com.ufcg.psoft.commerce.repository.EntregadorRepository;
import com.ufcg.psoft.commerce.repository.EstabelecimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssociacaoV1AprovarService implements AssociacaoAprovarService {

    @Autowired
    AssociacaoRepository associacaoRepository;

    @Autowired
    EntregadorRepository entregadorRepository;

    @Autowired
    EstabelecimentoRepository estabelecimentoRepository;

    @Override
    public Associacao aprovar(Long entregadorId, Long estabelecimentoId, String codigoAcesso) {
        Estabelecimento estabelecimento = estabelecimentoRepository.findById(estabelecimentoId).orElseThrow(EstabelecimentoNaoExisteException::new);
        entregadorRepository.findById(entregadorId).orElseThrow(EntregadorNaoExisteException::new);

        if (!(estabelecimento.getCodigoAcesso().equals(codigoAcesso))) {
            throw new CodigoDeAcessoInvalidoException();
        }

        Associacao associacao = associacaoRepository.findByEntregadorIdAndEstabelecimentoId(entregadorId, estabelecimentoId).orElseThrow(AssociacaoNaoExisteException::new);

        associacao.setStatusAssociacao(true);

        return associacaoRepository.save(associacao);
    }
}
