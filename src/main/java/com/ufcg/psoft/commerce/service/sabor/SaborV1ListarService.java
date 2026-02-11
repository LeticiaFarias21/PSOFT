package com.ufcg.psoft.commerce.service.sabor;

import com.ufcg.psoft.commerce.exception.CommerceException;
import com.ufcg.psoft.commerce.exception.EstabelecimentoNaoExisteException;
import com.ufcg.psoft.commerce.exception.SaborNaoExisteException;
import com.ufcg.psoft.commerce.exception.CodigoDeAcessoInvalidoException;
import com.ufcg.psoft.commerce.model.Sabor;
import com.ufcg.psoft.commerce.model.Estabelecimento;
import com.ufcg.psoft.commerce.repository.EstabelecimentoRepository;
import com.ufcg.psoft.commerce.repository.SaborRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SaborV1ListarService implements SaborListarService {

    @Autowired
    private SaborRepository saborRepository;

    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;

    @Override
    public List<Sabor> listar() {
        return saborRepository.findAll();
    }

    @Override
    public List<Sabor> listar(Long saborId, Long estabelecimentoId) {
        Estabelecimento estabelecimento = estabelecimentoRepository.findById(estabelecimentoId).orElseThrow(EstabelecimentoNaoExisteException::new);

        if (saborId != null && saborId > 0) {
            return List.of(saborRepository.findById(saborId).orElseThrow(SaborNaoExisteException::new));
        }
        return saborRepository.findAll();
    }

    @Override
    public Sabor buscar(Long id) {
        if (!this.saborRepository.existsById(id)) {
            throw new CommerceException("O sabor consultado nao existe!");
        }
        return this.saborRepository.findById(id).get();
    }
}