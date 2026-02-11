package com.ufcg.psoft.commerce.service.pedido;

import com.ufcg.psoft.commerce.dto.pedido.PedidoGetRequestDTO;
import com.ufcg.psoft.commerce.exception.ClienteNaoExisteException;
import com.ufcg.psoft.commerce.exception.CodigoDeAcessoInvalidoException;
import com.ufcg.psoft.commerce.exception.EstabelecimentoNaoExisteException;
import com.ufcg.psoft.commerce.exception.PedidoNaoExisteException;
import com.ufcg.psoft.commerce.model.Cliente;
import com.ufcg.psoft.commerce.model.Pedido;
import com.ufcg.psoft.commerce.repository.ClienteRepository;
import com.ufcg.psoft.commerce.repository.EstabelecimentoRepository;
import com.ufcg.psoft.commerce.repository.PedidoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteV1ListarPedidoEstabelecimentoService implements ClienteListarPedidoEstabelecimentoService {
    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    EstabelecimentoRepository estabelecimentoRepository;
    @Autowired
    PedidoRepository pedidoRepository;
    @Autowired
    ModelMapper modelMapper;

    public List<PedidoGetRequestDTO> listar(Long pedidoId, Long clienteId, Long estabelecimentoId, String codigoAcesso, String status) {
        Cliente cliente = clienteRepository.findById(clienteId).orElseThrow(ClienteNaoExisteException::new);
        estabelecimentoRepository.findById(estabelecimentoId).orElseThrow(EstabelecimentoNaoExisteException::new);

        if (!cliente.getCodigoAcesso().equals(codigoAcesso)) {
            throw new CodigoDeAcessoInvalidoException();
        }

        if (pedidoId != null) {
            PedidoGetRequestDTO pedido = new PedidoGetRequestDTO(pedidoRepository.findById(pedidoId).orElseThrow(PedidoNaoExisteException::new));

            if (!pedido.getClienteId().equals(clienteId) || !pedido.getEstabelecimentoId().equals(estabelecimentoId)) {
                throw new PedidoNaoExisteException();
            }

            return List.of(pedido);
        } else {
            List<Pedido> pedidos = new ArrayList<>();
            pedidoRepository.findAll().forEach(pedido -> {
                if (pedido.getEstabelecimentoId().equals(estabelecimentoId) && (pedido.getClienteId().equals(clienteId))) {
                    pedidos.add(pedido);
                }
            });
            if (status == null) {
                return pedidos.stream()
                        .map(pedido -> modelMapper.map(pedido, PedidoGetRequestDTO.class))
                        .sorted(Comparator.comparing((PedidoGetRequestDTO pedido) -> pedido.getStatusEntrega().equals("Pedido entregue") ? 1 : 0)
                                .thenComparing(PedidoGetRequestDTO::getStatusEntrega))
                        .collect(Collectors.toList());
            } else {
                return pedidos.stream()
                        .filter(pedido -> pedido.getStatusEntrega().equals(status))
                        .map(pedido -> modelMapper.map(pedido, PedidoGetRequestDTO.class))
                        .collect(Collectors.toList());
            }
        }
    }
}
