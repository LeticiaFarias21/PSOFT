package com.ufcg.psoft.commerce.service.sabor;

import com.ufcg.psoft.commerce.dto.sabor.SaborPostPutRequestDTO;
import com.ufcg.psoft.commerce.model.Sabor;
import com.ufcg.psoft.commerce.repository.SaborRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaborV1AtualizarService implements SaborAtualizarService{

    @Autowired
    SaborRepository saborRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public Sabor atualizar(Long id, SaborPostPutRequestDTO saborPostPutRequestDTO) {
        Sabor sabor = this.saborRepository.findById(id).orElseThrow();
        this.modelMapper.map(saborPostPutRequestDTO, sabor);
        return this.saborRepository.save(sabor);
    }
}
