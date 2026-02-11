package com.ufcg.psoft.commerce.service.pedido;

import com.ufcg.psoft.commerce.exception.CancelarPedidoInvalidoException;
import com.ufcg.psoft.commerce.exception.ClienteNaoExisteException;
import com.ufcg.psoft.commerce.exception.CodigoDeAcessoInvalidoException;
import com.ufcg.psoft.commerce.exception.PedidoNaoExisteException;
import com.ufcg.psoft.commerce.model.Cliente;
import com.ufcg.psoft.commerce.model.Pedido;
import com.ufcg.psoft.commerce.repository.ClienteRepository;
import com.ufcg.psoft.commerce.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoV1CancelarService implements PedidoCancelarService{

    @Autowired
    PedidoRepository pedidoRepository;
    @Autowired
    ClienteRepository clienteRepository;

    @Override
    public void cancelar(Long pedidoId, String codigoAcessoCliente) {

        if (pedidoId != null && pedidoId > 0) {
            Pedido pedido = pedidoRepository.findById(pedidoId).orElseThrow(PedidoNaoExisteException::new);
            Cliente clientePedido = clienteRepository.findById(pedido.getClienteId()).orElseThrow(ClienteNaoExisteException::new);

            if (!clientePedido.getCodigoAcesso().equals(codigoAcessoCliente)) {
                throw new CodigoDeAcessoInvalidoException();
            }

            if (pedido.getStatusEntrega().equals("Pedido pronto") || pedido.getStatusEntrega().equals("Pedido em rota")) {
                throw new CancelarPedidoInvalidoException();
            } else {
                pedidoRepository.deleteById(pedidoId);
            }
        }
    }
}
