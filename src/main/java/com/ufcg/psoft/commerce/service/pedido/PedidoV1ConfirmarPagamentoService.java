package com.ufcg.psoft.commerce.service.pedido;

import com.ufcg.psoft.commerce.dto.pedido.PedidoPostPutRequestDTO;
import com.ufcg.psoft.commerce.exception.ClienteNaoExisteException;
import com.ufcg.psoft.commerce.exception.CodigoDeAcessoInvalidoException;
import com.ufcg.psoft.commerce.exception.PedidoNaoExisteException;
import com.ufcg.psoft.commerce.model.Cliente;
import com.ufcg.psoft.commerce.model.Pedido;
import com.ufcg.psoft.commerce.repository.ClienteRepository;
import com.ufcg.psoft.commerce.repository.PedidoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoV1ConfirmarPagamentoService implements PedidoConfirmarPagamentoService {
    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public Pedido confirmarPagamento(Long clienteId, Long pedidoId, String metodoPagamento, String codigoAcessoCliente, PedidoPostPutRequestDTO pedidoPostPutRequestDTO) {
        Cliente cliente = clienteRepository.findById(clienteId).orElseThrow(ClienteNaoExisteException::new);
        if (!cliente.getCodigoAcesso().equals(codigoAcessoCliente)) {
            throw new CodigoDeAcessoInvalidoException();
        }

        Pedido pedido = pedidoRepository.findById(pedidoId).orElseThrow(PedidoNaoExisteException::new);

        if (metodoPagamento.equals("PIX")) {
            pedido.setPreco(pedido.getPreco() * 0.95);
        } else if (metodoPagamento.equals("Cartão de débito")) {
            pedido.setPreco(pedido.getPreco() * 0.975);
        }

        pedido.setStatusPagamento(true);
        pedido.setStatusEntrega("Pedido em preparo");
        modelMapper.map(pedidoPostPutRequestDTO, pedido);
        return pedidoRepository.save(pedido);
    }
}
