package com.ufcg.psoft.commerce.service.entregador;

import com.ufcg.psoft.commerce.dto.entregador.EntregadorGetResponseDTO;
import com.ufcg.psoft.commerce.dto.entregador.EntregadorPostPutRequestDTO;
import com.ufcg.psoft.commerce.model.Entregador;
import com.ufcg.psoft.commerce.repository.EntregadorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntregadorV1CriarService implements EntregadorCriarService {
    @Autowired
    EntregadorRepository entregadorRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public Entregador criar(EntregadorPostPutRequestDTO entregadorSave) {
        Entregador entregador = modelMapper.map(entregadorSave, Entregador.class);

        Entregador result = entregadorRepository.save(entregador);

        return modelMapper.map(result, Entregador.class);
    }
}
