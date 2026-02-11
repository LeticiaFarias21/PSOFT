package com.ufcg.psoft.commerce.service.entregador;

import com.ufcg.psoft.commerce.dto.entregador.EntregadorPostPutRequestDTO;
import com.ufcg.psoft.commerce.exception.CodigoDeAcessoInvalidoException;
import com.ufcg.psoft.commerce.exception.EntregadorNaoExisteException;
import com.ufcg.psoft.commerce.model.Entregador;
import com.ufcg.psoft.commerce.repository.EntregadorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntregadorV1DefinirDisponibilidadeService implements EntregadorDefinirDisponibilidadeService {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    private EntregadorRepository entregadorRepository;

    public Entregador definirDisponibilidade(Long entregadorId, EntregadorPostPutRequestDTO entregadorPostPutRequestDTO, String codigoAcesso, boolean disponibilidade){
        Entregador entregador = entregadorRepository.findById(entregadorId).orElseThrow(EntregadorNaoExisteException::new);

        if (!entregador.getCodigoAcesso().equals(codigoAcesso)) {
            throw new CodigoDeAcessoInvalidoException();
        }

        entregador.setDisponibilidade(disponibilidade);

        Entregador updatedEntregador = modelMapper.map(entregadorPostPutRequestDTO, Entregador.class);
        entregadorRepository.save(updatedEntregador);

        return updatedEntregador;
    }
}
