package com.ufcg.psoft.commerce.service.estabelecimento;

import com.ufcg.psoft.commerce.exception.CodigoJaCadastradoException;
import com.ufcg.psoft.commerce.exception.CodigoNaoCadastradoException;
import com.ufcg.psoft.commerce.repository.EstabelecimentoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstabelecimentoV1ExcluirService implements EstabelecimentoExcluirService{

    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void excluir(Long id) {
        if (this.estabelecimentoRepository.findById(id).isEmpty()){
            throw new CodigoNaoCadastradoException();
        }
        this.estabelecimentoRepository.deleteById(id);
    }
}
