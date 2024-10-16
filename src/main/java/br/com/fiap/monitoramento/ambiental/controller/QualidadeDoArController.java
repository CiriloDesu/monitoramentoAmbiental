package br.com.fiap.monitoramento.ambiental.controller;

import br.com.fiap.monitoramento.ambiental.dto.QualidadeDoArDTO;
import br.com.fiap.monitoramento.ambiental.model.QualidadeDoAr;
import br.com.fiap.monitoramento.ambiental.service.QualidadeDoArService;
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
public class QualidadeDoArController {

    @Autowired
    private QualidadeDoArService qualidadeDoArService;


    @PostMapping("/qualidade")
    @ResponseStatus(HttpStatus.CREATED)
    public QualidadeDoArDTO gravar(@RequestBody @Valid QualidadeDoArDTO qualidadeDoArDTO){
        return qualidadeDoArService.criarNovo(qualidadeDoArDTO);
    }

    @GetMapping("/qualidades")
    @ResponseStatus(HttpStatus.OK)
    public Page<QualidadeDoArDTO> listarTodos(Pageable pageable){
        return qualidadeDoArService.listarTodos(pageable);
    }

    @GetMapping("/qualidade/{id}")
    @ResponseStatus(HttpStatus.OK)
    public QualidadeDoArDTO buscarPorId(@PathVariable Long id){
        return qualidadeDoArService.buscarPorId(id);
    }

    @DeleteMapping("/qualidade/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id){
        qualidadeDoArService.excluir(id);
    }

    @PutMapping("/qualidade")
    @ResponseStatus(HttpStatus.OK)
    public QualidadeDoAr atualizar(@RequestBody QualidadeDoAr qualidadeDoAr){
        return qualidadeDoArService.atualizar(qualidadeDoAr);
    }

    @GetMapping(value = "/qualidades", params = {"dataInicial", "dataFinal"})
    public List<QualidadeDoArDTO> listarPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal
    ){
        return qualidadeDoArService.listarPorPeriodo(dataInicial, dataFinal);
    }

}