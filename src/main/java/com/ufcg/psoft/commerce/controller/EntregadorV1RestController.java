package com.ufcg.psoft.commerce.controller;

import com.ufcg.psoft.commerce.dto.entregador.EntregadorPostPutRequestDTO;
import com.ufcg.psoft.commerce.service.associacao.AssociacaoService;
import com.ufcg.psoft.commerce.service.entregador.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(
        value = "/v1/entregadores",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class EntregadorV1RestController {

    @Autowired
    EntregadorAtualizarService entregadorAtualizarService;
    @Autowired
    EntregadorLerService entregadorLerService;
    @Autowired
    EntregadorCriarService entregadorCriarService;
    @Autowired
    EntregadorExcluirService entregadorExcluirService;
    @Autowired
    AssociacaoService associacaoService;
    @Autowired
    EntregadorDefinirDisponibilidadeService entregadorDefinirDisponibilidadeService;

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarUmEntregador(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(entregadorLerService.ler(id).get(0));
    }

    @GetMapping("")
    public ResponseEntity<?> buscarTodosEntregadores() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(entregadorLerService.ler(null));
    }

    @PostMapping()
    public ResponseEntity<?> salvarEntregador(@RequestBody @Valid EntregadorPostPutRequestDTO entregador) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(entregadorCriarService.criar(entregador));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarEntregador(@PathVariable Long id, @RequestBody @Valid EntregadorPostPutRequestDTO entregadorPostPutRequestDTO) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(entregadorAtualizarService.atualizar(id, entregadorPostPutRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirEntregador(@PathVariable Long id, @RequestParam(value = "codigoDeAcesso") String codigoDeAcesso) {
        entregadorExcluirService.excluir(id, codigoDeAcesso);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body("");
    }

    @PostMapping("/solicitar-associacao/")
    public ResponseEntity<Void> solicitarAssociacao(@RequestParam String codigoAcessoEntregador, @RequestParam Long estabelecimentoId, @RequestParam Long entregadorId) {

        associacaoService.associarEntregadorEstabelecimento(entregadorId, estabelecimentoId, codigoAcessoEntregador);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}/disponibilidade")
    public ResponseEntity<?> definirDisponibilidade(@PathVariable Long id, @RequestParam String codigoAcesso, @RequestParam boolean disponibilidade,
                                                    @RequestBody @Valid EntregadorPostPutRequestDTO entregadorPostPutRequestDto
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(entregadorDefinirDisponibilidadeService.definirDisponibilidade(id, entregadorPostPutRequestDto, codigoAcesso, disponibilidade));
    }
}
