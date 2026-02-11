package com.ufcg.psoft.commerce.service.cardapio;

import com.ufcg.psoft.commerce.model.Sabor;
import com.ufcg.psoft.commerce.service.sabor.SaborListarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardapioV1Service implements CardapioService{

    @Autowired
    SaborListarService saborListarService;

    @Override
    public List<Sabor> cardapioCompleto(Long idEstabelecimento){
        List<Sabor> sabores = saborListarService.listar(null, idEstabelecimento);
        List<Sabor> saboresOrdenados = new ArrayList<Sabor>();

        // Adiciona todos os sabores disponíveis primeiro
        for (Sabor sabor : sabores) {
            if (sabor.getDisponivel()) {
                saboresOrdenados.add(sabor);
            }
        }

        for (Sabor sabor : sabores) {
            if (!saboresOrdenados.contains(sabor)) {
                saboresOrdenados.add(sabor);
            }
        }

        return saboresOrdenados;

    }

    @Override
    public List<Sabor> cardapioSaboresSalgados(Long idEstabelecimento){

        List<Sabor> cardapioCompleto = cardapioCompleto(idEstabelecimento);
        List<Sabor> saboresSalgados = new ArrayList<>();

        for (Sabor sabor:cardapioCompleto){
            if (sabor.getTipo().toLowerCase().contains("salgado")){
                saboresSalgados.add(sabor);
            }
        }

        return saboresSalgados;
    }

    @Override
    public List<Sabor> cardapioSaboresDoces(Long idEstabelecimento){

        List<Sabor> cardapioCompleto = cardapioCompleto(idEstabelecimento);
        List<Sabor> saboresDoces = new ArrayList<>();

        for (Sabor sabor:cardapioCompleto){
            if (sabor.getTipo().toLowerCase().contains("doce")){
                saboresDoces.add(sabor);
            }
        }

        return saboresDoces;

    }

}
