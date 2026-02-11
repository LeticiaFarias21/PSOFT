package com.ufcg.psoft.commerce.service.pedido;

import com.ufcg.psoft.commerce.dto.pedido.PedidoPostPutRequestDTO;
import com.ufcg.psoft.commerce.exception.CodigoDeAcessoInvalidoException;
import com.ufcg.psoft.commerce.exception.EstabelecimentoNaoExisteException;
import com.ufcg.psoft.commerce.exception.PedidoNaoExisteException;
import com.ufcg.psoft.commerce.exception.PedidoStatusInvalidoException;
import com.ufcg.psoft.commerce.model.Estabelecimento;
import com.ufcg.psoft.commerce.model.Pedido;
import com.ufcg.psoft.commerce.repository.EntregadorRepository;
import com.ufcg.psoft.commerce.repository.EstabelecimentoRepository;
import com.ufcg.psoft.commerce.repository.PedidoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstabelecimentoV1AssociarPedidoEntregadorService implements EstabelecimentoAssociarPedidoEntregadorService{

    @Autowired
    PedidoNotificarClienteService pedidoNotificarClienteService;

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    EstabelecimentoRepository estabelecimentoRepository;

    @Autowired
    EntregadorRepository entregadorRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public Pedido associar(Long pedidoId, Long entregadorId, Long estabelecimentoId, String codigoAcessoEstabelecimento, PedidoPostPutRequestDTO pedidoPostPutRequestDTO) {
        Estabelecimento estabelecimento = estabelecimentoRepository.findById(estabelecimentoId).orElseThrow(EstabelecimentoNaoExisteException::new);

        if (!estabelecimento.getCodigoAcesso().equals(codigoAcessoEstabelecimento)) {
            throw new CodigoDeAcessoInvalidoException();
        }

        Pedido pedido = pedidoRepository.findById(pedidoId).orElseThrow(PedidoNaoExisteException::new);

        if (!pedido.getStatusEntrega().equals("Pedido pronto")) {
            throw new PedidoStatusInvalidoException();
        }

        pedido.setEntregadorId(entregadorId);
        pedido.setStatusEntrega("Pedido em rota");
        pedidoNotificarClienteService.notificar(pedidoId);

        modelMapper.map(pedidoPostPutRequestDTO, pedido);
        pedidoRepository.save(pedido);
        return pedido;
    }
}
