package com.ufcg.psoft.commerce.service.cliente;

import com.ufcg.psoft.commerce.dto.cliente.ClientePostPutRequestDTO;
import com.ufcg.psoft.commerce.exception.ClienteNaoExisteException;
import com.ufcg.psoft.commerce.exception.CodigoDeAcessoInvalidoException;
import com.ufcg.psoft.commerce.exception.IdInvalidoException;
import com.ufcg.psoft.commerce.model.Cliente;
import com.ufcg.psoft.commerce.repository.ClienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteV1AtualizarService implements ClienteAtualizarService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ModelMapper modelMapper;

    private static boolean soNumeros(String codigo) {
        for (char caractere : codigo.toCharArray()) {
            if (!Character.isDigit(caractere)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Cliente atualizar(Long id, String codigoAcesso, ClientePostPutRequestDTO clientePostPutRequestDTO) {
        Cliente cliente = this.clienteRepository.findById(id).orElseThrow(ClienteNaoExisteException::new);
        if( clientePostPutRequestDTO.getCodigoAcesso().length() != 6 || !soNumeros(clientePostPutRequestDTO.getCodigoAcesso()) || !cliente.getCodigoAcesso().equals(codigoAcesso)){
            throw new CodigoDeAcessoInvalidoException();
        }
        this.modelMapper.map(clientePostPutRequestDTO, cliente);
        return this.clienteRepository.save(cliente);
    }
}
