package com.ufcg.psoft.commerce.service.estabelecimento;

import com.ufcg.psoft.commerce.dto.estabelecimento.EstabelecimentoPostPutRequestDTO;
import com.ufcg.psoft.commerce.exception.CodigoDeAcessoInvalidoException;
import com.ufcg.psoft.commerce.model.Estabelecimento;
import com.ufcg.psoft.commerce.model.Sabor;
import com.ufcg.psoft.commerce.repository.EstabelecimentoRepository;
import com.ufcg.psoft.commerce.service.sabor.SaborNotificarClienteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class EstabelecimentoV1AtualizarService implements EstabelecimentoAtualizarService {

    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;

    @Autowired
    private SaborNotificarClienteService saborNotificarClientesService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Estabelecimento atualizar(Long id, EstabelecimentoPostPutRequestDTO estabelecimentoPostPutRequestDTO) {
        Estabelecimento estabelecimento = this.estabelecimentoRepository.findById(id).orElseThrow();
        if (estabelecimentoPostPutRequestDTO.getCodigoAcesso().length() != 6) {
            throw new CodigoDeAcessoInvalidoException();
        }
        this.modelMapper.map(estabelecimentoPostPutRequestDTO, estabelecimento);
        return this.estabelecimentoRepository.save(estabelecimento);
    }

    @Override
    public Estabelecimento alterarDisponibilidade(Long id, String codigoDeAcesso, Boolean disponibilidade, Long saborId) {
        Estabelecimento estabelecimento = this.estabelecimentoRepository.findById(id).orElseThrow();
        if (estabelecimento.getCodigoAcesso().equals(codigoDeAcesso)) {
            Set<Sabor> sabores = estabelecimento.getSabores();
            for (Sabor sabor : sabores) {
                if (sabor.getId().equals(saborId)) {
                    sabor.setDisponivel(disponibilidade);
                    saborNotificarClientesService.notificarClientes(saborId);
                }
            }
            estabelecimento.setSabores(sabores);
        } else {
            throw new CodigoDeAcessoInvalidoException();
        }
        return this.estabelecimentoRepository.save(estabelecimento);
    }
}
