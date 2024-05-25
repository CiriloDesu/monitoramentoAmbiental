package br.com.fiap.monitoramento.ambiental.controller;

import br.com.fiap.monitoramento.ambiental.dto.AlertaDTO;
import br.com.fiap.monitoramento.ambiental.model.Alerta;
import br.com.fiap.monitoramento.ambiental.repository.AlertaRepository;
import br.com.fiap.monitoramento.ambiental.service.AlertaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AlertaController {
    @Autowired
    private AlertaService alertaService;


    @PostMapping("/alerta")
    @ResponseStatus(HttpStatus.CREATED)
    public AlertaDTO gravar(@RequestBody @Valid AlertaDTO alertaDTO){
        return alertaService.criarAlerta(alertaDTO);
    }

    @GetMapping("/alertas")
    @ResponseStatus(HttpStatus.OK)
    public Page<AlertaDTO> listarTodos(Pageable pageable){
        return alertaService.listarTodos(pageable);
    }

    @GetMapping("/alerta/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AlertaDTO buscarPorId(@PathVariable Long id){
        return alertaService.buscarPorId(id);
    }

    @DeleteMapping("/alerta/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id){
        alertaService.excluir(id);
    }

    @PutMapping("/alerta")
    @ResponseStatus(HttpStatus.OK)
    public Alerta atualizar(@RequestBody Alerta alerta){
        return alertaService.atualizar(alerta);
    }

    @GetMapping(value = "/alertas", params = {"dataInicial", "dataFinal"})
    public List<AlertaDTO> listarPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal
    ){
        return alertaService.listarAlertasPorPeriodo(dataInicial, dataFinal);
    }
}


