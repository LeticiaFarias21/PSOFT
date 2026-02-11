package com.ufcg.psoft.commerce.service.sabor;

import com.ufcg.psoft.commerce.repository.SaborRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaborV1ExcluirService implements SaborExcluirService {

    @Autowired
    private SaborRepository saborRepository;

    @Override
    public void excluir(Long id) {
        this.saborRepository.findById(id).orElseThrow();
        this.saborRepository.deleteById(id);
    }
}
