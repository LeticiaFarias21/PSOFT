package com.ufcg.psoft.commerce.service.pedido;

import com.ufcg.psoft.commerce.exception.CodigoDeAcessoInvalidoException;
import com.ufcg.psoft.commerce.exception.EstabelecimentoNaoExisteException;
import com.ufcg.psoft.commerce.exception.PedidoNaoExisteException;
import com.ufcg.psoft.commerce.model.Estabelecimento;
import com.ufcg.psoft.commerce.model.Pedido;
import com.ufcg.psoft.commerce.repository.EstabelecimentoRepository;
import com.ufcg.psoft.commerce.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstabelecimentoV1ExcluirPedidoService implements EstabelecimentoExcluirPedidoService {

    @Autowired
    PedidoRepository pedidoRepository;
    @Autowired
    EstabelecimentoRepository estabelecimentoRepository;

    public void estabelecimentoExcluir(Long pedidoId, Long estabelecimentoExclusorId) {
        if (pedidoId != null && pedidoId > 0) {
            Pedido pedido = pedidoRepository.findById(pedidoId).orElseThrow(PedidoNaoExisteException::new);
            Estabelecimento estabelecimentoPedido = estabelecimentoRepository.findById(pedido.getEstabelecimentoId()).orElseThrow(EstabelecimentoNaoExisteException::new);
            Estabelecimento estabelecimentoExclusor = estabelecimentoRepository.findById(estabelecimentoExclusorId).orElseThrow(EstabelecimentoNaoExisteException::new);

            if (!estabelecimentoPedido.getCodigoAcesso().equals(estabelecimentoExclusor.getCodigoAcesso())) {
                throw new CodigoDeAcessoInvalidoException();
            }
        }

        if (pedidoId == null) {
            pedidoRepository.findAll().forEach(pedido -> {
                if (pedido.getEstabelecimentoId().equals(estabelecimentoExclusorId)) {
                    pedidoRepository.deleteById(pedido.getId());
                }
            });
        } else {
            pedidoRepository.deleteById(pedidoId);
        }
    }
}
