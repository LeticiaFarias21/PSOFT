package com.ufcg.psoft.commerce.service.entregador;

import com.ufcg.psoft.commerce.dto.entregador.EntregadorGetResponseDTO;
import com.ufcg.psoft.commerce.exception.EntregadorNaoExisteException;
import com.ufcg.psoft.commerce.model.Entregador;
import com.ufcg.psoft.commerce.repository.EntregadorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EntregadorV1LerService implements EntregadorLerService{

    @Autowired
    EntregadorRepository entregadorRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<EntregadorGetResponseDTO> ler(Long id) {
        if (id == null) {
            List<Entregador> entregadores = entregadorRepository.findAll();
            return entregadores.stream()
                    .map(entregador -> modelMapper.map(entregador, EntregadorGetResponseDTO.class))
                    .collect(Collectors.toList());
        }
        Entregador entregador = entregadorRepository.findById(id).orElseThrow(EntregadorNaoExisteException::new);
        EntregadorGetResponseDTO entregadorGetResponseDTO = modelMapper.map(entregador, EntregadorGetResponseDTO.class);
        List<EntregadorGetResponseDTO> entregadores = new ArrayList<>();
        entregadores.add(entregadorGetResponseDTO);
        return entregadores;
    }
}
