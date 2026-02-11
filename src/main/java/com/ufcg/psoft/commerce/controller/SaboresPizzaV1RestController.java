package com.ufcg.psoft.commerce.controller;

import com.ufcg.psoft.commerce.dto.sabor.SaborPostPutRequestDTO;
import com.ufcg.psoft.commerce.service.sabor.SaborAtualizarService;
import com.ufcg.psoft.commerce.service.sabor.SaborCriarService;
import com.ufcg.psoft.commerce.service.sabor.SaborExcluirService;
import com.ufcg.psoft.commerce.service.sabor.SaborListarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(
        value = "/sabores",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class SaboresPizzaV1RestController {

    @Autowired
    SaborCriarService saborCriarService;

    @Autowired
    SaborListarService saborListarService;

    @Autowired
    SaborAtualizarService saborAtualizarService;

    @Autowired
    SaborExcluirService saborExcluirService;

    @GetMapping("/")
    public ResponseEntity<?> getSabores() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(saborListarService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSabor( @PathVariable("id") Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(saborListarService.buscar(id));
    }

    @PostMapping("/")
    public ResponseEntity<?> postSabor(
            @Valid @RequestBody SaborPostPutRequestDTO saborPostPutRequestDTO
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(saborCriarService.criar(saborPostPutRequestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putSabor(
            @PathVariable("id") Long id, @Valid @RequestBody SaborPostPutRequestDTO saborPostPutRequestDTO
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(saborAtualizarService.atualizar(id, saborPostPutRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSabor(@PathVariable("id") Long id) {
        this.saborExcluirService.excluir(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body("");
    }
}