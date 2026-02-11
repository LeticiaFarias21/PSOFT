package com.ufcg.psoft.commerce.service.cliente;

import com.ufcg.psoft.commerce.dto.cliente.ClienteResponseDTO;
import com.ufcg.psoft.commerce.exception.ClienteNaoExisteException;
import com.ufcg.psoft.commerce.model.Cliente;
import com.ufcg.psoft.commerce.repository.ClienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ClienteV1LerService implements ClienteLerService{
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ClienteResponseDTO ler(Long id) {
        Cliente cliente = this.clienteRepository.findById(id).orElseThrow(ClienteNaoExisteException::new);
        return modelMapper.map(cliente, ClienteResponseDTO.class);
    }

    @Override
    public List<ClienteResponseDTO> ler() {
        List<Cliente> clientes = this.clienteRepository.findAll();
        return clientes.stream()
                .map(cliente -> modelMapper.map(cliente, ClienteResponseDTO.class))
                .collect(Collectors.toList());
    }
}
