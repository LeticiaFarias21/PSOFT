package com.ufcg.psoft.commerce.service.pedido;

import com.ufcg.psoft.commerce.dto.pedido.PedidoPostPutRequestDTO;
import com.ufcg.psoft.commerce.exception.ClienteNaoExisteException;
import com.ufcg.psoft.commerce.exception.CodigoDeAcessoInvalidoException;
import com.ufcg.psoft.commerce.exception.PedidoNaoExisteException;
import com.ufcg.psoft.commerce.exception.PedidoStatusInvalidoException;
import com.ufcg.psoft.commerce.model.Cliente;
import com.ufcg.psoft.commerce.model.Pedido;
import com.ufcg.psoft.commerce.repository.ClienteRepository;
import com.ufcg.psoft.commerce.repository.PedidoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteV1ConfirmarPedidoService implements ClienteConfirmarPedidoService {

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    PedidoNotificarEstabelecimentoService pedidoNotificarEstabelecimentoService;
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public Pedido confirmar(Long pedidoId, Long clienteId, String codigoAcessoCliente, PedidoPostPutRequestDTO pedidoPostPutRequestDTO) {
        Cliente cliente = clienteRepository.findById(clienteId).orElseThrow(ClienteNaoExisteException::new);

        if (!cliente.getCodigoAcesso().equals(codigoAcessoCliente)) {
            throw new CodigoDeAcessoInvalidoException();
        }

        Pedido pedido = pedidoRepository.findById(pedidoId).orElseThrow(PedidoNaoExisteException::new);

        if (!pedido.getStatusEntrega().equals("Pedido em rota")) {
            throw new PedidoStatusInvalidoException();
        }

        pedido.setStatusEntrega("Pedido entregue");

        modelMapper.map(pedidoPostPutRequestDTO, pedido);
        pedidoRepository.save(pedido);
        pedidoNotificarEstabelecimentoService.notificar(pedidoId);
        return pedido;
    }
}
