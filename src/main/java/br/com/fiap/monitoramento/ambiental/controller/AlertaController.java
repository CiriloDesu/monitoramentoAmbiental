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
import java.util.List;

@RestController
@RequestMapping("/api")
public class AlertaController {

    @Autowired
    private AlertaService alertaService;
    @Autowired
    private AlertaRepository alertaRepository;

    @PostMapping("/alerta")
    @ResponseStatus(HttpStatus.CREATED)
    public AlertaDTO gravar(@RequestBody @Valid AlertaDTO alertaDTO) {
        return alertaService.criarAlerta(alertaDTO);
    }

    @GetMapping("/alertas")
    @ResponseStatus(HttpStatus.OK)
    public Page<Alerta> listarTodos(Pageable pageable) {
        return alertaRepository.findAll(pageable);
    }

    @GetMapping("/alerta/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AlertaDTO buscarPorId(@PathVariable String id) { // Alterado de Long para String
        return alertaService.buscarPorId(id);
    }

    @DeleteMapping("/alerta/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable String id) { // Alterado de Long para String
        alertaService.excluir(id);
    }

    @PutMapping("/alerta/{id}")
    public AlertaDTO atualizarAlerta(@PathVariable String id, @RequestBody AlertaDTO alertaDTO) {
        return alertaService.atualizarAlerta(id, alertaDTO);
    }



    @GetMapping(value = "/alertas", params = {"dataInicial", "dataFinal"})
    public List<AlertaDTO> listarPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal) {
        return alertaService.listarAlertasPorPeriodo(dataInicial, dataFinal);
    }
}
