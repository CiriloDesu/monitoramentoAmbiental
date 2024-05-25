package br.com.fiap.monitoramento.ambiental.controller;

import br.com.fiap.monitoramento.ambiental.dto.AlertaDTO;
import br.com.fiap.monitoramento.ambiental.dto.ControleIrrigacaoDTO;
import br.com.fiap.monitoramento.ambiental.model.Alerta;
import br.com.fiap.monitoramento.ambiental.model.ControleIrrigacao;
import br.com.fiap.monitoramento.ambiental.service.ControleIrrigacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ControleIrrigacaoController {

    @Autowired
    private ControleIrrigacaoService controleIrrigacaoService;


    @PostMapping("/irrigacao")
    @ResponseStatus(HttpStatus.CREATED)
    public ControleIrrigacaoDTO gravar(@RequestBody @Valid ControleIrrigacaoDTO controleIrrigacaoDTO){
        return controleIrrigacaoService.criarIrrigacao(controleIrrigacaoDTO);
    }

    @GetMapping("/irrigacoes")
    @ResponseStatus(HttpStatus.OK)
    public Page<ControleIrrigacaoDTO> listarTodos(Pageable pageable){
        return controleIrrigacaoService.listarTodos(pageable);
    }

    @GetMapping("/irrigacao/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ControleIrrigacaoDTO buscarPorId(@PathVariable Long id){
        return controleIrrigacaoService.buscarPorId(id);
    }

    @DeleteMapping("/irrigacao/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id){
        controleIrrigacaoService.excluir(id);
    }

    @PutMapping("/irrigacao")
    @ResponseStatus(HttpStatus.OK)
    public ControleIrrigacao atualizar(@RequestBody ControleIrrigacao controleIrrigacao){
        return controleIrrigacaoService.atualizar(controleIrrigacao);
    }

    @GetMapping(value = "/irrigacoes", params = {"dataInicial", "dataFinal"})
    public List<ControleIrrigacaoDTO> listarPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal
    ){
        return controleIrrigacaoService.listarPorPeriodo(dataInicial, dataFinal);
    }
}


