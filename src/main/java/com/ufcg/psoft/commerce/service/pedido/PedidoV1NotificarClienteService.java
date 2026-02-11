package com.ufcg.psoft.commerce.service.pedido;

import com.ufcg.psoft.commerce.exception.PedidoNaoExisteException;
import com.ufcg.psoft.commerce.exception.PedidoStatusInvalidoException;
import com.ufcg.psoft.commerce.model.Entregador;
import com.ufcg.psoft.commerce.model.Pedido;
import com.ufcg.psoft.commerce.repository.ClienteRepository;
import com.ufcg.psoft.commerce.repository.EntregadorRepository;
import com.ufcg.psoft.commerce.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoV1NotificarClienteService implements PedidoNotificarClienteService{
    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    EntregadorRepository entregadorRepository;

    @Override
    public String notificar(Long pedidoId) {
        Pedido pedido = pedidoRepository.findById(pedidoId).orElseThrow(PedidoNaoExisteException::new);
        Entregador entregador = entregadorRepository.findById(pedido.getEntregadorId()).orElseThrow();
        if (!pedido.getStatusEntrega().equals("Pedido em rota")) {
            throw new PedidoStatusInvalidoException();
        }
        String notificacao = "Pedido em rota: \n"+
                "Entregador: "+entregador.getNome()+"\n"+
                "Veiculo: "+entregador.getTipoVeiculo() +", "+entregador.getCorVeiculo()+", Placa: '"+entregador.getPlacaVeiculo()+"'";
       // Parte de notificanao

        System.out.println(notificacao);
        return notificacao;
    }
}
