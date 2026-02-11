package com.ufcg.psoft.commerce.service.estabelecimento;

import com.ufcg.psoft.commerce.exception.IdInvalidoException;
import com.ufcg.psoft.commerce.model.Estabelecimento;
import com.ufcg.psoft.commerce.repository.EstabelecimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstabelecimentoV1LerService implements EstabelecimentoLerService{

    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;

        @Override
        public Estabelecimento ler(Long id) {
            if (this.estabelecimentoRepository.findById(id).isEmpty()) {
               throw new IdInvalidoException();
            }
            return this.estabelecimentoRepository.findById(id).get();
        }
}


