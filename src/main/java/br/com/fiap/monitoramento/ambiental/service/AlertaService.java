package br.com.fiap.monitoramento.ambiental.service;

import br.com.fiap.monitoramento.ambiental.dto.AlertaDTO;
import br.com.fiap.monitoramento.ambiental.model.Alerta;
import br.com.fiap.monitoramento.ambiental.repository.AlertaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertaService {

    @Autowired
    private AlertaRepository alertaRepository;

    public Page<AlertaDTO> listarTodos(Pageable paginacao){

        return alertaRepository
                .findAll(paginacao)
                .map(AlertaDTO::new);
    }

    public Alerta criarAlerta(Alerta alerta){
        return alertaRepository.save(alerta);
    }


}
