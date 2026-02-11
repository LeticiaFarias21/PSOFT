package com.ufcg.psoft.commerce.service.pedido;

import com.ufcg.psoft.commerce.dto.pedido.PedidoPostPutRequestDTO;
import com.ufcg.psoft.commerce.exception.*;
import com.ufcg.psoft.commerce.model.Cliente;
import com.ufcg.psoft.commerce.model.Pedido;
import com.ufcg.psoft.commerce.model.Pizza;
import com.ufcg.psoft.commerce.repository.ClienteRepository;
import com.ufcg.psoft.commerce.repository.EstabelecimentoRepository;
import com.ufcg.psoft.commerce.repository.PedidoRepository;
import com.ufcg.psoft.commerce.repository.SaborRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoV1CriarService implements PedidoCriarService {

    @Autowired
    EstabelecimentoRepository estabelecimentoRepository;

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    SaborRepository saborRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public Pedido salvar(Long clienteId, String clienteCodigoAcesso, Long estabelecimentoId, PedidoPostPutRequestDTO pedidoPostPutRequestDTO) {
        estabelecimentoRepository.findById(estabelecimentoId).orElseThrow(EstabelecimentoNaoExisteException::new);

        Cliente cliente = clienteRepository.findById(clienteId).orElseThrow(ClienteNaoExisteException::new);
        if (!cliente.getCodigoAcesso().equals(clienteCodigoAcesso)) {
            throw new CodigoDeAcessoInvalidoException();
        }

        if (pedidoPostPutRequestDTO.getEnderecoEntrega() == null || pedidoPostPutRequestDTO.getEnderecoEntrega().isEmpty() || pedidoPostPutRequestDTO.getEnderecoEntrega().isBlank()) {
            pedidoPostPutRequestDTO.setEnderecoEntrega(cliente.getEndereco());
        }

        Pedido pedido = modelMapper.map(pedidoPostPutRequestDTO, Pedido.class);

        Double precoTotal = 0.0;
        for (Pizza pizza : pedidoPostPutRequestDTO.getPizzas()) {
            saborRepository.findById(pizza.getSabor1().getId()).orElseThrow(SaborNaoExisteException::new);
            if (!pizza.getSabor1().getDisponivel()) {
                throw new SaborNaoEstaDisponivelException();
            }
            if (pizza.getSabor2() != null) {
                if (pizza.getTamanho().equals("media")) {
                    throw new QuantidadeDeSaboresInvalidaException();
                }
                saborRepository.findById(pizza.getSabor2().getId()).orElseThrow(SaborNaoExisteException::new);
                if (!pizza.getSabor2().getDisponivel()) {
                    throw new SaborNaoEstaDisponivelException();
                }
            }

            if (pizza.getTamanho().equals("media")) {
                precoTotal += pizza.getSabor1().getPrecoM();
            } else if (pizza.getTamanho().equals("grande") && pizza.getSabor2() == null) {
                precoTotal += pizza.getSabor1().getPrecoG();
            } else {
                precoTotal += (pizza.getSabor1().getPrecoG() + pizza.getSabor2().getPrecoG()) / 2;
            }
        }

        pedido.setPreco(precoTotal);
        pedido.setPizzas(pedidoPostPutRequestDTO.getPizzas());
        pedido.setClienteId(clienteId);
        pedido.setEstabelecimentoId(estabelecimentoId);
        return pedidoRepository.save(pedido);
    }
}
