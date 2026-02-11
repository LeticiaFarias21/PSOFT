package com.ufcg.psoft.commerce.service.sabor;

import com.ufcg.psoft.commerce.dto.sabor.SaborPostPutRequestDTO;
import com.ufcg.psoft.commerce.model.Sabor;
import com.ufcg.psoft.commerce.repository.SaborRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaborV1CriarService implements SaborCriarService {

    @Autowired
    private SaborRepository saborRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Sabor criar(SaborPostPutRequestDTO saborPostPutRequestDTO) {
        return this.saborRepository.save(this.modelMapper.map(saborPostPutRequestDTO, Sabor.class));
    }
}
