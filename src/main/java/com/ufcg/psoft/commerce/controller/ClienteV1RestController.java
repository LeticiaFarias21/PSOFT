package com.ufcg.psoft.commerce.controller;

import com.ufcg.psoft.commerce.dto.cliente.ClientePostPutRequestDTO;
import com.ufcg.psoft.commerce.service.cliente.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping(
        value = "/clientes",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class ClienteV1RestController {

        @Autowired
        private ClienteCriarService clienteCriarService;

        @Autowired
        private ClienteLerService clienteLerService;

        @Autowired
        private ClienteExcluirService clienteExcluirService;

        @Autowired
        private ClienteAtualizarService clienteAtualizarService;

        @Autowired
        ClienteDemonstrarInteresseService clienteDemonstrarInteresseService;

        @PostMapping()
        public ResponseEntity<?> criarCliente(
                @Valid @RequestBody ClientePostPutRequestDTO clientePostPutRequestDTO
                ){
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(this.clienteCriarService.criar(clientePostPutRequestDTO));
        }

        @GetMapping("/{id}")
        public ResponseEntity<?> lerCliente(@PathVariable("id") Long id){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(clienteLerService.ler(id));
        }

        @GetMapping("")
        public ResponseEntity<?> lerClientes(){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(clienteLerService.ler());
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<?> excluirCliente(@PathVariable("id") Long id,
                                                @RequestParam String codigoAcesso){
            this.clienteExcluirService.excluir(id, codigoAcesso);
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .build();

        }
        @PutMapping("/{id}")
        public ResponseEntity<?> atualizarCliente(
                @PathVariable("id") Long id,
                @RequestParam String codigoAcesso,
                @Valid @RequestBody ClientePostPutRequestDTO clientePostPutRequestDTO
                ){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.clienteAtualizarService.atualizar(id, codigoAcesso, clientePostPutRequestDTO));
        }

        @PutMapping("/{id}/demonstrarInteresse")
        public ResponseEntity<?> demonstrarInteresse(@PathVariable Long id, @RequestParam String codigoAcesso, @RequestParam Long saborId) {
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(clienteDemonstrarInteresseService.demonstrarInteresse(id, codigoAcesso, saborId));
        }
}
