package com.ufcg.psoft.commerce.service.cliente;

import com.ufcg.psoft.commerce.dto.cliente.ClientePostPutRequestDTO;
import com.ufcg.psoft.commerce.exception.CodigoDeAcessoInvalidoException;
import com.ufcg.psoft.commerce.exception.IdInvalidoException;
import com.ufcg.psoft.commerce.model.Cliente;
import com.ufcg.psoft.commerce.repository.ClienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteV1CriarService implements ClienteCriarService{
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
    public Cliente criar(ClientePostPutRequestDTO clientePostPutRequestDTO) {
        if (clientePostPutRequestDTO.getCodigoAcesso().length() != 6 || !soNumeros(clientePostPutRequestDTO.getCodigoAcesso())) {
            throw new CodigoDeAcessoInvalidoException();
        }
        return this.clienteRepository.save(this.modelMapper.map(clientePostPutRequestDTO, Cliente.class));
    }
}
