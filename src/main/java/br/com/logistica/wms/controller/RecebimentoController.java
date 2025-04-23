package br.com.logistica.wms.controller;

import br.com.logistica.wms.model.Recebimento;
import br.com.logistica.wms.service.RecebimentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recebimento")
public class RecebimentoController {
    private final RecebimentoService recebimentoService;

    public RecebimentoController(RecebimentoService recebimentoService) {
        this.recebimentoService = recebimentoService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Recebimento> cadastrar(@RequestBody Recebimento recebimento){
        var dados = recebimentoService.salvar(recebimento);
        return ResponseEntity.status(201).body(dados);
    }
}
