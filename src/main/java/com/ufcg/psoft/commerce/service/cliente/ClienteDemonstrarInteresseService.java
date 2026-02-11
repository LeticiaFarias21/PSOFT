package com.ufcg.psoft.commerce.service.cliente;

import com.ufcg.psoft.commerce.model.Sabor;

public interface ClienteDemonstrarInteresseService {
    public Sabor demonstrarInteresse(Long clienteId, String codigoAcesso, Long saborId);

}
