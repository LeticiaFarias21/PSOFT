package com.ufcg.psoft.commerce.service.pedido;

import com.ufcg.psoft.commerce.exception.ClienteNaoExisteException;
import com.ufcg.psoft.commerce.exception.CodigoDeAcessoInvalidoException;
import com.ufcg.psoft.commerce.exception.PedidoNaoExisteException;
import com.ufcg.psoft.commerce.model.Cliente;
import com.ufcg.psoft.commerce.model.Pedido;
import com.ufcg.psoft.commerce.repository.ClienteRepository;
import com.ufcg.psoft.commerce.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteV1LerPedidoService implements ClienteLerPedidoService {

    @Autowired
    PedidoRepository pedidoRepository;
    @Autowired
    ClienteRepository clienteRepository;


    public List<Pedido> clienteListar(Long pedidoId, Long clienteListadorId) {
        if (pedidoId != null && pedidoId > 0) {
            Pedido pedido = pedidoRepository.findById(pedidoId).orElseThrow(PedidoNaoExisteException::new);
            Cliente clientePedido = clienteRepository.findById(pedido.getClienteId()).orElseThrow(ClienteNaoExisteException::new);
            Cliente clienteListador = clienteRepository.findById(clienteListadorId).orElseThrow(ClienteNaoExisteException::new);

            if (!clientePedido.getCodigoAcesso().equals(clienteListador.getCodigoAcesso())) {
                throw new CodigoDeAcessoInvalidoException();
            }
        }

        if (pedidoId == null) {
            // Listar todos os pedidos que o cliente possui
            List<Pedido> pedidos = new ArrayList<>();
            pedidoRepository.findAll().forEach(pedido -> {
                if (pedido.getClienteId().equals(clienteListadorId)) {
                    pedidos.add(pedido);
                }
            });
            return pedidos;
        } else {
            Pedido pedido = pedidoRepository.findById(pedidoId).orElseThrow(PedidoNaoExisteException::new);
            return List.of(pedido);
        }
    }
}
