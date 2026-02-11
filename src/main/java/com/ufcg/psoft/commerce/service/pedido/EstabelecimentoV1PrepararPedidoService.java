package com.ufcg.psoft.commerce.service.pedido;

import com.ufcg.psoft.commerce.dto.pedido.PedidoPostPutRequestDTO;
import com.ufcg.psoft.commerce.exception.CodigoDeAcessoInvalidoException;
import com.ufcg.psoft.commerce.exception.EstabelecimentoNaoExisteException;
import com.ufcg.psoft.commerce.exception.PedidoNaoExisteException;
import com.ufcg.psoft.commerce.exception.PedidoStatusInvalidoException;
import com.ufcg.psoft.commerce.model.Estabelecimento;
import com.ufcg.psoft.commerce.model.Pedido;
import com.ufcg.psoft.commerce.repository.EstabelecimentoRepository;
import com.ufcg.psoft.commerce.repository.PedidoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstabelecimentoV1PrepararPedidoService implements EstabelecimentoPrepararPedidoService{

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    EstabelecimentoRepository estabelecimentoRepository;

    @Autowired
    ModelMapper modelMapper;
    @Override
    public Pedido prepararPedido(Long pedidoId, Long estabelecimentoId, String codigoAcessoEstabelecimento, PedidoPostPutRequestDTO pedidoPostPutRequestDTO) {
        Estabelecimento estabelecimento = estabelecimentoRepository.findById(estabelecimentoId).orElseThrow(EstabelecimentoNaoExisteException::new);

        if (!estabelecimento.getCodigoAcesso().equals(codigoAcessoEstabelecimento)) {
            throw new CodigoDeAcessoInvalidoException();
        }

        Pedido pedido = pedidoRepository.findById(pedidoId).orElseThrow(PedidoNaoExisteException::new);

        if (!pedido.getStatusEntrega().equals("Pedido em preparo")) {
            throw new PedidoStatusInvalidoException();
        }

        pedido.setStatusEntrega("Pedido pronto");

        modelMapper.map(pedidoPostPutRequestDTO, pedido);
        return pedidoRepository.save(pedido);
    }
}
