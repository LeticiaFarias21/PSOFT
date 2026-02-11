package com.ufcg.psoft.commerce.controller;

import com.ufcg.psoft.commerce.dto.estabelecimento.EstabelecimentoPostPutRequestDTO;
import com.ufcg.psoft.commerce.model.Associacao;
import com.ufcg.psoft.commerce.service.associacao.AssociacaoAprovarService;
import com.ufcg.psoft.commerce.service.associacao.AssociacaoService;
import com.ufcg.psoft.commerce.service.estabelecimento.EstabelecimentoAtualizarService;
import com.ufcg.psoft.commerce.service.estabelecimento.EstabelecimentoCriarService;
import com.ufcg.psoft.commerce.service.estabelecimento.EstabelecimentoExcluirService;
import com.ufcg.psoft.commerce.service.estabelecimento.EstabelecimentoLerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(
    value = "/estabelecimentos", produces = MediaType.APPLICATION_JSON_VALUE
)
public class EstabelecimentoV1RestController {

    @Autowired
    private EstabelecimentoCriarService estabelecimentoCriarService;

    @Autowired
    private EstabelecimentoLerService estabelecimentoLerService;

    @Autowired
    private EstabelecimentoExcluirService estabelecimentoExcluirService;

    @Autowired
    private EstabelecimentoAtualizarService estabelecimentoAtualizarService;

    @Autowired
    AssociacaoService associacaoService;

    @Autowired
    AssociacaoAprovarService associacaoAprovarService;

    @PostMapping
    public ResponseEntity<?> criarEstabelecimento(
            @Valid @RequestBody EstabelecimentoPostPutRequestDTO estabelecimentoPostPutRequestDTO
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.estabelecimentoCriarService.criar(estabelecimentoPostPutRequestDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> lerEstabelecimento(@PathVariable("id") Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(estabelecimentoLerService.ler(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirEstabelecimento(@PathVariable("id") Long id) {
        this.estabelecimentoExcluirService.excluir(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarEstabelecimento(
            @PathVariable("id") Long id,
            @Valid @RequestBody EstabelecimentoPostPutRequestDTO estabelecimentoPostPutRequestDTO
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.estabelecimentoAtualizarService.atualizar(id, estabelecimentoPostPutRequestDTO));
    }

    @PostMapping("/associar_entregador")
    public ResponseEntity<Void> associarEntregador(@PathVariable Long entregadorId,
                                                   @PathVariable Long estabelecimentoId,
                                                   @PathVariable String codigoAcessoEstabelecimento,
                                                   @RequestBody boolean status
    ) {

        Associacao associacao = associacaoService.buscarAssociacao(entregadorId, estabelecimentoId, codigoAcessoEstabelecimento);
        if (status) {
            associacaoService.aceitarAssociacao(associacao.getId(), codigoAcessoEstabelecimento);
        } else {
            associacaoService.recusarAssociacao(associacao.getId(), codigoAcessoEstabelecimento);
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/rejeitar_entregador/{associacaoId}")
    public ResponseEntity<?> recusarAssociacao(@PathVariable Long associacaoId,
                                                @RequestParam(value = "codigoDeAcesso") String codigoDeAcesso) {

        associacaoService.recusarAssociacao(associacaoId, codigoDeAcesso);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body("");
    }

    @PutMapping("/alterar_disponibilidade/{id}")
    public ResponseEntity<?> alterarDisponibilidade(
            @PathVariable("id") Long id,
            @RequestParam(value = "codigoDeAcesso") String codigoDeAcesso,
            @RequestParam(value = "disponibilidade") Boolean disponibilidade,
            @RequestParam(value = "saborId") Long saborId
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.estabelecimentoAtualizarService.alterarDisponibilidade(id, codigoDeAcesso, disponibilidade, saborId));
    }

    @PutMapping()
    public ResponseEntity<?> aprovarAssociacao(@RequestParam Long entregadorId, @RequestParam Long estabelecimentoId, @RequestParam String codigoAcesso) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(associacaoAprovarService.aprovar(entregadorId, estabelecimentoId, codigoAcesso));
    }
}