package com.ufcg.psoft.commerce.service.estabelecimento;

import com.ufcg.psoft.commerce.exception.*;
import com.ufcg.psoft.commerce.model.Cliente;
import com.ufcg.psoft.commerce.model.Estabelecimento;
import com.ufcg.psoft.commerce.model.Pedido;
import com.ufcg.psoft.commerce.repository.ClienteRepository;
import com.ufcg.psoft.commerce.repository.EstabelecimentoRepository;
import com.ufcg.psoft.commerce.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstabelecimentoV1NotificaClienteService implements EstabelecimentoNotificaClienteService {

    @Autowired
    PedidoRepository pedidoRepository;
    @Autowired
    EstabelecimentoRepository estabelecimentoRepository;
    @Autowired
    ClienteRepository clienteRepository;

    @Override
    public String notificar(Long pedidoId) {
        Pedido pedido = pedidoRepository.findById(pedidoId).orElseThrow(PedidoNaoExisteException::new);
        Estabelecimento estabelecimento = estabelecimentoRepository.findById(pedido.getEstabelecimentoId()).orElseThrow(EstabelecimentoNaoExisteException::new);
        Cliente cliente = clienteRepository.findById(pedido.getClienteId()).orElseThrow(ClienteNaoExisteException::new);

        if (!pedido.getStatusEntrega().equals("Pedido pronto")) {
            throw new PedidoStatusInvalidoException();
        }
        if (!estabelecimento.getEntregadoresDisponiveis().isEmpty()) {
            throw new EstabelecimentoEntregadorDisponivelException();
        }

        String notificacao = "Olá, " + cliente.getNome() + "! Seu pedido #" + pedido.getId() + " está pronto mas não há entregadores disponíveis.";
        System.out.println(notificacao);
        return notificacao;
    }
}
