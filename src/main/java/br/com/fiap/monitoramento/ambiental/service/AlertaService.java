package br.com.fiap.monitoramento.ambiental.service;

import br.com.fiap.monitoramento.ambiental.dto.AlertaDTO;
import br.com.fiap.monitoramento.ambiental.exception.NaoEncontradoException;
import br.com.fiap.monitoramento.ambiental.model.Alerta;
import br.com.fiap.monitoramento.ambiental.repository.AlertaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class AlertaService {

    @Autowired
    private AlertaRepository alertaRepository;

    public Page<AlertaDTO> listarTodos(Pageable pageable){

        return alertaRepository
                .findAll(pageable)
                .map(AlertaDTO::new);
    }

    public AlertaDTO criarAlerta(AlertaDTO alertaDTO){
        Alerta alerta = new Alerta();
        BeanUtils.copyProperties(alertaDTO, alerta);
        return new AlertaDTO(alertaRepository.save(alerta));
    }

    public AlertaDTO buscarPorId(Long id){
        Optional<Alerta> alertaOptional = alertaRepository.findById(id);
        if (alertaOptional.isPresent()){
            return new AlertaDTO(alertaOptional.get());
        } else {
            throw new NaoEncontradoException("Alerta não existe!");        }
    }

    public void excluir(Long id){
        alertaRepository.delete(alertaRepository.findById(id).get());
    }

public Alerta atualizar(Alerta alerta){
        return alertaRepository.save(alerta);
}

    public List<AlertaDTO> listarAlertasPorPeriodo(LocalDate dataInicial, LocalDate dataFinal) {
        LocalDateTime inicio = dataInicial.atStartOfDay();
        LocalDateTime fim = dataFinal.atTime(LocalTime.MAX);

        return alertaRepository
                .listarAlertasPorPeriodo(inicio, fim)
                .stream()
                .map(AlertaDTO::new)
                .toList();
    }

}
