package com.ufcg.psoft.commerce.service.cardapio;

import com.ufcg.psoft.commerce.model.Sabor;

import java.util.List;

public interface CardapioService {
    List<Sabor> cardapioCompleto(Long idEstabelecimento);
    List<Sabor> cardapioSaboresSalgados(Long idEstabelecimento);
    List<Sabor> cardapioSaboresDoces(Long idEstabelecimento);
}
