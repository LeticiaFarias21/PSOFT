package com.ufcg.psoft.commerce.service.estabelecimento;

import com.ufcg.psoft.commerce.dto.estabelecimento.EstabelecimentoPostPutRequestDTO;
import com.ufcg.psoft.commerce.exception.CodigoJaCadastradoException;
import com.ufcg.psoft.commerce.model.Estabelecimento;
import com.ufcg.psoft.commerce.repository.EstabelecimentoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstabelecimentoV1CriarService implements EstabelecimentoCriarService{

    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Estabelecimento criar(EstabelecimentoPostPutRequestDTO estabelecimentoPostPutRequestDTO) {
        if (this.estabelecimentoRepository.findByCodigoAcesso(estabelecimentoPostPutRequestDTO.getCodigoAcesso()) != null){
            throw new CodigoJaCadastradoException();
        }
        return this.estabelecimentoRepository.save(this.modelMapper.map(estabelecimentoPostPutRequestDTO, Estabelecimento.class));
    }
}
