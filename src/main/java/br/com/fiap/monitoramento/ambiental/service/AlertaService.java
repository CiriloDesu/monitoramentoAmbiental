package br.com.fiap.monitoramento.ambiental.service;

import br.com.fiap.monitoramento.ambiental.dto.AlertaDTO;
import br.com.fiap.monitoramento.ambiental.exception.NaoEncontradoException;
import br.com.fiap.monitoramento.ambiental.model.Alerta;
import br.com.fiap.monitoramento.ambiental.model.HistoricoDeAlertas;
import br.com.fiap.monitoramento.ambiental.repository.AlertaRepository;
import br.com.fiap.monitoramento.ambiental.repository.HistoricoDeAlertasRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class AlertaService {
    @Autowired
    private HistoricoDeAlertasRepository historicoAlertasRepository;
    @Autowired
    private AlertaRepository alertaRepository;

    public List<AlertaDTO> listarTodos(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return alertaRepository
                .findAll(pageable)
                .map(AlertaDTO::new)
                .toList();
    }

    public AlertaDTO criarAlerta(AlertaDTO alertaDTO) {
        Alerta alerta = new Alerta();
        BeanUtils.copyProperties(alertaDTO, alerta);
        return new AlertaDTO(alertaRepository.save(alerta));
    }

    public AlertaDTO buscarPorId(String id) {
        Optional<Alerta> alertaOptional = alertaRepository.findById(id);
        if (alertaOptional.isPresent()) {
            return new AlertaDTO(alertaOptional.get());
        } else {
            throw new NaoEncontradoException("Alerta não existe!");
        }
    }

    public void excluir(String id) {
        alertaRepository.deleteById(id);
    }

    public AlertaDTO atualizar(AlertaDTO alertaDTO) {
        Alerta alerta = new Alerta();
        BeanUtils.copyProperties(alertaDTO, alerta);
        return new AlertaDTO(alertaRepository.save(alerta));
    }

    public List<AlertaDTO> listarAlertasPorPeriodo(LocalDate dataInicial, LocalDate dataFinal) {
        LocalDateTime inicio = dataInicial.atStartOfDay();
        LocalDateTime fim = dataFinal.atTime(LocalTime.MAX);

        return alertaRepository
                .findByTimestampBetween(inicio, fim)
                .stream()
                .map(AlertaDTO::new)
                .toList();
    }



    public AlertaDTO atualizarAlerta(String id, AlertaDTO alertaDTO) {
        // Buscar o alerta existente
        Optional<Alerta> alertaOptional = alertaRepository.findById(id);
        if (alertaOptional.isPresent()) {
            Alerta alerta = alertaOptional.get();

            // Criar histórico antes de atualizar
            HistoricoDeAlertas historico = new HistoricoDeAlertas(
                    alerta.getId(),
                    alerta.getTipo(),
                    alerta.getLocalizacao(),
                    LocalDateTime.now(Clock.systemDefaultZone()),
                    "Alerta atualizado. Tipo anterior: '" + alerta.getTipo() + "'"
            );
            historicoAlertasRepository.save(historico) ; // Salvar histórico

            // Atualizar o alerta
            BeanUtils.copyProperties(alertaDTO, alerta);
            alertaRepository.save(alerta);

            return new AlertaDTO(alerta);
        } else {
            throw new RuntimeException("Alerta não encontrado");
        }

}
}
