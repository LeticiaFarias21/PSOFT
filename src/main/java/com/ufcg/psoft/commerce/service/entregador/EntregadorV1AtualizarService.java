package com.ufcg.psoft.commerce.service.entregador;

import com.ufcg.psoft.commerce.dto.entregador.EntregadorGetResponseDTO;
import com.ufcg.psoft.commerce.dto.entregador.EntregadorPostPutRequestDTO;
import com.ufcg.psoft.commerce.exception.CodigoDeAcessoInvalidoException;
import com.ufcg.psoft.commerce.exception.EntregadorNaoExisteException;
import com.ufcg.psoft.commerce.model.Entregador;
import com.ufcg.psoft.commerce.repository.EntregadorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntregadorV1AtualizarService implements EntregadorAtualizarService {
    @Autowired
    EntregadorRepository entregadorRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public EntregadorGetResponseDTO atualizar(Long id, EntregadorPostPutRequestDTO entregadorAtualizado) throws CodigoDeAcessoInvalidoException {
        Entregador entregador = entregadorRepository.findById(id).orElseThrow(EntregadorNaoExisteException::new);

        if (!entregador.getCodigoAcesso().equals(entregadorAtualizado.getCodigoAcesso())) {
            throw new CodigoDeAcessoInvalidoException();
        }

        modelMapper.map(entregadorAtualizado, entregador);
        Entregador resultado = entregadorRepository.save(entregador);

        return modelMapper.map(resultado, EntregadorGetResponseDTO.class);
    }
}
